package restaurant.models.waiter;


public class HalfTimeWaiter extends BaseWaiter {

    public HalfTimeWaiter(String name) {
        super(name, 4);
    }

    @Override
    public void work() {
        int newEfficiency = super.getEfficiency();
        newEfficiency -= 2;
        if (newEfficiency < 0) {
            newEfficiency = 0;
        }
        super.setEfficiency(newEfficiency);
    }
}
