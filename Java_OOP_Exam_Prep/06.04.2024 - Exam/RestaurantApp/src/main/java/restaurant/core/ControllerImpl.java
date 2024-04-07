package restaurant.core;

import restaurant.common.ConstantMessages;
import restaurant.common.ExceptionMessages;
import restaurant.models.client.Client;
import restaurant.models.client.ClientImpl;
import restaurant.models.orders.TakenOrders;
import restaurant.models.orders.TakenOrdersImpl;
import restaurant.models.waiter.FullTimeWaiter;
import restaurant.models.waiter.HalfTimeWaiter;
import restaurant.models.waiter.Waiter;
import restaurant.models.working.Working;
import restaurant.models.working.WorkingImpl;
import restaurant.repositories.ClientRepository;
import restaurant.repositories.Repository;
import restaurant.repositories.WaiterRepository;

import java.util.Collection;
import java.util.Collections;

public class ControllerImpl implements Controller {

    private final Repository<Waiter> waiterRepository;
    private final Repository<Client> clientRepository;
    int totalOrders;

    public ControllerImpl() {
        this.waiterRepository = new WaiterRepository();
        this.clientRepository = new ClientRepository();
        this.totalOrders = 0;
    }

    @Override
    public String addWaiter(String type, String waiterName) {
        Waiter waiter;
        if (type.equals("HalfTimeWaiter")) {
            waiter = new HalfTimeWaiter(waiterName);
        } else if (type.equals("FullTimeWaiter")) {
            waiter = new FullTimeWaiter(waiterName);
        } else {
            throw new IllegalArgumentException(ExceptionMessages.WAITER_INVALID_TYPE);
        }
        this.waiterRepository.add(waiter);
        return String.format(ConstantMessages.WAITER_ADDED, type, waiterName);
    }

    @Override
    public String addClient(String clientName, String... orders) {
        Client client = new ClientImpl(clientName);
        for (String order : orders) {
            client.getClientOrders().add(order);
        }
        this.clientRepository.add(client);
        return String.format(ConstantMessages.CLIENT_ADDED, clientName);
    }

    @Override
    public String removeWaiter(String waiterName) {
        Waiter waiter = this.waiterRepository.byName(waiterName);
        if (waiter == null) {
            throw new IllegalArgumentException(String.format(ExceptionMessages.WAITER_DOES_NOT_EXIST, waiterName));
        }
        this.waiterRepository.remove(waiter);
        return String.format(ConstantMessages.WAITER_REMOVE, waiterName);
    }

    @Override
    public String removeClient(String clientName) {
        Client client = this.clientRepository.byName(clientName);
        if (client == null) {
            throw new IllegalArgumentException(String.format(ExceptionMessages.CLIENT_DOES_NOT_EXIST, clientName));
        }
        this.clientRepository.remove(client);
        return String.format(ConstantMessages.CLIENT_REMOVE, clientName);
    }

    @Override
    public String startWorking(String clientName) {
        Collection<Waiter> waiters = this.waiterRepository.getCollection();
        if (waiters.isEmpty()) {
            throw new IllegalArgumentException(ExceptionMessages.THERE_ARE_NO_WAITERS);
        }
        Client client = this.clientRepository.byName(clientName);
        int startOrders = client.getClientOrders().size();
        Working working = new WorkingImpl();
        working.takingOrders(client, waiters);


        this.totalOrders += 1;
        return String.format(ConstantMessages.ORDERS_SERVING, clientName);
    }

    @Override
    public String getStatistics() {
        StringBuilder sb = new StringBuilder();
        sb.append(String.format(ConstantMessages.FINAL_CLIENTS_COUNT, totalOrders));
        sb.append(System.lineSeparator());
        sb.append(ConstantMessages.FINAL_WAITERS_STATISTICS);
        sb.append(System.lineSeparator());
        for (Waiter waiter : this.waiterRepository.getCollection()) {
            sb.append(String.format(ConstantMessages.FINAL_WAITER_NAME, waiter.getName()));
            sb.append(System.lineSeparator());
            sb.append(String.format(ConstantMessages.FINAL_WAITER_EFFICIENCY, waiter.getEfficiency()));
            sb.append(System.lineSeparator());
            if (waiter.takenOrders().getOrdersList().isEmpty()) {
                sb.append(String.format(ConstantMessages.FINAL_WAITER_ORDERS, "None")).append(System.lineSeparator());
            } else {
                sb.append(String.format(ConstantMessages.FINAL_WAITER_ORDERS,
                        String.join(ConstantMessages.FINAL_WAITER_ORDERS_DELIMITER,
                                waiter.takenOrders().getOrdersList())));
                sb.append(System.lineSeparator());
            }
        }
        return sb.toString().trim();
    }
}
