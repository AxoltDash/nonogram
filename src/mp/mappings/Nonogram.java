package mp.mappings;

import mp.generator.Generator;

public class Nonogram {
    // Matrices
    private Cell[][] nonogram;
    private Cell[][] nonogramSolution;
    private int[][] horizontalHints;
    private int[][] verticalHints;
    private int size;

    public Nonogram(int size) {
        this.size = size;
    }

    public void generateNonogram() {
        Generator generator = new Generator();
        this.nonogramSolution = generator.generateNonogram(this.size); // Generate the solution matrix
        this.horizontalHints = generator.generateHints(this.nonogramSolution, true); // Hints for rows
        this.verticalHints = generator.generateHints(this.nonogramSolution, false); // Hints for columns
        this.nonogram = new Cell[this.size][this.size];
        for (int i = 0; i < this.size; i++) {
            for (int j = 0; j < this.size; j++) {
                this.nonogram[i][j] = new Cell();
            }
        }
    }

    public Cell[][] getNonogram() {
        return this.nonogram;
    }

    public Cell[][] getNonogramSolution() {
        return this.nonogramSolution;
    }

    public int[][] getVerticalHints() {
        return this.verticalHints;
    }

    public int[][] getHorizontalHints() {
        return this.horizontalHints;
    }

    // ====================================================================================================
    // Test that needs to be removed
    // Print the nonogramSolution
    public void printNonogramSolution() {
        System.out.println("Nonogram solution:");
        for (int i = 0; i < this.size; i++) {
            for (int j = 0; j < this.size; j++) {
                System.out.print(this.nonogramSolution[i][j].isFilled() ? "X" : ".");
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
