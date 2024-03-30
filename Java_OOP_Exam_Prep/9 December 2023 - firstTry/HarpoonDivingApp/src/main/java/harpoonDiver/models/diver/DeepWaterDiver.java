package harpoonDiver.models.diver;

public class DeepWaterDiver extends BaseDiver {
    private static double INITIAL_OXYGEN = 90;

    public DeepWaterDiver(String name) {
        super(name, INITIAL_OXYGEN);
    }
}
