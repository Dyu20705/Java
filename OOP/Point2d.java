package OOP;

public class Point2d {
	private static int x;
	private static int y;
	
	public Point2d() {
		super();
	}
	
	public static int getX() {
		return x;
	}
	public static void setX(int x) {
		Point2d.x = x;
	}
	public static int getY() {
		return y;
	}
	public static void setY(int y) {
		Point2d.y = y;
	}

	@Override
	public String toString() {
		return "Point2d [getClass()=" + getClass() + ", hashCode()=" + hashCode() + ", toString()=" + super.toString()
				+ "]";
	}
	
	
	
	
}
