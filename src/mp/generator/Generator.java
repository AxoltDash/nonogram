package mp.generator;

import java.util.ArrayList;

import mp.mappings.Cell;

/**
 * The Generator class generates a nonogram and its hints.
 */
public class Generator {

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
                nonogram[i][j] = new Cell();
                // Rand 50% chance
                if (Math.random() < 0.5) {
                    nonogram[i][j].setFilled();
                }
            }
        }
        return nonogram;
    }

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

