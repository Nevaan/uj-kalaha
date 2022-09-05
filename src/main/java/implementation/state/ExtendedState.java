package implementation.state;

import implementation.player.PlayerBoard;
import interfaces.GameStateObserver;
import interfaces.KalahaState;

import java.util.ArrayList;
import java.util.List;

public abstract class ExtendedState implements KalahaState {

    protected final PlayerBoard board;
    protected final List<GameStateObserver> observers;

    public ExtendedState(PlayerBoard board, List<GameStateObserver> observers) {
        this.board = board;
        this.observers = observers;
    }

    public abstract ExtendedState process();

    protected void notifyState() {
        for (GameStateObserver obs : observers) {
            obs.currentState(this);
        }
    }

    @Override
    public List<Integer> getPitsState() {
        return board.getPitState();
    }

}
