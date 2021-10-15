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
        handcard_list.add(new HandCard());
    }
    public ArrayList<HandCard> getHandCardList(){return handcard_list;}
    public void clearState(){
        handcard_list =  new ArrayList<HandCard>();
        handcard_list.add(new HandCard());
    }
    public Boolean isSplitable(Integer index){
        Integer bed_on = handcard_list.get(index).getBet();
        ArrayList<Integer> nums = handcard_list.get(index).splitable();
        return (money>=bed_on)&&(nums.size()>=0);
    }
    public String getName(){return name;}
    public Integer getMoney(){return money;}
    public Integer getIsOut(){return isOut;}
    public void receiveCard(Card card_, Integer handCards_index){
        handcard_list.get(handCards_index).addCard(card_);
    }
    public void splitCard(Integer index, Integer number){
        HandCard to_be_Split = handcard_list.get(index);
        Integer first_index = to_be_Split.getFirstIndex(number);
        HandCard new_handcard = new HandCard(to_be_Split.getHandCard(first_index));
        to_be_Split.removeCard(first_index);
        handcard_list.add(new_handcard);
    }
    public void makeBet(Integer bet){money -= bet;}
    public void reward(Integer reward_){money += reward_;}
    public void clearIsOut(){isOut = 0;}
    public void out(){isOut = 1;}
    public String toString(){
        // return all handcards
        String message = name + ": ";
        int count = 0;
        for (HandCard handcard: handcard_list){
            message += "\n"+"Handcards "+ count + ":" + handcard.toString(); 
        }
        return message;
    }

}