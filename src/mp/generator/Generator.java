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
                nonogram[i][j] = new Cell();
            }
        }

        // Generate anchor columns
        List<Integer> anchorColumns = new ArrayList<>();
        for (int i = 0; i < size / 2; i++) {
            anchorColumns.add(random.nextInt(size));
        }

        // Generate rows based on anchor columns
        for (int i = 0; i < size; i++) {
            generateRow(nonogram, i, size, anchorColumns);
        }

        return nonogram;
    }

    private void generateRow(Cell[][] nonogram, int row, int size, List<Integer> anchorColumns) {
        int filledCells = 0;
        List<Integer> blocks = new ArrayList<>();

        // Generate blocks for the row
        while (filledCells < size) {
            int maxBlockSize = Math.min(size - filledCells, size / 4); // Limit block size to a quarter of the row size
            int blockSize = random.nextInt(maxBlockSize) + 1;
            blocks.add(blockSize);
            filledCells += blockSize + 1; // +1 for the space between blocks
        }

        // Adjust blocks to fit the row size
        while (filledCells > size) {
            int lastBlock = blocks.remove(blocks.size() - 1);
            filledCells -= lastBlock + 1;
        }

        // Place blocks in the row
        int currentPos = 0;
        for (int block : blocks) {
            for (int i = 0; i < block; i++) {
                nonogram[row][currentPos++].setFilled();
            }
            if (currentPos < size) {
                currentPos++; // Skip one cell for the space between blocks
            }
        }

        // Ensure anchor columns are not completely filled
        for (int col : anchorColumns) {
            if (nonogram[row][col].isFilled()) {
                nonogram[row][col].setFilled(false);
            }
        }

        // Ensure no column is completely filled or empty
        for (int col = 0; col < size; col++) {
            if (isColumnFull(nonogram, col, size)) {
                nonogram[row][col].setFilled(false);
            }
        }
    }

    private boolean isColumnFull(Cell[][] nonogram, int col, int size) {
        int filledCount = 0;
        for (int row = 0; row < size; row++) {
            if (nonogram[row][col].isFilled()) {
                filledCount++;
            }
        }
        return filledCount == size;
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

