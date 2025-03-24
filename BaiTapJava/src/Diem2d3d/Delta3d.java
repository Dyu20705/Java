package Diem2d3d;

public class Delta3d extends Delta2d{
	private int z;

	public int getZ() {
		return z;
	}

	public void setZ(int z) {
		this.z = z;
	}

	@Override
	public String toString() {
		return "Delta3d [z=" + z + "]";
	}

	public Delta3d(int x, int y, int z) {
		super(x, y);
		this.z = z;
	}
}
