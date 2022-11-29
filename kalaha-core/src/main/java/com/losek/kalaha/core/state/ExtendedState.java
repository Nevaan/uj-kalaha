package com.losek.kalaha.core.state;

import com.losek.kalaha.core.player.PlayerBoard;
import com.losek.kalaha.interfaces.GameStateObserver;
import com.losek.kalaha.interfaces.KalahaState;

import java.util.List;

public abstract class ExtendedState implements KalahaState {

    protected final PlayerBoard board;
    protected final List<GameStateObserver> observers;

    protected ExtendedState(PlayerBoard board, List<GameStateObserver> observers) {
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
