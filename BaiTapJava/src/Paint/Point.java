package Paint;

public class Point extends Shape {
	private double x;
	private double y;

	@Override
	public String toString() {
		return "Point [x=" + x + ", y=" + y + "]";
	}

	public Point(double x, double y) {
		super();
		this.x = x;
		this.y = y;
	}

	public double getX() {
		return x;
	}

	public void setX(double x) {
		this.x = x;
	}

	public double getY() {
		return y;
	}

	public void setY(double y) {
		this.y = y;
	}

	@Override
	public double area() {
		return 0;
	}

	@Override
	public double perimeter() {
		return 0;
	}

	@Override
	public double distance() {
		return Math.hypot(x, y);
	}

	@Override
	public void move(double dx, double dy) {
		this.x += dx;
		this.y += dy;
	}

	@Override
	public void rotate(double angle) {
		return;
	}

	@Override
	public void zoom(double ratio) {
		return;
	}
	
	public double distance(Point point) {
		return Math.hypot(this.x - point.getX(), this.y - point.getY());
	}

}
