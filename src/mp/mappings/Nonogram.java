package mp.mappings;

import java.util.Random;
import java.util.ArrayList;
import java.util.List;

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

    public boolean markHollowCell(int x, int y) {
        return this.nonogram[x][y].markHollow();
    }

    public boolean askForHint() {
        List<int[]> possibleHints = new ArrayList<>();
        
        // Recopilar todas las celdas que cumplen con las condiciones
        for (int x = 0; x < this.size; x++) {
            for (int y = 0; y < this.size; y++) {
                if (this.nonogram[x][y].isFilled() && !this.nonogram[x][y].isMarked()) {
                    possibleHints.add(new int[]{x, y});
                }
            }
        }
        
        // Si no hay posibles pistas, devolver false
        if (possibleHints.isEmpty()) {
            return false;
        }
        
        // Seleccionar una pista al azar de las posibles
        int[] hint = possibleHints.get(new Random().nextInt(possibleHints.size()));
        this.nonogram[hint[0]][hint[1]].mark();
        fillCells(hint[0], hint[1]);
        
        return true;
    }

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
