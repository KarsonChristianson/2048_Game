package edu.umn.d.chri5410._2048_correct;

import java.io.*;

public class PersistenceManager {
    private static final String SAVE_FILE = "savegame.txt";

    public static void save(GameBoard board) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(SAVE_FILE))) {
            int size = board.getSize();
            writer.println(size);
            for (int x = 0; x < size; x++) {
                for (int y = 0; y < size; y++) {
                    writer.print(board.getTile(x, y).getValue());
                    if (y < size - 1) writer.print(",");
                }
                writer.println();
            }
        } catch (IOException e) {
            System.err.println("Failed to save game: " + e.getMessage());
        }
    }

    public static void load(GameBoard board) {
        try (BufferedReader reader = new BufferedReader(new FileReader(SAVE_FILE))) {
            int size = Integer.parseInt(reader.readLine());
            for (int x = 0; x < size; x++) {
                String[] line = reader.readLine().split(",");
                for (int y = 0; y < size; y++) {
                    int val = Integer.parseInt(line[y]);
                    board.getTile(x, y).setValue(val);
                }
            }
        } catch (IOException | NumberFormatException e) {
            System.err.println("Failed to load game: " + e.getMessage());
        }
    }

    public static boolean saveFileExists() {
        return new File(SAVE_FILE).exists();
    }
}
