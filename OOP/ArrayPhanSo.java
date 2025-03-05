package OOP;

import java.util.HashSet;
import java.util.Set;

public class ArrayPhanSo {
	Set<PhanSo> set = new HashSet<>();
	
	public void them(PhanSo phanso) {
		set.add(phanso);
	}
	
	public PhanSo timPhanSoLonNhat() {
		PhanSo max = null;
        for (PhanSo p : set) {
            if (max == null || p.soSanh(max) > 0) {
                max = p;
            }
        }
        return max;
	}
	
	public PhanSo tinhTong() {
		PhanSo tong = new PhanSo();
        for (PhanSo p : set) {
            tong = tong.tong(p);
        }
        return tong;
	}
	
	public int demPhanSoKhongNguyen() {
		int count = 0;
        for (PhanSo p : set) {
            if (p.getT() % p.getM() != 0) {
                count++;
            }
        }
        return count;
	}
}
