import java.util.*;
public class BJGame {
    public ArrayList<BJPlayer> player_list = new ArrayList<>();
    public BJDealer dealer;
    public AllCard cards = new AllCard();
    public ArrayList<Boolean> richPlayer = new ArrayList<>();

    // constructor
    BJGame(){
        set_players();
        // cards? cardSet? allcard? card? U decide.
        cards = new AllCard();
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
            if(player_list.get(i).getMoney()<=0) {
                count --;
                richPlayer.set(i,false);
            }
        }
        return count != 0;
        
    }

    public void new_round(){
        clearAllIsOutState();
        cards.reset();
        // hit.

        // hit for dealer/

        // check player left.
        int numberOfLeft = checkPlayerLeft();
        for (int i = 0; i < player_list.size(); i ++){
            // get boolean/
            
            // get is_state.
            if(richPlayer.get(i) && player_list.get(i).getIsOut()==0){
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
            System.out.println("Players all busted. Dealer wins.");
            //
            // code needed here for dealer.


        }

        while(dealer.getState()!=0){
            // dealer hit
            // dealer check.
        }

        // if dealer bust:
        if (dealer.getScore() > 21){
            for(int i = 0 ; i < player_list.size(); i++){
                if(richPlayer.get(i) && player_list.get(i).getIsOut() == 0){
                    // player_list.get(i).recievebet
                }
            }

        }
        else{
            for(int i = 0 ; i < player_list.size(); i++){
                if(richPlayer.get(i) && player_list.get(i).getIsOut() == 0){
                    for(HandCard h : player_list.get(i).getHandCardList()){
                        if (h.getScore() > dealer.getScore()){
                            player_list.get(i).reward(h.getBet()*2);
                            // dealer make bet
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
            n += (b.getIsOut()==0)? 1: 0;
        }
        return n;
    }
}
