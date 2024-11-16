package mp.grid;

public class Cell {
    private boolean isFilled; // [#] or [0]
    private boolean isMarked; // [X] or [ ]

    public Cell() {
        this.isFilled = false;
        this.isMarked = false;
    }

    public boolean mark() {
        if (isFilled) {
            isMarked = true;
            return true;
        }
        return false;
    }

    public boolean isCorrect() {
        return isFilled && isMarked;
    }
}
