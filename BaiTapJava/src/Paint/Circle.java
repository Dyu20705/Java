package Paint;

public class Circle extends Shape{
	private Point center;
	private double r;

	public Point getCenter() {
		return center;
	}

	public void setCenter(Point center) {
		this.center = center;
	}

	public double getR() {
		return r;
	}

	public void setR(double r) {
		this.r = r;
	}

	public Circle(Point center, double r) {
		super();
		this.center = center;
		this.r = r;
	}

	@Override
	public String toString() {
		return "Circle [center=" + center + ", r=" + r + "]";
	}

	@Override
	public double area() {
		return Math.PI * this.r * this.r;
	}

	@Override
	public double perimeter() {
		return Math.PI * this.r * 2.0;
	}

	@Override
	public double distance() {
		return center.distance();
	}

	@Override
	public void move(double dx, double dy) {
		center.move(dx, dy);
	}

	@Override
	public void rotate(double angle) {
		return;
	}

	@Override
	public void zoom(double ratio) {
		r *= ratio;
	}

}
