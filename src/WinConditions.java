import java.sql.SQLOutput;
import java.util.*;

public class WinConditions {
    List<String> currentGlobalHand;
    List<String> personalHand;

    WinConditions(List<String> currentGlobalHand, List<String> personalHand) {
        this.currentGlobalHand = currentGlobalHand;
        this.personalHand = personalHand;
        List<List<String>> handTest = PersonalAndGlobalHand(TriGlobalHandComb(currentGlobalHand, new ArrayList<>()), personalHand);
        System.out.println(toUnsortedNumericHand(handTest));

    }
    WinConditions(List<String> personalHand) {
        this.personalHand = personalHand;
    }

    protected void Calculator() {

    }
    public List<List<Integer>> toSortedNumericHand(List<List<Integer>> unsortedNumericHand){

        List<List<Integer>> sortedNumericHand = new ArrayList<>(unsortedNumericHand);

        for(List<Integer> cardList : unsortedNumericHand){
            List<Integer> numericCards = new ArrayList<>();
            boolean swap = true;
            while (swap) {
                swap = false;
                for (int j = 0; j < cardList.size(); j++) {
                    if (cardList.get(j) > cardList.get(j+1)) {

                        swap = true;
                    }
                }
            }
            sortedNumericHand.add(numericCards);
        }

        return sortedNumericHand;
    }
    public List<List<Integer>> toUnsortedNumericHand(List<List<String>> hand){

        List<List<Integer>> UnsortedNumericHand = new ArrayList<>();
        for(List<String> cardList : hand){
            List<Integer> numericCards = new ArrayList<>();
            for(String cardStr : cardList){
                int length = cardStr.length();
                numericCards.add(Integer.parseInt(cardStr.substring(0, cardStr.length()-1)));
            }
            UnsortedNumericHand.add(numericCards);
        }
        return UnsortedNumericHand;
    }
    public List<List<String>> PersonalAndGlobalHand(List<List<String>> globalHandComb, List<String> personalHand) {

        List<List<String>> result = new ArrayList<>();

        for (int i = 0; i <globalHandComb.size(); i++) {
            List<String> combination = new ArrayList<>();
            combination.addAll(personalHand);
            combination.addAll(globalHandComb.get(i).subList(0, 3));
            result.add(combination);
        }
        return result;
    }
    public List<List<String>> TriGlobalHandComb(List<String> globalHand, List<String> comb) {
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
        return result;
    }


}