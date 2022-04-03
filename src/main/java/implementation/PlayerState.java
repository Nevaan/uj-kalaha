package implementation;

import interfaces.KalahPlayer;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class PlayerState {

    private InnerState player1;
    private InnerState player2;

    private class InnerState {
        private KalahPlayer player;
        private List<Integer> legalMoves;

        public InnerState(KalahPlayer player) {
            this.player = player;
        }

        public KalahPlayer getPlayer() {
            return player;
        }

        public List<Integer> getLegalMoves() {
            return legalMoves;
        }

        public void setLegalMoves(List<Integer> legalMoves) {
            this.legalMoves = legalMoves;
        }
    }

    public PlayerState() { }

    public void addPlayer(KalahPlayer player) {
        if(player1 == null) {
            player1 = new InnerState(player);
        } else {
            player2 = new InnerState(player);
        }
    }

    public void initLegalMoves(int houses) {
        List<Integer> legalMovesP1 = IntStream.range(0, houses)
                .boxed()
                .collect(Collectors.toList());
        player1.setLegalMoves(legalMovesP1);

        List<Integer> legalMovesP2 = IntStream.range(houses+ 1, (2 * houses) + 1)
                .boxed()
                .collect(Collectors.toList());
        player2.setLegalMoves(legalMovesP2);
    }


    public boolean isLegalMove(int pitIdx, int activePlayer) {
        return getPlayerState(activePlayer).getLegalMoves().contains(pitIdx);
    }

    public KalahPlayer getPlayer(int number) {
        return getPlayerState(number).getPlayer();
    }

    private InnerState getPlayerState(int number) {
        return (number == 1 ? player1 : player2);
    }


}
