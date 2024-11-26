package mp.game;

import javax.swing.SwingUtilities;

import mp.game.graphicView.GraphicMode;
import mp.game.terminalView.TerminalMode;
import mp.mappings.Nonogram;

/**
 * The Game class represents a game of Nonogram.
 * It stores the player, nonogram, and terminal mode.
 */
public class Game {
    private boolean hardMode;
    private Player player;
    private Mode mode;
    private GraphicMode graphic;
    private Nonogram nonogram;

    /*
     * Constructor
     * 
     * @param size        Size of the nonogram
     * @param colorFormat Color format for the terminal
     * @param name        Name of the player
     */
    public Game(boolean graphicMode, int size, String colorFormat, String name) {
        this.player = new Player(name);
        this.nonogram = new Nonogram(size);

        if (size == 5 || size == 10) {
            this.hardMode = false;
        } else {
            this.hardMode = true;
            player.setHints(0);
        }

        if (graphicMode) {
            if (player.getName().equals("admin")) {
                System.out.println("=== Cheat activated, omg! ===");
                nonogram.printNonogramSolution();
                System.out.println("=============================");
            }
            SwingUtilities.invokeLater(() -> {
                this.graphic = new GraphicMode(size, nonogram, player);
                this.graphic.setVisible(true);
            });
        } else {
            this.mode = new TerminalMode(hardMode, colorFormat);
        }
    }

    /*
     * Start the game
     */
    public void startGame() {
        boolean gameContinue = true;
        int option;
        do {
            option = mode.showAndAsk(hardMode, nonogram, player);

            switch (option) {
                case -1:
                    if ("admin".equals(player.getName())) {
                        System.out.println("=== Cheat activated, omg! ===");
                        nonogram.printNonogramSolution();
                        System.out.println("=============================");
                    }
                    break;
                case 1:
                    gameContinue = mode.markCell(false, nonogram, player);
                    break;
                case 2:
                    gameContinue = mode.markCell(true, nonogram, player);
                    break;
                case 3:
                    // I know this is not the best way to do it :p
                    if (!hardMode) {
                        if (player.getHints() > 0) {
                            if (mode.askForHint(nonogram)) {
                                player.useHint();
                            }
                        } else {
                            mode.printNoHints();
                        }
                    }
                    break;
                default:
                    gameContinue = false;
                    break;
            }

            if (nonogram.isSolved()) {
                gameContinue = false;
            }

        } while (gameContinue);
        mode.printEndGame(nonogram, player);
    }
}
