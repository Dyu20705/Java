package Utils;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import Controller.Game;

public class FileManager {
	private String fileName = "rsc/save/save.txt";
	private Game game;

	public FileManager(Game game) {
		this.game = game;
		createSaveDirectory();
	}

	// Create the save directory if it doesn't exist
	public void createSaveDirectory() {
		File file = new File(fileName);
		if (!file.exists()) {
			try {
				file.createNewFile();
			} catch (IOException e) {
				System.out.println("An error occurred while creating the save directory.");
				e.printStackTrace();
			}
		}
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	// Save the game state to a file
	public void saveGame(int HpEnemy, int HpPlayer) {
		try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName, true))) {
			List<String> saves = readAllSaves();
			int saveCount = saves.size() + 1;

			// Đọc theo định dạng [a][bcdef][gh]
			String formattedName = String.format("%01d%05d%02d", saveCount, HpEnemy, HpPlayer);
			writer.write(formattedName + "\n");
			writer.newLine();
			writer.close();
		} catch (IOException e) {
			System.out.println("An error occurred while saving the game.");
			e.printStackTrace();
		}
	}

	// Load the game state from a file
	public void loadGame(int saveIndex) {
		List<String> saves = readAllSaves();
		if (saveIndex < 1 || saveIndex > saves.size())
			return;
		String saveData = saves.get(saveIndex - 1);
		int bossHp = Integer.parseInt(saveData.substring(1, 6)); // bcdef
		int playerHp = Integer.parseInt(saveData.substring(6, 8)); // gh

		game.getEnemy().setHp(bossHp);
		game.getPlayer().setHp(playerHp);
	}

	public List<String> readAllSaves() {
		List<String> saves = new ArrayList<>();
		try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
			String line;
			while ((line = reader.readLine()) != null) {
				if (line.matches("\\d{8}")) {
					saves.add(line);
				}
			}
		} catch (IOException e) {
			System.out.println("An error occurred while reading the saves.");
			e.printStackTrace();
		}
		return saves;
	}
}
