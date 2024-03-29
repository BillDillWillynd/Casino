import java.util.*;

public class WinConditions {
    protected List<String> currentGlobalHand;
    protected List<String> personalHand;

    WinConditions(List<String> currentGlobalHand, List<String> personalHand) {
        this.currentGlobalHand = currentGlobalHand;
        this.personalHand = personalHand;

    }

    protected int Calculator(int constructorNum) {
        int Totalvalue = 0;

        if(constructorNum == 1){

            List<List<String>> tempHand = new ArrayList<>(TriGlobalHandComb(currentGlobalHand, new ArrayList<>()));
            List<List<Integer>> handSuite = new ArrayList<>(toSortedNumericHand(toUnsortedNumOrSuite(tempHand, "suite")));
            List<List<Integer>> handNum = new ArrayList<>(toSortedNumericHand(toUnsortedNumOrSuite(tempHand, "num")));

            int[] valueTracker = new int[10];
            for(int i = 0; i<valueTracker.length; i++) valueTracker[i] = 0;
            if(currentGlobalHand.size() >= 5){
                valueTracker[4] = Straight(handNum);
                valueTracker[5] = Flush(handSuite);
                valueTracker[8] = StraightFlush(valueTracker[4], valueTracker[5]);
                valueTracker[9] = RoyalFlush(valueTracker[4], valueTracker[5]);
                if(valueTracker[9] != 0) return valueTracker[9];
                else if(valueTracker[8] != 0) return valueTracker[8];
                else if(valueTracker[5] != 0) return valueTracker[5];
                else if(valueTracker[4] != 0) return valueTracker[4];
                else{
                    valueTracker[6] = FullHouse(handNum);
                    if(valueTracker[6] != 0) return valueTracker[6];
                    else{
                        int ofAkind = OfAkind(handNum);
                        if (ofAkind > 100 && ofAkind < 115) valueTracker[1] = ofAkind;
                        else if (ofAkind > 200 && ofAkind < 215) valueTracker[2] = ofAkind;
                        else if (ofAkind > 300 && ofAkind < 315) valueTracker[3] = ofAkind;
                        else if (ofAkind > 700 && ofAkind < 715) valueTracker[7] = ofAkind;
                        if (ofAkind != 0) return ofAkind;
                        else{
                            valueTracker[0] = HighCard(personalHand);
                            return valueTracker[0];
                        }
                    }
                }
            }
        }else if(constructorNum == 2){
            List<Integer> personalHand2 = new ArrayList<>();
            for(int i = 0; i<2; i++){
                personalHand2.add(Integer.parseInt(personalHand.get(i).substring(0, personalHand.get(i).length()-1)));
            }
            int highCard = Collections.max(personalHand2);
            if(personalHand2.get(0).equals(personalHand2.get(1))){
                return HandValues.ONE_PAIR.values + highCard;
            }
            else return highCard;
        }
        return 0;
    }
    protected int RoyalFlush(int Straight, int Flush){
        int value = 0;
        if(Straight == 414 && Flush > 0 ){
            value = HandValues.ROYAL_FLUSH.values;
        }
        return value;
    }
    protected int StraightFlush(int Straight, int Flush){
        int value = 0;
        if(Straight > 0 && Flush > 0){
            value = HandValues.STRAIGHT_FLUSH.values;
        }
        return value;
    }
    protected int Straight(List<List<Integer>> handNum){
        int value = 0;
        for(int i = 0; i<handNum.size(); i++){
            int count = 1;
            for(int j = 0; j<handNum.get(i).size()-1; j++){
                if((handNum.get(i).get(j))+1 == handNum.get(i).get(j+1)){
                    count++;
                }
            }
            if(count == 5){
                int highCard = Collections.max(handNum.get(i));
                value = HandValues.STRAIGHT.values + highCard;
                break;
            }
        }

        return value;
    }
    protected int Flush(List<List<Integer>> handSuite){
        int value = 0;
        for(int i = 0; i<handSuite.size(); i++){
            int count = 1;
            for(int j = 0; j<handSuite.get(i).size()-1; j++){
                if(handSuite.get(i).get(j) == handSuite.get(i).get(j+1)){
                    count++;
                }
            }
            if(count == 5){
                int higCard = HighCard(personalHand);
                value = HandValues.FLUSH.values + higCard;
                break;
            }
        }
        return value;
    }
    protected int OfAkind(List<List<Integer>> handNum){
        List<Integer> PairHolder = new ArrayList<>(); //highCard decider
        int highCard = 0;
        int temp = 0;
        int value = 0;
        int numOfPairsCMP = 0;
        int numOfPairs = 0;
        for(List<Integer> list : handNum){
            int count = 1;
            numOfPairsCMP = 0;
            for(int i = 0; i<list.size()-1; i++){
                if(list.get(i).equals(list.get(i+1))){
                    PairHolder.add(list.get(i));
                    PairHolder.add(list.get(i+1));
                    count++;
                    numOfPairsCMP++;
                    temp = count;
                    if(temp > value) value = temp;
                    if(numOfPairsCMP  > numOfPairs) numOfPairs = numOfPairsCMP ;
                    highCard = Collections.max(PairHolder);
                }else{
                    count = 1;
                }
            }
        }
        if(value == 4) return HandValues.FOUR_OF_A_KIND.values + highCard;
        else if(value == 3) return HandValues.THREE_OF_A_KIND.values + highCard;
        else if(value == 2 && numOfPairs == 2) return HandValues.TWO_PAIR.values + highCard;
        else if(value == 2) return HandValues.ONE_PAIR.values + highCard;
        return 0;
    }
    protected int FullHouse(List<List<Integer>> handNum){
        for(int i = 0; i<handNum.size(); i++){
            int highCard = Collections.max(handNum.get(i));
            if(handNum.get(i).get(0).equals(handNum.get(i).get(1)) && handNum.get(i).get(0).equals(handNum.get(i).get(2))
                    && handNum.get(i).get(3).equals(handNum.get(i).get(4))){
                return HandValues.FULL_HOUSE.values + highCard;
            }else if(handNum.get(i).get(0).equals(handNum.get(i).get(1)) && handNum.get(i).get(2).equals(handNum.get(i).get(3))
                    && handNum.get(i).get(2).equals(handNum.get(i).get(4))){
                return HandValues.FULL_HOUSE.values + highCard;
            }
        }
        return 0;
    }
    public int HighCard(List<String> personalHand){
        List<Integer> NumericPersonalHand = new ArrayList<>();
        for(int i = 0; i<personalHand.size(); i++){
            int length = personalHand.get(i).length();
            NumericPersonalHand.add(Integer.parseInt(personalHand.get(i).substring(0, length-1)));
        }
        int highCard = Collections.max(NumericPersonalHand);
        return HandValues.HIGH_CARD.values + highCard;
    }
    protected List<List<Integer>> toSortedNumericHand(List<List<Integer>> unsortedNumericHand){

        for (int i = 0; i < unsortedNumericHand.size(); i++) {
            boolean swap = true;
            while (swap) {
                swap = false;
                for (int j = 0; j < unsortedNumericHand.get(i).size()-1; j++) {
                    if (unsortedNumericHand.get(i).get(j) > unsortedNumericHand.get(i).get(j+1)) {
                        int temp = unsortedNumericHand.get(i).get(j);
                        unsortedNumericHand.get(i).set(j, unsortedNumericHand.get(i).get(j+1));
                        unsortedNumericHand.get(i).set(j+1, temp);
                        swap = true;
                    }
                }
            }
        }
        return unsortedNumericHand; // sorted the List using bubble sort
    }
    protected List<List<Integer>> toUnsortedNumOrSuite(List<List<String>> hand, String numOrSuite){

        List<List<Integer>> UnsortedNumericHand = new ArrayList<>();
        List<List<Integer>> SuiteHand = new ArrayList<>();
        for(List<String> cardList : hand){
            List<Integer> numericCards = new ArrayList<>();
            List<Integer> suiteCards = new ArrayList<>();
            for(String cardStr : cardList){
                int length = cardStr.length();
                numericCards.add(Integer.parseInt(cardStr.substring(0, length-1)));
                suiteCards.add((int)(cardStr.charAt(length-1)));
            }
            UnsortedNumericHand.add(numericCards);
            SuiteHand.add(suiteCards);
        }
        if(numOrSuite == "num") return UnsortedNumericHand; //took away all the suites from the hand so "12c" -> "12"
        else return SuiteHand;
    }
    protected List<List<String>> TriGlobalHandComb(List<String> currentglobalHand, List<String> comb) {
        ArrayList<String> source = new ArrayList<>(currentglobalHand);

        if (comb.size() == 5) {
            List<List<String>> result = new ArrayList<>();
            result.add(comb);
            return result;
        }
        List<List<String>> result = new ArrayList<>();
        Iterator<String> iterator = source.iterator();
        while (iterator.hasNext()) {
            String item = iterator.next();
            iterator.remove();

            List<String> newComb = new ArrayList<>(comb);
            newComb.add(item);

            result.addAll(TriGlobalHandComb(new ArrayList<>(source), newComb));
        }
        return result;
    }


}