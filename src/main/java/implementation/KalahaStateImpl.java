package implementation;

import implementation.pit.AbstractPit;
import implementation.pit.HousePit;
import implementation.pit.KalahPit;
import interfaces.KalahaState;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class KalahaStateImpl implements KalahaState {

    private int houses;

    private GameStates currentState = GameStates.INITAL_STATE;
    private GameResults result = GameResults.UNKNOWN;

    private List<AbstractPit> houseState = new ArrayList<>();

    public KalahaStateImpl(int seeds, int houses) {

        this.houses = houses;
        for (int j = 0; j < 2; j++) {
            for(int i = 0; i < houses;i++) {
                houseState.add(new HousePit((j * houses) + i + j, seeds, j + 1));
            }
            // base
            houseState.add(new KalahPit(((j+1) * houses) + j,j + 1));
        }

        // build chain

        for (int x = 0; x < houseState.size() - 1; x++) {
            houseState.get(x).setNextPit(houseState.get(x+1));
        }

        houseState.get(houseState.size() - 1).setNextPit(houseState.get(0));

    }

    @Override
    public List<Integer> getPitsState() {
        return houseState.stream().map(AbstractPit::getStoneAmount).collect(Collectors.toList());
    }

    @Override
    public GameStates getGameState() {
        return currentState;
    }

    @Override
    public GameResults getGameResult() {
        return result;
    }

    public boolean makeMove(int startIdx, int playerNo) {
        AbstractPit chosenPit = houseState.get(startIdx);
        int stonesInPit = chosenPit.getStoneAmount();

        chosenPit.clearAmount();

        List<Integer> elementsToIncrement = new ArrayList();

        while(stonesInPit > 0) {
            chosenPit = chosenPit.getNextPit();
            int indexToIncrement = chosenPit.shouldIncrement(playerNo);
            if(indexToIncrement >= 0) {
                elementsToIncrement.add(indexToIncrement);
                stonesInPit -= 1;
            }
        }

        int lastIndex = elementsToIncrement.get(elementsToIncrement.size() - 1);
        AbstractPit lastPit = houseState.get(lastIndex);
        boolean takeLast = false;
        if (lastPit.getPlayerNo() == playerNo && lastPit.getStoneAmount() == 0 && lastPit instanceof HousePit) {
            takeLast = true;

            elementsToIncrement.remove(elementsToIncrement.size() - 1);
        }

        elementsToIncrement.forEach(idx -> houseState.get(idx).incrementBy(1));

        if(takeLast) {
            int oppositeIndex = countOppositeIndex(lastIndex);
            AbstractPit oppositePit =  houseState.get(oppositeIndex);
            int stonesToTake = oppositePit.getStoneAmount() + 1;
            lastPit.clearAmount();
            oppositePit.clearAmount();
            int kalahaIdx = houses + ((playerNo - 1) * houses) + (playerNo - 1);
            AbstractPit kalahaPit = houseState.get(kalahaIdx);
            kalahaPit.incrementBy(stonesToTake);
        }

        // should player change?
        return true;
    }

    private int player1Score() {
        return houseState.get((this.houseState.size() / 2) - 1).getStoneAmount();
    }

    private int player2Score() {
        return houseState.get(this.houseState.size() - 1).getStoneAmount();
    }


    private int countOppositeIndex(int index) {
        return 2 * houses - index;
   }

}
