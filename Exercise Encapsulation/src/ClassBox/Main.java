package ClassBox;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        double l = Double.parseDouble(scanner.nextLine());
        double w = Double.parseDouble(scanner.nextLine());
        double h = Double.parseDouble(scanner.nextLine());

        try {
            Box box = new Box(l, w, h);
            System.out.println(box.toString());
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }

    }
}
