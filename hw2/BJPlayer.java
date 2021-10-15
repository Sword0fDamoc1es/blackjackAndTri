// new created BJPlayer.
import java.util.*;

public class BJPlayer {
    // attributes
    private String name;
    private Integer money;
    private Integer isOut;
    private ArrayList<HandCard> handcard_list =  new ArrayList<HandCard>();


    // constructor with parameters.
    BJPlayer(String name_, Integer money_){
        // codes need to be done.
        name = name_;
        money = money_;
        isOut = 0;
    }

    public String getName(){return name;}
    public Integer getMoney(){return money;}
    public Integer getIsOut(){return isOut;}
    public void receiveCard(Card card_, Integer handCards_index){
        handcard_list.get(handCards_index).addCard(card_);
    }
    public void splitCard(Integer index){
        HandCard handCard_out = handcard_list.get(index);
        handcard_list.remove(index);
        // I/O 
        // show cards and index for two new handcard
        // iterate all cards to ask user to split cards into two handcards

    }
    public void makeBet(Integer bet){money -= bet;}
    public void reward(Integer reward_){money += reward_;}
    public void clearIsOut(){isOut = 0;}
    public void out(){isOut = 1;}
    public String toString(){
        // return all handcards
        return new String();
    }

}