package Controller;

import javax.swing.JOptionPane;
import javax.swing.Timer;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import Model.Player;
import Model.Enemy;
import View.Board;

public class Game extends KeyAdapter {
	private final Player player;
	private final Board board;
	private final Enemy enemy;
	private int counterBoss = 0;
	private int counterPlayer = 0;

	public Game(Player player, Enemy enemy, Board board) {
		this.player = player;
		this.enemy = enemy;
		this.board = board;
		initInput();
	}

	public void startGameLoop() {
		int delay = 1;
		new Timer(delay, e -> update()).start();
	}

	private void update() {
		if (checkStatus() != 0) {
			showGameOverMessage();
			return;
		}

		if (counterPlayer % 10 == 0) {
			player.attack();
		}
		if (counterBoss % 50 == 0) {
			enemy.attack();
		}
		
		board.repaint();
		counterPlayer++;
		counterBoss++;
	}

	private void showGameOverMessage() {
		String winner = player.isAlive() ? "Player" : "Enemy";
		int option = JOptionPane.showConfirmDialog(board, winner + " Wins! Do you want to restart?", "Game Over",
				JOptionPane.YES_NO_OPTION);

		if (option == JOptionPane.YES_OPTION) {
			restartGame();
		} else {
			System.exit(0); // Thoát game nếu không muốn chơi lại
		}
	}

	private void restartGame() {
		player.reset();
		enemy.reset();
		board.repaint();
		counterPlayer = 0;
		counterBoss = 0;
		startGameLoop();
	}

	private void initInput() {
		board.addKeyListener(this); // Chấp thuận key
	}

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

	@Override
	public void keyReleased(KeyEvent e) {
		return;
	}

	public int checkStatus() {
		if (player.getHp() == 0) {
			return -1;
		}
		if (enemy.getHp() == 0) {
			return 1;
		}
		return 0;
	}
}
