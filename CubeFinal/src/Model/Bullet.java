package Model;

import java.awt.Image;

import javax.swing.ImageIcon;

import View.Board;

public class Bullet implements Entity {
	private int bulletWidth = 10;
	private int bulletHeight = 10;
	private double x;
	private double y;
	private double vx;
	private double vy;
	private int speed;
	private double angle;
	private double startX;
//	private double startY;
	private double phase;
	private final double amplitude = 50;
	private final double frequency = 0.02;
	private double spiralOffset = 0;
	private Board gameView;
	private final Image model = new ImageIcon(getClass().getResource("/img/Bullet.png")).getImage();

	public Bullet(double x, double y, int speed, double angle, double phase) {
		super();
		this.x = x;
		this.y = y;
		this.angle = angle;
		this.speed = speed;
		this.startX = x;
		this.phase = phase;
	}

	public int getSpeed() {
		return speed;
	}

	public void setSpeed(int speed) {
		this.speed = speed;
	}

	public double getAngle() {
		return angle;
	}

	public void setAngle(double angle) {
		this.angle = angle;
	}

	public double getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public double getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public int getBulletWidth() {
		return bulletWidth;
	}

	public void setBulletWidth(int bulletWidth) {
		this.bulletWidth = bulletWidth;
	}

	public int getBulletHeight() {
		return bulletHeight;
	}

	public void setBulletHeight(int bulletHeight) {
		this.bulletHeight = bulletHeight;
	}

	public Board getGameView() {
		return gameView;
	}

	public void setGameView(Board gameView) {
		this.gameView = gameView;
	}

	public Image getModel() {
		return model;
	}

	public void straight() {
		move(0, -speed);
	}

	public void radial() {
		double dx = speed * Math.cos(angle);
		double dy = speed * Math.sin(angle);
		move(dx, dy);
	}

	public void spiral() {
		spiralOffset += 0.05; // Tốc độ xoắn
		double currentAngle = angle + spiralOffset;

		double dx = speed * Math.cos(currentAngle);
		double dy = speed * Math.sin(currentAngle);
		move(dx, dy);
	}

	public void fan() {
		double angleRad = Math.toRadians(angle);
		this.vx = Math.cos(angleRad);
		this.vy = Math.sin(angleRad);
		move(vx * speed, vy * speed);
	}

//	public void homing(Player player) {
//		if (player == null) return;
//
//		double dx = player.getX() - this.x;
//		double dy = player.getY() - this.y;
//		double length = Math.sqrt(dx * dx + dy * dy);
//
//		// Tránh chia cho 0
//		if (length == 0) return;
//
//		double vx = (dx / length) * speed;
//		double vy = (dy / length) * speed;
//
//		move(vx, vy);
//	}

	public double getStartX() {
		return startX;
	}

	public void setStartX(double startX) {
		this.startX = startX;
	}

	public double getPhase() {
		return phase;
	}

	public void setPhase(double phase) {
		this.phase = phase;
	}

	public void wave() {
		this.y += speed;
		this.x = startX + amplitude * Math.sin(frequency * y + phase);
	}

	@Override
	public void move(double dx, double dy) {
		this.x += dx;
		this.y += dy;
	}

	@Override
	public void attack() {
		return;
	}

	@Override
	public void takeDamage(int dmg) {
		return;
	}

	@Override
	public boolean isAlive() {
		return false;
	}

	@Override
	public boolean checkCollision(Entity other) {
		if (other == null) {
			return false;
		}

		if (other instanceof Enemy) {
			Enemy e = (Enemy) other;
			return x < e.getX() + e.getEnemyWidth() && x + bulletWidth > e.getX() && y < e.getY() + e.getEnemyHeight()
					&& y + bulletHeight > e.getY();
		} else if (other instanceof Player) {
			Player e = (Player) other;
			return x < e.getX() + e.getPlayerWidth() && x + bulletWidth > e.getX() && y < e.getY() + e.getPlayerHeight()
					&& y + bulletHeight > e.getY();
		}

		return false;
	}
}
