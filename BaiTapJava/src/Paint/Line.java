package Paint;

public class Line extends Shape{
	private Point A;
	private Point B;
	private Point center;

	public Point getA() {
		return A;
	}

	public void setA(Point a) {
		A = a;
	}

	public Point getB() {
		return B;
	}

	public void setB(Point b) {
		B = b;
	}
	
	public Point getCenter() {
		return center;
	}

	public void setCenter(Point center) {
		this.center = center;
	}
	
	public Line(Point a, Point b) {
		super();
		A = a;
		B = b;
		center = new Point((A.getX() + B.getX()) / 2, (A.getY() + B.getY()) / 2);
	}

	@Override
	public String toString() {
		return "Line [A=" + A + ", B=" + B + "]";
	}

	@Override
	public double area() {
		return 0;
	}

	@Override
	public double perimeter() {
		return 0;
	}
	
	//Khoảng cách từ tâm
	@Override
	public double distance() {
		return center.distance();
	}

	@Override
	public void move(double dx, double dy) {
		A.move(dx, dy);
		B.move(dx, dy);
	}
	
	//Hai điểm xoay quanh tâm 
	@Override
	public void rotate(double angle) {
		double radians = Math.toRadians(angle);
		double cos = Math.cos(radians);
		double sin = Math.sin(radians);
		
		//Xoay điểm thứ nhất quanh tâm
		double dx1 = this.A.getX() - this.center.getX();
		double dy1 = this.A.getY() - this.center.getY();
		double newX1 = dx1 * cos - dy1 * sin + this.center.getX();
		double newY1 = dx1 * sin + dy1 * cos + this.center.getY();
		
		//Xoay điểm thứ hai quanh tâm
		double dx2 = this.B.getX() - this.center.getX();
		double dy2 = this.B.getY() - this.center.getY();
		double newX2 = dx2 * cos - dy2 * sin + this.center.getX();
		double newY2 = dx2 * sin + dy2 * cos + this.center.getY();
		
		//Cập nhật vị trí 2 điểm
		this.setA(new Point(newX1, newY1));
		this.setB(new Point(newX2, newY2));
	}
	
	//Phóng to thu nhỏ khoảng cách điểm so với tâm
	@Override
	public void zoom(double ratio) {
		double newX1 = this.center.getX() + (this.A.getX() - this.center.getX()) * ratio;
		double newY1 = this.center.getY() + (this.A.getY() - this.center.getY()) * ratio;
		
		double newX2 = this.center.getX() + (this.B.getX() - this.center.getX()) * ratio;
		double newY2 = this.center.getY() + (this.B.getY() - this.center.getY()) * ratio;
		
		this.setA(new Point(newX1, newY1));
		this.setB(new Point(newX2, newY2));
	}
	
	
}
