package implementation.pit;


public abstract class AbstractPit {

    protected int stoneAmount;
    private AbstractPit nextPit;
    private AbstractPit oppositePit;

    public AbstractPit(int stoneAmount) {
        this.stoneAmount = stoneAmount;
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

    public void incrementBy(int count) {
        this.stoneAmount += count;
    }

    public abstract void clearAmount();

    public AbstractPit getOppositePit() {
        return oppositePit;
    }

    public void setOppositePit(AbstractPit oppositePit) {
        this.oppositePit = oppositePit;
    }
}
