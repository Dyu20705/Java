package PhanSo;

public class PhanSo {
	private int tu;
	private int mau;

	public int getTu() {
		return tu;
	}

	public void setTu(int tu) {
		this.tu = tu;
	}

	public int getMau() {
		return mau;
	}

	public void setMau(int mau) {
		this.mau = mau;
	}
	
	public PhanSo() {
		this(0, 1);
	}
	
	public PhanSo(int tu, int mau) {
		super();
		this.tu = tu;
		this.mau = mau;
	}

	@Override
	public String toString() {
		return "PhanSo [tu=" + tu + ", mau=" + mau + "]";
	}
	
	public static int gcd(int a, int b) {
		return b == 0? a : gcd(b, a % b);
	}
	
	public PhanSo rutGonPhanSo() {
		int Gcd = gcd(this.tu, this.mau);
		return new PhanSo(this.tu / Gcd, this.mau / Gcd);
	}
	
	public int soSanhHaiPhanSo(PhanSo phanSo) {
		int Tu2 = phanSo.tu * this.mau;
		int Tu1 = phanSo.mau * this.tu;
		return Integer.compare(Tu1, Tu2);
	}
	
	public PhanSo tongHaiPhanSo(PhanSo phanSo) {
		int Tu = phanSo.mau * this.tu + phanSo.tu * this.mau;
		int Mau = phanSo.mau * phanSo.tu;
		return new PhanSo(Tu, Mau);
	}
	
	public PhanSo hieuHaiPhanSo(PhanSo phanSo) {
		int Tu = phanSo.mau * this.tu - phanSo.tu * this.mau;
		int Mau = phanSo.mau * phanSo.tu;
		return new PhanSo(Tu, Mau);
	}
	
	public PhanSo tichHaiPhanSo(PhanSo phanSo) {
		int Tu = this.tu * phanSo.tu;
		int Mau = phanSo.mau * phanSo.tu;
		return new PhanSo(Tu, Mau);
	}
	
	public PhanSo thuongHaiPhanSo(PhanSo phanSo) {
		int Tu = phanSo.mau * this.tu;
		int Mau = this.mau * phanSo.tu;
		return new PhanSo(Tu, Mau);
	}
	
	public boolean phanSoCoTuLaUocCuaMau() {
		return this.mau % this.tu == 0;
	}
	
	public boolean phanSoCoTuChiaHetChoMau() {
		return this.tu % this.mau == 0;
	}
}
