package Ngay1;

public class Main {
	public static void main(String[] args) {
		String string = "Lap trinh Java khong don gian";
		Str solve = new Str(string);
		solve.deleteCharacter1('a');
		System.out.println(solve.reverseString());
	}
}
