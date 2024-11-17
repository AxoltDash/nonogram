package mp.game;

import mp.game.Player;
import mp.game.view.TerminalMode;
import mp.utils.*;

public class Game {
    private int size;
    private Player player;
    private TerminalMode terminal;

    public Game(int size, String colorFormat, String name) {
        this.size = size;
        this.player = new Player(name);

        //Terminal mode build
        this.terminal = new TerminalMode(size, colorFormat);
    }
    public void printMatrix() {
        
    }

    public void startGame() {
    }
}
