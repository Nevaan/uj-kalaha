package com.losek.kalaha.core.state;

import com.losek.kalaha.core.player.PlayerBoard;
import com.losek.kalaha.interfaces.GameStateObserver;

import java.util.List;

public class EndState extends ExtendedState {

    private GameResults gameResults;

    public EndState(PlayerBoard board, GameResults gameResults, List<GameStateObserver> observers) {
        super(board, observers);
        this.gameResults = gameResults;
    }

    @Override
    public ExtendedState process() {
        this.notifyState();
        return null;
    }

    @Override
    public GameStates getGameState() {
        return GameStates.END_OF_GAME;
    }

    @Override
    public GameResults getGameResult() {
        return gameResults;
    }
}
