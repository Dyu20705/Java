package Paint;

import java.util.List;

public class Polygon extends Shape {
	private List<Point> points;
	private Point center;

	public Polygon(List<Point> points) {
		super();
		this.points = points;
		updateCenter();
	}
	
	private void updateCenter() {
        double sumX = 0;
        double sumY = 0;
        for (Point p : points) {
            sumX += p.getX();
            sumY += p.getY();
        }
        double centerX = sumX / points.size();
        double centerY = sumY / points.size();
        this.center = new Point(centerX, centerY);
    }

	@Override
	public String toString() {
		return "Polygon [points=" + points + ", center=" + center + "]";
	}

	public List<Point> getPoints() {
		return points;
	}

	public void setPoints(List<Point> points) {
		this.points = points;
	}

	public Point getCenter() {
		return center;
	}

	public void setCenter(Point center) {
		this.center = center;
	}

	@Override
	public double area() {
		double area = 0;
        int n = points.size();
        for (int i = 0; i < n; i++) {
            Point current = points.get(i);
            Point next = points.get((i + 1) % n);
            area += current.getX() * next.getY() - next.getX() * current.getY();
        }
        return Math.abs(area) / 2;
	}

	@Override
	public double perimeter() {
		double per = 0;
        int n = points.size();
        for (int i = 0; i < n; i++) {
            Point current = points.get(i);
            Point next = points.get((i + 1) % n);
            per += current.distance(next);
        }
        return per;
	}

	@Override
	public double distance() {
		return this.center.distance();
	}

	@Override
	public void move(double dx, double dy) {
		for (Point p : points) {
            p.move(dx, dy);
        }
        updateCenter();
	}

	@Override
	public void rotate(double angle) {
		double radians = Math.toRadians(angle);
        double cos = Math.cos(radians);
        double sin = Math.sin(radians);
        
        for (int i = 0; i < points.size(); i++) {
            Point p = points.get(i);
            double dx = p.getX() - center.getX();
            double dy = p.getY() - center.getY();
            double newX = dx * cos - dy * sin + center.getX();
            double newY = dx * sin + dy * cos + center.getY();
            points.set(i, new Point(newX, newY));
        }
	}

	@Override
	public void zoom(double ratio) {
		for (int i = 0; i < points.size(); i++) {
            Point p = points.get(i);
            double newX = center.getX() + (p.getX() - center.getX()) * ratio;
            double newY = center.getY() + (p.getY() - center.getY()) * ratio;
            points.set(i, new Point(newX, newY));
        }
		updateCenter();
	}
}
