package mp.game;

public class Player {
    private String name;
    private int score;
    private int lives;
    private int hints;

    public Player(String name) {
        this.name = name;
        this.score = 0;
        this.lives = 3;
        this.hints = 3;
    }

    public String getName() {
        return name;
    }

    public int getScore() {
        return score;
    }

    public int getLives() {
        return lives;
    }

    public int getHints() {
        return hints;
    }

    public void addScore(int score) {
        this.score += score;
    }

    public void resetScore() {
        this.score = 0;
    }
    
    public void loseLife() {
        if (this.lives > 0) {
            this.lives--;
        }
    }

    public void useHint() {
        if (this.hints > 0) {
            this.hints--;
        }
    }

    public void setHints(int hints) {
        this.hints = hints;
    }
}
