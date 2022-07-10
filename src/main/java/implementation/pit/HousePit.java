package implementation.pit;

public class HousePit extends AbstractPit {

    public HousePit(int stoneAmount, boolean isActive) {
        super(stoneAmount, isActive);
    }

    @Override
    public void clearAmount() {
        this.stoneAmount = 0;
    }

    @Override
    protected int handleInternal(int stones) {

        if (stones > 1) {
            this.incrementBy(1);
            return stones - 1;
        } else  if (stones == 1) {

            AbstractPit oppositePit = getOppositePit();
            if (isActive && stoneAmount == 0 && oppositePit.getStoneAmount() > 0) {
                int stonesToTake = oppositePit.getStoneAmount() + 1;
                clearAmount();
                oppositePit.clearAmount();
                incrementKalaha(stonesToTake);
                return 0;
            } else {
                this.incrementBy(1);
                return 0;
            }

        } else {
            return 0;
        }

//        if (stones > 1 || !isActive){
//            this.incrementBy(1);
//        } else {
//            AbstractPit oppositePit = getOppositePit();
//            if(stoneAmount == 0 && oppositePit.getStoneAmount() > 0) {
//                int stonesToIncrement = oppositePit.getStoneAmount() + 1;
//                clearAmount();
//                oppositePit.clearAmount();
//                incrementKalaha(stonesToIncrement);
//            } else {
//                this.incrementBy(1);
//            }
//        }
//
//        return stones - 1;
    }

    @Override
    protected void incrementKalaha(int stones) {
        getNextPit().incrementKalaha(stones);
    }

    @Override
    protected boolean shouldChangePlayer() {
        return true;
    }


}
