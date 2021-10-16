import java.util.*;
public class BJGame {
    public ArrayList<BJPlayer> player_list = new ArrayList<>();
    public BJDealer dealer;
    public AllCard cards = new AllCard();
    public ArrayList<Boolean> richPlayer = new ArrayList<>();
    Scanner scan = new Scanner(System.in);
    validio io = new validio();

    // constructor
    BJGame(){
        cards = new AllCard();
        set_players();
    }
    public void set_players(){
        player_list = new ArrayList<BJPlayer>();
        // I/O : how many players
        int num_of_players = io.playerNumber();
        for(int i=0 ; i < num_of_players; i++){
            // I/O : Name for player i
            // I/O : Total Money for player i
            String name = io.playername();
            int money = io.validMoney();
            player_list.add(new BJPlayer(name,money));
        }
        // I/O : Total Money for dealer
        String name = io.playername();
        int money = io.validMoney();
        dealer = new BJDealer(name,money);
    }
    public void run(){
        while(checkGameWin()){
            new_round();
        }
        ending();
    }

    public Boolean checkGameWin(){
        int count = player_list.size();
        for (BJPlayer p: player_list){
            if (p.getIsOut()!=0){count-=1;}
        }
        return count != 0;
        
    }
    public void displaydesk(){
        System.out.println("------------------------------------------------");
        System.out.println(dealer);
        for (BJPlayer p : player_list){
            // System.out.println(p.getName()+" ,now it your card.");
            if(p.getIsOut()==0){
                System.out.println(p);
            }
        }
        System.out.println("------------------------------------------------");
    }

    public void new_round(){
        System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++");
        System.out.println("Now we start a new round.");
        cards.reset();
        System.out.println("Now welcom players who still stays!!!");
        for (BJPlayer p : player_list){
            p.printInfo();
        }
        // make bet for all not out
        for(BJPlayer p: player_list){
            if(p.getIsOut()==0){
                // I/O : get new bet on this round for player name
                int mon = io.validBet(p.getMoney());
                p.makeBet(mon);
                p.getHandCardList().get(0).makeBet(mon);
            }
        }

        // first hit.
        for(BJPlayer p : player_list){
            Card c = cards.cardGenerate();
            p.receiveCard(c,0);
            System.out.println("Player "+p.getName()+" reveive card "+c);
            c = cards.cardGenerate();
            p.receiveCard(c,0);
            System.out.println("Player "+p.getName()+" reveive card "+c);
        }
        Card c = cards.cardGenerate();
        dealer.receiveCard(c);
        System.out.println("Dealer reveive card "+c);
        c.flipCard();
        c = cards.cardGenerate();
        dealer.receiveCard(c); 
        System.out.println("Dealer reveive card "+c);
        // display all.
        displaydesk();


        // check player left.
        Integer numberOfLeft = 0; // check if still any player on desk
        for (BJPlayer p: player_list){ 
            if(p.getIsOut()==0){
                Integer num_not_bust = 0; // check if player has un_bust handcard
                int count = 0;
                for(HandCard hc: p.getHandCardList()){
                    System.out.println("Now it's turn for Player "+p.getName()+" on handcard "+count);
                    //select operation for card set hc
                    boolean stand = false;
                    while(!stand){
                        // I/O -> what option you need to do for this handset
                        int op = io.operationType();
                        if(op == 1){
                            // hit
                            c = cards.cardGenerate();
                            hc.addCard(c);
                            System.out.println(p.getName()+" reveive card "+c);
                            if(hc.checkBust()){
                                stand = true;
                                System.out.println("Oops! Bust!");
                                // I/O: this handcard is bust, you lost bet on this handcard
                            }
                        }
                        if(op == 2){
                            //stand
                            num_not_bust+=1;
                            stand = true;
                        }
                        if(op == 3){
                            // double_up
                            Integer bet = hc.getBet();
                            // player -;
                            p.makeBet(bet);
                            hc.makeBet(2*bet);
                            c = cards.cardGenerate();
                            hc.addCard(c);
                            System.out.println(p.getName()+" reveive card "+c);
                            if (!hc.checkBust()){num_not_bust+=1;}
                            stand = true;
                        }
                        if(op == 4){
                            // split
                            // I/O : which number do you split
                            int num = io.validSplit(hc.splitable());
                            Integer index = p.getHandCardList().indexOf(hc);
                            p.splitCard(index, num);
                        }
                        displaydesk();
                    }
                    count += 1;
                }
                if (num_not_bust!=0){numberOfLeft+=1;}
            }
        }
        // user all bust
        if (numberOfLeft==0){
            round_cal(0); // dealer win
        }else{
            // show dealer card
            dealer.getHandCards().getHandCard(0).openCard();
            Integer dealer_score = dealer.getScore();
            System.out.println("Now the dealer open the flipped card.");
            displaydesk();
            
            while (dealer_score<=16){
                c = cards.cardGenerate();
                dealer.receiveCard(c);
                System.out.println("Dealer"+" reveive card "+c);
                dealer_score = dealer.getScore();
                displaydesk();
            }
            if (dealer_score>21){round_cal(1);} // dealer bust
            else{round_cal(2);}
        }
        // update user_state -> all user withou money left is out
        out_players();
        System.out.println("Now this round is end.");
    }

