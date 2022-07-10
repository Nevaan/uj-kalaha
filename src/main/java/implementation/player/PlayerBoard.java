package implementation.player;

import implementation.pit.AbstractPit;
import implementation.pit.HousePit;
import implementation.pit.KalahPit;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class PlayerBoard {


    private final List<AbstractPit> player1Pits;
    private final AbstractPit player1Kalaha;
    private final List<AbstractPit> player2Pits;
    private final AbstractPit player2Kalaha;

    public PlayerBoard(int seeds, int houses) {

        List<AbstractPit> player1Pits = new ArrayList<>();
        List<AbstractPit> player2Pits = new ArrayList<>();

        for (int x = 0; x < houses; x++) {

            AbstractPit player1Pit = new HousePit(seeds, true);
            AbstractPit player2Pit = new HousePit(seeds, false);
            player1Pit.setOppositePit(player2Pit);
            player2Pit.setOppositePit(player1Pit);

            player1Pits.add(player1Pit);
            player2Pits.add(player2Pit);
        }

        Collections.reverse(player2Pits);

        AbstractPit player1Kalaha = new KalahPit(true);
        AbstractPit player2Kalaha = new KalahPit(false);

        this.player1Pits = player1Pits;
        this.player1Kalaha = player1Kalaha;
        this.player2Pits = player2Pits;
        this.player2Kalaha = player2Kalaha;

        for (int x = 0; x < houses - 1; x++) {
            player1Pits.get(x).setNextPit(player1Pits.get(x+1));
            player2Pits.get(x).setNextPit(player2Pits.get(x+1));
        }

        player1Pits.get(player1Pits.size() - 1).setNextPit(player1Kalaha);
        player1Kalaha.setNextPit(player2Pits.get(0));

        player2Pits.get(player2Pits.size() - 1).setNextPit(player2Kalaha);
        player2Kalaha.setNextPit(player1Pits.get(0));

    }



//    private List<AbstractPit> initPits(int seeds, int houses) {
//        List<AbstractPit> player1Pits = new ArrayList<>();
//        List<AbstractPit> player2Pits = new ArrayList<>();
////        List<AbstractPit> boardPits = new ArrayList<>();
//
//        for (int x = 0; x < houses; x++) {
//
//            AbstractPit player1Pit = new HousePit(seeds, true);
//            AbstractPit player2Pit = new HousePit(seeds, false);
//            player1Pit.setOppositePit(player2Pit);
//            player2Pit.setOppositePit(player1Pit);
//
//            player1Pits.add(player1Pit);
//            player2Pits.add(player2Pit);
//        }
//
//        Collections.reverse(player2Pits);
//
//        AbstractPit player1Kalaha = new KalahPit(true);
//        AbstractPit player2Kalaha = new KalahPit(false);
//
////        player1Pits.add(player1Kalaha);
////        player2Pits.add(player2Kalaha);
//
//        this.player1Pits = player1Pits;
//        this.player1Kalaha = player1Kalaha;
//        this.player2Pits = player2Pits;
//        this.player2Kalaha = player2Kalaha;
//
//
////        boardPits.addAll(player1Pits);
////        boardPits.addAll(player2Pits);
//
//        for (int x = 0; x < houses; x++) {
//            player1Pits.get(x).setNextPit(player1Pits.get(x+1));
//            player2Pits.get(x).setNextPit(player2Pits.get(x+1));
//        }
//
//        player1Pits.get(player1Pits.size() - 1).setNextPit(player1Kalaha);
//        player1Kalaha.setNextPit(player2Pits.get(0));
//
//        player2Pits.get(player2Pits.size() - 1).setNextPit(player2Kalaha);
//        player2Kalaha.setNextPit(player1Pits.get(0));
//
////        boardPits.get(boardPits.size() -1).setNextPit(boardPits.get(0));
//
////        return boardPits;
//    }


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

    public int getPlayer1Result() {
        return player1Kalaha.getStoneAmount();
    }

    public int getPlayer2StonesLeft() {
        return player2Pits.stream().map(AbstractPit::getStoneAmount).reduce(0, (a,b) -> a+b);
    }

    public int getPlayer2Result() {
        return player1Kalaha.getStoneAmount();
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