package mp.mappings;

import java.util.Random;

import mp.generator.Generator;

public class Nonogram {
    // Matrices
    private Cell[][] nonogram;
    private int[][] horizontalHints;
    private int[][] verticalHints;
    private int size;

    public Nonogram(int size) {
        this.size = size;
        generateNonogram();
    }

    public void generateNonogram() {
        Generator generator = new Generator();
        this.nonogram = generator.generateNonogram(this.size); // Generate the solution matrix
        this.horizontalHints = generator.generateHints(this.nonogram, true); // Hints for rows
        this.verticalHints = generator.generateHints(this.nonogram, false); // Hints for columns
    }

    public boolean markCell(int x, int y) {
        return this.nonogram[x][y].mark();
    }

    public boolean askForHint() {
        boolean hintFound = false;
        for (int i = 0; i < this.size - 1; i++) {
            // random number between 0 and size - 1
            int x = new Random().nextInt(this.size - 1);
            int y = new Random().nextInt(this.size - 1);
            
            if (this.nonogram[x][y].isFilled() && !this.nonogram[x][y].isMarked()) {
                this.nonogram[x][y].mark();
                hintFound = true;
                break;
            }
        }
        if (!hintFound) {
            return false;
        }
        return true;
    }

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

    public Cell[][] getNonogram() {
        return this.nonogram;
    }

    public int[][] getVerticalHints() {
        return this.verticalHints;
    }

    public int[][] getHorizontalHints() {
        return this.horizontalHints;
    }

    public int getSize() {
        return this.size;
    }

    // ====================================================================================================
    // Test that needs to be removed
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

    public void printState() {
        System.out.println("Nonogram state:");
        for (int i = 0; i < this.size; i++) {
            for (int j = 0; j < this.size; j++) {
                System.out.print(this.nonogram[i][j].isMarked() ? "X" : ".");
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
