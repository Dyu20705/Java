package PhuongTrinh;

import Phuc.Phuc;

public class PTBac2 {
	private double a;
	private double b;
	private double c;

	@Override
	public String toString() {
		return "PTBac2 [" + a + "x^2 + " + b + "x + " + c + " = 0]";
	}

	public PTBac2(double a, double b, double c) {
		super();
		this.a = a;
		this.b = b;
		this.c = c;
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

	public double getC() {
		return c;
	}

	public void setC(double c) {
		this.c = c;
	}

	public double delta() {
		return b * b - 4 * a * c;
	}
	
	public boolean voNghiem() {
		return a == 0 && b == 0 && c != 0;
	}
	
	public boolean voSoNghiem() {
		return a == 0 && b == 0 && c == 0;
	}
	
	public double nghiemDon() {
		if(a == 0 && b != 0) {
			return - this.c / this.b;
		}
		if(this.delta() == 0) {
			return - this.b / this.a;
		}
		return 0;
	}
	
	public Phuc[] nghiemKep() {
		Phuc[] phuc = null;
		if(this.delta() > 0) {
			double x1 = (-b + Math.sqrt(this.delta())) / (2 * a);
            double x2 = (-b - Math.sqrt(this.delta())) / (2 * a);
            System.out.println("The equation has two distinct solutions: x1 = " + String.format("%.2f", x1) + ", x2 = " + String.format("%.2f", x2));
		} else if(this.delta() < 0) {
			return phuc;
		}
		return null;
	}
}
