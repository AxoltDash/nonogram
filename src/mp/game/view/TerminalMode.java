package mp.game.view;

import mp.game.Player;
import mp.mappings.Cell;
import mp.utils.Colors;

public class TerminalMode {
    
    private int size;
    private String colorFormat;
    private String menu;

    public TerminalMode (int size, String colorFormat) {
        this.colorFormat = colorFormat;
        this.size = size;
    }
// ____ ___ ____ _ _  _ ____ ____ 
// [__   |  |__/ | |\ | | __ [__  
// ___]  |  |  \ | | \| |__] ___]
// ===============================

    /*
     * Method to build the menu for the game.
     * If the size of the nonogram is 5 or 10, the menu will have 3 options.
     * Otherwise, the menu will have 2 options (HARD MODE).
     * 
     * @return A string with the menu.
     */
    public void stringBuildMenuInGame() {
        int x = 2;
        String label;
        StringBuilder sb = new StringBuilder();
        
        sb.append(Colors.hiToString("Select an option:", Colors.CYAN));
        sb.append("\n");

        if (size == 5 || size == 10) {
            x = 3;
        }

        for (int i = 1; i <= x; i++) {
            if (i == 2 && x == 2){
                i++;
            }
            switch (i) {
                case 1:
                    label = "Mark a cell (x,y)";
                    break;
                case 2:
                    label = "Ask for a hint";
                    break;
                default:
                    label = "Leave";
                    break;
            }
            sb.append(Colors.hiToString("["));
            sb.append(Colors.hiToString((i <= x) ? i : 0, this.colorFormat));
            sb.append(Colors.hiToString("] - " + label));
            sb.append("\n");
        }  
        menu = sb.toString();
    }
    
// ___  ____ _ _  _ ___ ____ 
// |__] |__/ | |\ |  |  [__  
// |    |  \ | | \|  |  ___]   
// ==========================

    public void mostrarEstado(int[][] horizontalHints, int[][] verticalHints, Cell[][] nonogram, Player player) {
        Colors.hiprint("Your avaliable lives are: ");
        Colors.hiprintln(player.getLives() + " lives", colorFormat);
        Colors.hiprint("Your avaliable hints are: ");
        Colors.hiprintln(player.getHints() + " hints", colorFormat);
        
        System.out.println("Estado del juego:");
        // Encabezado de columnas
        System.out.print("lalaa");
        for (int i = 1; i <= size; i++) {
            System.out.printf("%3d", i); // Imprime el número de columna con un ancho fijo de 3 caracteres
        }
        System.out.println();    
        // Impresión de cada fila con sus pistas
        for (int i = 0; i < size; i++) {
            System.out.printf("%3d | ", i + 1); // Imprime el número de fila con ancho fijo de 3 caracteres
            for (int j = 0; j < size; j++) {
                Cell cell = nonogram[i][j];
                System.out.print((cell.isMarked() ? "■" : cell.isFilled() ? "X" : ".") + "  "); // Espacio adicional para separación uniforme
            }
            System.out.print("| ");
            for (int p : horizontalHints[i]) {
                System.out.printf("%2d ", p); // Imprime cada pista de fila con un ancho fijo de 2 caracteres
            }
            System.out.println();
        }    
        // Separador y pistas de columnas
        System.out.print("    ");
        for (int i = 0; i < size; i++) {
            System.out.print("----");
        }
        System.out.println();    
        System.out.print("     ");
        for (int i = 0; i < size; i++) {
            if (verticalHints[i].length > 0) {
                System.out.printf("%2d ", verticalHints[i][0]);
            } else {
                System.out.print(" 0 ");
            }
        }
        System.out.println("\n");
    }

    public void printDeadMessage() {
        Colors.hiprintln("_  _ ____ _  _ . ____ ____    ___  ____ ____ ___      .-.", colorFormat);
        Colors.hiprintln("\\_/  |  | |  | ' |__| |__/    |  \\ |___ |__| |  \\    (0.0)", colorFormat);
        Colors.hiprintln(" |   |__| |__|   |  | |  \\    |__/ |___ |  | |__/  '=.|m|.='", colorFormat);
        Colors.hiprintln("-------------------------------------------------  .='`\"`'=.", colorFormat);                    
    }

// ____ ____ ____    _  _ ____ _ _  _    ____ _    ____ ____ ____ 
// |___ |  | |__/    |\/| |__| | |\ |    |    |    |__| [__  [__  
// |    |__| |  \    |  | |  | | | \|    |___ |___ |  | ___] ___]
// ===============================================================

    /*
     * Method to build the menu for the start. (ONLY MAIN)
     * 
     * @return A string with the menu.
     */
    public static String stringBuildMenu() {
        String colorFormat;
        String dificultyLabel;

        StringBuilder sb = new StringBuilder();
        sb.append(Colors.hiToString("Select a level (1, 2, or 3):", Colors.CYAN));
        sb.append("\n");
        for (int i = 1; i <= 4; i++) {
            switch (i) {
                case 1: colorFormat = Colors.GREEN;
                    dificultyLabel = "Level Easy";
                    break;
                case 2: colorFormat = Colors.YELLOW; 
                    dificultyLabel = "Level Medium";
                    break;
                case 3: colorFormat = Colors.RED; 
                    dificultyLabel = "Level Hard (NO HINTS)";
                    break;
                default: colorFormat = Colors.BLUE; 
                    dificultyLabel = "Leave";
                    break;
            }
            sb.append(Colors.hiToString("["));
            sb.append(Colors.hiToString((i <= 3) ? i : 0, colorFormat));
            sb.append(Colors.hiToString("] - " + dificultyLabel + ((i <= 3) ? " (" + i*5 + " x " + i*5 + ")" : "")));
            sb.append("\n");
        }  
        return sb.toString(); 
    }

}
