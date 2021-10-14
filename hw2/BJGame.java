import java.util.*;
public class BJGame {
    public ArrayList<BJPlayer> player_list = new ArrayList<>();
    public BJDealer dealer;
    public CardSet cards = new CardSet();
    public ArrayList<Boolean> richPlayer = new ArrayList<>();

    // constructor
    BJGame(){
        set_players();
        // cards? cardSet? allcard? card? U decide.
        cards = new Card52();
        //
    }
    public void set_players(){
        player_list = new ArrayList<BJPlayer>();
        // I/O needed : type input numbers 
        int n = 10;
        //

        for(int i=0 ; i < n; i++){
            // I/O is needed. : type String.
            // I/O is needed : type int.
            String name = "";
            int bet = 0;
            //

            player_list.set(i,new BJPlayer(name,bet));

        }
        //I/O is needed : type String.
        // I/O is needed : type int.
        
        String name = "";
        int bet = 0;
        // 
        
        dealer = new BJDealer(name,bet);

    }
    public void run(){
        while(checkWin()){
            new_round();

        }
        ending();
    }

    public Boolean checkWin(){
        int count = player_list.size();
        for (int i = 0 ; i < player_list.size(); i ++){
            // BJPlayer needs to add bet to remove following bug.
            if(player_list.get(i).bet.getBet()<=0) {
                count --;
                richPlayer.set(i,false);
            }
        }
        return count != 0;
        
    }

    public void new_round(){

    }
    public void ending(){

    }
}
