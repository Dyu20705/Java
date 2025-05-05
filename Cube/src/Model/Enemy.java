package Model;

import java.awt.Image;
import java.util.ArrayList;

import javax.swing.ImageIcon;

import View.Board;

public class Enemy implements Entity {
	private double enemyWidth = 200;
	private double enemyHeight = 200;
	private double x;
	private double y;
	private int hp = 10000;
	private final int maxHp = 10000;
	private int speed = 1;
//	private int direction = 1;
	private double spiralAngle = 0;
	private double centerAngle = 90; // Góc trung tâm (đi xuống)
	private double control = 1;
	private final Image model = new ImageIcon(getClass().getResource("/img/Boss.png")).getImage();
	private Board board;
	private Player player;
	private ArrayList<Bullet> bullets = new ArrayList<>();

	public Enemy(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public Player getPlayer() {
		return player;
	}

	public void setPlayer(Player player) {
		this.player = player;
	}

	public int getMaxHp() {
		return maxHp;
	}
	
	public void setHp(int hp) {
		this.hp = hp;
	}
	
	public Image getModel() {
		return model;
	}

	public void setBoard(Board board) {
		this.board = board;
	}

	public int getHp() {
		return hp;
	}

	public double getX() {
		return x;
	}

	public double getY() {
		return y;
	}

	public double getEnemyWidth() {
		return enemyWidth;
	}

	public void setEnemyWidth(double enemyWidth) {
		this.enemyWidth = enemyWidth;
	}

	public double getEnemyHeight() {
		return enemyHeight;
	}

	public void setEnemyHeight(double enemyHeight) {
		this.enemyHeight = enemyHeight;
	}

	public ArrayList<Bullet> getBullets() {
		return bullets;
	}

	public void setBullets(ArrayList<Bullet> bullets) {
		this.bullets = bullets;
	}

	public int getSpeed() {
		return speed;
	}

	public void setSpeed(int speed) {
		this.speed = speed;
	}

	@Override
	public void move(double dx, double dy) {
//		if (board == null)
//			return;
//		this.x += dx;
//		this.y += dy;
	}

	public void move() {
//		if (x + enemyWidth >= board.getW() * 3 / 4) {
//			direction = -1;
//		}
//
//		if (x <= 1) {
//			direction = 1;
//		}
//
//		move(speed * direction, 0);
	}

	private void attackRadial() {
		int count = 12; // Số lượng đạn
		for (int i = 0; i < count; i++) {
			double angle = (2 * Math.PI / count) * i; // Góc phân bố đều
			// Tạo đạn ở vị trí boss và set góc
			Bullet bullet = new Bullet(x + enemyWidth / 2, y + enemyHeight, 1, angle, 0);
			bullets.add(bullet);
		}
	}

	private void attackSpiral() {
		int bulletsPerWave = 12; // Số đạn mỗi vòng xoắn
		double angleIncrement = Math.PI / 30; // Tốc độ xoắn

		for (int i = 0; i < bulletsPerWave; i++) {
			double angle = spiralAngle + (2 * Math.PI / bulletsPerWave) * i;

			Bullet bullet = new Bullet(x + enemyWidth / 2 - 5, y + enemyHeight, 1, angle, 0);

			// Có thể điều chỉnh tốc độ đạn xoắn ốc
			bullet.setSpeed(6); // Thêm method setSpeed() trong class Bullet

			bullets.add(bullet);
		}

		spiralAngle += angleIncrement; // Cập nhật góc xoắn
	}

	private void attackFan() {
		int bulletCount = 7; // Số lượng đạn
		double spreadAngle = 90; // Góc mở hình quạt (độ)

		double startAngle = centerAngle - spreadAngle / 2;
		double angleStep = spreadAngle / (bulletCount - 1);

		for (int i = 0; i < bulletCount; i++) {
			double angleDeg = startAngle + i * angleStep;
//			double angleRad = Math.toRadians(angleDeg);

			Bullet bullet = new Bullet(x + enemyWidth / 2 - 5, y + enemyHeight, 1, // Tốc độ đạn
					angleDeg, // Góc bắn (độ)
					0);

			bullets.add(bullet);
		}
		if (centerAngle >= 180) {
			control = -1;
		} else if (centerAngle <= 0) {
			control = 1;
		}
		centerAngle += control * 30;
	}

//	private void attackHoming() {
//		if (player == null) return;
//
//		// Tạo 1 viên đạn nhắm vào player
//		Bullet bullet = new Bullet(
//			x + enemyWidth / 2 - 5,
//			y + enemyHeight,
//			3, // tốc độ cao hơn chút
//			0 // góc không cần thiết trong homing
//		);
//
//		bullet.homing(player); // Gọi hướng theo player
//		bullets.add(bullet);
//	}

	private void attackWave() {
		int bulletCount = 7; // số viên đạn trong dải
		double spread = board.getW() - 40; // bắn khắp chiều ngang (chừa 20px biên)
		double spacing = spread / (bulletCount - 1);

		for (int i = 0; i < bulletCount; i++) {
			double startX = 20 + i * spacing; // từ 20px qua trái
			double startY = 0;
			double phase = i * Math.PI / 4; // pha khác nhau

			Bullet b = new Bullet(startX, startY, speed, 0, phase);
			bullets.add(b);
		}
	}

	@Override
	public void attack() {
		double hpPercentage = (double) hp / maxHp;

		if (hpPercentage <= 0.25) { // 0-25% máu: dùng fan
			attackFan();
		} else if (hpPercentage <= 0.50) { // 25-50% máu: dùng spiral
			attackSpiral();
		} else if (hpPercentage <= 0.75) { // 50-75% máu: dùng radial
			attackRadial();
		} else { // 75-100% máu: dùng wave
			attackWave();
		}
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
		return 2;
	}

	public void reset() {
		this.hp = this.maxHp; // Khôi phục lại máu
		this.x = 600; // Vị trí ban đầu
		this.y = 50;
	}
}
