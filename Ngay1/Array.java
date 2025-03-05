package Ngay1;

import java.util.*;

public class Array {
	    public static void main(String[] args) {
	        int[] arr = {12, -3, 25, -7, 15, 30, 6, 8, -2, 5, 10, 21};
	        
	        countPositiveNotDivisibleBy3(arr);
	        calculateSumAndAverage(arr);
	        findMaxDivisibleBy3(arr);
	        sortArrayByAbsoluteValue(arr);
	        filterArray(arr);
	    }

	    private static void countPositiveNotDivisibleBy3(int[] arr) {
	        long count = Arrays.stream(arr).filter(x -> x > 0 && x % 3 != 0).count();
	        System.out.println("Số phần tử dương không chia hết cho 3: " + count);
	    }

	    private static void calculateSumAndAverage(int[] arr) {
	        IntSummaryStatistics stats = Arrays.stream(arr)
	                .filter(x -> x >= -5 && x <= 25)
	                .summaryStatistics();
	        System.out.println("Tổng các phần tử trong khoảng [-5, 25]: " + stats.getSum());
	        System.out.println("Trung bình cộng: " + (stats.getCount() > 0 ? stats.getAverage() : 0));
	    }

	    private static void findMaxDivisibleBy3(int[] arr) {
	        int maxDiv3 = Arrays.stream(arr).filter(x -> x % 3 == 0).max().orElse(Integer.MIN_VALUE);
	        System.out.println("Phần tử lớn nhất chia hết cho 3: " + maxDiv3);
	    }

	    private static void sortArrayByAbsoluteValue(int[] arr) {
	        Integer[] sortedArr = Arrays.stream(arr).boxed().toArray(Integer[]::new);
	        Arrays.sort(sortedArr, Comparator.comparingInt(Math::abs));
	        System.out.println("Mảng sau khi sắp xếp theo giá trị tuyệt đối: " + Arrays.toString(sortedArr));
	    }

	    private static void filterArray(int[] arr) {
	        int[] filteredArr = Arrays.stream(arr)
	                .filter(x -> !(x % 5 == 0 && x % 3 != 0))
	                .toArray();
	        System.out.println("Mảng sau khi loại bỏ phần tử chia hết cho 5 nhưng không chia hết cho 3: " + Arrays.toString(filteredArr));
	    }

}
