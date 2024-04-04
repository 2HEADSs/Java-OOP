package robotService.entities.robot;

public class MaleRobot extends BaseRobot{
    public MaleRobot(String name, String kind, double price) {
        super(name, kind, 9, price);
    }

    @Override
    public void eating() {
        int newKilos = super.getKilograms() + 3;
        super.setKilograms(newKilos);
    }
}
