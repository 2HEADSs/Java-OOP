package cards3;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        CardRanks[] cardRanks = CardRanks.values();
        CardSuits[] cardSuits = CardSuits.values();

        Scanner scanner = new Scanner(System.in);
        String rank = scanner.nextLine();
        String suit = scanner.nextLine();

        Card card = new Card(CardSuits.valueOf(suit), CardRanks.valueOf(rank));


        System.out.printf("Card name: %s of %s; Card power: %d", rank,suit, card.calculatePower());

    }
}
