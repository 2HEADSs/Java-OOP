package solid;

import solid.products.Chocolate;
import solid.products.Coke;
import solid.products.Lemonade;
import solid.products.Product;

import java.util.List;

public class CalorieCalculator {


    public CalorieCalculator() {
    }

    public double sum(List<Product> products) {
        double sum = 0;
        products.stream().mapToDouble(Product::amountOfCalories);


        return sum;
    }

    public double average(List<Product> products) {
        return sum(products) / products.size();
    }

}
