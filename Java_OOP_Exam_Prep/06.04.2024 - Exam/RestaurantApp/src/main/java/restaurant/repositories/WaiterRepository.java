package restaurant.repositories;

import restaurant.models.waiter.Waiter;

import java.util.Collection;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;

public class WaiterRepository implements Repository<Waiter> {
    private Map<String, Waiter> waiters;

    public WaiterRepository() {
        this.waiters = new LinkedHashMap<>();
    }

    @Override
    public Collection<Waiter> getCollection() {
        return Collections.unmodifiableCollection(this.waiters.values());
    }

    @Override
    public void add(Waiter waiter) {
        this.waiters.put(waiter.getName(), waiter);
    }

    @Override
    public boolean remove(Waiter waiter) {
        if (this.waiters.containsKey(waiter.getName())) {
            this.waiters.remove(waiter.getName());
            return true;
        }
        return false;
    }

    @Override
    public Waiter byName(String name) {
        if (waiters.containsKey(name)) {
            return this.waiters.get(name);
        } else {
            return null;
        }
    }
}
