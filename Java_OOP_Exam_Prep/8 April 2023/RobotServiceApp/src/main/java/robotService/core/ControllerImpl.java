package robotService.core;

import robotService.common.ConstantMessages;
import robotService.common.ExceptionMessages;
import robotService.entities.robot.FemaleRobot;
import robotService.entities.robot.MaleRobot;
import robotService.entities.robot.Robot;
import robotService.entities.services.MainService;
import robotService.entities.services.SecondaryService;
import robotService.entities.services.Service;
import robotService.entities.supplements.MetalArmor;
import robotService.entities.supplements.PlasticArmor;
import robotService.entities.supplements.Supplement;
import robotService.repositories.SupplementRepository;

import java.util.ArrayList;
import java.util.Collection;

public class ControllerImpl implements Controller {

    private SupplementRepository supplementRepository;
    private Collection<Service> services;

    public ControllerImpl() {
        this.supplementRepository = new SupplementRepository();
        this.services = new ArrayList<>();
    }

    @Override
    public String addService(String type, String name) {
        Service service;
        if (type.equals("MainService")) {
            service = new MainService(name);
        } else if (type.equals("SecondaryService")) {
            service = new SecondaryService(name);
        } else {
            throw new NullPointerException(ExceptionMessages.INVALID_SERVICE_TYPE);
        }
        this.services.add(service);
        return String.format(ConstantMessages.SUCCESSFULLY_ADDED_SERVICE_TYPE, type);
    }

    @Override
    public String addSupplement(String type) {
        Supplement supplement;
        if (type.equals("PlasticArmor")) {
            supplement = new PlasticArmor();
        } else if (type.equals("MetalArmor")) {
            supplement = new MetalArmor();
        } else {
            throw new IllegalArgumentException(ExceptionMessages.INVALID_SUPPLEMENT_TYPE);
        }
        this.supplementRepository.addSupplement(supplement);
        return String.format(ConstantMessages.SUCCESSFULLY_ADDED_SUPPLEMENT_TYPE, type);
    }

    @Override
    public String supplementForService(String serviceName, String supplementType) {
        Supplement supplement = this.supplementRepository.findFirst(supplementType);
        if (supplement == null) {
            throw new IllegalArgumentException(
                    String.format(ExceptionMessages.NO_SUPPLEMENT_FOUND, supplementType)
            );
        }
        for (Service service : this.services) {
            if (service.getName().equals(serviceName)) {
                service.addSupplement(supplement);
                this.supplementRepository.removeSupplement(supplement);
            }
        }
        return String.format(ConstantMessages.SUCCESSFULLY_ADDED_SUPPLEMENT_IN_SERVICE, supplementType, serviceName);
    }

    @Override
    public String addRobot(String serviceName, String robotType, String robotName, String robotKind, double price) {
        Robot robot;
        if (robotType.equals("MaleRobot")) {
            robot = new MaleRobot(robotName, robotKind, price);
        } else if (robotType.equals("FemaleRobot")) {
            robot = new FemaleRobot(robotName, robotKind, price);
        } else {
            throw new IllegalArgumentException(ExceptionMessages.INVALID_ROBOT_TYPE);
        }

        for (Service service : this.services) {
            if (service.getName().equals(serviceName)) {
                if (robotType.equals("MaleRobot") && service.getClass().getSimpleName().equals("MainService")) {
                    service.addRobot(robot);
                    return String.format(ConstantMessages.SUCCESSFULLY_ADDED_ROBOT_IN_SERVICE, robotType, serviceName);
                } else if (robotType.equals("FemaleRobot") && service.getClass().getSimpleName().equals("SecondaryService")) {
                    service.addRobot(robot);
                    return String.format(ConstantMessages.SUCCESSFULLY_ADDED_ROBOT_IN_SERVICE, robotType, serviceName);
                } else {
                    return ConstantMessages.UNSUITABLE_SERVICE;
                }
            }
        }

        return null;
    }

    @Override
    public String feedingRobot(String serviceName) {
        int feedRobots = 0;
        for (Service service : this.services) {
            if (service.getName().equals(serviceName)) {
                service.feeding();
                feedRobots += service.getRobots().size();
            }
        }

        return String.format(ConstantMessages.FEEDING_ROBOT, feedRobots);
    }

    @Override
    public String sumOfAll(String serviceName) {
        double value = 0;
        for (Service service : this.services) {
            if (service.getName().equals(serviceName)) {
                for (Robot robot : service.getRobots()) {
                    value += robot.getPrice();
                }
                for (Supplement supplement : service.getSupplements()) {
                    value += supplement.getPrice();
                }
            }
        }
        return String.format(ConstantMessages.VALUE_SERVICE, serviceName, value);
    }

    @Override
    public String getStatistics() {
        StringBuilder sb = new StringBuilder();
        for (Service service : this.services) {
            sb.append(service.getStatistics());
        }
        return sb.toString().trim();
    }
}
