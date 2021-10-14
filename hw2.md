# Games: BJ and Trianta.



#### 1. 主要成员：

player.

​										ArrayList<HandCard> handCardPool;

​										int[] score,

​										Bet playerBet,

​										int BetOnDesk,



AllCard.

​										int[] number = new int[13];

​										String/[]/[]  type = new String/[13]/[4];



Card.

​										int number;

​										String name;



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





TriPlayerOperation. : Tri中player的操作:

​										show,

​										hit,

​										stand,

​										bet.

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

​				

​				



