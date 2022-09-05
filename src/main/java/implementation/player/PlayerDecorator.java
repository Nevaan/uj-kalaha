package implementation.player;

import interfaces.KalahPlayer;

import java.util.List;

public class PlayerDecorator implements KalahPlayer {

    private final KalahPlayer kalahPlayer;
    private final int shift;
    private final int maximumIndex;

    public PlayerDecorator(KalahPlayer kalahPlayer, int shift, int maximumIndex) {
        this.kalahPlayer = kalahPlayer;
        this.shift = shift;
        this.maximumIndex = maximumIndex;
    }

    // PATTERN: dekorator
    @Override
    public int yourMove(List<Integer> pitsState) {

        int pitIndex = kalahPlayer.yourMove(pitsState);

        while (pitIndex< 0 || pitIndex > maximumIndex) {
            pitIndex = kalahPlayer.yourMove(pitsState);
        }

        return pitIndex + shift;
    }

}
