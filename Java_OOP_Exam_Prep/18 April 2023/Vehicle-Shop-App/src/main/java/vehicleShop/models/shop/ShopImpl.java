package vehicleShop.models.shop;

import vehicleShop.models.tool.Tool;
import vehicleShop.models.vehicle.Vehicle;
import vehicleShop.models.worker.Worker;

import java.util.Collection;

public class ShopImpl implements Shop {
    @Override
    public void make(Vehicle vehicle, Worker worker) {
        Collection<Tool> tools = worker.getTools();
        while (worker.canWork() && !vehicle.reached()) {
            for (Tool tool : tools) {
                if (!tool.isUnfit()) {
                    worker.working();
                    vehicle.making();
                    tool.decreasesPower();
                }
            }
        }
    }
}
