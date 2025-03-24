package SudokuTest;

import java.util.Arrays;

public class Game {
    private Node[][] game;
    private static final int SIZE = 9;
    
    public Game() {
        // Initialize the game board array
        game = new Node[SIZE][SIZE];
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                game[i][j] = new Node(i, j, 0);
            }
        }
    }
    
    public Game(Node[][] game) {
        super();
        this.game = game;
    }

    @Override
    public String toString() {
        return "Game [game=" + Arrays.toString(game) + "]";
    }

    public Node[][] getGame() {
        return game;
    }

    public void setGame(Node[][] game) {
        this.game = game;
    }

    public int query(Node value) {
        int row = value.getX();
        int col = value.getY();
        return game[row][col].getValue();
    }

    public void apply(Node value) {
        int row = value.getX();
        int col = value.getY();
        game[row][col].setValue(value.getValue());
    }

    public boolean valiate(Node value) {
        // Check row and column
        for (int i = 0; i < game.length; i++) {
            if (game[value.getX()][i].getValue() == value.getValue()
                    || game[i][value.getY()].getValue() == value.getValue()) {
                return false;
            }
        }

        // Check 3x3 subgrid
        int startX = value.getX() - value.getX() % 3;
        int startY = value.getY() - value.getY() % 3;
        for (int row = startX; row < startX + 3; row++) {
            for (int col = startY; col < startY + 3; col++) {
                if (game[row][col].getValue() == value.getValue()) {
                    return false;
                }
            }
        }

        return true;
    }

    public boolean valiateGame() {
        for (int row = 0; row < game.length; row++) {
            for (int col = 0; col < game[0].length; col++) {
                if (game[row][col].getValue() == 0) {
                    return false;
                }
            }
        }
        return true;
    }
}
