package cards2;


public class Main {
    public static void main(String[] args) {
        CardRansk[] cardSuits = CardRansk.values();
        System.out.println("Card Ranks:");
        for (CardRansk card : cardSuits) {
            System.out.printf("Ordinal value: %d; Name value: %s%n", card.ordinal(), card.name());
        }
    }
}
