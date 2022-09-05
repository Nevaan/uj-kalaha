package implementation.state;

import implementation.pit.AbstractPit;
import implementation.player.PlayerBoard;
import interfaces.GameStateObserver;
import interfaces.KalahPlayer;

import java.util.List;
import java.util.stream.Collectors;


public abstract class PlayerMoveState extends ExtendedState {

    protected final KalahPlayer activePlayer;
    protected final KalahPlayer opponent;

    public PlayerMoveState(PlayerBoard board, KalahPlayer activePlayer, KalahPlayer opponent, List<GameStateObserver> observers) {
        super(board, observers);
        this.activePlayer = activePlayer;
        this.opponent = opponent;
    }

    abstract ExtendedState nextState(boolean shouldChangePlayer);

    @Override
    public ExtendedState process() {

        if (checkIfGameIsDone()) {
            int player1Result = board.getPlayer1Kalaha().getStoneAmount();
            int player2Result = board.getPlayer2Kalaha().getStoneAmount();

            if(player1Result == player2Result) {
                return new EndState(board, GameResults.DRAW, this.observers);
            }

            if(player1Result > player2Result) {
                return new EndState(board, GameResults.PLAYER1_WON, this.observers);
            }

            if(player1Result < player2Result) {
                return new EndState(board, GameResults.PLAYER2_WON, this.observers);
            }
        }

        int startIdx = activePlayer.yourMove(getPlayerPerspectiveState());

        AbstractPit chosenPit = board.getAllPits().get(startIdx);

        int stonesInPit = chosenPit.getStoneAmount();
        chosenPit.clearAmount();

        boolean shouldPlayerChange = chosenPit.getNextPit().handleIncrement(stonesInPit);

        if (shouldPlayerChange) {
            board.getAllPits().forEach(AbstractPit::toggleActive);
        }

        this.notifyState();
        return nextState(shouldPlayerChange);
    }

    @Override
    public GameResults getGameResult() {
        return GameResults.UNKNOWN;
    }

    private List<Integer> getPlayerPerspectiveState() {
        return board.activeUserPerspectiveBoard().stream().map(AbstractPit::getStoneAmount).collect(Collectors.toList());
    }

    private boolean checkIfGameIsDone() {
        int player1Result = board.getPlayer1StonesLeft();
        int player2Result = board.getPlayer2StonesLeft();

        if (player1Result == 0 || player2Result == 0) {
            board.cleanUpBoard();
            board.getPlayer1Kalaha().incrementBy(player1Result);
            board.getPlayer2Kalaha().incrementBy(player2Result);
            return true;
        }
        return false;
    }

}
