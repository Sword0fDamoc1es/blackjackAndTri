import java.util.*;

public class AllCard {
    public ArrayList<Card> cards = new ArrayList<Card>();
    public Set<Integer> set;

    AllCard(){
        set = new HashSet<Integer>();
        String[] flowers = {"Heart", "Spade", "Diamond", "Club"};
        for(int i = 0; i < 4; i++){
            String name = flowers[i];
            for (int j = 1 ; j < 14; j ++){cards.add(new Card(j, name));}
        }
    }

    public Card cardGenerate(){
        Random rand = new Random();
// **??** rewrite the following to be rebust.
        int num = rand.nextInt(52);
        while (set.contains(num)){ num = rand.nextInt(52);}
        set.add(num);
        return cards.get(num);
    }

    public void reset(){
        set = new HashSet<Integer>();
    }
}