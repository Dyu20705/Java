package View;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.util.ArrayList;

import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import Model.Bullet;
import Model.Enemy;
import Model.Player;

public class Board extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final int w = 1920;
	private final int h = 1080;
	private Player player;
	private Enemy enemy;
	private boolean isPhaseShift = false; // Chuyển trạng thái bắn của enemy
//	private boolean isGameOver = false;

//	public Board(Player player, Enemy enemy) {
//		this.player = player;
//		this.enemy = enemy;
//		setPreferredSize(new Dimension(w, h));
//		setBackground(new Color(31, 30, 51));
//		setFocusable(true);
//		requestFocusInWindow();
//	}

	public Board() {
		//Kích thước của JPanel
		setPreferredSize(new Dimension(w, h));
		
		//Màu nền của JPanel
		setBackground(new Color(31, 30, 51));
		
		//Cho phép JPanel nhận sự kiện bàn phím
		setFocusable(true);
		
		//Yêu cầu JPanel lấy tiêu điểm
		focus();
	}

	public int getW() {
		return w;
	}

	public int getH() {
		return h;
	}

	public void setPlayer(Player player) {
		this.player = player;
	}

	public void setEnemy(Enemy enemy) {
		this.enemy = enemy;
	}
	
	// Vẽ hình ảnh lên JPanel
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);

		drawPlayer(g);
		drawHpPlayer(g);
		drawBulletPlayer(g);
		drawEnemy(g);
		drawEnemyHp(g);
		drawBulletEnemy(g);
	}
	
	// Vẽ người chơi
	private void drawPlayer(Graphics g) {
		g.drawImage(player.getModel(), (int) player.getX(), (int) player.getY(), (int) player.getPlayerWidth(),
				(int) player.getPlayerHeight(), null);
	}
	
	// Vẽ các trái tim của người chơi
	private void drawHpPlayer(Graphics g) {
		int hp = player.getHp();
		for (int i = 0; i < hp; i++) {
			if (i < hp) {
				g.drawImage(player.getHeartModel(), 30 + (i * 40), 680, 30, 30, this);
			}

		}
	}
	
	// Vẽ đạn của người chơi
	private void drawBulletPlayer(Graphics g) {
		ArrayList<Bullet> playerBullet = player.getBullets();
		ArrayList<Bullet> bulletsToRemove = new ArrayList<>();

		for (Bullet bullet : playerBullet) {
			bullet.straight();

			if (bullet.checkCollision(enemy)) {
				enemy.takeDamage(50);
				bulletsToRemove.add(bullet);
				continue;
			}

			g.drawImage(bullet.getModel(), (int) bullet.getX(), (int) bullet.getY(), (int) bullet.getBulletWidth(),
					(int) bullet.getBulletHeight(), null);
		}
		playerBullet.removeAll(bulletsToRemove);
	}

	// Vẽ đạn của kẻ thù
	private void drawBulletEnemy(Graphics g) {
		ArrayList<Bullet> enemyBullet = enemy.getBullets();
		ArrayList<Bullet> bulletsToRemove = new ArrayList<>();
		
		if (isPhaseShift) {
			enemyBullet.clear(); // Xoá toàn bộ đạn cũ khi chuyển phase
			isPhaseShift = false; // Reset cờ chuyển phase sau khi đã xử lý
			return; // Không vẽ gì thêm trong frame này
		}
		
		if(enemy.getHp() == enemy.getMaxHp() * 0.5 || enemy.getHp() == enemy.getMaxHp() * 0.75 || enemy.getHp() == enemy.getMaxHp() * 0.25) { 
			isPhaseShift = true; // Đánh dấu đã chuyển phase
		}
		
		for (Bullet bullet : enemyBullet) {
			double hpPercentage = (double) enemy.getHp() / enemy.getMaxHp();

			if (hpPercentage <= 0.25) { // 0-25% máu: dùng fan
				bullet.fan();
			} else if (hpPercentage <= 0.50) { // 25-50% máu: dùng spiral
				bullet.spiral();
			} else if (hpPercentage <= 0.75) { // 50-75% máu: dùng radial
				bullet.radial();
				
			} else { // 75-100% máu: dùng wave
				bullet.wave();
			}
//			bullet.homing(player);	

			if (bullet.checkCollision(player)) {
				player.takeDamage(1);
				bulletsToRemove.add(bullet);
				continue;
			}

			g.drawImage(bullet.getModel(), (int) bullet.getX(), (int) bullet.getY(), (int) bullet.getBulletWidth(),
					(int) bullet.getBulletHeight(), null);
		}
		enemyBullet.removeAll(bulletsToRemove);
	}
	
	// Vẽ kẻ thù
	private void drawEnemy(Graphics g) {
		enemy.move();
		g.drawImage(enemy.getModel(), (int) enemy.getX(), (int) enemy.getY(), (int) enemy.getEnemyWidth(),
				(int) enemy.getEnemyHeight(), null);
	}
	
	// Vẽ thanh máu của kẻ thù
	private void drawEnemyHp(Graphics g) {
		int maxHp = enemy.getMaxHp();
		int currentHp = enemy.getHp();
		int barWidth = w - 560;
		int barHeight = 20;
		int x = 0;
		int y = 0;

		// Nền thanh máu
		g.setColor(Color.GRAY);
		g.fillRect(x, y, barWidth, barHeight);

		// Thanh máu hiện tại
		g.setColor(Color.RED);
		int hpWidth = (int) ((currentHp / (float) maxHp) * barWidth);
		g.fillRect(x, y, hpWidth, barHeight);

		// Viền thanh máu
		g.setColor(Color.BLACK);
		g.drawRect(x, y, barWidth, barHeight);
	}
	
	public void focus() {
		setFocusable(true);
	    setFocusTraversalKeysEnabled(false); 
	    requestFocusInWindow();
	    if (!isFocusOwner()) {
	        SwingUtilities.invokeLater(() -> requestFocusInWindow());
	    }
	    repaint();
	}
}
