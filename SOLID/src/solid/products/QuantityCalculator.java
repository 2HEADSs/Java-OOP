package solid.products;

import java.util.List;

public class QuantityCalculator {
    private QuantityCalculator(){}
    public static double sum(List<Food> products) {
        return products.stream().mapToDouble(Food::amountOfFood).sum();
    }

    public static double average(List<Food> products) {
        return sum(products) / products.size();
    }

    public static double sumDrink(List<Drink> products) {
        return products.stream().mapToDouble(Drink::amountOfDrinks).sum();
    }

    public static double averageDrink(List<Drink> products) {
        return sumDrink(products) / products.size();
    }
}
