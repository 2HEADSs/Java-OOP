package ShoppingSpree;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Person implements Identity {
    private String name;
    private double money;
    private List<Product> products;

    public Person(String name, double money) {
        setName(name);
        setMoney(money);
        this.products = new ArrayList<>();
    }

    private void setName(String name) {
//        if (name == null || name.equals("\\s+") || name.isEmpty()) {
//            throw new IllegalArgumentException("Name cannot be empty");
//        }
        Validator.validateString(name);

        this.name = name;
    }

    private void setMoney(double money) {
//        if (money < 0) {
//            throw new IllegalArgumentException("Money cannot be negative");
//        }
        Validator.validateValueNonNegative(money);

        this.money = money;
    }

    public void buyProduct(Product product) {
        money = money - product.getCost();
        this.products.add(product);

    }

    public double getMoney() {
        return money;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return String.format("%s - %s", name,
                products.isEmpty()
                        ? "Nothing bought"
                        : products.stream()
                        .map(Product::getName)
                        .collect(Collectors.joining(", "))
        );
    }
}
