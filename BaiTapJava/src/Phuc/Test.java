package Phuc;

public class Test {
	public static void init() {
		Phuc[] phucArray = {new Phuc(), new Phuc(1, 0), new Phuc(1, 0), new Phuc(1, 1)};
		ArrayPhuc arrayPhuc = new ArrayPhuc(phucArray);
		
		System.out.println(arrayPhuc.counterHasNotImg());
		System.out.println(arrayPhuc.avg());
		System.out.println(arrayPhuc.maxModulus());
	}
	
	public static void main(String[] args) {
		init();
	}
}
