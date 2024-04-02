package climbers.models.climber;

public class WallClimber extends BaseClimber {
    private static double STRENGTH = 90;


    public WallClimber(String name) {
        super(name, STRENGTH);
    }

    @Override
    public void climb() {
        double newStrength = super.getStrength();
        newStrength -= 30;
        if (newStrength < 0) {
            newStrength = 0;
        }
        super.setStrength(newStrength);
    }
}
