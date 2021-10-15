import java.util.*;
public class TriGame {
    public ArrayList<TriPlayer> player_list = new ArrayList<>();
    public TriDealer dealer;
    public AllCard cards = new AllCard();
    public ArrayList<Boolean> richPlayer = new ArrayList<>();
    Scanner scan = new Scanner(System.in);
    validio io = new validio();

    // constructor
    TriGame(){
        cards = new AllCard();
        set_players();
    }
    public void switchDealer(TriPlayer p, TriDealer d){
        String pname = p.getName();
        int pmoney = p.getMoney();
        String dname = d.getName();
        int dmoney = d.getMoney();
        TriPlayer tempTriPlayer = new TriPlayer(dname,dmoney);
        TriDealer tempTriDealer = new TriDealer(pname,pmoney);
        player_list.remove(p);
        player_list.add(tempTriPlayer);
        dealer = tempTriDealer;   
    }


    public void set_players(){
        player_list = new ArrayList<TriPlayer>();
        // I/O : how many players
        int num_of_players = io.playerNumber();
        System.out.println("player will have the same amount of money;");
        int money = io.validMoney();
        for(int i=0 ; i < num_of_players; i++){
            // I/O : Name for player i
            // I/O : Total Money for player i
            String name = io.playername();
            player_list.add(new TriPlayer(name,money));
        }
        // I/O : Total Money for dealer
        System.out.println("Here comes the info for dealer.");
        String name = io.playername();
        int dmoney = io.validMoney(money);
        dealer = new TriDealer(name,dmoney);
    }

    public void run(){
        while(checkGameWin()){
            new_round();
        }
        ending();
    }

    public Boolean checkGameWin(){
        int count = player_list.size();
        for (TriPlayer p: player_list){
            if (p.getIsOut()!=0){count-=1;}
        }
        return count != 0;
        
    }
    public void displaydesk(){
        System.out.println("------------------------------------------------");
        System.out.println(dealer);
        for (TriPlayer p : player_list){
            // System.out.println(p.getName()+" ,now it your card.");
            if(p.getIsOut()==0){
                System.out.println(p);
            }
        }
        System.out.println("------------------------------------------------");
    }

    public void prepare(){
        // each player geta card.
        System.out.println("Each Player get a card!");
        for(TriPlayer p : player_list){
            Card c = cards.cardGenerate();
            c.flipCard();
            p.receiveCard(c, 0);
            System.out.println(p.getName()+" reveive card "+c);
        }
        //players need to know the value 
        // !!!!!!!!!! Code needed here for output player first handcard with face down.
        
        //
        Card c = cards.cardGenerate();
        dealer.receiveCard(c);
        System.out.println(dealer.getName()+" reveive card "+c);
        // dealers first card is up.

        // players turn again.
        // after they know their own card, choose bet or fold. using validyn
        validio io = new validio();
        for(TriPlayer p : player_list){
            String choice = io.validyn();
            if(choice.equals("n")){
                System.out.println(p.getName() + " chooses fold! out!");
                p.out();//fold. cannot hit anymore.
            }
            else{
                int bet = io.validBet(p.getMoney());
                p.makeBet(bet);
                p.getHandCardList().get(0).makeBet(bet);
            }
        }

        // finish betting. now each play receive two more cards. with face up.
        for(TriPlayer p : player_list){
            if(p.getIsOut()==0){
                Card cc = cards.cardGenerate();
                System.out.println(p.getName()+" reveive card "+cc);
                p.receiveCard(cc, 0);
                cc = cards.cardGenerate();
                p.receiveCard(cc, 0);
                System.out.println(p.getName()+" reveive card "+cc);
            }

        }



    }

