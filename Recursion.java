import java.util.*;

public class Main {
    private double x;
    private int n;

    public Main(double x, int n) {
        this.x = x;
        this.n = n;
    }

    // Hàm đệ quy tính giá trị S
    private double calculateS(double x, int n, int i, double currentPower, int currentFactorial, int currentPowerN) {
        if (i > n) {
            return 1; // Giá trị cơ sở: S = 1 khi i vượt quá n
        }

        // Tính toán giá trị hiện tại của tempx * tempn / tempi
        double term = (currentPower * currentPowerN) / currentFactorial;

        // Gọi đệ quy với i tăng lên và các giá trị được cập nhật
        return term + calculateS(x, n, i + 1, currentPower * x, currentFactorial * (i + 1), currentPowerN * (n - 1));
    }

    public void solve() {
        double result = calculateS(x, n, 1, x, 1, n);
        System.out.println("Giá trị của S là: " + result);
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Nhập giá trị x: ");
        double x = scanner.nextDouble();

        System.out.print("Nhập giá trị n: ");
        int n = scanner.nextInt();

        Main main = new Main(x, n);
        main.solve();

        scanner.close();
    }
}
