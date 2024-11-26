package mp.game.graphicView;

import javax.swing.*;

public class InitGraphicMode {
    
    private int size;
    private String name;

    public InitGraphicMode() {
        JOptionPane.showMessageDialog(null, "If you want to be able to see the response of a Nonogram, you need to put your name as \"admin\" and check the terminal for the response.");
        askName();
        selectDifficulty();
    }
    
    //Ask the name of the player
    private void askName() {
        name = JOptionPane.showInputDialog("Enter your name: ");
    }

    //Select the difficulty of the game
    private void selectDifficulty() {
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

    public int getSize() {
        return size;
    }

    public String getName() {
        return name;
    }
}
