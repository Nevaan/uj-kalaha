package implementation;

import implementation.pit.AbstractPit;
import implementation.pit.HousePit;
import implementation.pit.KalahPit;
import implementation.player.PlayerBoard;
import interfaces.KalahaState;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class KalahaStateImpl implements KalahaState {

    private GameStates currentState = GameStates.INITAL_STATE;
    private GameResults result = GameResults.UNKNOWN;

    private PlayerBoard playerBoard1;
    private PlayerBoard playerBoard2;

    public KalahaStateImpl(PlayerBoard playerBoard1, PlayerBoard playerBoard2) {

        this.playerBoard1 = playerBoard1;
        this.playerBoard2 = playerBoard2;

        this.playerBoard1.setNextKalahaPit(playerBoard2.getFirstPit());
        this.playerBoard2.setNextKalahaPit(playerBoard1.getFirstPit());


        List<AbstractPit> player1Houses = this.playerBoard1.getPlayerPits();
        List<AbstractPit> player2Houses = this.playerBoard2.getPlayerPits();

        Collections.reverse(player2Houses);

        for (int i = 0; i < player1Houses.size(); i++) {
            AbstractPit player1House = player1Houses.get(i);
            AbstractPit player2House = player2Houses.get(i);

            player1House.setOppositePit(player2House);
            player2House.setOppositePit(player1House);
        }


        Collections.reverse(player2Houses);
    }



    @Override
    public List<Integer> getPitsState() {
        List<Integer> player1PitsState = playerBoard1.getPitState();
        List<Integer> player2PitsState = playerBoard2.getPitState();

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

        List<AbstractPit> activePlayerPits = playerNo == 1 ? playerBoard1.getAllPits(): playerBoard2.getAllPits();
        AbstractPit chosenPit = activePlayerPits.get(startIdx);

        int stonesInPit = chosenPit.getStoneAmount();
        boolean shouldPlayerChange = true;

        chosenPit.clearAmount();

        List<AbstractPit> elementsToIncrement = new ArrayList();

        while (stonesInPit > 0) {
            chosenPit = chosenPit.getNextPit();
            if (activePlayerPits.contains(chosenPit) || !(chosenPit instanceof KalahPit)) {
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
        int p1Stones = playerBoard1.getPitStonesAmount();
        int p2Stones = playerBoard2.getPitStonesAmount();

        if (p1Stones == 0 || p2Stones == 0) {
            this.currentState = GameStates.END_OF_GAME;

            if (p1Stones == 0) {
                playerBoard2.clearPits();
                playerBoard2.getKalaha().incrementBy(p2Stones);
            }

            if (p2Stones == 0) {
                playerBoard1.clearPits();
                playerBoard1.getKalaha().incrementBy(p1Stones);
            }

            int player1Score = playerBoard1.getResult();
            int player2Score = playerBoard2.getResult();

            if (player1Score == player2Score) {
                this.result = GameResults.DRAW;
            }

            if (player1Score > player2Score) {
                this.result = GameResults.PLAYER1_WON;
            }

            if (player1Score < player2Score) {
                this.result = GameResults.PLAYER2_WON;
            }

            return;
        }

    }

}
