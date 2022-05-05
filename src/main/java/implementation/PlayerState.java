package implementation;

import interfaces.KalahPlayer;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class PlayerState {

    private KalahPlayer player1;
    private KalahPlayer player2;

    private List<Integer> legalMoves;

    public PlayerState() { }

    public void addPlayer(KalahPlayer player) {
        if(player1 == null) {
            player1 = player;
        } else {
            player2 = player;
        }
    }

    public void initLegalMoves(int houses) {
        legalMoves = IntStream.range(0, houses)
                .boxed()
                .collect(Collectors.toList());
    }


    public boolean isLegalMove(int pitIdx) {
        return legalMoves.contains(pitIdx);
    }

    public KalahPlayer getPlayer(int number) {
        return number == 1 ? player1 : player2;
    }

}
