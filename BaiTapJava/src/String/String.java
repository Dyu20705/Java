package String;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class String {
	private java.lang.String inputString;

    // Constructor để khởi tạo chuỗi đầu vào
    public String(Scanner scanner) {
        System.out.print("Nhập chuỗi: ");
        this.inputString = scanner.nextLine().trim(); // Loại bỏ khoảng trắng thừa
    }

    /**
     * Loại bỏ tất cả các ký tự 'targetChar' và đảo ngược chuỗi
     *
     * @param targetChar Ký tự cần loại bỏ
     */
    public void removeCharacterAndReverse(char targetChar) {
        StringBuilder filteredString = new StringBuilder();
        for (char c : inputString.toCharArray()) {
            if (c != targetChar) {
                filteredString.append(c);
            }
        }
        inputString = filteredString.reverse().toString(); // Đảo ngược chuỗi
    }

    /**
     * Kiểm tra chuỗi có hợp lệ và đếm các số khác nhau
     *
     * @param input Chuỗi cần kiểm tra
     */
    public void validateAndCountNumbers(String input) {
        if (input == null || ((CharSequence) input).isEmpty()) {
            System.out.println("Chuỗi không hợp lệ.");
            return;
        }

        ArrayList<Double> numbers = extractNumbersFromInput(input);

        if (!containsLetters(input)) {
            System.out.println("Chuỗi hợp lệ.");
            System.out.println("Có " + numbers.size() + " số.");
        } else {
            System.out.println("Chuỗi không hợp lệ.");
        }
    }

    // Phương thức phụ để tách số từ chuỗi
    private ArrayList<Double> extractNumbersFromInput(String input) {
        ArrayList<Double> numbers = new ArrayList<>();
        Pattern pattern = Pattern.compile("-?\\d+(\\.\\d+)?");
        Matcher matcher = pattern.matcher((CharSequence) input);

        while (matcher.find()) {
            double number = Double.parseDouble(matcher.group());
            numbers.add(number);
        }
        return numbers;
    }

    // Phương thức phụ để kiểm tra chuỗi có chứa chữ cái không
    private boolean containsLetters(String input) {
        return Pattern.compile("[a-zA-Z]").matcher((CharSequence) input).find();
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Khởi tạo đối tượng và nhập chuỗi
        String processor = new String(scanner);

        // Nhập ký tự cần loại bỏ và xử lý chuỗi
        System.out.print("Nhập ký tự cần loại bỏ: ");
        char targetChar = scanner.next().charAt(0);
        processor.removeCharacterAndReverse(targetChar);
        System.out.println("Chuỗi sau khi xử lý: " + processor.inputString);

        // Kiểm tra và đếm các số trong chuỗi
        scanner.nextLine(); // Clear buffer
        System.out.print("Nhập chuỗi để kiểm tra và đếm số: ");
        //String input = scanner.nextLine();
        //processor.validateAndCountNumbers(input);

        scanner.close();
    }
}
