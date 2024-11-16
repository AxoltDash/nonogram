package mp.game;

import mp.game.Player;
import mp.utils.*;

public class GameTerminalMode {
    private int size;
    private String colorFormat;
    private String menu;
    private Player player;

    public GameTerminalMode(int size, String colorFormat, String name) {
        this.size = size;
        this.colorFormat = colorFormat;
        this.player = new Player(name);
        this.menu = stringBuildMenu();

    }
    // .___              .__         ,    
    // [__  _.._  _.  .  [__)._.*._ -+- __
    // |   (_][ )(_.\_|  |   [  |[ ) | _) 
    // =========== ._| =============================

    /*
     * Method to build the menu for the game.
     * If the size of the nonogram is 5 or 10, the menu will have 3 options.
     * Otherwise, the menu will have 2 options (HARD MODE).
     * 
     * @return A string with the menu.
     */
    public String stringBuildMenu () {
        int x = 2;
        String label;
        StringBuilder sb = new StringBuilder();
        
        sb.append(Colors.hiToString("Select an option:", Colors.CYAN));
        sb.append("\n");

        if (this.size == 5 || this.size == 10) {
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
        return sb.toString(); 
    }
    public void printMatrix() {
        Colors.hiprint("Your avaliable lives are: ");
        Colors.hiprintln(player.getLives() + " lives", colorFormat);
        Colors.hiprint("Your avaliable hints are: ");
        Colors.hiprintln(player.getHints() + " hints", colorFormat);
    }

    public void startGame() {
    }
}
