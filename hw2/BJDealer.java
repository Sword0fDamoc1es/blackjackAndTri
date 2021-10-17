// new created 
import java.util.*;

public class BJDealer extends dealer {
    // attributes
    private String name;
    private Integer money;
    private HandCard handcards =  new HandCard();
    private Integer state = 0;


    // constructor with parameters.
    BJDealer(String name_, Integer money_){
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
    public void clearState(){handcards =  new HandCard();}
    public void reward(Integer reward_){money += reward_;}
    public String toString(){
        // return all handcards
        String message = "Dealer "+ name + " has the following cards: ";
        int count = 0;
        message += "\n"+"Handcards "+ count + ":" + handcards.toString(); 
        return message;
    }
    public String printInfo(){return "Dealer " + name +" with total money " + money+".";}
    public int getScore(){ 
        return handcards.getScore();
    }
    public int calScore(){
        return handcards.refresh_score();
    }
    public int getState(){
        return state;
    }
}
