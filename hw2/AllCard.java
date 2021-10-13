import java.util.Random;

public class AllCard {
    public int[] number = new int[13];
    // name may not be used??
    public String[][] name = new String[13][4];

    AllCard(){
        for(int i = 0; i < number.length; i++){
            number[i] = 4;
        }
        for (int j = 0 ; j < 13; j ++){
            name[j][0] = "Heart";
            name[j][1] = "Spade";
            name[j][2] = "Diamond";
            name[j][3] = "Club";
        }

    }

    public Card cardGenerate(){
        Random rand = new Random();
// **??** rewrite the following to be rebust.
        int num = rand.nextInt(13);
        int nindex = rand.nextInt(4);
        String n = "to be annouced";

        return new Card(num,n);
    }
}