    public void new_round(){
        cards.reset();

        // // make bet for all not out
        // for(TriPlayer p: player_list){
        //     if(p.getIsOut()==0){
        //         // I/O : get new bet on this round for player name
        //         int mon = io.validBet(p.getMoney());
        //         p.makeBet(mon);
        //         p.getHandCardList().get(0).makeBet(mon);
        //     }
        // }

        // // first hit.
        // for(TriPlayer p : player_list){
        //     Card c = cards.cardGenerate();
        //     p.receiveCard(c,0);
        //     System.out.println(p.getName()+" reveive card "+c);
        //     c = cards.cardGenerate();
        //     p.receiveCard(c,0);
        //     System.out.println(p.getName()+" reveive card "+c);
        // }
        // Card c = cards.cardGenerate();
        // c.flipCard();
        // dealer.receiveCard(c);
        // System.out.println("dealer reveive card "+c);
        // c = cards.cardGenerate();
        // dealer.receiveCard(c); 
        // System.out.println("dealer reveive card "+c);

        prepare();


        // display all.
        displaydesk();

        Card c;
        // check player left.
        Integer numberOfLeft = 0; // check if still any player on desk
        for (TriPlayer p: player_list){ 
            if(p.getIsOut()==0){
                Integer num_not_bust = 0; // check if player has un_bust handcard
                for(HandCard hc: p.getHandCardList()){
                    //select operation for card set hc
                    boolean stand = false;
                    while(!stand){
                        // I/O -> what option you need to do for this handset
                        int op = io.operationType();
                        if(op == 1){
                            // hit
                            c = cards.cardGenerate();
                            hc.addCard(c);
                            hc.refresh_score();
                            System.out.println(p.getName()+" reveive card "+c);
                            if(hc.checkBust(31)){
                                stand = true;
                                // I/O: this handcard is bust, you lost bet on this handcard
                                continue;
                            }
                        }
                        if(op == 2){
                            //stand
                            num_not_bust+=1;
                            stand = true;
                            continue;
                        }
                        
                        displaydesk();
                    }
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
            
            while (dealer_score<=26){
                c = cards.cardGenerate();
                dealer.receiveCard(c);
                dealer_score = dealer.getScore();
            }
            if (dealer_score>31){round_cal(1);} // dealer bust
            else{round_cal(2);}
        }
        // update user_state -> all user withou money left is out
        out_players();
    }

    public void out_players(){
        for (TriPlayer p: player_list){ 
            if (p.getMoney()==0){p.out();}
            else {p.clearState();}
        }
    }
    public void round_cal(Integer game_state){
        if (game_state == 0){
            // dealer win
            for (TriPlayer p: player_list){ 
                if(p.getIsOut()==0){
                    for(HandCard hc: p.getHandCardList()){
                        dealer.reward(hc.bet);
                    }
                }
            }
        }else if(game_state == 1){ // dealer bust
            int score = 0;
            TriPlayer pp = new TriPlayer("",-1);
            for (TriPlayer p: player_list){ 
                if(p.getIsOut()==0){
                    for(HandCard hc: p.getHandCardList()){
                        if (hc.isBust==1){dealer.reward(hc.getBet());}
                        else{
                            p.reward(hc.getBet()*2);
                            if(score<hc.getScore()){
                                score = hc.getScore();
                                pp = p;
                            }
                        }
                    }
                }
            }
            if(pp.getMoney()!=-1){
                switchDealer(pp, dealer);
            }
        }else{
            int score = 0;
            TriPlayer pp = new TriPlayer("",-1);
            for (TriPlayer p: player_list){ 
                if(p.getIsOut()==0){
                    for(HandCard hc: p.getHandCardList()){
                        if (hc.getScore()<dealer.getScore()){dealer.reward(hc.getBet());}
                        else if(hc.getScore()==dealer.getScore()){p.reward(hc.getBet());}
                        else{
                            p.reward(hc.getBet()*2);
                            if(score < hc.getScore()){
                                score = hc.getScore();
                                pp = p;
                            }
                        }
                    }
                }
            }
            if(pp.getMoney()!=-1){
                switchDealer(pp, dealer);
            }
        }
    }

    public void ending(){
        System.out.println("game_over");
    }

    public static void main(String[] args){
        TriGame newGame = new TriGame();
        newGame.run();
    }
}
