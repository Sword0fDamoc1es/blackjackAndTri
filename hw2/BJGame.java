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
            String promt = "Player "+(i+1);
            String name = io.playername(promt);
            int money = io.validMoney(promt);
            player_list.add(new BJPlayer(name,money));
        }
        // I/O : Total Money for dealer
        String name = io.playername("Dealer");
        int money = io.validMoney("Dealer");
        dealer = new BJDealer(name,money);
    }
    public void run(){
        while(checkGameWin()){
            new_round();
        }
        ending();
    }

    public Boolean checkGameWin(){
        if (dealer.getMoney()<=0){return false;}
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
        System.out.println(dealer.printInfo());
        System.out.println("Now welcom players who still stays!!!");
        for (BJPlayer p : player_list){
            if (p.getIsOut()==0){System.out.println(p.printInfo());}
        }
        // make bet for all not out
        for(BJPlayer p: player_list){
            if(p.getIsOut()==0){
                // I/O : get new bet on this round for player name
                // String prompt = "Player " + p.getName();
                int mon = io.validBet(p.getMoney(), p.printInfo());
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
                int i = 0;
                while(i<p.getHandCardList().size()){
                    HandCard hc = p.getHandCardList().get(i);
                    System.out.println("Now it's turn for Player "+p.getName()+" on handcard "+count);
                    //select operation for card set hc
                    boolean stand = false;
                    while(!stand){
                        // I/O -> what option you need to do for this handset
                        int split = 0;
                        int double_up = 0;
                        if (p.getMoney()>=hc.getBet()){double_up=1;}
                        if (hc.splitable().size()>0&&p.getMoney()>=hc.getBet()){split=1;}
                        int op = io.operationType(double_up,split);
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
                    i += 1;
                }
                if (num_not_bust!=0){numberOfLeft+=1;}
            }
        }
        // user all bust
        if (numberOfLeft==0){
            dealer.getHandCards().getHandCard(0).openCard();
            round_cal(0); // dealer win
        }else{
            // show dealer card
            dealer.getHandCards().getHandCard(0).openCard();
            System.out.println("Now the dealer open the flipped card.");
            displaydesk();
            Integer dealer_score = dealer.calScore();
            
            while (dealer_score<=16){
                c = cards.cardGenerate();
                dealer.receiveCard(c);
                System.out.println("Dealer"+" reveive card "+c);
                dealer_score = dealer.calScore();
                System.out.println("Dealer"+" now total score is "+dealer_score);
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
        dealer.clearState();
        for (BJPlayer p: player_list){ 
            if (p.getMoney()==0){p.out();}
            else {p.clearState();}
        }
    }
    public void round_cal(Integer game_state){
        System.out.println("Now this round ended, starts scores calculation.");
        System.out.println("1. First we calculate sum of all handcards for each player and dealer.");
        if (dealer.getHandCards().getScore()>21){
            System.out.println("dealer bust.");
        }else{
            System.out.println("For dealer: ");
            int score_ = dealer.getScore();
            if (score_==0){dealer.calScore();}
            System.out.println("-> The score is " + score_ + ".");
        }
        int score_;
        for (BJPlayer p: player_list){ 
            if(p.getIsOut()==0){
                for(HandCard hc: p.getHandCardList()){
                    System.out.println("For Player "+p.getName()+" on handcard set "+hc+":");
                    if (!hc.checkBust()){
                        score_ = hc.refresh_score();
                        System.out.println("-> The score is " + score_ + ".");
                    }
                }
            }
        } 
        System.out.println("2. Then we check the results.");
        if (game_state == 0){ // dealer win
            int total_score = 0;
            for (BJPlayer p: player_list){ 
                if(p.getIsOut()==0){
                    for(HandCard hc: p.getHandCardList()){
                        total_score += hc.bet;
                        dealer.reward(hc.bet);
                    }
                }
            }
            System.out.println("All bust!!! Dealer win all bets with a total of "+total_score+".");
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
                        if (hc.getScore()>21){
                            System.out.println("Player "+p.getName()+" bust and lose all bet on handcard "+ hc);//
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
        System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++");
        if (dealer.getMoney()<=0){
            System.out.println("Since dealer has no money, game is over.");
        }
        System.out.println("Since no player has money left, game is over.\nBring your money and see you next time!!");
    }

    public static void main(String[] args) {
        BJGame new_game = new BJGame();
        new_game.run();
    }

}
