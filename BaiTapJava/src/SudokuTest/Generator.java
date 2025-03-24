package SudokuTest;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Generator {
	private static final int SIZE = 9;
	private static Game solutionGame = new Game();

	public static Game getSolutionGame() {
		generateSolutionGame();
		return solutionGame;
	}

	public static boolean generateSolutionGame() {
		 for(int row = 0;row < SIZE;row++) {
			 for(int col = 0;col < SIZE;col++) {
				 if(solutionGame.getGame()[row][col].getValue() == 0) {
					 List<Integer> ranNum = randomNum();
					 for (Integer integer : ranNum) {
						Node value = new Node(row, col, integer);
						if(solutionGame.valiate(value)) {
							solutionGame.apply(value);
							if(generateSolutionGame()) {
								return true;
							}
							solutionGame.apply(new Node(row, col, 0));
						}
					}
					return false;
				 }
			 }
		 }
		 return true;
	}
	
	public static List<Integer> randomNum() {
		return IntStream.rangeClosed(1, SIZE)
				.boxed()
				.collect(Collectors.collectingAndThen(Collectors.toList(), list -> {
					Collections.shuffle(list);
                    return list;
				}));
	}
}
