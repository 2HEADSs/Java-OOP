package wild_farm;


import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String line = scanner.nextLine();

        while (!line.equals("End")) {
            Animal animal = createAnimal(line);
            Food food = createFood(scanner.nextLine());
            animal.makeSound();
            animal.eatFood(food);


            System.out.println(animal.toString());
            line = scanner.nextLine();
        }
    }

    private static Animal createAnimal(String line) {
        String[] tokens = line.split("\\s+");
        String type = tokens[0];
        String name = tokens[1];
        Double weight = Double.parseDouble(tokens[2]);
        String livingRegion = tokens[3];

        Animal animal;
        if (type.equals("Cat")) {
            animal = new Cat(name, type, weight, livingRegion, tokens[4]);
        } else if (type.equals("Mouse")) {
            animal = new Mouse(name, type, weight, livingRegion);
        } else if (type.equals("Tiger")) {
            animal = new Tiger(name, type, weight, livingRegion);
        } else {
            animal = new Zebra(name, type, weight, livingRegion);
        }

        return animal;
    };


    private static Food createFood(String line) {
        String[] tokens = line.split("\\s+");
        String foodType = tokens[0];
        Integer quantity = Integer.parseInt(tokens[1]);
        return foodType.equals("Meat") ? new Meat(quantity) : new Vegetable(quantity);
    }
}
