import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();

        if(!input.equals("somethinig_else")){
            throw  new MyCustomExtension("My custom error");
        }
    }
}
