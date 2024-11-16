package mp.grid;

import mp.generator.Generator;

public class Nonogram {
    // Matrices
    private int[][] nonogram;
    private int[][] nonogramSolution;
    private int[][] verticalHints;
    private int[][] horizontalHints;
    private int size;

    // User data
    private int lives;
    private int score;
    private int availableHints;

    public Nonogram(int size) {
        this.size = size;
        this.lives = 3;
        this.score = 0;
        this.availableHints = 3;
    }

    public void generateNonogram() {
        Generator generator = new Generator();
        this.nonogramSolution = generator.generateNonogram(this.size);
        this.verticalHints = generator.generateHints(this.nonogramSolution, false);
        this.horizontalHints = generator.generateHints(this.nonogramSolution, true);
        this.nonogram = new int[this.size][this.size]; 
    }
}
