package mp.mappings;

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

    public void setFilled() {
        isFilled = true;
    }

    public boolean isCorrect() {
        return isFilled && isMarked;
    }

    public boolean isFilled() {
        return isFilled;
    }

    public boolean isMarked() {
        return isMarked;
    }

}
