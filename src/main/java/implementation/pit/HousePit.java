package implementation.pit;

public class HousePit extends AbstractPit {

    public HousePit(int idx, int stoneAmount,  int playerNo) {
        super(idx, stoneAmount, playerNo);
    }

    @Override
    public void clearAmount() {
        this.stoneAmount = 0;
    }

    @Override
    public int shouldIncrement(int playerTakingMove) {
        return this.getIndex();
    }

}
