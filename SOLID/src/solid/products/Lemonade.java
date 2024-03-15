package solid.products;

public class Lemonade implements Product{

    public static final double CALORIES_PER_100_GRAMS = 53.0;
    public static final double DENSITY = 0.7;

    private double milliliters;

    public Lemonade(double milliliters) {
        this.milliliters = milliliters;
    }

    public double getMilliliters() {
        return milliliters;
    }

    @Override
    public double amountOfCalories() {
        double grams = getMilliliters() * DENSITY;
        return (Coke.CALORIES_PER_100_GRAMS / 100) * grams;
    }
}
