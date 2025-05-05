package Controller;

import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.Timer;

import java.awt.Dimension;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.List;

import Model.Player;
import Utils.FileManager;
import Utils.SQL;
import Model.Bullet;
import Model.Enemy;
import View.Board;
import View.Menu;

public class Game extends KeyAdapter {
	private final Player player;
	private final Board board;
	private final Enemy enemy;
	private int delay = 1; // FPS = 1000
	private int counterBoss = 0; // Số lần enemy bắn
	private int counterPlayer = 0; // Số lần player bắn
	private boolean isPaused = false; // Trạng thái game
	private Timer timer;
	private Menu menu;
	private FileManager file = new FileManager(this);

	public Game(Player player, Enemy enemy, Board board) {
		this.player = player;
		this.enemy = enemy;
		this.board = board;
		initInput();
	}

	public void startGameLoop() {
		// Dùng Timer để tạo vòng lặp game
		timer = new Timer(delay, e -> update());
		timer.start();

		// Khởi tạo các đối tượng game
		board.setPlayer(player);
		board.setEnemy(enemy);

		// Khởi tạo trạng thái board
		board.setFocusable(true);
		board.addKeyListener(this);
		board.focus();
	}

	private void update() {
		// 1. Kiểm tra trạng thái game
		if (checkStatus() != 0) {
			showGameOverMessage();
			return;
		}

		// 2. Cập nhật tần suất bắn của player và enemy
		if (counterPlayer % 10 == 0) {
			player.attack();
		}
		if (counterBoss % 50 == 0) {
			enemy.attack();
		}

		// 3. Cập nhật điểm
		if (menu != null) {
			menu.updateScore();
		}

		// 4. Cập nhật trạng thái board
		board.repaint();

		// 5. Tăng số lần bắn
		counterPlayer++;
		counterBoss++;
	}

	public void resetAll() {
		// 1. Dừng timer
		if (timer != null && timer.isRunning()) {
			timer.stop();
		}

		// 2. Xóa toàn bộ đạn
		List<Bullet> pBullets = player.getBullets();
		pBullets.clear();
		List<Bullet> eBullets = enemy.getBullets();
		eBullets.clear();

		// 3. Reset counters và trạng thái pause
		counterPlayer = 0;
		counterBoss = 0;
		isPaused = false;

		// 4. Reset vị trí và HP player/enemy
		player.reset();
		enemy.reset();

		// 5. Lấy focus và repaint
		board.focus();

		// 6. Bắt đầu lại vòng lặp
		timer = new Timer(delay, e -> update());
		timer.start();
	}

	private void showGameOverMessage() {
		// Xác định winner
		String winner = player.isAlive() ? "Player" : "Enemy";

		// Đưa điểm vào database
		SQL.insertScore(menu.getScore());

		// Hiển thị thông báo
		int option = JOptionPane.showConfirmDialog(board, winner + " Wins! Do you want to restart?", "Game Over",
				JOptionPane.YES_NO_OPTION);

		if (option == JOptionPane.YES_OPTION) {
			restartGame();
		} else {
			// Thoát game nếu không muốn chơi lại
			System.exit(0);
		}

		// Đưa focus về board
		board.focus();
	}

	private void initInput() {
		// Chấp thuận key
		board.addKeyListener(this);

		// Đưa focus vào board
		board.setFocusable(true);
		board.focus();
	}

	// Xử lý các phím bấm
	@Override
	public void keyPressed(KeyEvent e) {
		switch (e.getKeyCode()) {
		case KeyEvent.VK_LEFT -> player.moveLeft();
		case KeyEvent.VK_RIGHT -> player.moveRight();
		case KeyEvent.VK_UP -> player.moveUp();
		case KeyEvent.VK_DOWN -> player.moveDown();
		case KeyEvent.VK_SPACE -> player.attack();
		}
	}

	// Xử lý các phím thả
	@Override
	public void keyReleased(KeyEvent e) {
		return;
	}

	// Kiểm tra trạng thái game
	public int checkStatus() {
		// Nếu player hoặc enemy chết thì trả về -1 hoặc 1
		if (player.getHp() == 0) {
			return -1;
		}
		if (enemy.getHp() == 0) {
			return 1;
		}

		// Nếu cả 2 đều sống thì trả về 0
		return 0;
	}

	// Xử lý các sự kiện từ menu
	public void eventHandler(int i) {
		switch (i) {
		case 0 -> {
			if (isPaused())
				resumeGame();
			else
				pauseGame();
		}
		case 1 -> newGame();
		case 2 -> saveGame();
		case 3 -> loadGame();
		case 4 -> showAchievements();
		}
		
		board.focus();
	}

	public boolean isPaused() {
		return isPaused;
	}

	private void resumeGame() {
		if (timer != null && !timer.isRunning()) {
			timer.start();
			isPaused = false;
		}

		// Reset
		counterPlayer = 0;
		counterBoss = 0;
	}

	private void pauseGame() {
		if (timer != null && timer.isRunning()) {
			timer.stop();
			isPaused = true;
		}
	}

	private void newGame() {
		resetAll();
	}

	private void saveGame() {
		file.saveGame(enemy.getHp(), player.getHp());
		JOptionPane.showMessageDialog(board, "Game saved!");
	}

	private void loadGame() {
		file.loadGame();

		// Reset lại các thông số
		List<Bullet> pBullets = player.getBullets();
		pBullets.clear();
		List<Bullet> eBullets = enemy.getBullets();
		eBullets.clear();

		counterPlayer = 0;
		counterBoss = 0;
		isPaused = false;

		JOptionPane.showMessageDialog(board, "Game loaded!");
	}

	private void showAchievements() { 
	    // Lấy dữ liệu thành tựu như cũ
	    List<int[]> data = SQL.selectScores();
	    String[] columns = { "ID", "Score" };
	    Object[][] tableData = new Object[data.size()][2];
	    for (int i = 0; i < data.size(); i++) {
	        tableData[i][0] = data.get(i)[0];
	        tableData[i][1] = data.get(i)[1];
	    }
	    JTable table = new JTable(tableData, columns);
	    table.setFillsViewportHeight(true);
	    JScrollPane scroll = new JScrollPane(table);
	    scroll.setPreferredSize(new Dimension(300, 200));

	    // Hiển thị hộp thoại modal
	    JOptionPane.showMessageDialog(board, scroll, "Achievements", JOptionPane.PLAIN_MESSAGE);
	}

	private void restartGame() {
		resetAll();
	}

	public int getDelay() {
		return delay;
	}

	public void setDelay(int delay) {
		this.delay = delay;
	}

	public Timer getTimer() {
		return timer;
	}

	public void setTimer(Timer timer) {
		this.timer = timer;
	}

	public int getCounterBoss() {
		return counterBoss;
	}

	public void setCounterBoss(int counterBoss) {
		this.counterBoss = counterBoss;
	}

	public int getCounterPlayer() {
		return counterPlayer;
	}

	public void setCounterPlayer(int counterPlayer) {
		this.counterPlayer = counterPlayer;
	}

	public Player getPlayer() {
		return player;
	}

	public Board getBoard() {
		return board;
	}

	public Enemy getEnemy() {
		return enemy;
	}

	public void setPaused(boolean isPaused) {
		this.isPaused = isPaused;
	}

	public void setMenu(Menu menu) {
		this.menu = menu;
	}
}
