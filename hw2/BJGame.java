import java.util.*;
public class BJGame {
    public ArrayList<BJPlayer> player_list = new ArrayList<>();
    public BJDealer dealer;
    public AllCard cards = new AllCard();
    public ArrayList<Boolean> richPlayer = new ArrayList<>();
    Scanner scan = new Scanner(System.in);

    // constructor
    BJGame(){
        cards = new AllCard();
        set_players();
    }
    public void set_players(){
        player_list = new ArrayList<BJPlayer>();
        // I/O : how many players
        int num_of_players = 10;
        for(int i=0 ; i < num_of_players; i++){
            // I/O : Name for player i
            // I/O : Total Money for player i
            String name = "";
            int money = 0;
            player_list.add(new BJPlayer(name,money));
        }
        // I/O : Total Money for dealer
        int money = 0;
        dealer = new BJDealer("Dealer",money);
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
            if (p.getIsOut()==0){count-=1;}
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
        cards.reset();

        // make bet for all not out
        for(BJPlayer p: player_list){
            if(p.getIsOut()==0){
                // I/O : get new bet on this round for player name
                int mon = scan.nextInt();
                p.makeBet(mon);
                p.getHandCardList().get(0).makeBet(mon);
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
        Integer numberOfLeft = 0; // check if still any player on desk
        for (BJPlayer p: player_list){ 
            if(p.getIsOut()==0){
                Integer num_not_bust = 0; // check if player has un_bust handcard
                for(HandCard hc: p.getHandCardList()){
                    //select operation for card set hc
                    boolean stand = false;
                    while(!stand){
                        // I/O -> what option you need to do for this handset
                        int op = scan.nextInt();
                        if(op == 1){
                            // hit
                            c = cards.cardGenerate();
                            hc.addCard(c);
                            hc.refresh_score();
                            if(hc.bust()){
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
                        if(op == 3){
                            // double_up
                            Integer bet = hc.getBet();
                            // player -;
                            p.makeBet(bet);
                            hc.makeBet(2*bet);
                            c = cards.cardGenerate();
                            hc.addCard(c);
                            int s = hc.refresh_score();
                            if (!hc.bust()){num_not_bust+=1;}
                            stand = true;
                        }
                        if(op == 4){
                            // split
                            // I/O : which number do you split
                            int num = scan.nextInt();
                            Integer index = p.getHandCardList().indexOf(hc);
                            p.splitCard(index, num);
                        }
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
            dealer.getHandCard().getHandCard(0).openCard();
            Integer dealer_score = dealer.getScore();
            while (dealer_score<=16){
                c = cards.cardGenerate();
                dealer.receiveCard(c);
            }
            if (dealer_score>21){round_cal(1);} // dealer bust
            else{round_cal(2);}
        }
        // update user_state -> all user withou money left is out
        out_players();
    }

    public void out_players(){
        for (BJPlayer p: player_list){ 
            if (p.getMoney()==0){p.out();}
            else {p.clearState();}
        }
    }
    public void round_cal(Integer game_state){
        if (game_state == 0){
            // dealer win
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
                        if (hc.isBust==1){dealer.reward(hc.getBet());}
                        else{p.reward(hc.getBet()*2);}
                    }
                }
            }
        }else{
            for (BJPlayer p: player_list){ 
                if(p.getIsOut()==0){
                    for(HandCard hc: p.getHandCardList()){
                        if (hc.getScore()<dealer.getScore()){dealer.reward(hc.getBet());}
                        else if(hc.getScore()==dealer.getScore()){p.reward(hc.getBet());}
                        else{p.reward(hc.getBet()*2);}
                    }
                }
            }
        }
    }

    public void ending(){
        System.out.println("game_over");
    }

}
