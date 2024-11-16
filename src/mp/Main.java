package mp;

import mp.utils.*;
import mp.game.GameTerminalMode;

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
        String dificultyLabel;
        String colorFormat = "";

        //Build a fancy String to show the options
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
        String menu = sb.toString(); 


        // Print the title
        Colors.title("Welcome to NONOGRAM (Terminal mode)", Colors.CYAN, Colors.MAGENTA);
        
        // Ask player name
        String name = ConsoleGets.getString(Colors.hiToString("Enter your name: ", Colors.CYAN));
        
        //Menu Loop Terminal MODE
        do {
            // Ask for the level
            option = ConsoleGets.getInt(menu, Colors.toString("Invalid level, try again", Colors.RED), 0, 3);
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

                    Colors.hiprintln("_  _ ____ _  _ . ____ ____    ___  ____ ____ ___      .-.", colorFormat);
                    Colors.hiprintln("\\_/  |  | |  | ' |__| |__/    |  \\ |___ |__| |  \\    (0.0)", colorFormat);
                    Colors.hiprintln(" |   |__| |__|   |  | |  \\    |__/ |___ |  | |__/  '=.|m|.='", colorFormat);
                    Colors.hiprintln("-------------------------------------------------  .='`\"`'=.", colorFormat);                    
                    break;
                default:
                    Colors.hiprintln("Leave... ¡SEE YOU LATER!", Colors.BLUE);
                    break;
            }

            if (option != 0) {
                GameTerminalMode game = new GameTerminalMode(option*5, colorFormat, name);
                game.startGame();
            }

        } while (option != 0);
    }
}
    