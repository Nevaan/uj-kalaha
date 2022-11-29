package com.losek.kalaha.core.state;

import com.losek.kalaha.core.player.PlayerBoard;
import com.losek.kalaha.interfaces.GameStateObserver;
import com.losek.kalaha.interfaces.KalahPlayer;

import java.util.List;

public class Player2MoveState extends PlayerMoveState {

    public Player2MoveState(PlayerBoard board, KalahPlayer activePlayer, KalahPlayer opponent, List<GameStateObserver> observers) {
        super(board, activePlayer, opponent, observers);
    }

    @Override
    protected ExtendedState nextState(boolean shouldChangePlayer) {
        if (shouldChangePlayer) {
            return new Player1MoveState(board, opponent, activePlayer, observers);
        } else {
            return new Player2MoveState(board, activePlayer, opponent, observers);
        }
    }

    @Override
    public GameStates getGameState() {
        return GameStates.AFTER_PLAYER2_TURN;
    }
}
