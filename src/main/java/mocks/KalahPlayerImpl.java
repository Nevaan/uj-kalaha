package mocks;

import interfaces.KalahPlayer;

import java.util.List;

public class KalahPlayerImpl implements KalahPlayer {

    private int nextMove;

    @Override
    public int yourMove(List<Integer> pitsState) {
        return nextMove;
    }

    public void setNextMove(int nextMove) {
        this.nextMove = nextMove;
    }

}
