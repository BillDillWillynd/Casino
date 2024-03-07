import java.lang.foreign.GroupLayout;
import java.util.*;

public class WinConditions extends TexasHoldem{
    String[] fullGlobalHand;
    String[] personalHand;
    WinConditions(String[] fullGlobalHand, String[] personalHand){
        this.personalHand = personalHand;
        this.fullGlobalHand = fullGlobalHand;
        List<List<String>> TriGlobalHandComb = TriGlobalHandComb(new ArrayList<>(Arrays.asList(fullGlobalHand)), new ArrayList<>());
    }
    WinConditions(String[] personalHand){
        this.personalHand = personalHand;
    }
    protected void Calculator(){

    }
    protected List<List<String>> TriGlobalHandComb(List<String> globalHand, List<String> comb) {
        //WARNING!!! I did not write this
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
    protected String[][] DuoPersonalHand(List<List<String>> globalHandComb, String[] personalHand){

        String[][] result = new String[10][5];

        for(int i = 0; i<10; i++){
            result[i][0] = personalHand[0];
            result[i][1] = personalHand[1];
            for(int j = 0; j<3; j++){
                result[i][j+2] = globalHandComb.get(i).get(j);
            }
        }
        return result;
    }

}
