package restaurant.models.waiter;


public class FullTimeWaiter extends BaseWaiter {


    public FullTimeWaiter(String name) {
        super(name, 8);
    }

    @Override
    public void work() {
        int newEfficiency = super.getEfficiency();
        newEfficiency -= 1;
        if (newEfficiency < 0) {
            newEfficiency = 0;
        }
        super.setEfficiency(newEfficiency);
    }
}
