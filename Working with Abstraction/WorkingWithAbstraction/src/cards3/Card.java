package cards3;

public class Card {
    private CardSuits cardSuits;
    private CardRanks cardRanks;

    public Card(CardSuits cardSuits, CardRanks cardRanks) {
        this.cardSuits = cardSuits;
        this.cardRanks = cardRanks;
    }

    public int calculatePower() {
        return this.cardSuits.getValue() + this.cardRanks.getValue();
    }
}
