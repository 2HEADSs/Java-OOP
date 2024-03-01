package cards2;


public class Main {
    public static void main(String[] args) {
        CardRanks[] cardSuits = CardRanks.values();
        System.out.println("Card Ranks:");
        for (CardRanks card : cardSuits) {
            System.out.printf("Ordinal value: %d; Name value: %s%n", card.ordinal(), card.name());
        }
    }
}
