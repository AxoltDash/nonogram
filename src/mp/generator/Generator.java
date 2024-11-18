package mp.generator;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import mp.mappings.Cell;

/**
 * The Generator class generates a nonogram and its hints.
 */
public class Generator {

    private Random random = new Random();

    /**
     * Generates a nonogram of the specified size.
     * 
     * @param size The size of the nonogram.
     * @return The generated nonogram.
     */
    public Cell[][] generateNonogram(int size) {
        Cell[][] nonogram = new Cell[size][size];
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                nonogram[i][j] = new Cell(); // Initialize each cell
            }
        }
        for (int i = 0; i < size; i++) {
            List<Integer> rowClues = generateClues(size);
            fillRow(nonogram, i, rowClues);
        }
        for (int j = 0; j < size; j++) {
            List<Integer> colClues = generateClues(size);
            fillColumn(nonogram, j, colClues);
        }
        return nonogram;
    }

    /**
     * Generates clues that satisfy the immediate resolvability condition.
     * 
     * @param size The size of the row or column.
     * @return The generated clues.
     */
    private List<Integer> generateClues(int size) {
        List<Integer> clues = new ArrayList<>();
        int remaining = size;
        while (remaining > 0) {
            // Genera una pista con un tamaño máximo de la mitad del tamaño restante
            int maxClueSize = Math.max(1, remaining / 3);
            int clue = random.nextInt(maxClueSize) + 1;
            clues.add(clue);
            remaining -= clue + 1; // Resta el tamaño de la pista y un espacio
        }
        return clues;
    }

    /**
     * Fills a row in the nonogram based on the given clues.
     * 
     * @param nonogram The nonogram to fill.
     * @param rowIndex The index of the row to fill.
     * @param clues    The clues for the row.
     */
    private void fillRow(Cell[][] nonogram, int rowIndex, List<Integer> clues) {
        int colIndex = 0;
        for (int clue : clues) {
            for (int i = 0; i < clue; i++) {
                nonogram[rowIndex][colIndex++].setFilled();
            }
            if (colIndex < nonogram.length) {
                colIndex++; // Skip one cell for the space between blocks
            }
        }
    }

    /**
     * Fills a column in the nonogram based on the given clues.
     * 
     * @param nonogram The nonogram to fill.
     * @param colIndex The index of the column to fill.
     * @param clues    The clues for the column.
     */
    private void fillColumn(Cell[][] nonogram, int colIndex, List<Integer> clues) {
        int rowIndex = 0;
        for (int clue : clues) {
            for (int i = 0; i < clue; i++) {
                nonogram[rowIndex++][colIndex].setFilled();
            }
            if (rowIndex < nonogram.length) {
                rowIndex++; // Skip one cell for the space between blocks
            }
        }
    }

//=======================================================

    /**
     * Generates hints for the nonogram.
     * 
     * @param nonogram   The nonogram to generate hints for.
     * @param isRowClues True if generating row hints, false if generating column hints.
     * @return The generated hints.
     */
    public int[][] generateHints(Cell[][] nonogram, boolean isRowClues) {
        int size = nonogram.length;
        int[][] hints = new int[size][];
        for (int i = 0; i < size; i++) {
            ArrayList<Integer> hintsList = new ArrayList<>();
            int count = 0;
            for (int j = 0; j < size; j++) {
                boolean isFilled = isRowClues ? nonogram[i][j].isFilled() : nonogram[j][i].isFilled();
                if (isFilled) {
                    count++;
                } else if (count > 0) {
                    hintsList.add(count);
                    count = 0;
                }
            }
            if (count > 0) {
                hintsList.add(count);
            }
            if (hintsList.isEmpty()) {
                hintsList.add(0);
            }
            hints[i] = new int[hintsList.size()];
            for (int k = 0; k < hintsList.size(); k++) {
                hints[i][k] = hintsList.get(k);
            }
        }
        return hints; 
    }
}

