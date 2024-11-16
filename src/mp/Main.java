package mp;

import mp.utils.*;

// =========================================================================
//  __    _  _______  __    _  _______  _______  ______    _______  __   __ 
// |  |  | ||       ||  |  | ||       ||       ||    _ |  |   _   ||  |_|  |
// |   |_| ||   _   ||   |_| ||   _   ||    ___||   | ||  |  |_|  ||       |
// |       ||  | |  ||       ||  | |  ||   | __ |   |_||_ |       ||       |
// |  _    ||  |_|  ||  _    ||  |_|  ||   ||  ||    __  ||       ||       |
// | | |   ||       || | |   ||       ||   |_| ||   |  | ||   _   || ||_|| |
// |_|  |__||_______||_|  |__||_______||_______||___|  |_||__| |__||_|   |_|
// =========================================================================
// Writed by: Pérez Servin Darshan Israel
// Alias: AxoltDash
// =========================================================================

public class Main {

    public static void main(String[] args) {
        int option;
        String selectLabel;
        String colorFormat;
        String dificultyLabel;

        //Build a fancy string to show the options
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
                    dificultyLabel = "Level Hard";
                    break;
                default: colorFormat = Colors.BLUE; 
                    dificultyLabel = "Leave";
                    break;
            }
            sb.append(Colors.hiToString("[", Colors.WHITE));
            sb.append(Colors.hiToString((i <= 3) ? i : 0, colorFormat));
            sb.append(Colors.hiToString("] -" + dificultyLabel + ((i <= 3) ? " (" + i*5 + " x " + i*5 + ")" : ""), Colors.WHITE));
            sb.append("\n");
        }  
        selectLabel = sb.toString(); 


        // Print the title
        Colors.title("Welcome to NONOGRAM (Terminal mode)", Colors.CYAN, Colors.MAGENTA);

        //Menu Loop Terminal MODE
        do {
            // Ask for the level
            option = ConsoleGets.getInt(selectLabel, Colors.toString("Invalid level, try again", Colors.RED), 0, 3);
            Colors.hiprint("You select: ", Colors.CYAN);
            switch (option) {
                case 1:
                    colorFormat = Colors.GREEN;
                    Colors.hiprintln("Level Easy", colorFormat);
                    break;
                case 2:
                    colorFormat = Colors.YELLOW;
                    Colors.hiprintln("Level Medium", colorFormat);
                    break;
                case 3:
                    colorFormat = Colors.RED;
                    Colors.hiprintln("Level Hard", colorFormat);
                    break;
                default:
                    Colors.hiprintln("Leave... ¡SEE YOU LATER!", Colors.BLUE);
                    break;
            }
        } while (option != 0);
    }
}
    