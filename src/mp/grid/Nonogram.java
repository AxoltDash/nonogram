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
        this.nonogramSolution = generator.generateNonogram(this.size);
        this.verticalHints = generator.generateHints(this.nonogramSolution, false);
        this.horizontalHints = generator.generateHints(this.nonogramSolution, true);
        this.nonogram = new Cell[this.size][this.size]; // Initialize the nonogram matrix with empty cells
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
}
