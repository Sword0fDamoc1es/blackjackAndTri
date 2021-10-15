import java.util.*;
public class BJGame {
    public ArrayList<BJPlayer> player_list = new ArrayList<>();
    public BJDealer dealer;
    public AllCard cards = new AllCard();
    public ArrayList<Boolean> richPlayer = new ArrayList<>();
    Scanner scan = new Scanner(System.in);

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
            int money = 0;
            //

            player_list.set(i,new BJPlayer(name,money));

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
    public void displaydesk(){
        for (BJPlayer p : player_list){
            if(p.getIsOut()==0){
                System.out.println(p);
            }
        }
        System.out.println(dealer);
    }
    public void new_round(){
        clearAllIsOutState(); // only clear state for money>0
        cards.reset();

        // make bet for all not out
        for(int i = 0 ; i  <player_list.size();i++){
            if(player_list.get(i).getMoney()>0){
                // I/O needed.
                int mon = scan.nextInt();
                player_list.get(i).makeBet(mon);
                player_list.get(i).getHandCardList().get(0).makeBet(mon);
            }
        }

        // first hit.
        for(BJPlayer p : player_list){
            Card c = cards.cardGenerate();
            p.receiveCard(c,0);
            c = cards.cardGenerate();
            p.receiveCard(c,0);
        }
        Card c = cards.cardGenerate();
        c.flipCard();
        dealer.receiveCard(c);
        c = cards.cardGenerate();
        dealer.receiveCard(c); 
        // display all.
        displaydesk();


        // check player left.
        int numberOfLeft = checkPlayerLeft();

        for (int i = 0; i < player_list.size(); i ++){
            // get boolean/
            
            // get is_state.
            display dtool = new display();
            
            if(player_list.get(i).getMoney()>=0 && player_list.get(i).getIsOut()==0){
                //select operation.
                for(HandCard hc: player_list.get(i).getHandCardList()){
                    dtool.operationPlayer();

                    boolean stand = false;
                    while(!stand){
                            // operate
                        int op = scan.nextInt();
                        if(op == 1){
                            // hit
                            c = cards.cardGenerate();
                            hc.addCard(c);
                            int s = hc.refresh_score();
                            if(hc.bust()){
                                stand = true;
                                continue;
                            }
                        }
                        if(op == 2){
                            //stand
                            stand = true;
                            continue;
                        }
                        if(op == 3){
                            //double
                            Integer b = hc.getBet();
                            // player -;
                            player_list.get(i).makeBet(b);
                            hc.makeBet(2*b);
                            c = cards.cardGenerate();
                            hc.addCard(c);
                            int s = hc.refresh_score();
                            if(hc.bust()){
                                stand = true;
                                continue;
                            } 
                        }
                        if(op == 4){
                            //split
                            // i/o
                            ArrayList<Integer> a = hc.splitable();
                            System.out.println(a.toString());
                            // io.
                            int n = scan.nextInt();
                            Integer i = hc.getFirstIndex(n);
                            player_list.get(i);

                        }
                    }
                }
                
                // check player.
                int nnn =player_list.get(i).getHandCardList().size();
                for(HandCard hc : player_list.get(i).getHandCardList()){
                    if(hc.bust()){
                        nnn--;
                    }
                }
                if(nnn==0){
                    player_list.get(i).out();
                    numberOfLeft --;
                }
                // change is out?
                // numberOfLeft -- ?
            }

        }



        if(numberOfLeft==0){
            System.out.println("Players all busted. Dealer wins.");
            //
            //
            Integer sss = 0;
            for(BJPlayer p : player_list){

            }


        }







        
        while(dealer.getState() !=0){
            
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
                player_list.get(count).clearIsOut();
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
