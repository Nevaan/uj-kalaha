package com.losek.kalaha.core.state;

import com.losek.kalaha.core.player.PlayerBoard;
import com.losek.kalaha.interfaces.GameStateObserver;
import com.losek.kalaha.interfaces.KalahPlayer;

import java.util.List;

public class InitState extends ExtendedState {

    private final KalahPlayer activePlayer;
    private final KalahPlayer opponent;


    public InitState(PlayerBoard board, KalahPlayer activePlayer, KalahPlayer opponent, List<GameStateObserver> observers) {
        super(board, observers);
        this.activePlayer = activePlayer;
        this.opponent = opponent;
    }

    @Override
    public ExtendedState process() {
        this.notifyState();
        return new Player1MoveState(board, activePlayer, opponent, this.observers);
    }

    @Override
    public GameStates getGameState() {
        return GameStates.INITAL_STATE;
    }

    @Override
    public GameResults getGameResult() {
        return GameResults.UNKNOWN;
    }

}
