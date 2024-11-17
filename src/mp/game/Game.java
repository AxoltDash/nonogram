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
        }

        //Terminal mode build
        this.terminal = new TerminalMode(hardMode, colorFormat);
        
        startGame();
    }

    public void startGame() {
        boolean gameContinue = true;
        int option;
        do {
            option = terminal.showAndAsk(hardMode, nonogram, player);

            switch (option) {
                case -1:
                    System.out.println("Developer mode activated, omg!");
                    nonogram.printState();
                    nonogram.printNonogramSolution();
                    nonogram.printHorizontalHints();
                    nonogram.printVerticalHints();
                case 1:
                    gameContinue = terminal.markCell(nonogram, player);    
                    break;
                case 2:
                    if (!hardMode) {
                        if (player.getHints() > 0) {
                            player.useHint();
                            terminal.askForHint(nonogram);
                        } else {
                            terminal.printNoHints();
                        }
                    }
                    break;
                default:
                    return;
            }
            if (nonogram.isSolved()) {
                gameContinue = false;
            }
        } while (gameContinue);
        terminal.printEndGame(player);
    }
}
