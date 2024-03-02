package ShoppingSpree;

public class Product implements Identity {
    private String name;
    private double cost;


    public Product(String name, double cost) {
        setName(name);
        setCost(cost);
    }

    private void setName(String name) {
//        if (name == null || name.equals("\\s+") || name.isEmpty()) {
//            throw new IllegalArgumentException("Name cannot be empty");
//        }
        Validator.validateString(name);
        this.name = name;
    }

    private void setCost(double cost) {
//        if (cost < 0) {
//            throw new IllegalArgumentException("Money cannot be negative");
//        }
        Validator.validateValueNonNegative(cost);
        this.cost = cost;
    }

    @Override
    public String getName() {
        return name;
    }

    public double getCost() {
        return cost;
    }
}
