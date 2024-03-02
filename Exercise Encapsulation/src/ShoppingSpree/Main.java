package ShoppingSpree;

import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.Optional;
import java.util.Scanner;
import java.util.function.BiFunction;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        LinkedHashMap<String, Person> people = readMapOf(scanner, Person::new);

        LinkedHashMap<String, Product> products = readMapOf(scanner, Product::new);
        String input = scanner.nextLine();
        while (!input.equalsIgnoreCase("end")) {

            String[] tokens = input.split("\\s+");
            Person person = people.get(tokens[0]);
            Product product = products.get(tokens[1]);

            if (person != null || product != null) {
                sellProduct(person, product);
            }

            input = scanner.nextLine();
        }
        people.values().stream()
                .map(Person::toString)
                .collect(Collectors.joining(System.lineSeparator()));

    }

    private static void sellProduct(Person person, Product product) {
        if (person.getMoney() < product.getCost()) {
            System.out.printf("%s can't afford %s%n", person.getName(), product.getName());
            return;
        }

        System.out.printf("%s bought %s%n", person.getName(), product.getName());
        person.buyProduct(product);

    }

    private static <T extends Identity> LinkedHashMap<String, T> readMapOf(Scanner scanner, BiFunction<String, Double, T> constructor) {
        return Arrays.stream(scanner.nextLine().split(";"))
                .map(s -> createEntity(s, constructor))
                .filter(Optional::isPresent)
                .map(Optional::get)
                .collect(Collectors.toMap(Identity::getName, p -> p, (x, y) -> y, LinkedHashMap::new));
    }

    public static <T> Optional<T> createEntity(String data, BiFunction<String, Double, T> createEntity) {
        String[] tokens = data.split("=");
        String name = tokens[0];
        double money = Double.parseDouble(tokens[1]);

        Optional<T> entity = Optional.empty();

        try {
            entity = Optional.of(createEntity.apply(name, money));
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
        return entity;
    }

}
