package implementation.state;

import implementation.player.PlayerBoard;
import interfaces.KalahPlayer;

public class Player2MoveState extends PlayerMoveState {

    public Player2MoveState(PlayerBoard board, KalahPlayer activePlayer, KalahPlayer opponent) {
        super(board, activePlayer, opponent);
    }

    @Override
    protected ExtendedState nextState(boolean shouldChangePlayer) {
        if (shouldChangePlayer) {
            return new Player1MoveState(board, opponent, activePlayer);
        } else {
            return new Player2MoveState(board, activePlayer, opponent);
        }
    }

    @Override
    public GameStates getGameState() {
        return GameStates.AFTER_PLAYER2_TURN;
    }
}