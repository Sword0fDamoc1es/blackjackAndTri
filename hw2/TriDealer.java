// new created 
import java.util.*;

public class TriDealer {
    // attributes
    private String name;
    private Integer money;
    private HandCard handcards =  new HandCard();
    private Integer state = 0;


    // constructor with parameters.
    TriDealer(String name_, Integer money_){
        // codes need to be done.
        name = name_;
        money = money_;

    }
    
    public String getName(){return name;}
    public Integer getMoney(){return money;}
    public HandCard getHandCards(){return handcards;}
    public void receiveCard(Card card_){
        handcards.addCard(card_);
    }
    public void reward(Integer reward_){money += reward_;}
    public String toString(){
        // return all handcards
        String message = "Dealer "+ name + " has the following cards: ";
        int count = 0;
        message += "\n"+"Handcards "+ count + ":" + handcards.toString(); 
        return message;
    }
    public int getScore(){ 
        return handcards.refresh_score();
    }
    public int getState(){
        return state;
    }
}

