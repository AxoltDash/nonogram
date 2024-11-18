package mp.mappings;

import java.util.Random;
import java.util.ArrayList;
import java.util.List;

import mp.generator.Generator;

/**
 * The Nonogram class represents the Nonogram game grid and its associated logic.
 * It handles the generation of the grid, marking cells, providing hints, and checking if the puzzle is solved.
 */
public class Nonogram {
    // Matrices
    private Cell[][] nonogram;
    private int[][] horizontalHints;
    private int[][] verticalHints;
    private int size;

    /**
     * Constructor for Nonogram.
     * Initializes the Nonogram with the specified size and generates the grid and hints.
     * 
     * @param size The size of the Nonogram grid.
     */
    public Nonogram(int size) {
        this.size = size;
        generateNonogram();
    }

    /**
     * Generates the Nonogram grid and the associated hints.
     */
    public void generateNonogram() {
        Generator generator = new Generator();
        this.nonogram = generator.generateNonogram(this.size); // Generate the solution matrix
        this.horizontalHints = generator.generateHints(this.nonogram, true); // Hints for rows
        this.verticalHints = generator.generateHints(this.nonogram, false); // Hints for columns
    }

    /**
     * Marks a cell at the specified coordinates.
     * 
     * @param x The x-coordinate of the cell.
     * @param y The y-coordinate of the cell.
     * @return true if the cell was successfully marked, false otherwise.
     */
    public boolean markCell(int x, int y) {
        return this.nonogram[x][y].mark();
    }

    /**
     * Marks a cell as hollow at the specified coordinates.
     * 
     * @param x The x-coordinate of the cell.
     * @param y The y-coordinate of the cell.
     * @return true if the cell was successfully hollow marked, false otherwise.
     */
    public boolean markHollowCell(int x, int y) {
        return this.nonogram[x][y].markHollow();
    }

    /**
     * Provides a hint by marking a random unmarked filled cell.
     * 
     * @return true if a hint was provided, false otherwise.
     */
    public boolean askForHint() {
        List<int[]> possibleHints = new ArrayList<>();
        
        // Collect all cells that meet the conditions
        for (int x = 0; x < this.size; x++) {
            for (int y = 0; y < this.size; y++) {
                if (this.nonogram[x][y].isFilled() && !this.nonogram[x][y].isMarked()) {
                    possibleHints.add(new int[]{x, y});
                }
            }
        }
        
        // If there are no possible hints, return false
        if (possibleHints.isEmpty()) {
            return false;
        }
        
        // Select a random hint from the possible ones
        int[] hint = possibleHints.get(new Random().nextInt(possibleHints.size()));
        this.nonogram[hint[0]][hint[1]].mark();
        fillCells(hint[0], hint[1]);
        
        return true;
    }

    /**
     * Fills the cells in the specified row and column if they are completely marked.
     * 
     * @param row The row to check and fill.
     * @param column The column to check and fill.
     */
    public void fillCells(int row, int column) {
        boolean filaCompleta = true;
        boolean columnaCompleta = true;

        // Check if the row is completely marked
        for (int j = 0; j < size; j++) {
            if (nonogram[row][j].isFilled() && !nonogram[row][j].isMarked()) {
                filaCompleta = false;
                break;
            }
        }

        // If the row is complete, mark the rest of the cells as empty
        if (filaCompleta) {
            for (int j = 0; j < size; j++) {
                if (!nonogram[row][j].isMarked()) {
                    nonogram[row][j].markHollow();
                }
            }
        }

        // Check if the column is completely marked
        for (int i = 0; i < size; i++) {
            if (nonogram[i][column].isFilled() && !nonogram[i][column].isMarked()) {
                columnaCompleta = false;
                break;
            }
        }
        
        // If the column is complete, mark the rest of the cells as empty
        if (columnaCompleta) {
            for (int i = 0; i < size; i++) {
                if (!nonogram[i][column].isMarked()) {
                    nonogram[i][column].markHollow();
                }
            }
        }
    }

    /**
     * Checks if the Nonogram puzzle is solved.
     * 
     * @return true if the puzzle is solved, false otherwise.
     */
    public boolean isSolved() {
        for (int i = 0; i < this.size; i++) {
            for (int j = 0; j < this.size; j++) {
                if (this.nonogram[i][j].isFilled() != this.nonogram[i][j].isMarked()) {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * Gets the Nonogram grid.
     * 
     * @return The Nonogram grid.
     */
    public Cell[][] getNonogram() {
        return this.nonogram;
    }

    /**
     * Gets the vertical hints for the Nonogram.
     * 
     * @return The vertical hints.
     */
    public int[][] getVerticalHints() {
        return this.verticalHints;
    }

    /**
     * Gets the horizontal hints for the Nonogram.
     * 
     * @return The horizontal hints.
     */
    public int[][] getHorizontalHints() {
        return this.horizontalHints;
    }

    /**
     * Gets the size of the Nonogram grid.
     * 
     * @return The size of the Nonogram grid.
     */
    public int getSize() {
        return this.size;
    }

    // ====================================================================================================
    // Develop tools
    // Print the nonogramSolution
    public void printNonogramSolution() {
        System.out.println("Nonogram solution:");
        for (int i = 0; i < this.size; i++) {
            for (int j = 0; j < this.size; j++) {
                System.out.print(this.nonogram[i][j].isFilled() ? "X" : ".");
            }
            System.out.println();
        }
    }

    // Print the nonogram
    public void printState() {
        System.out.println("Nonogram state:");
        for (int i = 0; i < this.size; i++) {
            for (int j = 0; j < this.size; j++) {
                System.out.print(this.nonogram[i][j].isMarked() ? "X" : this.nonogram[i][j].isHollowMarked() ? "O" : ".");
            }
            System.out.println();
        }
    }

    // Print the vertical hints
    public void printVerticalHints() {
        System.out.println("Vertical hints:");
        for (int i = 0; i < this.size; i++) {
            System.out.print("Column " + (i + 1) + ": ");
            for (int hint : this.verticalHints[i]) {
                System.out.print(hint + " ");
            }
            System.out.println();
        }
    }

    // Print the horizontal hints
    public void printHorizontalHints() {
        System.out.println("Horizontal hints:");
        for (int i = 0; i < this.size; i++) {
            System.out.print("Row " + (i + 1) + ": ");
            for (int hint : this.horizontalHints[i]) {
                System.out.print(hint + " ");
            }
            System.out.println();
        }
    }
}
