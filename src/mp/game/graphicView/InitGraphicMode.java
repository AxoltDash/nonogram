package mp.game.graphicView;

import javax.swing.*;
import java.awt.*;

import mp.game.Player;
import mp.mappings.Nonogram;

public class InitGraphicMode {
    private int size;
    private boolean hardMode;
    private Player player;
    private Nonogram nonogram;

    public InitGraphicMode() {
        askName();
        selectDifficulty();

        nonogram = new Nonogram(size);

        if (size == 5 || size == 10) {
            hardMode = false;
        } else {
            hardMode = true;
            player.setHints(0);
        }
    }
    //Ask the name of the player
    public void askName() {
        String name = JOptionPane.showInputDialog("Enter your name: ");
        player = new Player(name);
    }

    public void selectDifficulty() {
        String[] options = {"Easy (5x5)", "Medium (10x10)", "Hard (15x15) no HINTS"};

        int selection = JOptionPane.showOptionDialog(
                null,
                "Select a difficulty",
                "Nonogram",
                JOptionPane.DEFAULT_OPTION,
                JOptionPane.QUESTION_MESSAGE,
                null,
                options,
                options[0]
        );

        this.size = switch (selection) {
            case 0 -> 5;
            case 1 -> 10;
            case 2 -> 15;
            default -> 5;
        };
    }

    //test prints
    public void printTest() {
        System.out.println(this.size);
        System.out.println(this.hardMode);
        System.out.println(this.player.getName());
    }
}
