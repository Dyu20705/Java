package Fibonacci;

public class Main {
	public static void main(String[] args) {
		FibonacciArray fibArr = new FibonacciArray();
		fibArr.render(30);
		System.out.println(fibArr.query1(6));
		System.out.println(fibArr.query2(21));
	}
}
