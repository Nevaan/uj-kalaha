package implementation.state;

import implementation.player.PlayerBoard;
import implementation.player.PlayerDecorator;
import interfaces.KalahPlayer;

import java.util.List;

public class InitState extends ExtendedState {

    private final KalahPlayer activePlayer;
    private final KalahPlayer opponent;


    public InitState(PlayerBoard board, KalahPlayer activePlayer, KalahPlayer opponent) {
        super(board);
        this.activePlayer = activePlayer;
        this.opponent = opponent;
    }

    @Override
    public ExtendedState process() {
        return new Player1MoveState(board, activePlayer, opponent);
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
