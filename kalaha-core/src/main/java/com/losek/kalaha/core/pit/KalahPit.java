package com.losek.kalaha.core.pit;

public class KalahPit extends AbstractPit {

    public KalahPit(boolean isActive) {
        super(0, isActive);
    }

    @Override
    protected int handleInternal(int stones) {
        if (!isActive) {
            return stones;
        } else {
           this.incrementBy(1);
           return stones - 1;
        }
    }


    // PATTERN: lancuch zobowiazan (chain of responsibility)
    @Override
    protected void incrementKalaha(int stones) {

        if(isActive) {
            incrementBy(stones);
        } else {
            getNextPit().incrementKalaha(stones);
        }
    }

    @Override
    protected boolean shouldChangePlayer() {
        return !isActive;
    }

}
