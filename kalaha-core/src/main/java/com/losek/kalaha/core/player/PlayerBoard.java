package com.losek.kalaha.core.player;

import com.losek.kalaha.core.pit.AbstractPit;
import com.losek.kalaha.core.pit.HousePit;
import com.losek.kalaha.core.pit.KalahPit;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class PlayerBoard {

    private final List<AbstractPit> player1Pits;
    private final AbstractPit player1Kalaha;
    private final List<AbstractPit> player2Pits;
    private final AbstractPit player2Kalaha;

    public PlayerBoard(int seeds, int houses) {

        this.player1Pits = new ArrayList<>();
        this.player2Pits = new ArrayList<>();

        for (int x = 0; x < houses; x++) {
            AbstractPit player1Pit = new HousePit(seeds, true);
            AbstractPit player2Pit = new HousePit(seeds, false);
            player1Pit.setOppositePit(player2Pit);
            player2Pit.setOppositePit(player1Pit);

            player1Pits.add(player1Pit);
            player2Pits.add(player2Pit);
        }

        Collections.reverse(player2Pits);

        this.player1Kalaha = new KalahPit(true);
        this.player2Kalaha = new KalahPit(false);

        for (int x = 0; x < houses - 1; x++) {
            player1Pits.get(x).setNextPit(player1Pits.get(x+1));
            player2Pits.get(x).setNextPit(player2Pits.get(x+1));
        }

        player1Pits.get(player1Pits.size() - 1).setNextPit(player1Kalaha);
        player1Kalaha.setNextPit(player2Pits.get(0));

        player2Pits.get(player2Pits.size() - 1).setNextPit(player2Kalaha);
        player2Kalaha.setNextPit(player1Pits.get(0));

    }

    public List<AbstractPit> getAllPits() {
        List<AbstractPit> result = new ArrayList<>();

        result.addAll(player1Pits);
        result.add(player1Kalaha);
        result.addAll(player2Pits);
        result.add(player2Kalaha);

        return result;
    }

    public List<AbstractPit> activeUserPerspectiveBoard() {
        List<AbstractPit> active = getAllPits().stream().filter(AbstractPit::isActive).collect(Collectors.toList());
        List<AbstractPit> inactive = getAllPits().stream().filter(x -> !x.isActive()).collect(Collectors.toList());
        List<AbstractPit> finalList = new ArrayList<>();
        finalList.addAll(active);
        finalList.addAll(inactive);
        return finalList;
    }

    public List<Integer> getPitState() {
        return getAllPits().stream().map(AbstractPit::getStoneAmount).collect(Collectors.toList());
    }

    public int getPlayer1StonesLeft() {
        return player1Pits.stream().map(AbstractPit::getStoneAmount).reduce(0, (a,b) -> a+b);
    }

    public int getPlayer2StonesLeft() {
        return player2Pits.stream().map(AbstractPit::getStoneAmount).reduce(0, (a,b) -> a+b);
    }

    public void cleanUpBoard() {
        player1Pits.forEach(AbstractPit::clearAmount);
        player2Pits.forEach(AbstractPit::clearAmount);
    }

    public AbstractPit getPlayer1Kalaha() {
        return player1Kalaha;
    }

    public AbstractPit getPlayer2Kalaha() {
        return player2Kalaha;
    }
}
