package ShoppingSpree;

public class Validator {
    private Validator(){

    }
    public static void validateString(String string) {
        if (string == null || string.isBlank()) {
            throw new IllegalArgumentException("Name cannot be empty");
        }
    }

    public static void validateValueNonNegative(double value) {
        if (value < 0) {
            throw new IllegalArgumentException("Money cannot be negative");
        }
    }

}
