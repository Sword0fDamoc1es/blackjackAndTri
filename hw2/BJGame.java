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
        clearAllIsOutState();

        // hit.

        // hit for dealer/

        // check player left.
        int numberOfLeft = checkPlayerLeft();
        for (int i = 0; i < player_list.size(); i ++){
            // get boolean/
            
            // get is_state.
            if(richPlayer.get(i) && player_list.get(i).is_out==0){
                //select operation.
                boolean stand = false;
                while(!stand){
                    // operate
                }
                
                // check player.
                // change is out?
                // numberOfLeft -- ?
            }

        }
        if(numberOfLeft==0){
            System.out.println("Players all busted. Dealer wins.")
            //
            // code needed here for dealer.


        }

        while(dealer.state()){
            // dealer hit
            // dealer check.
        }

        // if dealer bust:
        if (dealer.score > 21){
            for(int i = 0 ; i < player_list.size(); i++){
                if(richPlayer.get(i) && player_list.get(i).is_out == 0){
                    // player_list.get(i).recievebet
                }
            }

        }
        else{
            for(int i = 0 ; i < player_list.size(); i++){
                if(richPlayer.get(i) && player_list.get(i).is_out == 0){
                    for(HandCard h : player_list.get(i).HandCardPool){
                        if (h.getScore > dealer.score){
                            player_list.get(i).add(bet);
                        }
                    }
                }
            }

        }

    }
    public void ending(){

    }


    

    public void clearAllIsOutState(){
        // clear state only for rich player.
        int count = 0;
        for(Boolean b : richPlayer){
            if(b){
                player_list.get(count).is_out = 0;
            }
            count++;
        }
    }
    public int checkPlayerLeft(){
        int n = 0;
        for(BJPlayer b : player_list){
            n += (b.is_out==0)? 1: 0;
        }
        return n;
    }
}
