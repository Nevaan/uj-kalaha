package implementation.pit;


public abstract class AbstractPit {

    private int index;
    protected int stoneAmount;
    private AbstractPit nextPit;
    private final int playerNo;

    public AbstractPit(int index, int stoneAmount, int playerNo) {
        this.index = index;
        this.stoneAmount = stoneAmount;
        this.playerNo = playerNo;
    }

    public AbstractPit getNextPit() {
        return nextPit;
    }

    public void setNextPit(AbstractPit nextPit) {
        this.nextPit = nextPit;
    }

    public int getStoneAmount() {
        return stoneAmount;
    }

    public int getPlayerNo() {
        return playerNo;
    }

    public int getIndex() {
        return index;
    }

    public void incrementBy(int count) {
        this.stoneAmount += count;
    }

    public abstract void clearAmount();

    public abstract int shouldIncrement(int playerTakingMove);

}
