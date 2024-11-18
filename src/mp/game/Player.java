package mp.game;

/**
 * The Player class represents a player in the Nonogram game.
 * It stores the player's name, score, lives, and hints.
 */
public class Player {
    private String name;
    private int score;
    private int lives;
    private int hints;

    /**
     * Constructor for Player.
     * Initializes the player with the specified name, a score of 0, 3 lives, and 30 hints.
     * 
     * @param name The name of the player.
     */
    public Player(String name) {
        this.name = name;
        this.score = 0;
        this.lives = 3;
        this.hints = 3;
    }

    /**
     * Gets the name of the player.
     * 
     * @return The name of the player.
     */
    public String getName() {
        return name;
    }

    /**
     * Gets the score of the player.
     * 
     * @return The score of the player.
     */
    public int getScore() {
        return score;
    }

    /**
     * Gets the number of lives the player has.
     * 
     * @return The number of lives the player has.
     */
    public int getLives() {
        return lives;
    }

    /**
     * Gets the number of hints the player has.
     * 
     * @return The number of hints the player has.
     */
    public int getHints() {
        return hints;
    }

    /**
     * Adds the specified score to the player's current score.
     * 
     * @param score The score to add.
     */
    public void addScore(int score) {
        this.score += score;
    }

    /**
     * Resets the player's score to 0.
     */
    public void resetScore() {
        this.score = 0;
    }
    
    /**
     * Decreases the player's lives by 1.
     */
    public void loseLife() {
        if (this.lives > 0) {
            this.lives--;
        }
    }

    /**
     * Uses a hint.
     */
    public void useHint() {
        if (this.hints > 0) {
            this.hints--;
        }
    }

    /**
     * Sets the number of hints the player has.
     * 
     * @param hints The number of hints to set.
     */
    public void setHints(int hints) {
        this.hints = hints;
    }
}
