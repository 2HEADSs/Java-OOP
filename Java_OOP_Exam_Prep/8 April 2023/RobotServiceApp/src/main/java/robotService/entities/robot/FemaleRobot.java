package robotService.entities.robot;

public class FemaleRobot extends BaseRobot {
    public FemaleRobot(String name, String kind, double price) {
        super(name, kind, 7, price);
    }

    @Override
    public void eating() {
        int newKilos = super.getKilograms() + 1;
        super.setKilograms(newKilos);
    }
}
