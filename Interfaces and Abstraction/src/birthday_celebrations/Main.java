package birthday_celebrations;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<Birthable> birthableList = new ArrayList<>();
        String line = scanner.nextLine();
        while (!"End".equals(line)) {
            String[] tokens = line.split("\\s+");
            switch (tokens[0]) {
                case "Citizen":
                    Citizen citizen = new Citizen(tokens[1], Integer.parseInt(tokens[2]), tokens[3], tokens[4]);
                    birthableList.add(citizen);
                    break;
                case "Pet":
                    Pet pet = new Pet(tokens[1], tokens[2]);
                    birthableList.add(pet);
                    break;
//                case "Robot":
//                    break;
            }
            line = scanner.nextLine();
        }
        String year = scanner.nextLine();
        birthableList.stream()
                .filter(birthable -> birthable.getBirthDate().endsWith(year))
                .forEach(birthable -> System.out.println(birthable.getBirthDate()));
    }
}

