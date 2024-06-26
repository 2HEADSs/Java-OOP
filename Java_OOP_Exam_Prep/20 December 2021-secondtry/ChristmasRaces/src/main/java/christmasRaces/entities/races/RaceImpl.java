package christmasRaces.entities.races;

import christmasRaces.common.ExceptionMessages;
import christmasRaces.entities.drivers.Driver;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

public class RaceImpl implements Race {

    private String name;
    private int laps;
    private Collection<Driver> drivers;

    public RaceImpl(String name, int laps) {
        this.setName(name);
        this.setLaps(laps);
        this.setDrivers();
    }

    public void setName(String name) {
        if (name == null || name.trim().length() < 5) {
            throw new IllegalArgumentException(
                    String.format(ExceptionMessages.INVALID_NAME, name, 5)
            );
        }
        this.name = name;
    }

    public void setDrivers() {
        this.drivers = new ArrayList<>();
    }

    @Override
    public String getName() {
        return this.name;
    }

    public void setLaps(int laps) {
        if (laps < 1) {
            throw new IllegalArgumentException(
                    String.format(ExceptionMessages.INVALID_NUMBER_OF_LAPS, 1)
            );
        }
        this.laps = laps;
    }

    @Override
    public int getLaps() {
        return this.laps;
    }

    @Override
    public Collection<Driver> getDrivers() {
        return this.drivers;
    }



    @Override
    public void addDriver(Driver driver) {
        if (driver == null) {
            throw new IllegalArgumentException(
                    String.format(ExceptionMessages.DRIVER_INVALID)
            );
        } else if (!driver.getCanParticipate()) {
            throw new IllegalArgumentException(
                    String.format(ExceptionMessages.DRIVER_NOT_PARTICIPATE,driver.getName())
            );
        }else if(drivers.contains(driver)){
            throw new IllegalArgumentException(
                    String.format(ExceptionMessages.DRIVER_ALREADY_ADDED, driver.getName(),this.name)
            );
        }

        drivers.add(driver);
    }
}
