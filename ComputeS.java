import java.util.*;

public class Main {
    // Hàm tính giá trị S sử dụng vòng lặp
    public static double computeS(double x, int n) {
        double S = 1; // Giá trị ban đầu của S
        double PowerN = n; // Biến lưu giá trị n giai thừa
        double Factorial = 1; // Biến lưu mẫu số (giai thừa của i)

        // Vòng lặp tính giá trị S
        for (int i = 1; i <= n; i++) {
            // Thêm vào giá trị tại bước i
            S += PowerN * Math.pow(x, i) / Factorial;
            PowerN *= (n - i); // Cập nhật giá trị PowerN (n giai thừa)
            Factorial *= (i + 1); // Cập nhật giá trị mẫu số
        }
        return S;
    }

    // Hàm tính giá trị S sử dụng đệ quy
    // currentPowerN lưu giá trị giai thừa n tại mỗi bước
    // currentFactorial lưu giá trị mẫu số (giai thừa của i)
    private double calculateS(double x, int n, int currentPowerN, int currentFactorial, int i) {
        if (i > n) {
            return 1; // Trường hợp cơ sở: nếu i > n, trả về 1
        }

        // Tính toán giá trị tại bước i
        double term = currentPowerN * Math.pow(x, i) / currentFactorial;
        
        // Đệ quy tính giá trị S cho bước tiếp theo
        return term + calculateS(x, n, currentPowerN * (n - i), currentFactorial * (i + 1), i + 1);
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Nhập giá trị x và n từ người dùng
        System.out.print("Nhập giá trị x: ");
        double x = scanner.nextDouble();

        System.out.print("Nhập giá trị n: ");
        int n = scanner.nextInt();

        // Tạo đối tượng solve và tính giá trị S sử dụng đệ quy
        Main solve = new Main();
        System.out.println("Giá trị của S là: " + solve.calculateS(x, n, n, 1, 1));

        scanner.close();
    }
}
