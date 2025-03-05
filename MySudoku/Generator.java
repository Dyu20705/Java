package MySudoku;

import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Generator {
    private static final int SIZE = 9;
    private static int[][] solutionGame;
    
    /**
     * Khởi tạo mảng lời giải và sinh lời giải hoàn chỉnh bằng phương pháp quay lui.
     */
    public static void initializeSolution() {
        solutionGame = new int[SIZE][SIZE];
        generateSolutionGame();
    }
    
    /**
     * Getter for the solution game.
     */
    public static int[][] getSolutionGame() {
        return solutionGame;
    }
    
    /**
     * Sinh lời giải cho Sudoku sử dụng quay lui.
     */
    public static boolean generateSolutionGame() {
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                if (solutionGame[i][j] == 0) {
                    List<Integer> numbers = getRandomNumbers();
                    for (int number : numbers) {
                        if (isValid(i, j, number)) {
                            solutionGame[i][j] = number;
                            if (generateSolutionGame()) {
                                return true;
                            }
                            solutionGame[i][j] = 0;
                        }
                    }
                    return false;
                }
            }
        }
        return true;
    }
    
    /**
     * Sinh bảng thử thách bằng cách xóa ngẫu nhiên một số ô trên bảng lời giải.
     * @param deletedCells số ô cần xóa
     * @return bảng thử thách
     */
    public static int[][] generateChallengeGame(int deletedCells) {
        int[][] challengeGame = new int[SIZE][SIZE];
        
        for (int i = 0; i < SIZE; i++) {
            System.arraycopy(solutionGame[i], 0, challengeGame[i], 0, SIZE);
        }
        
        Random random = new Random();
        while (deletedCells > 0) {
            int row = random.nextInt(SIZE);
            int col = random.nextInt(SIZE);
            if (challengeGame[row][col] != 0) {
                challengeGame[row][col] = 0;
                deletedCells--;
            }
        }
        return challengeGame;
    }
    
    /**
     * Kiểm tra xem giá trị có thể đặt tại ô (row, col) theo luật Sudoku hay không.
     */
    public static boolean isValid(int x, int y, int value) {
        // Kiểm tra hàng và cột
        for (int i = 0; i < SIZE; i++) {
            if (solutionGame[x][i] == value || solutionGame[i][y] == value) {
                return false;
            }
        }
        
        // Kiểm tra vùng 3x3
        int startCol = y - y % 3;
        int startRow = x - x % 3;
        for (int i = startRow; i < startRow + 3; i++) { 
            for (int j = startCol; j < startCol + 3; j++) {  
                if (solutionGame[i][j] == value) {
                    return false;
                }
            }
        }
        return true;
    }
    
    private static List<Integer> getRandomNumbers() {
        return IntStream.rangeClosed(1, SIZE)
                        .boxed()
                        .collect(Collectors.collectingAndThen(Collectors.toList(), list -> {
                            Collections.shuffle(list);
                            return list;
                        }));
    }
}
