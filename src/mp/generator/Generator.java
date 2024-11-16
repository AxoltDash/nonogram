package mp.generator;

import java.util.ArrayList;

public class Generator {
    
    public int[][] generateNonogram(int size) {
        int[][] nonogram = new int[size][size];
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                nonogram[i][j] = (int) (Math.random() * 2);
            }
        }
        return nonogram;
    }

    public int[][] generateHints(int[][] matrix, boolean isRowClues) {
        int size = matrix.length;
        int[][] hints = new int[size][];
        for (int i = 0; i < size; i++) {
            ArrayList<Integer> hintsList = new ArrayList<>();
            int count = 0;
            for (int j = 0; j < size; j++) {
                int cell = isRowClues ? matrix[i][j] : matrix[j][i];
                if (cell == 1) {
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

