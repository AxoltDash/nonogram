package mp.game.graphicView;

import mp.game.Mode;

import mp.game.Player;
import mp.mappings.*;

public class GraphicMode implements Mode {
    
    public GraphicMode() {
        // Empty constructor
    }

    @Override
    public int showAndAsk(boolean hardMode, Nonogram n, Player player) {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public boolean markCell(boolean hollow, Nonogram n, Player player) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean askForHint(Nonogram n) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public void printNoHints() {
        // TODO Auto-generated method stub
    }

    @Override
    public void printEndGame(Nonogram n, Player player) {
        // TODO Auto-generated method stub
    }
}