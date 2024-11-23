package mp.game.graphicView;

import mp.game.Player;
import mp.mappings.*;
import mp.utils.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class GraphicMode extends JFrame {
    private JPanel gridPanel;
    private JPanel controlPanel;
    private JPanel mainPanel;
    private JPanel topNumbersPanel;
    private JPanel leftNumbersPanel;
    private JToggleButton[][] cells;
    private final int GRID_SIZE;
    private final int CELL_SIZE = 30;
    private final Color FILLED_COLOR = Color.BLACK;
    private final Color EMPTY_COLOR = Color.WHITE;
    private final Color MARKED_COLOR = Color.LIGHT_GRAY;
    private Nonogram nonogram;
    private Player player;

    public GraphicMode(Nonogram nonogram, Player player) {
        this.nonogram = nonogram;
        this.player = player;
        this.GRID_SIZE = nonogram.getSize();
        setTitle("Nonogram");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        initializeComponents();
        pack();
        setLocationRelativeTo(null);
    }

    private void initializeComponents() {
        mainPanel = new JPanel(new BorderLayout(10, 10));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Create the grid with numbers
        createGridWithNumbers();

        // Create control panel
        createControlPanel();

        // Add panels to main panel
        mainPanel.add(topNumbersPanel, BorderLayout.NORTH);
        mainPanel.add(leftNumbersPanel, BorderLayout.WEST);
        mainPanel.add(gridPanel, BorderLayout.CENTER);
        mainPanel.add(controlPanel, BorderLayout.SOUTH);

        add(mainPanel);
    }

    private void createGridWithNumbers() {
        // Create top numbers panel
        topNumbersPanel = new JPanel(new GridLayout(2, GRID_SIZE));
        for (int i = 0; i < 2 * GRID_SIZE; i++) {
            JLabel label = new JLabel("1", SwingConstants.CENTER);
            topNumbersPanel.add(label);
        }

        // Create left numbers panel
        leftNumbersPanel = new JPanel(new GridLayout(GRID_SIZE, 2));
        for (int i = 0; i < GRID_SIZE * 2; i++) {
            JLabel label = new JLabel("1", SwingConstants.RIGHT);
            label.setBorder(BorderFactory.createEmptyBorder(0, 5, 0, 5));
            leftNumbersPanel.add(label);
        }

        // Create grid panel
        gridPanel = new JPanel(new GridLayout(GRID_SIZE, GRID_SIZE));
        cells = new JToggleButton[GRID_SIZE][GRID_SIZE];

        for (int row = 0; row < GRID_SIZE; row++) {
            for (int col = 0; col < GRID_SIZE; col++) {
                cells[row][col] = createCell(row, col);
                gridPanel.add(cells[row][col]);
            }
        }
    }

    private JToggleButton createCell(int row, int col) {
        JToggleButton cell = new JToggleButton();
        cell.setPreferredSize(new Dimension(CELL_SIZE, CELL_SIZE));
        cell.setBackground(EMPTY_COLOR);
        cell.setBorderPainted(true);
        cell.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (SwingUtilities.isLeftMouseButton(e)) {
                    cell.setBackground(cell.isSelected() ? FILLED_COLOR : EMPTY_COLOR);
                    nonogram.markCell(row, col);
                } else if (SwingUtilities.isRightMouseButton(e)) {
                    cell.setBackground(MARKED_COLOR);
                    cell.setSelected(false);
                    nonogram.markHollowCell(row, col);
                }
                updateGameState();
            }
        });
        return cell;
    }

    private void createControlPanel() {
        controlPanel = new JPanel();
        JButton checkButton = new JButton("Check");
        JButton resetButton = new JButton("Reset");

        checkButton.addActionListener(e -> checkGameState());
        resetButton.addActionListener(e -> resetGrid());

        controlPanel.add(checkButton);
        controlPanel.add(resetButton);
    }

    private void resetGrid() {
        for (int row = 0; row < GRID_SIZE; row++) {
            for (int col = 0; col < GRID_SIZE; col++) {
                cells[row][col].setSelected(false);
                cells[row][col].setBackground(EMPTY_COLOR);
            }
        }
    }

    private void checkGameState() {
        if (nonogram.isSolved()) {
            JOptionPane.showMessageDialog(this, "Congratulations " + player.getName() + ", you have solved the nonogram!");
        } else {
            JOptionPane.showMessageDialog(this, "The nonogram is not solved yet.");
        }
    }

    private void updateGameState() {
        // Update the game state based on the current state of the cells
        // This method can be expanded to include more game logic
    }

    public static void main(String[] args) {
        Nonogram nonogram = new Nonogram(10); // Example size
        Player player = new Player("Player1");
        SwingUtilities.invokeLater(() -> {
            GraphicMode game = new GraphicMode(nonogram, player);
            game.setVisible(true);
        });
    }
}