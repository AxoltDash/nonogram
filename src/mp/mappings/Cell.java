package mp.mappings;

public class Cell {
    private boolean isFilled;
    private boolean isMarked;
    private boolean isHollowMarked;

    public Cell() {
        this.isFilled = false;
        this.isMarked = false;
        this.isHollowMarked = false;
    }

    public boolean mark() {
        if (isFilled) {
            isMarked = true;
            return true;
        }
        return false;
    }

    public boolean markHollow() {
        if (!isFilled) {
            isHollowMarked = true;
            return true;
        }
        return false;
    }

    public void setFilled() {
        isFilled = true;
    }

    public boolean isFilled() {
        return isFilled;
    }

    public boolean isMarked() {
        return isMarked;
    }

    public boolean isHollowMarked() {
        return isHollowMarked;
    }

}
