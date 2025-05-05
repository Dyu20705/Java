package Utils;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

import Controller.Game;

public class FileManager {
	private String fileName = "rsc/save/save.txt"; 
	private Game game;
	
	public FileManager(Game game) {
		this.game = game;
	}
	
	public String getFileName() {
		return fileName;
	}
	
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	
	// Save the game state to a file
	public void saveGame(int HpEnemy, int HpPlayer) {
		try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))){
			writer.write(HpEnemy + "\n");
			writer.write(HpPlayer + "\n");
			writer.close();
		} catch (IOException e) {
			System.out.println("An error occurred while saving the game.");
			e.printStackTrace();
		}
	}
	
	// Load the game state from a file
	public void loadGame() {
		try {
			File file = new File(fileName);
			if (file.exists()) {
				Scanner scanner = new Scanner(file);
				if (scanner.hasNextInt()) {
					int hpEnemy = scanner.nextInt();
					if (scanner.hasNextInt()) {
						int hpPlayer = scanner.nextInt();
						game.getEnemy().setHp(hpEnemy);
						game.getPlayer().setHp(hpPlayer);
					}
				}
				scanner.close();
			} else {
				System.out.println("No saved game found.");
			}
		} catch (IOException e) {
			System.out.println("An error occurred while loading the game.");
			e.printStackTrace();
		}
	}
}
