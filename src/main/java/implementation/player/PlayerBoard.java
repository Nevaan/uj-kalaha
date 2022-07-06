package implementation.player;

import implementation.pit.AbstractPit;
import implementation.pit.HousePit;
import implementation.pit.KalahPit;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class PlayerBoard {

    private final List<AbstractPit> playerPits;
    private final AbstractPit kalaha;

    public PlayerBoard(int seeds, int houses) {
        this.kalaha = new KalahPit();
        this.playerPits = initPits(seeds, houses);
    }


    private List<AbstractPit> initPits(int seeds, int houses) {
        List<AbstractPit> pits = new ArrayList<>();
        for (int x = 0; x < houses; x++) {
            pits.add(new HousePit(seeds));
        }

        for (int x = 0; x < pits.size() - 1; x++) {
            pits.get(x).setNextPit(pits.get(x+1));
        }

        pits.get(pits.size() - 1).setNextPit(kalaha);

        return pits;
    }

    public List<AbstractPit> getPlayerPits() {
        return playerPits;
    }

    public AbstractPit getKalaha() {
        return kalaha;
    }

    public List<AbstractPit> getAllPits() {
        return Stream.concat(playerPits.stream(), Stream.of(kalaha)).collect(Collectors.toList());
    }

    public AbstractPit getFirstPit() {
        return playerPits.get(0);
    }

    public void setNextKalahaPit(AbstractPit pit) {
        kalaha.setNextPit(pit);
    }

    public List<Integer> getPitState() {
        return getAllPits().stream().map(AbstractPit::getStoneAmount).collect(Collectors.toList());
    }

    public int getResult() {
        return kalaha.getStoneAmount();
    }

    public int getPitStonesAmount() {
        return playerPits.stream()
                .map(AbstractPit::getStoneAmount)
                .reduce(0, (a, b) -> a + b);
    }

    public void clearPits() {
        playerPits.forEach(AbstractPit::clearAmount);
    }

}