    public void out_players(){
        for (BJPlayer p: player_list){ 
            if (p.getMoney()==0){p.out();}
            else {p.clearState();}
        }
    }
    public void round_cal(Integer game_state){
        System.out.println("Now this round ended, starts scores calculation.");
        if (game_state == 0){ // dealer win
            for (BJPlayer p: player_list){ 
                if(p.getIsOut()==0){
                    for(HandCard hc: p.getHandCardList()){
                        dealer.reward(hc.bet);
                    }
                }
            }
        }else if(game_state == 1){ // dealer bust
            for (BJPlayer p: player_list){ 
                if(p.getIsOut()==0){
                    for(HandCard hc: p.getHandCardList()){
                        if (hc.checkBust()){
                            System.out.println("Player "+p.getName()+" bust and lose all bet on handcard "+ hc);//
                            dealer.reward(hc.getBet());
                            System.out.println("-> Dealer receive "+hc.getBet()+ " money.");
                        }
                        else{
                            System.out.println("Dealer bust, player " + p.getName() + " get twice of bet back on handcard "+hc);
                            p.reward(hc.getBet()*2);}
                    }
                }
            }
        }else{
            for (BJPlayer p: player_list){ 
                if(p.getIsOut()==0){
                    for(HandCard hc: p.getHandCardList()){
                        if (hc.refresh_score()>21){
                            System.out.println("Player "+p.getName()+" is smaller and lose all bet on handcard "+ hc);//
                            dealer.reward(hc.getBet());
                            System.out.println("-> Dealer receive "+hc.getBet()+ " money.");

                        }
                        else if (hc.getScore()<dealer.getScore()){
                            System.out.println("Player "+p.getName()+" is smaller and lose all bet on handcard "+ hc);//
                            dealer.reward(hc.getBet());
                            System.out.println("-> Dealer receive "+hc.getBet()+ " money.");

                        }
                        else if(hc.getScore()==dealer.getScore()){
                            System.out.println("Player "+p.getName()+" not win/lose on handcard "+ hc);//
                            p.reward(hc.getBet());
                            System.out.println("-> Player "+p.getName()+" receive all bet back");//
                        }
                        else{
                            System.out.println("Player "+p.getName()+" is larger and win twice of bet on handcard "+ hc);//
                            p.reward(hc.getBet()*2);
                        }
                    }
                }
            }
        }
    }

    public void ending(){
        System.out.println("game_over");
    }

    public static void main(String[] args) {
        BJGame new_game = new BJGame();
        new_game.run();
    }

}
