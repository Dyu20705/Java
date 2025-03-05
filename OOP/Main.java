package OOP; 

public class Main {
	public static void main(String[] args) {
		ArrayPhanSo arrayPhanSo = new ArrayPhanSo();
		arrayPhanSo.them(new PhanSo(1, 2));
		arrayPhanSo.them(new PhanSo(3, 4));
		arrayPhanSo.them(new PhanSo(5, 1)); 
		arrayPhanSo.them(new PhanSo(7, 3));
		arrayPhanSo.them(new PhanSo(2, 5));
		
		// Tìm phân số lớn nhất
        PhanSo maxPhanSo = arrayPhanSo.timPhanSoLonNhat();
        System.out.println("Phân số lớn nhất: " + maxPhanSo);

        // Tính tổng các phân số
        PhanSo tong = arrayPhanSo.tinhTong();
        System.out.println("Tổng các phân số: " + tong);

        // Đếm số phân số không phải số nguyên
        int count = arrayPhanSo.demPhanSoKhongNguyen();
        System.out.println("Số phân số không phải số nguyên: " + count);
	}
}
