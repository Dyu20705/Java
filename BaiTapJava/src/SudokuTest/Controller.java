package SudokuTest;

import java.util.Scanner;

public class Controller {
    private static final int SIZE = 9;
    // Set initial allowed errors to 3 instead of 0.
    private static int remainingErrors = 3;
    private static Game game;
    
    public Controller() {
        game = Generator.getSolutionGame();
    }
    
    public static int getStatus() {
        if (game.valiateGame()) {
            return 1; // Game won
        } else if (remainingErrors <= 0) {
            return -1; // Game lost
        }
        return 0; // Game continues
    }
    
    public static boolean makeMove(Node value) {
        if (game.query(value) == 0) {
            if (game.valiate(value)) {
                game.apply(value);
                return true;
            } else {
                remainingErrors--;
                return false;
            }
        } else {
            System.out.println("Ô này đã được điền giá trị!");
            return false;
        }
    }
    
    public static void play() {
        new Controller();
        Scanner scanner = new Scanner(System.in);
        
        while(getStatus() == 0) {
            View.showGame(game.getGame(), SIZE);
            System.out.print("Nhập hàng, cột, và số: ");
            int row = scanner.nextInt();
            int col = scanner.nextInt();
            int num = scanner.nextInt();
            
            Node value = new Node(row, col, num);
            
            if (row == 0 || col == 0 || num == 0) {
                System.out.println("Thoát game. Cảm ơn đã chơi!");
                break;
            }
            
            if (makeMove(value)) {
                System.out.println("Nước đi hợp lệ!");
            } else {
                System.out.println("Nước đi không hợp lệ. Hãy thử lại.");
                continue;
            }
            
            if (getStatus() == 1) {
                System.out.println("Chúc mừng, bạn đã chiến thắng!");
                break;
            } else if (getStatus() == -1) {
                System.out.println("Mày đã sai tận 3 lần? Gà vậy!");
                break;
            }
        }
        scanner.close();
    }
}
