package Model;

import java.awt.Image;
import java.util.ArrayList;

import javax.swing.ImageIcon;

import View.Board;

public class Player implements Entity {
	private double playerWidth = 48;
	private double playerHeight = 48;
	private double x;
	private double y;
	private int hp = 20;
	private final int maxHp = 20;
	private int speed = 10;
	private final Image model = new ImageIcon(getClass().getResource("/img/Cube.png")).getImage();
	private final Image heartModel = new ImageIcon(getClass().getResource("/img/heart.png")).getImage();
	private Board board;
	private ArrayList<Bullet> bullets = new ArrayList<>();

	public Player(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public Image getHeartModel() {
		return heartModel;
	}

	public void setBoard(Board board) {
		this.board = board;
	}

	public int getMaxHp() {
		return maxHp;
	}

	public Image getModel() {
		return model;
	}

	public void setGameView(Board board) {
		this.board = board;
	}

	public int getHp() {
		return hp;
	}
	
	public void setHp(int hp) {
		this.hp = hp;
	}

	public int getSpeed() {
		return speed;
	}

	public double getX() {
		return x;
	}

	public double getY() {
		return y;
	}

	public double getPlayerWidth() {
		return playerWidth;
	}

	public void setPlayerWidth(double playerWidth) {
		this.playerWidth = playerWidth;
	}

	public double getPlayerHeight() {
		return playerHeight;
	}

	public void setPlayerHeight(double playerHeight) {
		this.playerHeight = playerHeight;
	}

	public ArrayList<Bullet> getBullets() {
		return bullets;
	}

	public void setBullets(ArrayList<Bullet> bullets) {
		this.bullets = bullets;
	}

	@Override
	public void move(double dx, double dy) {
		if (board == null)
			return;
		this.x = Math.max(0, Math.min(dx, board.getWidth() - playerWidth));
		this.y = Math.max(0, Math.min(dy, board.getHeight() - playerHeight));
	}

	public void moveLeft() {
		move(x - speed, y);
	}

	public void moveRight() {
		move(x + speed, y);
	}

	public void moveUp() {
		move(x, y - speed);
	}

	public void moveDown() {
		move(x, y + speed);
	}

	@Override
	public void attack() {
		Bullet bullet = new Bullet(x + playerWidth / 2, y, 10, 0, 0);
		bullets.add(bullet);
	}
	
	@Override
	public void takeDamage(int dmg) {
		hp = Math.max(0, hp - dmg);
	}

	@Override
	public boolean isAlive() {
		return hp > 0;
	}

	@Override
	public boolean checkCollision(Entity other) {
		return false;
	}
	
	public int whoImI() {
		return 1;
	}
	
	public void reset() {
	    this.hp = this.maxHp; // Khôi phục lại máu
	    this.x = 680;      // Vị trí ban đầu
	    this.y = 600;
	}
}