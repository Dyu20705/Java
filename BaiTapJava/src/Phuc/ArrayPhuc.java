package Phuc;

import java.util.Arrays;

public class ArrayPhuc {
	private Phuc[] arrayPhuc;

	public Phuc[] getArrayPhuc() {
		return arrayPhuc;
	}

	public void setArrayPhuc(Phuc[] arrayPhuc) {
		this.arrayPhuc = arrayPhuc;
	}

	@Override
	public String toString() {
		return "ArrayPhuc [arrayPhuc=" + Arrays.toString(arrayPhuc) + "]";
	}

	public ArrayPhuc(Phuc[] arrayPhuc) {
		super();
		this.arrayPhuc = arrayPhuc;
	}
	
	public Phuc avg() {
		Phuc avgPhuc = new Phuc();
		for (Phuc phuc : arrayPhuc) {
			avgPhuc = avgPhuc.cong(phuc);
		}
		return new Phuc(avgPhuc.getA() / arrayPhuc.length, avgPhuc.getB() / arrayPhuc.length);
	}
	
	public Phuc maxModulus() {
		Phuc hasMaxModulusPhuc = new Phuc();
		double maxModulus = 0.0;
		for (Phuc phuc : arrayPhuc) {
			double modulusPhuc = phuc.modulus();
			if(modulusPhuc > maxModulus) {
				hasMaxModulusPhuc = phuc;
				maxModulus = modulusPhuc;
			}
		}
		return hasMaxModulusPhuc;
	}
	
	public int counterHasNotImg() {
		int dem = 0;
		for (Phuc phuc : arrayPhuc) {
			if(!phuc.hasImg()) {
				dem++;
			}
		}
		return dem;
	}
}
