import java.util.*;

public class Main {
    // Hàm đệ quy tính giá trị S
    private double calculateS(double x, int n, int currentPowerN, int currentFactorial, int i) {
        if (i > n) {
            return 1; // Giá trị cơ sở
        }

        double term = currentPowerN * Math.pow(x, i) / currentFactorial;
        
        return term + calculateS(x, n, currentPowerN * (n - i), currentFactorial * (i + 1), i + 1);
    }
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Nhập giá trị x: ");
        double x = scanner.nextDouble();

        System.out.print("Nhập giá trị n: ");
        int n = scanner.nextInt();

        Main solve = new Main();
        System.out.println("Giá trị của S là: " + solve.calculateS(x, n, n, 1, 1));

        scanner.close();
    }
}
