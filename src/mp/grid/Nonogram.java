package mp.grid;

import mp.generator.Generator;

public class Nonogram {
    // Matrices
    private Cell[][] nonogram;
    private Cell[][] nonogramSolution;
    private int[][] verticalHints;
    private int[][] horizontalHints;
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
}
