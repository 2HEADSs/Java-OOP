package restaurant.models.working;

import restaurant.models.client.Client;
import restaurant.models.waiter.Waiter;

import java.util.Collection;

public class WorkingImpl implements Working {
    @Override
    public void takingOrders(Client client, Collection<Waiter> waiters) {
        Collection<String> orders = client.getClientOrders();
        for (Waiter waiter : waiters) {
            while (waiter.canWork() && orders.iterator().hasNext()) {
                String currentOrder = orders.iterator().next();
                waiter.work();
                waiter.takenOrders().getOrdersList().add(currentOrder);
                orders.remove(currentOrder);
            }
        }
    }
}
