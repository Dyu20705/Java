package SudokuTest;

public class View {
	public static void showGame(Node[][] game, int SIZE) {
		System.out.println("Bảng Sudoku hiện tại:");
		for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                if (game[i][j].getValue() == 0) {
                    System.out.print("*\t");
                } else {
                    System.out.print(game[i][j].getValue() + "\t");
                }
            }
            System.out.println();
        }
	}
}
