import java.util.ArrayList;

public class player{
    ArrayList<HandCard> handCardPool = new ArrayList<>();
    ArrayList<Integer> score = new ArrayList<>();
    bet playerBet;
    int betOnDesk;

    player(){
        handCardPool = new ArrayList<HandCard>();
        score = new ArrayList<Integer>();
        score.add(0);
        handCardPool.add(new HandCard());
        playerBet = new bet();
        betOnDesk = 0;
    }

}