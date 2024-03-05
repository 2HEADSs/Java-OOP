package food_shortage;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Map<String, Buyer> personMap = new HashMap<>();

        int n = Integer.parseInt(scanner.nextLine());
        for (int i = 0; i < n; i++) {
            String[] tokens = scanner.nextLine().split("\\s+");
            String name = tokens[0];
            int age = Integer.parseInt(tokens[1]);

            if (tokens.length == 4) {
                Citizen citizen = new Citizen(name, age, tokens[2], tokens[3]);
                personMap.put(name, citizen);
            } else {
                Rebel rebel = new Rebel(name, age, tokens[2]);
                personMap.put(name, rebel);
            }
        }
        String line = scanner.nextLine();
        int totalFood;
        while (!"End".equals(line)) {
            String name = line;
            Buyer buyer = personMap.get(name);
            if (buyer != null) {
                buyer.buyFood();
            }
            line = scanner.nextLine();
        }
        totalFood = personMap.values().stream().mapToInt(Buyer::getFood).sum();
        System.out.println(totalFood);
    }
}

