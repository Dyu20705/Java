package Array;

import java.util.Arrays;
import java.util.Scanner;

public class Main {
	private int[] array;

    // Constructor to initialize the array
    public Main(Scanner scanner) {
        int n = scanner.nextInt();
        array = new int[n];
        for (int i = 0; i < n; i++) {
            array[i] = scanner.nextInt();
        }
    }

    // Method to print the array
    public void printArray() {
        System.out.println(Arrays.toString(array));
    }

    // Count positive numbers not divisible by 3
    public int countPositiveNotDiv3() {
        return (int) Arrays.stream(array)
                           .filter(x -> x > 0 && x % 3 != 0)
                           .count();
    }

    // Filter elements in the range (start, end)
    public int[] filterArray(int start, int end) {
        return Arrays.stream(array)
                     .filter(x -> x > start && x < end)
                     .toArray();
    }

    // Sum elements in the range (start, end)
    public int sumInRange(int start, int end) {
        return Arrays.stream(filterArray(start, end))
                     .sum();
    }

    // Calculate the average of elements in the range (start, end)
    public double averageInRange(int start, int end) {
        int[] filteredArray = filterArray(start, end);
        return filteredArray.length > 0 ? (double) sumInRange(start, end) / filteredArray.length : 0;
    }

    // Find the maximum element divisible by 3
    public int maxElementDiv3() {
        return Arrays.stream(array)
                     .filter(x -> x % 3 == 0)
                     .max()
                     .orElse(Integer.MIN_VALUE);
    }

    // Sort the array by absolute values in ascending order
    public void sortAbsArray() {
        //Arrays.sort(array, (a, b) -> Integer.compare(Math.abs(a), Math.abs(b)));
    }

    // Remove elements divisible by 5 but not divisible by 3
    public void removeDiv5NotDiv3() {
        array = Arrays.stream(array)
                      .filter(x -> !(x % 5 == 0 && x % 3 != 0))
                      .toArray();
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Main solution = new Main(scanner);

        // Count positive numbers not divisible by 3
        int count = solution.countPositiveNotDiv3();
        System.out.println("Số phần tử dương không chia 3 là: " + count);

        // Calculate sum in the range [-5, 25]
        int sum = solution.sumInRange(-5, 25);
        System.out.println("Tổng các phần tử trong khoảng [-5, 25] là: " + sum);

        // Calculate average in the range [-5, 25]
        double average = solution.averageInRange(-5, 25);
        System.out.println("Trung bình cộng các phần tử trong khoảng [-5, 25] là: " + average);

        // Find the maximum element divisible by 3
        int maxDiv3 = solution.maxElementDiv3();
        System.out.println("Phần tử lớn nhất trong dãy chia hết cho 3 là: " + maxDiv3);

        // Sort the array by absolute values in ascending order
        solution.sortAbsArray();
        System.out.println("Mảng sau khi sắp xếp theo giá trị tuyệt đối tăng dần:");
        solution.printArray();

        // Remove elements divisible by 5 but not divisible by 3
        solution.removeDiv5NotDiv3();
        System.out.println("Mảng sau khi loại bỏ phần tử chia hết cho 5 nhưng không chia hết cho 3:");
        solution.printArray();

        scanner.close();
    }
}
