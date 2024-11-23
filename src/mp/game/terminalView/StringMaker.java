package mp.game.terminalView;

import mp.utils.Colors;

public class StringMaker {
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
    public static String stringBuildMenuInGame(boolean hardMode, String colorFormat) {
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
            sb.append(Colors.hiToString((i <= uwu -1) ? i : 0, colorFormat));
            sb.append(Colors.hiToString("] - " + label));
            sb.append("\n");
        }  
        return sb.toString();
    }

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

    public static String modeString() {
        StringBuilder sb = new StringBuilder();
        sb.append(Colors.hiToString("Select the mode you want to play: "));
        sb.append("\n");
        sb.append(Colors.hiToString("0. Terminal mode", Colors.CYAN));
        sb.append("\n");
        sb.append(Colors.hiToString("1. Graphical mode", Colors.CYAN));
        return sb.toString();
    }
}