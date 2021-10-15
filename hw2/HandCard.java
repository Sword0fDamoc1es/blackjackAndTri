import java.util.*;
public class HandCard {
    // each player/dealer has some handcard.
    // a handcard is a collection of cards.
    // it needs FUNCTIONS : add, remove, getScore, and initiate.
    // the getScore method uses on object card, card will return a score.
    //                      then accumulate the scores. Thus, select score is done in Card, not this object.
    // the add method add a card to the pool. obj Card is generated by obj allCard.
    // the remove method removes a card from the pool, used when split is called. (Split is a function from interface.)
    // the initiate method creates a new pool.
    //                      When split is used, a second initiate method can be used.


    // members & reason:
    public ArrayList<Integer> handCardsIsFlipped;
    public ArrayList<Card> handCards;
    public Integer score = 0;
    public Integer isBust = 0;
    public Integer isBJ = 0; 
    public Integer bet;

    //constructor1.
    HandCard(){
        handCards = new ArrayList<Card>();
        score = 0; 
    }
    //constructor2.
    HandCard(Card c){
        handCards = new ArrayList<Card>();
        addCard(c);
        score = c.selectVal();
    }
    public Integer getBet(){return bet;}
    public void makeBet(Integer bet_){bet = bet_;}
    public void addBet(Integer num){ bet += num;}
    public int clearBet(){
        int b = bet;
        makeBet(0);
        return b;
    }
    public ArrayList<Card> getAllCards(){return handCards;}
    public void addCard(Card c){
        handCards.add(c);
        score += c.selectVal();
    }
    // remove method is used in split method.
    // before using remove, there is a comparison in split,
    // IF there exsists same card in hand, can do remove.
    // then return a index ** important, how to return index.
    // IF not exsists, split won't call remove.
    // 
    // For this method, it get a index after comparison. index is the input.
    public void removeCard(int index){
        handCards.remove(index);
        score = refresh_score();
    }
    
    // this is used to return the score to the judge.
    // judge will decide whether a player is out.
    // we just need to return a score.
    // further encapsulation is needed, we just set score as public first.
    public int getScore(){ return score;}

    // almost forget, for every player, once stand, he has already fix his score.
    // no further change of score until next turn.
    // thus we need a refresh score to ask for refresh.
    public int refresh_score(){
        System.out.println("Your current score is :");
        System.out.println(score);
        System.out.println("Do you want to change?");
        // **??** I/O is needed here!
        score = 0;
        for(Card c : handCards){
            score += c.selectVal();
        }

        return score;
    }

    // for the following function, pd means player or dealer, 0 is player, 1 is dealer.
    //                             state means what is the state of dealer.
    // for player, their cards always face up, until they stand or bust.
    // for dealer, before each player made their decition, one face up, the other face down.
    // for that state(0), dealer has two cards in pool, index0 is down and index1 is up. Call getHideCard to fetch the value.
    // after every players' turn, dealer first flip index0 card. then all his hit-card is faced up.
    // for this state(1), dealer hit until score goes >= 17.
    //
    // this function is only called right after hit, once.
    public void setFlip(int pd, int state){
        if(pd == 0){
            handCardsIsFlipped.add(1);//1 means face up.
        }
        if(pd == 1 && state == 0){
            handCardsIsFlipped.set(0, 0);
            handCardsIsFlipped.add(1);
        }
        if(pd == 1 && state == 1){
            handCardsIsFlipped.set(0,1);
            handCardsIsFlipped.add(1);
        }
    }

    public String toString(){
        String message = "{";
        for (Card d: handCards){
            message += " " + d.getNumber();
        }
        message += "}";
        return message;
    }
    // below will show functions from interface.
    public boolean bust(){
        return score>21;
    }
    public ArrayList<Integer> splitable(){
        ArrayList<Integer> cardarray = new ArrayList<Integer>();
        int[] allnum = new int[13];
        for(int i = 0; i < handCards.size(); i++){
            allnum[handCards.get(i).getNumber()]++;
        }
        for(int j = 0; j < 13; j ++){
            if(allnum[j]>1){
                cardarray.add(j);
            }
        }
        return cardarray;
    }
    public Card getHandCard(Integer i){
        return handCards.get(i);
    }
    public Integer getFirstIndex(Integer num){
        for(Integer i = 0 ; i < handCards.size(); i ++){
            if(handCards.get(i).getNumber()==num){
                return i;
            }
        }
        return -1;
    }
}
