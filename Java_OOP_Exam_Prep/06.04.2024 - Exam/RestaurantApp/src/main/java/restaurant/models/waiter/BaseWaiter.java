package restaurant.models.waiter;


import restaurant.common.ExceptionMessages;
import restaurant.models.orders.TakenOrders;
import restaurant.models.orders.TakenOrdersImpl;

public abstract class BaseWaiter implements Waiter {
    private String name;
    private int efficiency;
    private TakenOrders takenOrders;

    public BaseWaiter(String name, int efficiency) {
        this.setName(name);
        this.setEfficiency(efficiency);
        this.takenOrders = new TakenOrdersImpl();
    }

    public void setName(String name) {
        if (name == null || name.trim().isEmpty()) {
            throw new NullPointerException(ExceptionMessages.WAITER_NAME_NULL_OR_EMPTY);
        }
        this.name = name;
    }

    public void setEfficiency(int efficiency) {
        if (efficiency < 0) {
            throw new IllegalArgumentException(ExceptionMessages.WAITER_EFFICIENCY_LESS_THAN_ZERO);
        }
        this.efficiency = efficiency;
    }


    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public int getEfficiency() {
        return this.efficiency;
    }

    @Override
    public boolean canWork() {
        return this.efficiency > 0;
    }

    @Override
    public TakenOrders takenOrders() {
        return this.takenOrders;
    }

    @Override
    public abstract void work();
}
