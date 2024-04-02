package climbers.models.climber;

public class RockClimber extends BaseClimber{
    private static double STRENGTH = 120;

    public RockClimber(String name) {
        super(name, STRENGTH);
    }


    @Override
    public void climb() {
        double newStrength = super.getStrength();
        newStrength -= 60;
        if (newStrength < 0) {
            newStrength = 0;
        }
        super.setStrength(newStrength);
    }
}
