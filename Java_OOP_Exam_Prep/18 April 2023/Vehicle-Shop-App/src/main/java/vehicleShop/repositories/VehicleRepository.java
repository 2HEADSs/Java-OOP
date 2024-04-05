package vehicleShop.repositories;

import vehicleShop.models.vehicle.Vehicle;

import java.util.Collection;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;

public class VehicleRepository implements Repository<Vehicle> {

    private Map<String, Vehicle> vehicles;

    public VehicleRepository() {
        this.vehicles = new LinkedHashMap<>();
    }

    @Override
    public Collection<Vehicle> getWorkers() {
        return Collections.unmodifiableCollection(this.vehicles.values());
    }

    @Override
    public void add(Vehicle vehicle) {
        this.vehicles.putIfAbsent(vehicle.getName(), vehicle);
    }

    @Override
    public boolean remove(Vehicle vehicle) {
        if (this.vehicles.containsKey(vehicle.getName())) {
            this.vehicles.remove(vehicle.getName());
            return true;
        }
        return false;
    }

    @Override
    public Vehicle findByName(String name) {
        if (this.vehicles.containsKey(name)) {
            return this.vehicles.get(name);
        }
        return null;
    }
}
