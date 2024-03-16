package solid;

import solid.products.Cloud;
import solid.products.QuantityCalculator;

import java.util.List;

public class Main {

    public static void main(String[] args) {
        Cloud cloud = new Cloud();
        QuantityCalculator.average(List.of(cloud));
    }
}
