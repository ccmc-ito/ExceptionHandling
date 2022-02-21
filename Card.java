public class Card {
    final public static int MIN_RANK = 1;
    final public static int MAX_RANK = 13;

    private Suit suit;
    private int rank;

    public Card(Suit suit, int rank) throws CardRankOutOfRangeException {
        if(rank < Card.MIN_RANK || rank > Card.MAX_RANK) {
            throw new CardRankOutOfRangeException("RANKが範囲外です。");
        }

        this.suit = suit;
        this.rank = rank;
    }
    // このような例外処理をまとめるためにも、実際はsetRank(int rank)メソッドを作り利用した方が良い

    @Override
    public String toString() {
        return String.format("%2d/%s", this.rank, this.suit);
    }
}
