package vehicleShop.repositories;

import vehicleShop.models.worker.Worker;

import java.util.Collection;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;

public class WorkerRepository implements Repository<Worker> {
    private Map<String, Worker> workers;

    public WorkerRepository() {
        this.workers = new LinkedHashMap<>();
    }

    @Override
    public Collection<Worker> getWorkers() {
        return Collections.unmodifiableCollection(this.workers.values());
    }

    @Override
    public void add(Worker worker) {
        this.workers.putIfAbsent(worker.getName(), worker);
    }

    @Override
    public boolean remove(Worker worker) {
        if (this.workers.containsKey(worker.getName())) {
            this.workers.remove(worker.getName());
            return true;
        }
        return false;
    }

    @Override
    public Worker findByName(String name) {
        if (this.workers.containsKey(name)) {
            return this.workers.get(name);
        }
        return null;
    }
}
