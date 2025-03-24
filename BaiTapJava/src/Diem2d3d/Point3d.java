package Diem2d3d;

public class Point3d extends Point2d {
	private int z;

	public int getZ() {
		return z;
	}

	public void setZ(int z) {
		this.z = z;
	}

	@Override
	public String toString() {
		return "Point3d [z=" + z + "]";
	}

	public Point3d(int x, int y, int z) {
		super(x, y);
		this.z = z;
	}
	
	@Override
	public int distance(Point2d point) {
	    if(point.whoAmI() == 3) {
	        Point3d p = (Point3d) point;
	        return (int) Math.hypot(super.distance(p), this.z - p.getZ());
	    } else {
	        return super.distance(point);
	    }
	}
	
	public Point2d symmetric() {
		return new Point3d(-super.getX(), -super.getY(), -this.z);
	}
	
	public void translate(Delta3d delta3d) {
		super.setX(super.getX() + delta3d.getX());
		super.setY(super.getY() + delta3d.getY());
		this.z += delta3d.getZ();
	}
	
	public int whoAmI() {
		return 3;
	}
}
