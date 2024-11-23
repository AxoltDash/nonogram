package mp;

import mp.utils.*;

import javax.swing.SwingUtilities;

import mp.game.Game;
import mp.game.Player;
import mp.game.graphicView.GraphicMode;
import mp.game.graphicView.InitGraphicMode;
import mp.game.terminalView.StringMaker;
import mp.mappings.Nonogram;

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
        int mode;
        String modeMenu = StringMaker.modeString();

        mode = ConsoleGets.getInt(modeMenu, Colors.toString("Invalid mode, try again", Colors.RED), 0, 1);
//                         __    __                           __       
// .-----.----.---.-.-----|  |--|__.----.  .--------.-----.--|  .-----.
// |  _  |   _|  _  |  _  |     |  |  __|  |        |  _  |  _  |  -__|
// |___  |__| |___._|   __|__|__|__|____|  |__|__|__|_____|_____|_____|
// |_____|          |__|
// ==========================================================================
        if (mode == 1){
            InitGraphicMode init = new InitGraphicMode();
            init.printTest();

// __                      __             __                      __       
// |  |_.-----.----.--------|__.-----.---.-|  |  .--------.-----.--|  .-----.
// |   _|  -__|   _|        |  |     |  _  |  |  |        |  _  |  _  |  -__|
// |____|_____|__| |__|__|__|__|__|__|___._|__|  |__|__|__|_____|_____|_____|
// ==========================================================================
        } else {
            int option;
            String colorFormat = "";

            //Build a fancy String to show the options
            String menu = StringMaker.stringBuildMenu();
            
            // Ask player name
            Colors.println("If you want to be able to see the response of a Nonogram, you need to put your name as \"admin\" and within the game write the option \"-1\"", Colors.LOW_INTENSITY + Colors.CYAN);
            String name = ConsoleGets.getString(Colors.hiToString("Enter your name: ", Colors.CYAN));
            
            //Main menu LOOP
            do {
                // Print the title
                Colors.title("Welcome to NONOGRAM \"" + name + "\" - (Terminal mode)", Colors.CYAN, Colors.MAGENTA);

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
                        break;
                    default:
                        Colors.hiprintln("Leave... ¡SEE YOU LATER!", Colors.BLUE);
                        break;
                }

                if (option != 0) {
                    Game game = new Game(option*5, colorFormat, name);
                    game.startGame();
                }

            } while (option != 0);
        }
    }
}
    