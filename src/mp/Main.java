package mp;

import mp.utils.*;
import mp.game.Game;
import mp.mappings.Nonogram;

import mp.game.view.TerminalMode;

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
        String colorFormat = "";

        //Build a fancy String to show the options
        String menu = TerminalMode.stringBuildMenu();

        // Print the title
        Colors.title("Welcome to NONOGRAM (Terminal mode)", Colors.CYAN, Colors.MAGENTA);
        
        // Ask player name
        String name = ConsoleGets.getString(Colors.hiToString("Enter your name: ", Colors.CYAN));
        
        //Menu LOOP
        do {
            // Ask for the level
            option = ConsoleGets.getInt(menu, Colors.toString("Invalid level, try again", Colors.RED), 0, 3);
            Colors.hiprint("You select: ", Colors.CYAN);
            switch (option) {
                case 1:
                    colorFormat = Colors.GREEN;
                    Colors.hiprintln("Level Easy", colorFormat);
                    // Nonogram juego = new Nonogram(5);
                    // juego.generateNonogram();
                    // juego.mostrarEstado();
                    // juego.printNonogramSolution();
                    // juego.printVerticalHints();

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
    