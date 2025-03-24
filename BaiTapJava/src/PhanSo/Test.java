package PhanSo;

public class Test {
	public static void init() {
		PhanSo[] phanSo = {new PhanSo(1, 2), new PhanSo(2, 1), new PhanSo(1, 2), new PhanSo(2, 1)};
		ArrayPhanSo arrayPhanSo = new ArrayPhanSo(phanSo);
		
		System.out.println(arrayPhanSo.phanSoCoTuLonNhat().toString());
		System.out.println(arrayPhanSo.timPhanSoLonNhat().toString());
		System.out.println(arrayPhanSo.tinhTongCacPhanSo().toString());
		System.out.println(arrayPhanSo.soPhanSoKhongPhaiSoNguyen());
	}
	
	public static void main(String[] args) {
		init();
	}
}
