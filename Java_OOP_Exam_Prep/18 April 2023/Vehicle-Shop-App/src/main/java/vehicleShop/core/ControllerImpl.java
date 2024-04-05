package vehicleShop.core;

import vehicleShop.common.ConstantMessages;
import vehicleShop.common.ExceptionMessages;
import vehicleShop.models.shop.Shop;
import vehicleShop.models.shop.ShopImpl;
import vehicleShop.models.tool.Tool;
import vehicleShop.models.tool.ToolImpl;
import vehicleShop.models.vehicle.Vehicle;
import vehicleShop.models.vehicle.VehicleImpl;
import vehicleShop.models.worker.FirstShift;
import vehicleShop.models.worker.SecondShift;
import vehicleShop.models.worker.Worker;
import vehicleShop.repositories.Repository;
import vehicleShop.repositories.VehicleRepository;
import vehicleShop.repositories.WorkerRepository;

import java.util.Collection;
import java.util.stream.Collectors;

public class ControllerImpl implements Controller {
    private Repository<Vehicle> vehicleRepository;
    private Repository<Worker> workerRepository;

    public ControllerImpl() {
        vehicleRepository = new VehicleRepository();
        workerRepository = new WorkerRepository();
    }

    @Override
    public String addWorker(String type, String workerName) {
        Worker worker;
        if (type.equals("FirstShift")) {
            worker = new FirstShift(workerName);
        } else if (type.equals("SecondShift")) {
            worker = new SecondShift(workerName);
        } else {
            throw new IllegalArgumentException(ExceptionMessages.WORKER_TYPE_DOESNT_EXIST);
        }
        this.workerRepository.add(worker);
        return String.format(ConstantMessages.ADDED_WORKER, type, workerName);

    }

    @Override
    public String addVehicle(String vehicleName, int strengthRequired) {
        Vehicle vehicle = new VehicleImpl(vehicleName, strengthRequired);
        this.vehicleRepository.add(vehicle);
        return String.format(ConstantMessages.SUCCESSFULLY_ADDED_VEHICLE, vehicleName);

    }

    @Override
    public String addToolToWorker(String workerName, int power) {
        Worker worker = this.workerRepository.findByName(workerName);
        if (worker == null) {
            throw new IllegalArgumentException(ExceptionMessages.HELPER_DOESNT_EXIST);
        }
        Tool tool = new ToolImpl(power);
        worker.addTool(tool);
        return String.format(ConstantMessages.SUCCESSFULLY_ADDED_TOOL_TO_WORKER, power, workerName);
    }

    @Override
    public String makingVehicle(String vehicleName) {
        Collection<Worker> currentWorkers = this.workerRepository.getWorkers()
                .stream()
                .filter(w -> w.getStrength() > 70)
                .collect(Collectors.toList());
        if (currentWorkers.isEmpty()) {
            throw new IllegalArgumentException(ExceptionMessages.NO_WORKER_READY);
        }
        Vehicle vehicle = this.vehicleRepository.findByName(vehicleName);
        Shop shop = new ShopImpl();
        int usedTools = 0;

        for (Worker currentWorker : currentWorkers) {
            shop.make(vehicle, currentWorker);
            for (Tool tool : currentWorker.getTools()) {
                if (!tool.isUnfit()) {
                    usedTools++;
                }
        }
            if (vehicle.reached()) {
                break;
            }
        }
        if (vehicle.reached()) {
            return String.format(ConstantMessages.VEHICLE_DONE, vehicleName, "done") + String.format(ConstantMessages.COUNT_BROKEN_INSTRUMENTS, usedTools);
        } else {
            return String.format(ConstantMessages.VEHICLE_DONE, vehicleName, "not done") + String.format(ConstantMessages.COUNT_BROKEN_INSTRUMENTS, usedTools);

        }
    }

    @Override
    public String statistics() {
        int madeCars = 0;
        for (Vehicle vehicle : this.vehicleRepository.getWorkers()) {
            if(vehicle.reached()){
                madeCars++;
            }
        }

        StringBuilder sb = new StringBuilder();
        sb.append(String.format("%d vehicles are ready!",madeCars)).append(System.lineSeparator());
        sb.append("Info for workers:").append(System.lineSeparator());
        for (Worker worker : this.workerRepository.getWorkers()) {
            int leftTools = 0;
            for (Tool tool : worker.getTools()) {
                if(!tool.isUnfit()){
                    leftTools++;
                }
            }
            sb.append(String.format("Name: %s, Strength: %d",worker.getName(),worker.getStrength()));
            sb.append(System.lineSeparator());
            sb.append(String.format("Tools: %s fit left",leftTools));
            sb.append(System.lineSeparator());
        }

        return sb.toString().trim();
    }
}
