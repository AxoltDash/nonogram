package mp.game.graphicView;

import javax.swing.*;
import java.awt.*;
import mp.game.Player;
import mp.mappings.*;

public class GraphicMode extends JFrame {
    private JButton[][] buttonsNonogram;
    private JLabel jlLives;
    private JLabel jlHints;
    private JLabel jlScore;

    public GraphicMode(int size, Nonogram n, Player player) {
        setTitle("Nonogram");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(900,900);
        setLocationRelativeTo(null);

        this.buttonsNonogram = new JButton[size][size];
        this.jlLives = new JLabel("Lives: " + player.getLives());
        this.jlHints = new JLabel("Hints: " + player.getHints());
        this.jlScore = new JLabel("Score: " + player.getScore());

        createGraphic(size, n, player);
        System.out.println("Graphic mode not implemented yet");
    }

    private void createGraphic(int size, Nonogram n, Player player) {
        setLayout(new BorderLayout());
        JPanel jpanel = new JPanel();
        jpanel.setLayout(new GridLayout(size + 1, size + 1));
        jpanel.add(new JLabel());

        for (int i = 0; i < size; i++) {
            JPanel jpanelColumnHints = new JPanel();
            jpanelColumnHints.setLayout(new GridLayout(5, 1));
            jpanelColumnHints.setPreferredSize(new Dimension(50, 70));

            for (int j : n.getVerticalHints()[i]) {
                JLabel lbHint1 = new JLabel(String.valueOf(j), SwingConstants.CENTER);
                lbHint1.setFont(new Font("Arial", Font.PLAIN, 11));
                jpanelColumnHints.add(lbHint1);
            }

            jpanel.add(jpanelColumnHints);
        }

        for (int i = 0; i < size; i++) {
            JPanel jpanelRowHints = new JPanel();
            jpanelRowHints.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

            JLabel lbHint2 = new JLabel(reFormat(n.getHorizontalHints()[i]), SwingConstants.CENTER);
            lbHint2.setFont(new Font("Arial", Font.PLAIN, 11));
            
            jpanelRowHints.add(lbHint2);
            jpanel.add(jpanelRowHints);

            for (int j = 0; j < size ; j++){
                final int I = i;
                final int J = j;

                JButton b = new JButton();

                this.buttonsNonogram[i][j] = b;

                b.setPreferredSize(new Dimension(50,50));
                b.setBackground(Color.DARK_GRAY);
                b.addActionListener(e ->  managementCell(b, I, J, player, n));
                
                jpanel.add(b);
            }
        }

        JPanel jpanelControls = new JPanel();
        jpanelControls.setLayout(new GridLayout(1, 4));
        
        JButton jbHint = new JButton("Hint");
        jbHint.addActionListener(e -> askForHint(player, n));

        JButton jbExit = new JButton("Exit");
        jbExit.addActionListener(e -> System.exit(0));

        jlLives = new JLabel("Lives: " + player.getLives());
        jlHints = new JLabel("Hints: " + player.getHints());
        jlScore = new JLabel("Score: " + player.getScore());

        jpanelControls.add(jlLives);
        jpanelControls.add(jlHints);
        jpanelControls.add(jlScore);
        jpanelControls.add(jbHint);
        jpanelControls.add(jbExit);

        add(jpanel, BorderLayout.CENTER);
        add(jpanelControls, BorderLayout.SOUTH);
        updateGame(player, n);
    }

    private String reFormat(int[] h){
        StringBuilder sb = new StringBuilder();
        for (int i : h){
            sb.append(i).append(" ");
        }
        return sb.toString().trim();
    }

    private void managementCell(JButton b, int i, int j, Player player, Nonogram n) {
        String[] options = {"Filled", "Void", "Cancel"};
        int eleccion = JOptionPane.showOptionDialog(
            this,
            "Select cell state:",
            "Matk Cell",
            JOptionPane.DEFAULT_OPTION,
            JOptionPane.INFORMATION_MESSAGE,
            null,
            options,
            options[0]
        );
        
        boolean correct = true;
        
        if (eleccion == 0){
            correct = n.markCell(i, j);
            n.fillCells(i, j);
            player.addScore(100);
    
            updateGame(player, n);
        } else if (eleccion == 1) {
            correct = n.markHollowCell(i, j);
            n.fillCells(i, j);
            player.addScore(50);

            updateGame(player, n);
        } else {
            return;
        }

        if (!correct){
            player.loseLife();
            JOptionPane.showMessageDialog(this, "You have lost a life, you have " + player.getLives() + " lives left");
        }

        if (n.isSolved()){
            JOptionPane.showMessageDialog(this, "Congratulations"+ player.getName() +", you have solved the nonogram");
            
            System.exit(0);
        } else if (player.getLives() <= 0){
            JOptionPane.showMessageDialog(this, "You have lost all your lives, GAME OVER");
            
            System.exit(0);
        }               
    }

    public void askForHint(Player player, Nonogram n){
        if (player.getHints() > 0){
            try {
                if (n.askForHint()){
                    JOptionPane.showMessageDialog(this, "Correctly applied tread");
                    updateGame(player, n);
                } else {
                    JOptionPane.showMessageDialog(this, "The hint was not applied correctly");
                }
            } catch (NumberFormatException | ArrayIndexOutOfBoundsException e){
                JOptionPane.showMessageDialog(this, "There was an error while trying to get a hint");
            }
        } else {
            JOptionPane.showMessageDialog(this, "You don't have any hints left");
        }
    }

    private void updateGame(Player player, Nonogram n){
        jlLives.setText("Lives: " + player.getLives());
        jlHints.setText("Hints: " + player.getHints());
        jlScore.setText("Score: " + player.getScore());
        
        Cell[][] nonogram = n.getNonogram();

        for (int i = 0; i < n.getSize(); i++){
            for (int j = 0; j < n.getSize(); j++){
                if (nonogram[i][j].isMarked() && !nonogram[i][j].isHollowMarked()){
                    buttonsNonogram[i][j].setBackground(Color.BLACK);
                } else if (nonogram[i][j].isHollowMarked()){
                    buttonsNonogram[i][j].setBackground(Color.GRAY);
                } else {
                    buttonsNonogram[i][j].setBackground(Color.WHITE);
                }
            }
        }
    }
}