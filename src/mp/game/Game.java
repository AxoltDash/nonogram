package mp.game;

import mp.game.view.TerminalMode;
import mp.mappings.Nonogram;

public class Game {
    private boolean hardMode;
    private Player player;
    private TerminalMode terminal;
    private Nonogram nonogram;

    public Game(int size, String colorFormat, String name) {
        this.player = new Player(name);
        this.nonogram = new Nonogram(size);

        if (size == 5 || size == 10) {
            this.hardMode = false;
        } else {
            this.hardMode = true;
            player.setHints(0);
        }

        //Terminal mode build
        this.terminal = new TerminalMode(hardMode, colorFormat);
        
        // startGame();
    }

    public void startGame() {
        boolean gameContinue = true;
        int option;
        do {
            option = terminal.showAndAsk(hardMode, nonogram, player);

            switch (option) {
                case -1:
                    System.out.println("=== Cheat activated, omg! ===");
                    nonogram.printNonogramSolution();
                    System.out.println("=============================");
                    break;
                case 1:
                    gameContinue = terminal.markCell(false, nonogram, player);
                    break;
                case 2:
                    gameContinue = terminal.markCell(true, nonogram, player);
                    break;
                case 3:
                    // I know this is not the best way to do it :p
                    if (!hardMode) {
                        if (player.getHints() > 0) {
                            if (terminal.askForHint(nonogram)) {
                                player.useHint();
                            }
                        } else {
                            terminal.printNoHints();
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
        terminal.printEndGame(nonogram, player);
    }
}
