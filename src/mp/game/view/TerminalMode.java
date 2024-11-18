package mp.game.view;

import mp.game.Player;
import mp.mappings.*;
import mp.utils.*;

public class TerminalMode {
    private String colorFormat;
    private String menu;

    public TerminalMode (boolean hardMode, String colorFormat) {
        this.colorFormat = colorFormat;
        stringBuildMenuInGame(hardMode);
    }

// _  _ ____ ___ _  _ ____ ___  ____ 
// |\/| |___  |  |__| |  | |  \ [__  
// |  | |___  |  |  | |__| |__/ ___]
// ==================================

    /*
     * Method to show the menu and ask the user for an option.
     * 
     * @param hardMode Boolean to know if the game is in hard mode.
     * @param n Nonogram object.
     * @param player Player object.
     * @return The option selected by the user.
     */
    public int showAndAsk (boolean hardMode, Nonogram n, Player player) {
        printGameState(n, player);

        // We obtain the option from the user
        int option = ConsoleGets.getInt(
            Colors.hiToString(menu, colorFormat), 
            Colors.hiToString("Invalid option, try again", Colors.RED), 
            -1, hardMode ? 2 : 3);
        
        return option;
    }

    /*
     * Method to mark a cell in the nonogram.
     * 
     * @param hollow Boolean to know if you want to mark a cell or a hollow cell.
     * @param n Nonogram object.
     * @param player Player object.
     * @return A boolean to know if the game continues.
     */
    public boolean markCell(boolean hollow, Nonogram n, Player player) {
        int size = n.getSize();
        int points;

        Colors.hiprintln("(If you put a \"0\", the action will be cancelated)", colorFormat);
        // We obtain the coordinates from the user
        int[] coordinates = coords(size);

        // If the user wants to cancel the action
        if (coordinates[0] == 0 || coordinates[1] == 0) {
            Colors.hiprintln("Action canceled", colorFormat);
            return true;
        }
        
        boolean marked;
        if (hollow) {
            marked = n.markHollowCell(coordinates[0] - 1, coordinates[1] - 1);
        } else {
            marked = n.markCell(coordinates[0] - 1, coordinates[1] - 1);
        }

        if (marked) {
            Colors.hiprint("Cell marked, Good job " + player.getName() + "!", colorFormat);
            if (n.isSolved()) {
                Colors.hiprintln("Congratulations " + player.getName() + ", you have solved the nonogram!", colorFormat);
                return false;
            } else {
                points = hollow ? 50 : 100;
                Colors.hiprintln(" +" + points + " points");
                player.addScore(points);
            }

        } else {
            Colors.hiprintln("Cell can't be marked, srry", colorFormat);
            player.loseLife();
            if (player.getLives() == 0) {
                printDeadMessage();
                return false;
            }
        }
        return true;
    }

    /*
     * Method to ask for a hint.
     * 
     * @param player Player object.
     */
    public boolean askForHint(Nonogram n) {    
        if (n.askForHint()) {
            Colors.hiprintln("Hint used! Cheeck the your Nonogram", colorFormat);
            if (n.isSolved()) {
                Colors.hiprintln("Congratulations, you have solved the nonogram with a HINT xd!", colorFormat);
            }
            return true;
        } else {
            Colors.hiprintln("Hint can't be used, don't have any cell to mark", colorFormat);
            return false;
        }
    }

    public int[] coords(int size){
        return ConsoleGets.getCoordinates(size,
            Colors.hiToString("Enter the column number \"" + 1 + " - " + size +"\" "+ Colors.hiToString("(columna)", colorFormat), Colors.CYAN), 
            Colors.hiToString("Enter the row number \"" + 1 + " - " + size + "\" "+ Colors.hiToString("(fila)", colorFormat), Colors.CYAN), 
            Colors.hiToString("Invalid coordinates, try again", Colors.RED));
    }
    
// ____ ___ ____ _ _  _ ____ ____ 
// [__   |  |__/ | |\ | | __ [__  
// ___]  |  |  \ | | \| |__] ___]
// ===============================

    /*
     * Method to build the menu for the game.
     * If the size of the nonogram is 5 or 10, the menu will have 3 options.
     * Otherwise, the menu will have 2 options (no hints).
     * 
     * @param hardMode Boolean to know if the game is in hard mode.
     * @return A string with the menu.
     */
    public void stringBuildMenuInGame(boolean hardMode) {
        String label;
        StringBuilder sb = new StringBuilder();
        
        sb.append(Colors.hiToString("Select an option:", Colors.CYAN));
        sb.append("\n");

        int uwu = hardMode ? 3 : 4; // If hardMode is true, the menu will have 3 options, otherwise 4

        for (int i = 1; i <= uwu; i++) {
            if (i == 3 && hardMode){
                i++;
            }
            switch (i) {
                case 1:
                    label = "Mark a cell (x,y)";
                    break;
                case 2:
                    label = "Mark a cell like hollow (x,y)";
                    break;
                case 3:
                    label = "Ask for a hint";
                    break;
                default:
                    label = "Leave actual game";
                    break;
            }
            sb.append(Colors.hiToString("["));
            sb.append(Colors.hiToString((i <= uwu -1) ? i : 0, this.colorFormat));
            sb.append(Colors.hiToString("] - " + label));
            sb.append("\n");
        }  
        menu = sb.toString();
    }
    
// ___  ____ _ _  _ ___ ____ 
// |__] |__/ | |\ |  |  [__  
// |    |  \ | | \|  |  ___]   
// ==========================

