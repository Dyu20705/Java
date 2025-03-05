package MySudoku;

import java.util.Scanner;

public class Controller {
    private static final int SIZE = 9;
    private static int remainingErrors = 3;
    private static int remainingCells = 31;
    private static int[][] solutionGame;
    private static int[][] challengeGame;
    
    public Controller() {
        // Initialize the solution and challenge board
        Generator.initializeSolution();
        solutionGame = Generator.getSolutionGame(); // Get the generated solution
        challengeGame = Generator.generateChallengeGame(remainingCells);
    }
    
    public static void showGame() {
        System.out.println("Bảng Sudoku hiện tại:");
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                // Display "*" for blank cells and the number for filled cells
                if (challengeGame[i][j] == 0) {
                    System.out.print("*\t");
                } else {
                    System.out.print(challengeGame[i][j] + "\t");
                }
            }
            System.out.println();
        }
    }
    
    /**
     * status() trả về:
     *  1: chiến thắng (không còn ô trống)
     * -1: thua (hết số lỗi cho phép)
     *  0: game còn tiếp tục
     */
    public static int status() {
        if (remainingCells == 0) {
            return 1;
        } else if (remainingErrors == 0) {
            return -1;
        }
        return 0;
    }
    
    /**
     * makeMove: nếu ô đang trống và số nhập đúng theo lời giải, cập nhật bảng và giảm số ô trống.
     * Nếu sai, giảm số lỗi cho phép.
     */
    public static boolean makeMove(int row, int col, int value) {
        if (challengeGame[row][col] == 0) {
            if (solutionGame[row][col] == value) {
                challengeGame[row][col] = value;
                remainingCells--;
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
        // Initialize game (static variables are set via the constructor)
        new Controller();
        Scanner scanner = new Scanner(System.in);
        // Continue while the game is ongoing (status() returns 0)
        while (status() == 0) {
            showGame();
            System.out.print("Nhập hàng, cột, và số: ");
            int row = scanner.nextInt();
            int col = scanner.nextInt();
            int num = scanner.nextInt();
            
            if (row == 0 || col == 0 || num == 0) {
                System.out.println("Thoát game. Cảm ơn đã chơi!");
                break;
            }
    
            if (makeMove(row - 1, col - 1, num)) {
                System.out.println("Nước đi hợp lệ!");
            } else {
                System.out.println("Nước đi không hợp lệ. Hãy thử lại.");
                continue;
            }
            
            if (status() == 1) {
                System.out.println("Chúc mừng, bạn đã chiến thắng!");
                break;
            } else if (status() == -1) {
                System.out.println("Mày đã sai tận 3 lần? Gà vậy!");
                break;
            }
        }
        scanner.close();
    }
    
    public static void main(String[] args) {
        play();
    }
}
