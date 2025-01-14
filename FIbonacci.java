import java.util.Arrays;
import java.util.Scanner;

public class Main {
    private long[] dp;
    private int size;

    // Hàm khởi tạo
    public Main(int n) {
        size = n;
        dp = new long[size + 1];
        dp[0] = 0;
        if (size > 0) {
            dp[1] = 1;
        }
    }

    // Tính toán dãy Fibonacci
    public void computeFibonacci() {
        for (int i = 2; i <= size; i++) {
            dp[i] = dp[i - 1] + dp[i - 2];
        }
    }

    // Truy vấn phần tử Fibonacci thứ n
    public long queryNth(int n) {
        if (n <= size) {
            return dp[n];
        }
        return -1; // Không hợp lệ nếu n lớn hơn size
    }

    // Tạo dãy Fibonacci có kích thước 2 * m (m là giá trị nhập từ người dùng)
    public void createFibonacciForM(long m) {
        int newSize = (int) (2 * m); // Kích thước mới cần tính toán
        dp = new long[newSize + 1];

        // Tính dãy Fibonacci
        dp[0] = 0;
        if (newSize > 0) {
            dp[1] = 1;
        }
        for (int i = 2; i <= newSize; i++) {
            dp[i] = dp[i - 1] + dp[i - 2];
        }
    }

    // Tìm phần tử có giá trị gần m nhất
    public long closestToM(long m) {
        int pos = Arrays.binarySearch(dp, 0, size + 1, m);

        if (pos < 0) {
            pos = -pos - 1;
        }

        long closest = dp[pos];
        if (pos > 0 && Math.abs(dp[pos - 1] - m) < Math.abs(closest - m)) {
            closest = dp[pos - 1];
        }
        if (pos < size && Math.abs(dp[pos + 1] - m) < Math.abs(closest - m)) {
            closest = dp[pos + 1];
        }

        return closest;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Quá trình 1: Nhập n và truy vấn phần tử thứ n
        System.out.print("Nhập n: ");
        int n = scanner.nextInt();
        Main fib1 = new Main(n);
        fib1.computeFibonacci();
        System.out.println("Phần tử Fibonacci thứ " + n + " là: " + fib1.queryNth(n));

        // Quá trình 2: Nhập m và tạo dãy Fibonacci mới với size = 2 * m
        System.out.print("Nhập m: ");
        long m = scanner.nextLong();
        Main fib2 = new Main(2 * (int) m); // Tạo dãy Fibonacci với size tối đa gấp đôi m
        fib2.createFibonacciForM(m); // Tạo lại dãy Fibonacci với kích thước mới

        // Tìm phần tử gần m nhất
        System.out.println("Phần tử Fibonacci gần " + m + " nhất là: " + fib2.closestToM(m));

        scanner.close();
    }
}
