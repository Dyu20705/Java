package Phuc;

public class Phuc {
	private double a;
	private double b;

	@Override
	public String toString() {
		return "Phuc [a=" + a + ", b=" + b + "]";
	}
	
	public Phuc() {
		this(1.0, -1.0);
	}
	
	public Phuc(double a, double b) {
		super();
		this.a = a;
		this.b = b;
	}

	public double getA() {
		return a;
	}

	public void setA(double a) {
		this.a = a;
	}

	public double getB() {
		return b;
	}

	public void setB(double b) {
		this.b = b;
	}
	
	public double modulus() {
		return Math.hypot(a, b);
	}
	
	public Phuc cong(Phuc phuc) {
		return new Phuc(this.a + phuc.getA(), this.b + phuc.getB());
	}
	
	public Phuc tru(Phuc phuc) {
		return new Phuc(this.a - phuc.getA(), this.b - phuc.getB());
	}
	
	public Phuc nhan(Phuc phuc) {
		return new Phuc(this.a * phuc.getA() - this.b * phuc.getB(), this.a * phuc.getB() + phuc.getA() * this.b);
	}

	public Phuc chia(Phuc phuc) {
		return new Phuc(phuc.modulus() * phuc.modulus(), 0).nhan(this.nhan(phuc.lienHop()));
	}
	
	public Phuc lienHop() {
		return new Phuc(a, -b);
	}
	
	public boolean hasImg() {
		return b != 0;
	}
}
