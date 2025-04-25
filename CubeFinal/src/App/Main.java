package App;

import java.io.IOException;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.*;

import Controller.Game;
import Model.Enemy;
import Model.Player;
import Utils.ThemeSound;
import View.Board;

public class Main {
	public static void init() {
		JFrame window = new JFrame();
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setResizable(true);
		window.setTitle("Cube");
		
		Player player = new Player(680, 600);
		Enemy enemy = new Enemy(600, 50);
		Board board = new Board();
		
		board.setEnemy(enemy);
		board.setPlayer(player);
		player.setBoard(board);
		enemy.setBoard(board);
		enemy.setPlayer(player);

		window.add(board);
		window.pack();
		window.setLocationRelativeTo(null);
		window.setVisible(true);
		
		Game game = new Game(player, enemy, board);
		game.startGameLoop();
		
//		try {
//			ThemeSound.play();
//		} catch (UnsupportedAudioFileException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (LineUnavailableException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
	}

	public static void main(String[] args) throws Exception {
		init();
	}
}