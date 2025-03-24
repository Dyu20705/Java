package Paint;

public class Rectangle extends Shape {
	private Point A;
	private Point B;
	private Point C;
	private Point D;
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

	public Point getC() {
		return C;
	}

	public void setC(Point c) {
		C = c;
	}

	public Point getD() {
		return D;
	}

	public void setD(Point d) {
		D = d;
	}

	public Rectangle(Point a, Point b, Point c, Point d) {
		super();
		A = a;
		B = b;
		C = c;
		D = d;
		center = new Point((A.getX() + B.getX() + C.getX() + D.getX()) / 4,
				(A.getY() + B.getY() + C.getY() + D.getY()) / 4);
	}

	@Override
	public String toString() {
		return "Rectangle [A=" + A + ", B=" + B + ", C=" + C + ", D=" + D + "]";
	}

	@Override
	public double area() {
		double AB = A.distance(B);
		double BC = B.distance(C);
		return AB * BC;
	}

	@Override
	public double perimeter() {
		double AB = A.distance(B);
		double BC = B.distance(C);
		double CD = C.distance(D);
		double DA = D.distance(A);
		return AB + BC + CD + DA;
	}

	@Override
	public double distance() {
		return center.distance();
	}

	@Override
	public void move(double dx, double dy) {
		A.move(dx, dy);
		B.move(dx, dy);
		C.move(dx, dy);
		D.move(dx, dy);
	}

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
		
		//Xoay điểm thứ nhất quanh tâm
		double dx3 = this.C.getX() - this.center.getX();
		double dy3 = this.C.getY() - this.center.getY();
		double newX3 = dx3 * cos - dy3 * sin + this.center.getX();
		double newY3 = dx3 * sin + dy3 * cos + this.center.getY();
		
		//Xoay điểm thứ nhất quanh tâm
		double dx4 = this.D.getX() - this.center.getX();
		double dy4 = this.D.getY() - this.center.getY();
		double newX4 = dx4 * cos - dy4 * sin + this.center.getX();
		double newY4 = dx4 * sin + dy4 * cos + this.center.getY();
		
		//Cập nhật vị trí 4 điểm
		this.setA(new Point(newX1, newY1));
		this.setB(new Point(newX2, newY2));
		this.setC(new Point(newX3, newY3));
		this.setD(new Point(newX4, newY4));
	}

	@Override
	public void zoom(double ratio) {
		double newX1 = this.center.getX() + (this.A.getX() - this.center.getX()) * ratio;
		double newY1 = this.center.getY() + (this.A.getY() - this.center.getY()) * ratio;
		
		double newX2 = this.center.getX() + (this.B.getX() - this.center.getX()) * ratio;
		double newY2 = this.center.getY() + (this.B.getY() - this.center.getY()) * ratio;
		
		double newX3 = this.center.getX() + (this.C.getX() - this.center.getX()) * ratio;
		double newY3 = this.center.getY() + (this.C.getY() - this.center.getY()) * ratio;
		
		double newX4 = this.center.getX() + (this.D.getX() - this.center.getX()) * ratio;
		double newY4 = this.center.getY() + (this.D.getY() - this.center.getY()) * ratio;
		
		this.setA(new Point(newX1, newY1));
		this.setB(new Point(newX2, newY2));
		this.setC(new Point(newX3, newY3));
		this.setD(new Point(newX4, newY4));
	}

}
