import java.util.*;

public class WinConditions {
    protected List<String> currentGlobalHand;
    protected List<String> personalHand;

    WinConditions(List<String> currentGlobalHand, List<String> personalHand) {
        this.currentGlobalHand = currentGlobalHand;
        this.personalHand = personalHand;

        List<List<String>> tempHand = new ArrayList<>(PersonalAndGlobalHand(TriGlobalHandComb(currentGlobalHand, new ArrayList<>()), personalHand));
        List<List<Integer>> hand = new ArrayList<>(toSortedNumericHand(toUnsortedNumOrSuite(tempHand, "num")));
        System.out.println(hand);
    }
    WinConditions(List<String> personalHand) {
        this.personalHand = personalHand;
        List<List<String>> personalHand2 = new ArrayList<>();
        personalHand2.add(personalHand);

        List<List<String>> tempHand = new ArrayList<>(PersonalAndGlobalHand(personalHand2, personalHand));
        List<List<Integer>> hand = new ArrayList<>(toSortedNumericHand(toUnsortedNumOrSuite(tempHand, "num"))); // for the overloaded constructor you will only ever need "num"
        System.out.println(hand);
    }

    protected void FullCalculator() {

    }
    protected int RoyalFlush(){
        int value = 0;
        return value;
    }
    protected int StraightFlush(){
        int value = 0;
        return value;
    }
    protected int Straight(){
        int value = 0;
        return value;
    }
    protected int Flush(){
        int value = 0;
        return value;
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
    protected List<List<String>> PersonalAndGlobalHand(List<List<String>> globalHandComb, List<String> personalHand) {

        List<List<String>> result = new ArrayList<>();

        for (int i = 0; i <globalHandComb.size(); i++) {
            List<String> combination = new ArrayList<>();
            combination.addAll(personalHand);
            combination.addAll(globalHandComb.get(i).subList(0, 3));
            result.add(combination);
        }
        return result;
    }
    protected List<List<String>> TriGlobalHandComb(List<String> globalHand, List<String> comb) {
        ArrayList<String> source = new ArrayList<>(globalHand);

        if (comb.size() == 3) {
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
        return result; //returns all the combinations of the global hand within a range of 3
    }


}
