import java.util.Arrays;
import java.util.Scanner;

public class Main {
    private int[] array;

    // Hàm khởi tạo
    public Main(Scanner scanner) {
        int n = scanner.nextInt();
        array = new int[n];
        for (int i = 0; i < n; i++) {
            array[i] = scanner.nextInt();
        }
    }

    // Hàm in mảng
    public void printArray() {
        System.out.println(Arrays.toString(array));
    }

    // Đếm số phần tử dương không chia hết cho 3
    public int countPositiveNotDiv3() {
        return (int) Arrays.stream(array).filter(x -> x > 0 && x % 3 != 0).count();
    }

    // Tính tổng trong khoảng
    public int sumInRange(int start, int end) {
        return Arrays.stream(array, Math.max(0, start), Math.min(array.length, end + 1)).sum();
    }

    // Tính trung bình cộng trong khoảng
    public double averageInRange(int start, int end) {
        int[] subArray = Arrays.copyOfRange(array, Math.max(0, start), Math.min(array.length, end + 1));
        return subArray.length > 0 ? Arrays.stream(subArray).average().orElse(0) : 0;
    }

    // Xác định phần tử lớn nhất chia hết cho 3
    public int maxElementDiv3() {
        return Arrays.stream(array).filter(x -> x % 3 == 0).max().orElse(Integer.MIN_VALUE);
    }

    // Sắp xếp mảng theo giá trị tuyệt đối tăng dần
    public void sortAbsArray() {
        Arrays.sort(array, (a, b) -> Integer.compare(Math.abs(a), Math.abs(b)));
    }

    // Loại bỏ phần tử chia hết cho 5 nhưng không chia hết cho 3
    public void removeDiv5NotDiv3() {
        array = Arrays.stream(array).filter(x -> !(x % 5 == 0 && x % 3 != 0)).toArray();
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Main solution = new Main(scanner);

        // Đếm phần tử dương không chia hết cho 3
        int count = solution.countPositiveNotDiv3();
        System.out.println("Số phần tử dương không chia 3 là: " + count);

        // Tính tổng trong khoảng [-5, 25]
        int sum = solution.sumInRange(-5, 25);
        System.out.println("Tổng các phần tử trong khoảng [-5, 25] là: " + sum);

        // Tính trung bình cộng trong khoảng [-5, 25]
        double average = solution.averageInRange(-5, 25);
        System.out.println("Trung bình cộng các phần tử trong khoảng [-5, 25] là: " + average);

        // Tìm phần tử lớn nhất chia hết cho 3
        int maxDiv3 = solution.maxElementDiv3();
        System.out.println("Phần tử lớn nhất trong dãy chia hết cho 3 là: " + maxDiv3);

        // Sắp xếp mảng theo giá trị tuyệt đối tăng dần
        solution.sortAbsArray();
        System.out.println("Mảng sau khi sắp xếp theo giá trị tuyệt đối tăng dần:");
        solution.printArray();

        // Loại bỏ phần tử chia hết cho 5 nhưng không chia hết cho 3
        solution.removeDiv5NotDiv3();
        System.out.println("Mảng sau khi loại bỏ phần tử chia hết cho 5 nhưng không chia hết cho 3:");
        solution.printArray();

        scanner.close();
    }
}
