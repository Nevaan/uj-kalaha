package implementation.state;

import implementation.player.PlayerBoard;
import interfaces.GameStateObserver;
import interfaces.KalahPlayer;

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
