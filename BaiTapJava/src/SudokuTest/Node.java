package SudokuTest;

public class Node {
	private int x;
	private int y;
	private int value;

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

	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}

	@Override
	public String toString() {
		return "Node [x=" + x + ", y=" + y + ", value=" + value + "]";
	}

	public Node(int x, int y, int value) {
		super();
		this.x = x;
		this.y = y;
		this.value = value;
	}

}
