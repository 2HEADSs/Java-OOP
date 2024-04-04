package robotService.entities.services;

import robotService.common.ConstantMessages;
import robotService.common.ExceptionMessages;
import robotService.entities.robot.Robot;
import robotService.entities.supplements.Supplement;

import java.util.ArrayList;
import java.util.Collection;
import java.util.stream.Collectors;

public abstract class BaseService implements Service {
    private String name;
    private int capacity;
    private Collection<Supplement> supplements;
    private Collection<Robot> robots;


    public BaseService(String name, int capacity) {
        this.setName(name);
        this.setCapacity(capacity);
        this.supplements = new ArrayList<>();
        this.robots = new ArrayList<>();
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    @Override
    public void setName(String name) {
        if (name == null || name.trim().isEmpty()) {
            throw new NullPointerException(ExceptionMessages.SERVICE_NAME_CANNOT_BE_NULL_OR_EMPTY);
        }
        this.name = name;
    }

    @Override
    public String getName() {
        return this.name;
    }


    @Override
    public Collection<Robot> getRobots() {
        return this.robots;
    }

    @Override
    public Collection<Supplement> getSupplements() {
        return this.supplements;
    }

    @Override
    public void addRobot(Robot robot) {
        if (this.robots.size() >= this.capacity) {
            throw new IllegalArgumentException(ConstantMessages.NOT_ENOUGH_CAPACITY_FOR_ROBOT);
        }
        this.robots.add(robot);
    }

    @Override
    public void removeRobot(Robot robot) {
        this.robots.remove(robot);

    }

    @Override
    public void addSupplement(Supplement supplement) {
        this.supplements.add(supplement);
    }

    @Override
    public void feeding() {
        for (Robot robot : this.robots) {
            robot.eating();
        }
    }

    @Override
    public int sumHardness() {
        int sum = 0;
        for (Supplement supplement : this.supplements) {
            sum += supplement.getHardness();
        }
        return sum;
    }

    @Override
    public String getStatistics() {

        if (this.robots.isEmpty()) {
            return String.format("%s %s:%nRobots: none%nSupplements: %d Hardness: %d%n",
                    this.name, this.getClass().getSimpleName(),
                    this.supplements.size(),
                    this.sumHardness()
            );
        } else {
            return String.format("%s %s:%nRobots: %s%nSupplements: %d Hardness: %d%n",
                    this.name, this.getClass().getSimpleName(),
                    this.robots.stream().map(Robot::getName).collect(Collectors.joining(" ")),
                    this.supplements.size(),
                    this.sumHardness()
            );
        }

    }
}
