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
System.out.println("#####################################");
        // Inicializa una matriz de celdas del tamaño especificado
        Cell[][] nonogram = new Cell[size][size];
        
        // Llena cada fila del nonogram con nuevas celdas y genera líneas resolubles
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                nonogram[i][j] = new Cell();
            }
            generateResolvableLine(nonogram[i], size);
        }
        // Print of the nonogram
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                System.out.print(nonogram[i][j].isFilled() ? "1" : "0");
            }
            System.out.println();
        }

        // Genera líneas resolubles para cada columna
        for (int j = 0; j < size; j++) {
            Cell[] column = new Cell[size];
            for (int i = 0; i < size; i++) {
                column[i] = nonogram[i][j];
            }
            generateResolvableLine(column, size);
        }
        
System.out.println("--------------");
        // Print of this for
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                System.out.print(nonogram[i][j].isFilled() ? "1" : "0");
            }
            System.out.println();
        }

System.out.println("#####################################");
        return nonogram;
    }

    private void generateResolvableLine(Cell[] line, int size) {
        int filledCells = 0; // Contador de celdas llenas
        // Número de segmentos de celdas llenas, por ejemplo, para un tamaño de 10, puede generar entre 1 y 5 segmentos
        int segments = random.nextInt(size / 2) + 1;
        int[] segmentSizes = new int[segments]; // Tamaños de los segmentos
// System.out.println("1 ------------------------------------");
        // Genera tamaños aleatorios para cada segmento
        for (int i = 0; i < segments; i++) {
            int maxSegmentSize = size - filledCells - (segments - i - 1);
            if (maxSegmentSize <= 0) {
                segmentSizes[i] = 1;
            } else {
               segmentSizes[i] = random.nextInt(maxSegmentSize) + 1;
            }
            filledCells += segmentSizes[i];
// System.out.println("Tamaños aleatorios para cada segmento " + i + " size: " + segmentSizes[i]);
        }
        
        int emptyCells = size - filledCells; // Celdas vacías restantes
        int[] emptySpaces = new int[segments + 1]; // Espacios vacíos entre segmentos
// System.out.println("2 ------------------------------------");
        // Genera tamaños aleatorios para los espacios vacíos
        for (int i = 0; i <= segments; i++) {
            int maxEmptySpace = emptyCells - (segments - i);
            if (maxEmptySpace <= 0) {
                emptySpaces[i] = 1;
            } else {
                emptySpaces[i] = random.nextInt(maxEmptySpace) + 1;
            }
            emptyCells -= emptySpaces[i];
// System.out.println("Tamaños aleatorios para cada espacio vacio" + i + " size: " + emptySpaces[i]);
        }
        
        int index = 0; // Índice para recorrer la línea
// System.out.println("3 ------------------------------------");
        // Llena la línea con los espacios vacíos y segmentos generados
        for (int i = 0; i < segments; i++) {
            for (int j = 0; j < emptySpaces[i]; j++) {
                if (index < size) {
                    line[index++].setFilled(false);
                }
            }
            for (int j = 0; j < segmentSizes[i]; j++) {
                if (index < size) {
                    line[index++].setFilled(true);
                }
            }
// System.out.println("Segment " + i + ": " + segmentSizes[i] + " filled cells, " + emptySpaces[i] + " empty cells");
        }
// System.out.println("4 ------------------------------------");   
        // Llena el espacio vacío final
        for (int j = 0; j < emptySpaces[segments]; j++) {
            if (index < size) {
            line[index++].setFilled(false);
            }
        }
// System.out.println("Final empty space: " + emptySpaces[segments] + " empty cells");

// System.out.println("=====================================");
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

