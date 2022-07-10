package implementation.state;

import implementation.player.PlayerBoard;
import interfaces.KalahaState;

import java.util.List;

public abstract class ExtendedState implements KalahaState {

    protected final PlayerBoard board;

    public ExtendedState(PlayerBoard board) {
        this.board = board;
    }

    public abstract ExtendedState process();

    @Override
    public List<Integer> getPitsState() {
        return board.getPitState();
    }

}
