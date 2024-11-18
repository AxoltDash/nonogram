package mp.mappings;

/**
 * The Cell class represents a single cell in the Nonogram game grid.
 * It stores the state of the cell, including whether it is filled, marked, or hollow marked.
 */
public class Cell {
    private boolean isFilled;
    private boolean isMarked;
    private boolean isHollowMarked;

    /**
     * Constructor for Cell.
     * Initializes the cell with default values: not filled, not marked, and not hollow marked.
     */
    public Cell() {
        this.isFilled = false;
        this.isMarked = false;
        this.isHollowMarked = false;
    }

    /**
     * Marks the cell if it is filled and not already marked.
     * 
     * @return true if the cell was successfully marked, false otherwise.
     */
    public boolean mark() {
        if (isFilled && !isMarked) {
            isMarked = true;
            return true;
        }
        return false;
    }

    /**
     * Marks the cell as hollow if it is not filled and not already hollow marked.
     * 
     * @return true if the cell was successfully hollow marked, false otherwise.
     */
    public boolean markHollow() {
        if (!isFilled && !isHollowMarked) {
            isHollowMarked = true;
            return true;
        }
        return false;
    }

    /**
     * Sets the cell as filled.
     */
    public void setFilled() {
        isFilled = true;
    }

    /**
     * Checks if the cell is filled.
     * 
     * @return true if the cell is filled, false otherwise.
     */
    public boolean isFilled() {
        return isFilled;
    }

    /**
     * Checks if the cell is marked.
     * 
     * @return true if the cell is marked, false otherwise.
     */
    public boolean isMarked() {
        return isMarked;
    }

    /**
     * Checks if the cell is hollow marked.
     * 
     * @return true if the cell is hollow marked, false otherwise.
     */
    public boolean isHollowMarked() {
        return isHollowMarked;
    }
}
