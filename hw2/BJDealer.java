// new created 
import java.util.*;

public class BJDealer {
    // attributes
    private String name;
    private Integer money;
    private HandCard handcards =  new HandCard();
    private Integer state;
    



    // constructor with parameters.
    BJDealer(String name_, Integer money_){
        // codes need to be done.
        name = name_;
        money = money_;
    }
    
    public String getName(){return name;}
    public Integer getMoney(){return money;}
    public void receiveCard(Card card_, Integer handCards_index){
        handcards.addCard(card_);
    }
    public void reward(Integer reward_){money += reward_;}
    public String toString(){
        // return all handcards
        String message = "Dealer " + name + ": ";
        int count = 0;
        message += "\n"+"Handcards "+ count + ":" + handcards.toString(); 
        return message;
    }
    public int getScore(){
        return handcards.getScore();
    }
}
