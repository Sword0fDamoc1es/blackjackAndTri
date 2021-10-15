import java.util.*;
public class Card {
    private Integer number;
    private String name;
    private Integer flipped;
    // private Integer isFlipped = 0;


    Card(int number, String name){
        this.number = number;
        this.name = name;
        flipped = 0;
    }

    public Integer getNumber(){return number;}
    public String getName(){return name;}
    public void flipCard(){flipped = 1;}
    public void openCard(){flipped = 0;}
    public Integer isFlipped(){return flipped;}


    // Card this a concrete obj in handCards.
    // it contains nunmber and name.
    // on the desk, at most 52 cards.
    // a card can only be generated from AllCard.
    // In AllCard, it will return number and name randomly to card constructor.
    // thus Card constructor only constructs with these value.

    // the core function of this class is selectVal.
    // According to the rule,
    // 2-10 is set as value 2-10.
    // JQK faces are also 10.
    // A can be 1 or 11.
    // the logic of the game is that:
    // player/dealer can add score multi times before they end current turn.
    // the first time to add is when they recieve the card.
    // Then it will call selectVal to player.
    // ! IF number is A, it needs player to select,
    // ! Else, directly return the value. Addtion on HandCard Class would be done later.

    public int selectVal(){
        int value;
        if(number == 1){
            // **??** I/O is needed here!.
            Scanner scan = new Scanner(System.in);
            String request = scan.nextLine();
            // the above needs rewrite.
            value = (request=="11")?11:1;
            scan.close();
        }
        if(number>=10){
            value = 10;
        }
        value = number;
        System.out.println(value+" is the score of this card.");

        System.out.println();
        return value;
    }

    public String toString(){
        if (flipped == 1){return "*";}
        return number.toString();
    }

}
