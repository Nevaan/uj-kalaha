package implementation.pit;

public class KalahPit extends AbstractPit {

    public KalahPit(int idx, int playerNo) {
        super(idx,0, playerNo);
    }

    @Override
    public void clearAmount() {
        // NOOP
    }

    @Override
    public int shouldIncrement(int playerTakingMove) {
        if(this.getPlayerNo() == playerTakingMove) {
            return this.getIndex();
        }
        return -1;
    }
}
