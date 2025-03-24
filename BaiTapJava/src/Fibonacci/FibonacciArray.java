package Fibonacci;

import java.util.Arrays;

public class FibonacciArray {
	private int[] fib;

	@Override
	public String toString() {
		return "FibonacciArray [fib=" + Arrays.toString(fib) + "]";
	}
	
	public void render(int n) {
		fib[0] = 0;
		fib[1] = 1;
		for (int i = 2; i <= n; i++) {
			fib[i] = fib[i-1] + fib[i-2];
		}
	}
	
	public int query1(int n) {
		return fib[n];
	}
	
	public int query2(int m) {
		if(m == 0) {
			return 0;
		} else if(m == 1) {
			return 1;
		}
		for (int i = 2; i < fib.length; i++) {
			if(fib[i] >= m) {
				return fib[i - 1];
			}
		}
		return 0;
	}
}
