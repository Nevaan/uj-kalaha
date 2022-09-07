package implementation.pit;


public abstract class AbstractPit {

    protected int stoneAmount;
    private AbstractPit nextPit;
    private AbstractPit oppositePit;
    protected boolean isActive;

    protected AbstractPit(int stoneAmount, boolean isActive) {
        this.stoneAmount = stoneAmount;
        this.isActive = isActive;
    }

    public AbstractPit getNextPit() {
        return nextPit;
    }

    public int getStoneAmount() {
        return stoneAmount;
    }

    public void incrementBy(int count) {
        this.stoneAmount += count;
    }

    // PATTERN: metoda zaczepowa (hook method)
    public void clearAmount() {

    }

    public AbstractPit getOppositePit() {
        return oppositePit;
    }

    public void setOppositePit(AbstractPit oppositePit) {
        this.oppositePit = oppositePit;
    }

    public void toggleActive() {
        this.isActive = !this.isActive;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setNextPit(AbstractPit nextPit) {
        this.nextPit = nextPit;
    }


    // PATTERN: metoda szablonowa (template method)
    // PATTERN: lancuch zobowiazan (chain of responsibility)
    public boolean handleIncrement(int stones) {
        int handleInternal = handleInternal(stones);

        if (handleInternal == 0) {
            return shouldChangePlayer();
        } else {
            return this.nextPit.handleIncrement(handleInternal);
        }
    }

    protected abstract int handleInternal(int stones);

    protected abstract void incrementKalaha(int stones);

    protected abstract boolean shouldChangePlayer();

}