    public void printGameState(Nonogram n, Player player) {
        int size = n.getSize();
        String line = Colors.hiToString("|", colorFormat);

        int[][] horizontalHints = n.getHorizontalHints();
        int[][] verticalHints = n.getVerticalHints();
        Cell[][] nonogram = n.getNonogram();

        System.out.println();
        Colors.hiprint("Your available lives are: ");
        Colors.hiprintln(player.getLives() + " lives", colorFormat);
        Colors.hiprint("Your available hints are: ");
        Colors.hiprintln(player.getHints() + " hints", colorFormat);
        
        // Column headers
        System.out.print("\n    ");
        for (int i = 1; i <= size; i++) {
            // String columnHeader = String.format("%3d", i); // Create column number string with a fixed width of 3 characters
            // System.out.print(columnHeader); // Print the column number string

            Colors.hiprint(String.format("%3d", i));
        }
        System.out.println();    
        // Print each row with its hints
        for (int i = 0; i < size; i++) {
            // System.out.printf("%3d " + line, i + 1); // Print row number with a fixed width of 3 characters
            Colors.hiprint(String.format("%3d " + line, i + 1));

            // Print each cell
            for (int j = 0; j < size; j++) {
                Cell cell = nonogram[i][j];
                if (cell.isMarked()) {
                    Colors.hiprint(" ■ ");
                } else if (cell.isHollowMarked()) {
                    Colors.hiprint(" / ");
                } else {
                    Colors.hiprint(" . ");
                }
            }

            System.out.print(line +" ");
            for (int p : horizontalHints[i]) {
                System.out.printf("%2d ", p); // Print each row hint with a fixed width of 2 characters
            }
            System.out.println();
        }    
        // Separator and column hints
        System.out.print("    ");
        for (int i = 0; i < size; i++) {
            Colors.hiprint("---", colorFormat);
        }
        Colors.hiprint("--", colorFormat);
        System.out.println();

        // Print column hints
        int uwu = 0;
        for (int[] hints : verticalHints) {
            if (hints.length > uwu) {
                uwu = hints.length;
            }
        }
        for (int i = 0; i < uwu; i++) {    
            System.out.print("     ");
            for (int j = 0; j < size; j++) {
                if (i < verticalHints[j].length) {
                    System.out.printf("%2d ", verticalHints[j][i]);
                } else {
                    System.out.print("   ");
                }
            }
            System.out.println();
        }
        System.out.println();
    }

    public void printDeadMessage() {
        Colors.hiprintln("_  _ ____ _  _ . ____ ____    ___  ____ ____ ___      .-.", colorFormat);
        Colors.hiprintln("\\_/  |  | |  | ' |__| |__/    |  \\ |___ |__| |  \\    (0.0)", colorFormat);
        Colors.hiprintln(" |   |__| |__|   |  | |  \\    |__/ |___ |  | |__/  '=.|m|.='", colorFormat);
        Colors.hiprintln("-------------------------------------------------  .='`\"`'=.", colorFormat);                    
    }

    public void printNoHints() {
        Colors.hiprint(" _._     _,-'\"\"`-._");
        Colors.hiprintln("   - You don't have any hints!", colorFormat);
        Colors.hiprintln("(,-.`._,'(       |\\`-/|");
        Colors.hiprintln("    `-.-' \\ )-`( , o o)");
        Colors.hiprintln("          `-    \\`_`\"'-");
        Colors.hiprintln("-----------------------------------", colorFormat);
    }

    public void printEndGame(Player player) {
        Colors.hiprintln("|\\---/|", colorFormat);
        Colors.hiprintln("| ^_^ |   Thanks for PLAY \"" + player.getName() + "\" !!!", colorFormat);
        Colors.hiprintln(" \\_`_/-..----.", colorFormat);
        Colors.hiprintln(" ___/ `   ' ,\"\"+ \\", colorFormat);
        Colors.hiprintln("(__...'   __\\    |`.___.';", colorFormat);
        Colors.hiprintln("(_,...'(_,.`__)/'.....+", colorFormat);
        System.out.println();
        Colors.hiprintln("Your score was: " + player.getScore()); 
        Colors.hiprintln("Your lives were: " + player.getLives());
        Colors.hiprintln("Your hints were: " + player.getHints());
        Colors.hiprintln("-----------------------------------", colorFormat);
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
