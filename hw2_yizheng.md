# Games: BJ and Trianta.



#### 1. 主要成员：

player.

​										ArrayList<HandCard> handCardPool;

​										int[] score,

​										Bet playerBet,

​										int BetOnDesk,

CardSet:



52CardSet extend CardSet;

- Card[] cartset = new;
- 



Card.

- int number;
- String name;
- int value; 



PokerCard extends Card:

- String flower;



Judge.

​										ArrayList<playerPool>,

​										ArrayList<DealerPool>,

​										

Bet.

​										int amountLeft;

HandCard.

​										int currentScore;

​										ArrayList<Card> handCard;

​										ArrayList<Boolean> handCardIsFlipped;



#### 2. 接口：

BJCard.  : 用在AllCard上，将54张 => BJ 类型卡:

​										initiateBJ,

​										,

TriCard. : 用在AllCard上，将54张=> Tri 类型卡

​										initiateTri,

​										



BJPlayerOperation. : BJ中player的操作: 

​										show,

​										hit, 

​										stand, 

​										split, 

​										double_up, 

​										bet 等 

BJDealerOperation. : BJ中dealer的操作: 

​										show,

​										hit 等

BJ_JudgeOperation. : BJ中judge等操作: 

​										addPlayer, 

​										addDealer,

​										cheackEveryState,

​										removeBet,

​										addBet,

​										removePlayer,

​										removeDealer.

BetOperation:

- show
- hit
- stand

TriDealerOperation. : Tri中dealer的操作:

​										show,

​										hit.

TriJudgeOperation. : Tri中judge的操作:

​										addPlayer,

​										addDealer,

​										checkEveryState,

​										removeBet,

​										addBet,

​										removePlayer,

​										removeDealer.



#### 3. 类的方法： 不包括接口

player:

​				// operate on handcardpool.

​				int[] returnScore();

Dealer: 



Judge:

​				startgame();

​				endgame();

AllCard:

​				typeSelect();//select the type.

​				numberSelect();//select the numbers.

​				removerNumber(int index);// each number array has 13 slots, integer in a slot is smaller than/ equal to 4, and larger than 0.       => Number[index]--;

​				removeType(int index, int typeIndex);

​				restart();

Card:

​				card(int number, String type);

​				int returnVal()



Judge:

- Selection:

  BJgame new_game = new BJGame()

  New_game.run()

  TriGame()



BJGame:

```java
// atributes
BJPlayer[] player_list; [ "mao", "xie"]
BJDealer dealer;
Cardset cards;
boolean[] rich_player;  [true,false]
```

```java
// methods
BJGame(){
   set_players();
   set_dealer();
   cards = new 52Card();
}
set_players{
   // input # of players -> n
   player_list = new Player[n];
   // initialize player
   for i in n{
      // input name, bet
      player_list[i] = new BJPlayer(name,bet)
   } 
   // init dealer
   // name, bet
}
run(){
   while(checkWin()) new_round()
   ending(); // 
}
void new_round(){
   // clear is_out state
   // hit for all players. *2
   // hit for dealer. *2 
   // check_player_left = n
   // for each rich_player.	
      // players operate.
   		// while stand or player.isBust()
   			// hit -> player.hit(cards.generate())
      		// split -> player.split()
   			// double-up -> player.makebet(bet_pool[i])
   			// show_desk() <- player.showHandCards
      // check player. -> 
   if check_player_left == 0: // dealer win return 
   while(dealer.state()){
      // dealer hit
      // dealer check
   }
   // if dealer out: reward players not out
   // else: check who gets reward
}

boolean checkWin(){
   int count = player_list.length;
   for(int i = 0; i < player_list.length; i++){
      if(plist[i].bet.getremain <=0){
         count--;
         rich_player[i] = false;
      }
   }
   return count != 0;
}
```

TriGame:

​				

​				



