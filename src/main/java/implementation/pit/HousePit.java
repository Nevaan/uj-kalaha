package implementation.pit;

public class HousePit extends AbstractPit {

    public HousePit(int stoneAmount) {
        super(stoneAmount);
    }

    @Override
    public void clearAmount() {
        this.stoneAmount = 0;
    }


}
