package climbers.models.climber;

import climbers.common.ExceptionMessages;
import climbers.models.roster.Roster;
import climbers.models.roster.RosterImpl;

public abstract class BaseClimber implements Climber {
    private String name;
    private double strength;
    private Roster roster;

    public BaseClimber(String name, double strength) {
        this.setName(name);
        this.setStrength(strength);
        this.roster = new RosterImpl();
    }

    public void setName(String name) {
        if (name == null || name.trim().isEmpty()) {
            throw new NullPointerException(ExceptionMessages.CLIMBER_NAME_NULL_OR_EMPTY);
        }
        this.name = name;
    }

    public void setStrength(double strength) {
        if (strength < 0) {
            throw new IllegalArgumentException(ExceptionMessages.CLIMBER_STRENGTH_LESS_THAN_ZERO);
        }
        this.strength = strength;
    }

    public void setRoster(Roster roster) {
        this.roster = roster;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public double getStrength() {
        return this.strength;
    }

    @Override
    public boolean canClimb() {
        return this.strength > 0;
    }

    @Override
    public Roster getRoster() {
        return this.roster;
    }

}
