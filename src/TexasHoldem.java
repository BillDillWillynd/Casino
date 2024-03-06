import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

public class TexasHoldem {
    int roundCounter = 0;
    HashMap<String, Integer> userInfo = new HashMap<>();
    String[][] deck = {{"2h","2d","2c","2s",},
            {"3h","3d","3c","3s"},
            {"4h","4d","4c","4s"},
            {"5h","5d","5c","5s"},
            {"6h","6d","6c","6s"},
            {"7h","7d","7c","7s"},
            {"8h","8d","8c","8s"},
            {"9h","9d","9c","9s"},
            {"10h","10d","10c","10s"},
            {"11h","11d","11c","11s"}, //11 = Jack
            {"12h","12d","12c","12s"}, //12 = Queen
            {"13h","13d","13c","13s"}, //13 = King
            {"14h","14d","14c","14s"}}; //14 = Ace

    public TexasHoldem(){
        bot1();
        System.out.println(userInfo);
    }
    public void bot1(){
        userInfo.put("bot1", null);
    }
    public void bot2(){
        userInfo.put("bot2", null);
    }
    public void bot3(){
        userInfo.put("bot3", null);
    }
    public void bot4(){
        userInfo.put("bot4", null);
    }
    public void player(){
        userInfo.put("player", null);
    }

    public String[] shuffle(int length) {
        String[] resultHand = new String[length];
        int ranCardY = 0;
        int ranCardX = 0;
        for(int i = 0; i<length; i++) {
            while (true) {
                ranCardY = (int) (Math.random() * 13);
                ranCardX = (int) (Math.random() * 4);
                if (!deck[ranCardY][ranCardX].equals("00")) {
                    break;
                }
            }
            resultHand[i] = deck[ranCardY][ranCardX];
        }
        deck[ranCardY][ranCardX] = "00";
        return resultHand;
    }
    public List<List<String>> GlobalHandComb(List<String> globalHand, List<String> comb) {
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

            result.addAll(GlobalHandComb(new ArrayList<>(source), newComb));
        }
        return result;

    }
}
