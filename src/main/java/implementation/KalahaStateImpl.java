package implementation;

import implementation.pit.AbstractPit;
import implementation.pit.HousePit;
import implementation.pit.KalahPit;
import interfaces.KalahaState;

import java.util.ArrayList;
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
            for (int i = 0; i < houses; i++) {
                houseState.add(new HousePit((j * houses) + i + j, seeds, j + 1));
            }
            // base
            houseState.add(new KalahPit(((j + 1) * houses) + j, j + 1));
        }

        // build chain

        for (int x = 0; x < houseState.size() - 1; x++) {
            houseState.get(x).setNextPit(houseState.get(x + 1));
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

        int mappedIndex = (houses * (playerNo - 1)) + startIdx +(playerNo - 1);

        AbstractPit chosenPit = houseState.get(mappedIndex);
        int stonesInPit = chosenPit.getStoneAmount();
        boolean shouldPlayerChange = true;

        chosenPit.clearAmount();

        List<Integer> elementsToIncrement = new ArrayList();

        while (stonesInPit > 0) {
            chosenPit = chosenPit.getNextPit();
            int indexToIncrement = chosenPit.shouldIncrement(playerNo);
            if (indexToIncrement >= 0) {
                elementsToIncrement.add(indexToIncrement);
                stonesInPit -= 1;
            }
        }

        int lastIndex = elementsToIncrement.get(elementsToIncrement.size() - 1);
        AbstractPit lastPit = houseState.get(lastIndex);

        List<Integer> incrementUnconditionally = elementsToIncrement.subList(0, elementsToIncrement.size() - 1);
        incrementUnconditionally.forEach(idx -> houseState.get(idx).incrementBy(1));

        boolean takeLast = false;
        if (lastPit.getPlayerNo() == playerNo &&
                lastPit.getStoneAmount() == 0 &&
                houseState.get(countOppositeIndex(lastIndex)).getStoneAmount() > 0 &&
                lastPit instanceof HousePit) {
            takeLast = true;

            elementsToIncrement.remove(elementsToIncrement.size() - 1);
        }

        if (lastPit.getPlayerNo() == playerNo && lastPit instanceof KalahPit) {
            shouldPlayerChange = false;
        }


        if (takeLast) {
            int oppositeIndex = countOppositeIndex(lastIndex);
            AbstractPit oppositePit = houseState.get(oppositeIndex);
            int stonesToTake = oppositePit.getStoneAmount() + 1;
            lastPit.clearAmount();
            oppositePit.clearAmount();
            int kalahaIdx = houses + ((playerNo - 1) * houses) + (playerNo - 1);
            AbstractPit kalahaPit = houseState.get(kalahaIdx);
            kalahaPit.incrementBy(stonesToTake);
        } else {
            lastPit.incrementBy(1);
        }

        endGame();

        return shouldPlayerChange;
    }

    private void endGame() {
        List<AbstractPit> player1State = houseState.stream().filter(x -> x.getIndex() < (this.houseState.size() / 2) - 1).collect(Collectors.toList());
        List<AbstractPit> player2State = houseState.stream().skip(houses + 1).filter(x -> x.getIndex() < houseState.size() - 1).collect(Collectors.toList());
        int p1Stones = player1State.stream().map(AbstractPit::getStoneAmount).reduce(0, (a, b) -> a + b);
        int p2Stones = player2State.stream().map(AbstractPit::getStoneAmount).reduce(0, (a, b) -> a + b);
        if (p1Stones == 0) {
            int stonesLeft = player2State.stream().map(AbstractPit::getStoneAmount).reduce(0, (a, b) -> a + b);
            player2State.forEach(AbstractPit::clearAmount);
            getPlayer2KalahPit().incrementBy(stonesLeft);
            this.currentState = GameStates.END_OF_GAME;
            this.result = GameResults.PLAYER2_WON;
            return;
        }
        if (p2Stones == 0) {
            int stonesLeft = player1State.stream().map(AbstractPit::getStoneAmount).reduce(0, (a, b) -> a + b);
            player1State.forEach(AbstractPit::clearAmount);
            getPlayer1KalahPit().incrementBy(stonesLeft);
            this.currentState = GameStates.END_OF_GAME;
            this.result = GameResults.PLAYER1_WON;
            return;
        }


    }

    private AbstractPit getPlayer1KalahPit() {
        return houseState.get((this.houseState.size() / 2) - 1);
    }

    private AbstractPit getPlayer2KalahPit() {
        return houseState.get(this.houseState.size() - 1);
    }

    private int countOppositeIndex(int index) {
        return 2 * houses - index;
    }

}
