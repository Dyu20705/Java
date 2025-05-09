package App;

import java.awt.BorderLayout;

import javax.swing.*;

import Controller.Game;
import Model.Enemy;
import Model.Player;
import Utils.ExceptionHandler;
import View.Board;
import View.Menu;

public class Main {
	private static Menu menu;
	private static Board board;

	public static void init() {
		ExceptionHandler.setupGlobalExceptionHandler();
		// Set window properties
		JFrame window = new JFrame();
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setResizable(true);
		window.setTitle("Cube");
		window.setIconImage(new ImageIcon("rsc/img/Cube.png").getImage());

		// Set element 
		Player player = new Player(680, 600);
		Enemy enemy = new Enemy(600, 50);
		board = new Board();
		Game game = new Game(player, enemy, board);
		menu = new Menu(game);
		
		game.setMenu(menu);
		board.setEnemy(enemy);
		board.setPlayer(player);
		player.setBoard(board);
		enemy.setBoard(board);
		enemy.setPlayer(player);
		
		// Set layout
		window.setLayout(new BorderLayout());
		window.add(menu, BorderLayout.NORTH);
		window.add(board, BorderLayout.CENTER);
		
		// Set size and visibility
		window.pack();
		window.setLocationRelativeTo(null);
		window.setVisible(true);
		
		// Start the game loop
		game.startGameLoop();
	}

	public static Menu getMenu() {
		return menu;
	}

	public static void setMenu(Menu menu) {
		Main.menu = menu;
	}

	public static Board getBoard() {
		return board;
	}

	public static void setBoard(Board board) {
		Main.board = board;
	}

	public static void main(String[] args) throws Exception {
		init();
	}
}