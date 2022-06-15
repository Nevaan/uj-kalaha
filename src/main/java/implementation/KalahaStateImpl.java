package implementation;

import implementation.pit.AbstractPit;
import implementation.pit.HousePit;
import implementation.pit.KalahPit;
import interfaces.KalahaState;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class KalahaStateImpl implements KalahaState {

    private int houses;

    private GameStates currentState = GameStates.INITAL_STATE;
    private GameResults result = GameResults.UNKNOWN;

    private List<AbstractPit> player1Pits;
    private List<AbstractPit> player2Pits;

    public KalahaStateImpl(int seeds, int houses) {

        this.houses = houses;

        player1Pits = initPits(seeds, houses);
        player2Pits = initPits(seeds, houses);


        player1Pits.get(houses).setNextPit(player2Pits.get(0));
        player2Pits.get(houses).setNextPit(player1Pits.get(0));

        List<AbstractPit> player1Houses = player1Pits.subList(0, player1Pits.size() - 1);
        List<AbstractPit> player2Houses = player2Pits.subList(0, player1Pits.size() - 1);

        Collections.reverse(player2Houses);

        for (int i = 0; i < player1Houses.size(); i++) {
            AbstractPit player1House = player1Houses.get(i);
            AbstractPit player2House = player2Houses.get(i);

            player1House.setOppositePit(player2House);
            player2House.setOppositePit(player1House);
        }


        Collections.reverse(player2Houses);
    }

    private List<AbstractPit> initPits(int seeds, int houses) {
        List<AbstractPit> pits = new ArrayList<>();
        for (int x = 0; x < houses; x++) {
            pits.add(new HousePit(seeds));
        }
        pits.add(new KalahPit());

        for (int x = 0; x < pits.size() - 1; x++) {
            pits.get(x).setNextPit(pits.get(x+1));
        }

        return pits;
    }

    @Override
    public List<Integer> getPitsState() {
        List<Integer> player1PitsState = player1Pits.stream().map(AbstractPit::getStoneAmount).collect(Collectors.toList());
        List<Integer> player2PitsState = player2Pits.stream().map(AbstractPit::getStoneAmount).collect(Collectors.toList());

        return Stream.of(player1PitsState, player2PitsState)
                .flatMap(x -> x.stream())
                .collect(Collectors.toList());
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

        List<AbstractPit> activePlayerPits = playerNo == 1 ? player1Pits: player2Pits;
        AbstractPit chosenPit = activePlayerPits.get(startIdx);

        int stonesInPit = chosenPit.getStoneAmount();
        boolean shouldPlayerChange = true;

        chosenPit.clearAmount();

        List<AbstractPit> elementsToIncrement = new ArrayList();

        while (stonesInPit > 0) {
            chosenPit = chosenPit.getNextPit();
            if (!(!activePlayerPits.contains(chosenPit) && chosenPit instanceof KalahPit)) {
                elementsToIncrement.add(chosenPit);
                stonesInPit -= 1;
            }
        }

        AbstractPit lastPit = elementsToIncrement.get(elementsToIncrement.size() - 1);

        List<AbstractPit> incrementUnconditionally = elementsToIncrement.subList(0, elementsToIncrement.size() - 1);
        incrementUnconditionally.forEach(pit -> pit.incrementBy(1));

        boolean takeLast = false;
        if (activePlayerPits.contains(lastPit) &&
                lastPit instanceof HousePit &&
                lastPit.getStoneAmount() == 0 &&
                lastPit.getOppositePit().getStoneAmount() > 0) {
            takeLast = true;

            elementsToIncrement.remove(elementsToIncrement.size() - 1);
        }

        if (activePlayerPits.contains(lastPit) && lastPit instanceof KalahPit) {
            shouldPlayerChange = false;
        }


        if (takeLast) {
            AbstractPit oppositePit = lastPit.getOppositePit();
            int stonesToTake = oppositePit.getStoneAmount() + 1;
            lastPit.clearAmount();
            oppositePit.clearAmount();
            AbstractPit kalahaPit = activePlayerPits.get(activePlayerPits.size() - 1);
            kalahaPit.incrementBy(stonesToTake);
        } else {
            lastPit.incrementBy(1);
        }

        if (playerNo==1) {
            this.currentState = GameStates.AFTER_PLAYER1_TURN;
        } else {
            this.currentState = GameStates.AFTER_PLAYER2_TURN;
        }

        return shouldPlayerChange;
    }

    public void checkIfGameIsDone() {
        List<AbstractPit> player1State = player1Pits.subList(0, houses);
        List<AbstractPit> player2State = player2Pits.subList(0, houses);

        int p1Stones = player1State.stream().map(AbstractPit::getStoneAmount).reduce(0, (a, b) -> a + b);
        int p2Stones = player2State.stream().map(AbstractPit::getStoneAmount).reduce(0, (a, b) -> a + b);

        if (p1Stones == 0 && p2Stones == 0) {
            this.currentState = GameStates.END_OF_GAME;
            this.result = GameResults.DRAW;
            return;
        }

        if (p1Stones == 0) {
            int stonesLeft = player2State.stream().map(AbstractPit::getStoneAmount).reduce(0, (a, b) -> a + b);
            player2State.forEach(AbstractPit::clearAmount);
            player2Pits.get(houses).incrementBy(stonesLeft);
            this.currentState = GameStates.END_OF_GAME;
            this.result = GameResults.PLAYER2_WON;
            return;
        }

        if (p2Stones == 0) {
            int stonesLeft = player1State.stream().map(AbstractPit::getStoneAmount).reduce(0, (a, b) -> a + b);
            player1State.forEach(AbstractPit::clearAmount);
            player1Pits.get(houses).incrementBy(stonesLeft);
            this.currentState = GameStates.END_OF_GAME;
            this.result = GameResults.PLAYER1_WON;
            return;
        }


    }

    // todo: remove
    public List<AbstractPit> getPlayer1Pits() {
        return player1Pits;
    }

    // todo: remove
    public List<AbstractPit> getPlayer2Pits() {
        return player2Pits;
    }
}
