package PhanSo;

import java.util.Arrays;

public class ArrayPhanSo {
	private PhanSo[] arrayPhanSo;

	public ArrayPhanSo(PhanSo[] arrayPhanSo) {
		super();
		this.arrayPhanSo = arrayPhanSo;
	}

	public PhanSo[] getArrayPhanSo() {
		return arrayPhanSo;
	}

	public void setArrayPhanSo(PhanSo[] arrayPhanSo) {
		this.arrayPhanSo = arrayPhanSo;
	}

	@Override
	public String toString() {
		return "ArrayPhanSo [arrayPhanSo=" + Arrays.toString(arrayPhanSo) + "]";
	}
	
	public PhanSo timPhanSoLonNhat() {
		PhanSo phanSoRes = new PhanSo();
		for (PhanSo phanSo : arrayPhanSo) {
			if(phanSoRes.soSanhHaiPhanSo(phanSo) == -1) {
				phanSoRes = phanSo;
			}
		}
		return phanSoRes.rutGonPhanSo();
	}
	
	public PhanSo tinhTongCacPhanSo() {
		PhanSo phanSoRes = new PhanSo();
		for (PhanSo phanSo : arrayPhanSo) {
			phanSoRes = phanSoRes.tongHaiPhanSo(phanSo);
		}
		return phanSoRes.rutGonPhanSo();
	}
	
	public int soPhanSoKhongPhaiSoNguyen() {
		int dem = 0;
		for (PhanSo phanSo : arrayPhanSo) {
			if(!phanSo.phanSoCoTuChiaHetChoMau()) {
				dem++;
			}
		}
		return dem;
	}
	
	public PhanSo phanSoCoTuLonNhat() {
		PhanSo phanSoRes = new PhanSo();
		for (PhanSo phanSo : arrayPhanSo) {
			if(phanSoRes.getTu() < phanSo.rutGonPhanSo().getTu()) {
				phanSoRes = phanSo;
			}
		}
		return phanSoRes;
	}
}
