public enum HandValues {
    ROYAL_FLUSH(900), STRAIGHT_FLUSH(800), FOUR_OF_A_KIND(700), FULL_HOUSE(600),
    FLUSH(500), STRAIGHT(400), THREE_OF_A_KIND(300), TWO_PAIR(200), ONE_PAIR(100);
    final int values;
    HandValues(int values){
        this.values = values;
    }
}
