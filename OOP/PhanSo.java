package OOP;

public class PhanSo {
	private int T;
	private int M;
	
	public PhanSo() {
		T = 0;
		M = 1;
	}

	public PhanSo(int t, int m) {
		super();
		T = t;
		M = m;
	}
	
	public int getT() {
		return T;
	}
	public void setT(int t) {
		T = t;
	}
	public int getM() {
		return M;
	}
	public void setM(int m) {
		M = m;
	}

	@Override
	public String toString() {
		return "Phân Số [Tử = " + T + ", Mẫu = " + M + "]";
	}
	
	public static int gcd(int a, int b) {
		return b == 0? a : gcd(a, b % a);
	}
	
	public PhanSo rutGonPhanSo() {
		int GCD = gcd(this.T, this.M);
		return new PhanSo(this.T / GCD, this.M / GCD);
	}
	
	public int soSanh(PhanSo phanSo) {
		int tu1 = this.T * phanSo.getM();
		int tu2 = this.M * phanSo.getT();
		return Integer.compare(tu1, tu2);
	}
	
	public PhanSo tong(PhanSo phanSo) {
		int tuTong = phanSo.getT() * this.M + this.T * phanSo.getM();
		int mauTong = this.M * phanSo.getM();
		return new PhanSo(tuTong, mauTong).rutGonPhanSo();
	}
	
	public PhanSo hieu(PhanSo phanSo) {
		int tuTong = phanSo.getT() * this.M - this.T * phanSo.getM();
		int mauTong = this.M * phanSo.getM();
		return new PhanSo(tuTong, mauTong).rutGonPhanSo();
	}

	public PhanSo tich(PhanSo phanSo) {
		int tuTong = phanSo.getT() * this.T;
		int mauTong = this.M * phanSo.getM();
		return new PhanSo(tuTong, mauTong).rutGonPhanSo();
	}
	
	public PhanSo thuong(PhanSo phanSo) {
		int tuTong = phanSo.getT() * this.M;
		int mauTong = this.T * phanSo.getM();
		return new PhanSo(tuTong, mauTong).rutGonPhanSo();
	}
}
