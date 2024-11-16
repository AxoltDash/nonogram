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
        // Print the title
        Colors.title("Welcome to NONOGRAM (Terminal mode)", Colors.CYAN, Colors.MAGENTA);
    
        // Build the select label
        StringBuilder sb = new StringBuilder();
        sb.append(Colors.hiToString("Select a level (1, 2, or 3):", Colors.CYAN));
        sb.append("\n");
        for (int i = 1; i <= 3; i++) {
            sb.append(Colors.hiToString("[", Colors.WHITE));
            sb.append(Colors.hiToString(i, Colors.RED));
            sb.append(Colors.hiToString("] - Level " + i + " ("+ i*5 + " x " + i*5 +").", Colors.WHITE));
            sb.append("\n");
        }
        String selectLabel = sb.toString();

        // Ask for the level
        int level = ConsoleGets.getInt(selectLabel, Colors.toString("Invalid level, try again", Colors.RED), 1, 3);
    }
}
    