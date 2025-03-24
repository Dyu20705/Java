package Diem2d3d;

public class Point2d {
	private int x;
	private int y;

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	@Override
	public String toString() {
		return "Point2d [x=" + x + ", y=" + y + "]";
	}

	public Point2d(int x, int y) {
		super();
		this.x = x;
		this.y = y;
	}
	
	public int distance(Point2d other) {
	    return (int) Math.hypot(this.x - other.x, this.y - other.y);
	}
	
	public Point2d symmetric() {
		return new Point2d(-this.x, -this.y);
	}
	
	public void translate(Delta2d delta2d) {
		this.x += delta2d.getX();
		this.y += delta2d.getY();
	}
	
	public int whoAmI() {
		return 2;
	}
}
