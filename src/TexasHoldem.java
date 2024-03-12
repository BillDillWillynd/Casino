import java.util.*;

public class TexasHoldem {
    int roundCounter = 0;
    HashMap<String, ArrayList<Integer>> userInfo = new HashMap<>();
    String[][] deck = {{"2h", "2d", "2c", "2s",},
            {"3h", "3d", "3c", "3s"},
            {"4h", "4d", "4c", "4s"},
            {"5h", "5d", "5c", "5s"},
            {"6h", "6d", "6c", "6s"},
            {"7h", "7d", "7c", "7s"},
            {"8h", "8d", "8c", "8s"},
            {"9h", "9d", "9c", "9s"},
            {"10h", "10d", "10c", "10s"},
            {"11h", "11d", "11c", "11s"}, //11 = Jack
            {"12h", "12d", "12c", "12s"}, //12 = Queen
            {"13h", "13d", "13c", "13s"}, //13 = King
            {"14h", "14d", "14c", "14s"}}; //14 = Ace

    List<Integer> Positions = Arrays.asList(1, 2, 3, 4, 5);
    List<String> currentGlobalHand = getCurrentGlobalHand(); //correct


    public TexasHoldem() {
        Positions = getPosition();
        bot1();
        bot2();
        bot3();
        bot4();
        player();

    }

    public void bot1() {
        userInfo.put("bot1", new ArrayList<>());
        userInfo.get("bot1").add(null); //value
        userInfo.get("bot1").add(null); //cash
        userInfo.get("bot1").add(null); //position

    }

    public void bot2() {
        userInfo.put("bot2", new ArrayList<>());
        userInfo.get("bot2").add(null); //value
        userInfo.get("bot2").add(null); //cash
        userInfo.get("bot2").add(null); //position
    }

    public void bot3() {
        userInfo.put("bot3", new ArrayList<>());
        userInfo.get("bot3").add(null); //value
        userInfo.get("bot3").add(null); //cash
        userInfo.get("bot3").add(3); //position
    }

    public void bot4() {
        userInfo.put("bot4", new ArrayList<>());
        userInfo.get("bot4").add(null); //value
        userInfo.get("bot4").add(null); //cash
        userInfo.get("bot4").add(4); //position
    }

    public void player() {
        userInfo.put("player", new ArrayList<>());
        userInfo.get("player").add(null); //value
        userInfo.get("player").add(null); //cash
        userInfo.get("player").add(5); //position
    }

    public int valueCalcluator(String[] personalHand) {
        //return 14 = High Card ace
        //return 106 = One Pair six
        //return 1000 = Royal Flush
        return 1;
    }

    public List<String> shuffle(int length) {
        String[] resultHand = new String[length];
        int ranCardY = 0;
        int ranCardX = 0;
        for (int i = 0; i < length; i++) {
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
        return Arrays.asList(resultHand);
    }

    public List<String> getCurrentGlobalHand() {
        List<String> temp = new ArrayList<>();
        if (roundCounter == 1) {
            temp.addAll(shuffle(3));
        } else if (roundCounter == 2) {
            temp.addAll(shuffle(1));
        } else if (roundCounter == 3) {
            temp.addAll(shuffle(1));
        }
        return temp;
    }
    public List<Integer> getPosition(){
        if(roundCounter == 0){
          Collections.shuffle(Positions, new Random());
        }
        return Positions;
    }
    public boolean usersTurn(int position, int turn){
        if(turn == position) return true;
        else return false;
    }
}
