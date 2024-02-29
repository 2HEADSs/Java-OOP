package cards1;

public class Main {
    public static void main(String[] args) {
        CardSuits[] cardSuits = CardSuits.values();
        System.out.println("Card Suits:");
        for (CardSuits card : cardSuits) {
            System.out.printf("Ordinal value: %d; Name value: %s%n", card.ordinal(), card.name());
        }
    }
}
