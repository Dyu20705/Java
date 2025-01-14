// Giải phương trình bậc 2
import java.util.Scanner;

public class Main {
    private double a;
    private double b;
    private double c;

    // Hàm khởi tạo
    public Main(double inp1, double inp2, double inp3) {
        this.a = inp1;
        this.b = inp2;
        this.c = inp3;
    }

    //Hàm tính phương trình bậc 2
    public void Solve(){
        if(a == 0){ // bx + c = 0 => tồn tại duy nhất nghiệm
            if(b == 0){ // c = 0 => Ko tồn tại nghiệm
                if(c == 0){// 0 = 0 => Luôn đúng
                    System.out.println("The equation has infinite solutions!");
                } else {
                    System.out.println("The equation has no solution!");
                }
            }
            double x = -c / b;
            System.out.println("The equation has a unique solution: x = " + String.format("%.2f", x));
        } else { // Quadratic equation
            double delta = b * b - 4 * a * c;
            if(delta > 0){ //Hai nghiệm phân biệt
                double x1 = (-b + Math.sqrt(delta)) / (2 * a);
                double x2 = (-b - Math.sqrt(delta)) / (2 * a);
                System.out.println("The equation has two distinct solutions: x1 = " + String.format("%.2f", x1) + ", x2 = " + String.format("%.2f", x2));
            } else if (delta == 0) { // Nghiệm kép
                double x = -b / (2 * a);
                System.out.println("The equation has a double root: x = " + String.format("%.2f", x));
            } else { // Không có nghiệm thực
                System.out.println("The equation has no real solution!");
            }
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Nhập hệ số
        System.out.print("Nhập hệ số a: ");
        double a = scanner.nextDouble();

        System.out.print("Nhập hệ số b: ");
        double b = scanner.nextDouble();

        System.out.print("Nhập hệ số c: ");
        double c = scanner.nextDouble();

        // Giải phương trình
        Main sol = new Main(a, b, c);
        sol.Solve();

        scanner.close();
    }
}
