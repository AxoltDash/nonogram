package mp.game;

import mp.mappings.Nonogram;

public interface Mode {
    public int showAndAsk(boolean hardMode, Nonogram n, Player player);
    public boolean markCell(boolean hollow, Nonogram n, Player player);
    public boolean askForHint(Nonogram n);
    public void printNoHints();
    public void printEndGame(Nonogram n, Player player);
}
