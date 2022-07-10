package implementation.state;

import implementation.player.PlayerBoard;

public class EndState extends ExtendedState {

    private GameResults gameResults;

    public EndState(PlayerBoard board, GameResults gameResults) {
        super(board);
        this.gameResults = gameResults;
    }

    @Override
    public ExtendedState process() {
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
