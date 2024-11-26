package mp.game;

import mp.mappings.Nonogram;

/**
 * The Mode interface defines the methods required for different game modes in the Nonogram game.
 */
public interface Mode {
    /**
     * Displays the game state and asks the player for their next move.
     *
     * @param hardMode Indicates if the game is in hard mode.
     * @param n The Nonogram puzzle.
     * @param player The player making the move.
     * @return An integer representing the player's choice.
     */
    public int showAndAsk(boolean hardMode, Nonogram n, Player player);

    /**
     * Marks a cell in the Nonogram puzzle.
     *
     * @param hollow Indicates if the cell should be marked as hollow.
     * @param n The Nonogram puzzle.
     * @param player The player making the move.
     * @return True if the cell was successfully marked, false otherwise.
     */
    public boolean markCell(boolean hollow, Nonogram n, Player player);

    /**
     * Asks the player if they want a hint.
     *
     * @param n The Nonogram puzzle.
     * @return True if the player wants a hint, false otherwise.
     */
    public boolean askForHint(Nonogram n);

    /**
     * Prints a message indicating that no hints are available.
     */
    public void printNoHints();

    /**
     * Prints the end game message.
     *
     * @param n The Nonogram puzzle.
     * @param player The player who played the game.
     */
    public void printEndGame(Nonogram n, Player player);
}
