package harpoonDiver.models.diver;

public class OpenWaterDiver extends BaseDiver{
    private static double INITIAL_OXYGEN = 30;

    public OpenWaterDiver(String name) {
        super(name,INITIAL_OXYGEN);
    }
}
