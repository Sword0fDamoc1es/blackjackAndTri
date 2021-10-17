Black Jack.
Group Work: Mao Mao, Yizheng Xie.

In this program, we set up Black Jack game.
Using the parts of that game, we also established TriantaEna.

1. compile and run.
   1. javac *
   2. java Main

2. classes and description.
   1. AllCard: this class initiate a new pile of card. This randomly generate Card type and output it. It can fit every card game using poker card.
   2. Card: this class is for it card. it has number and type, generated randomly from AllCard. It has basic function to operate like flip, open and get value. We establish toString for it to print easier.
   3. handcard: a set of cards in a player or dealer is called handcard. It contains a pool of cards, its score and bet on it and some flags. the functions showing its member value is contained.
   4. BJPlayer: this class is for player attending Black Jack. players contains handcard pool, money and name and is_out.  it has function to limit its operation like whether there is enough money to split, and it also has other special functions for Black Jack.
   5. BJDealer: similar as BJPlayer, but it only has a handcard, not handcard pool, and it acts differently.
   6. BJGame: the game logic of black jack. also with function for output and check player/dealer state.
   7. TriDealer: similar to BJDealer, but acts in a triantaEna way.
   8. TriPlayer: similar to BJPlayer, but acts in a TriantaEna way.
   9. Trigame: similar to BJGame, it changes the prepare stage logic and add functions like switch dealer. the operation in such game is less than black jack.
   10. validio: check various io is valid, then correct it. Used from previous homework.
   11. display: output interfaces as GUI, used from previous homework.

3. Discussion & Thoughts:
   1. Design Pattern Part: the first part is to design the overal object oriented patterns including classes and types(abstract class and interface). To be specific, we first physically interpret games to extract components like player, dealer, card, Poker cards. Then we get into details to define attributes and behaviors in each components. At the meantime, we can generalize our design to see what kinds of components can be reused or further generalized as super class. This part produces a well designed UML diagram for all classes and interfaces.
   2. Data Flow Part: For a collaborated development work, we need to assign different work to each teamates like one programming game logic part while some others working on components part. In this case, a well defined data flow works as a robust API to connect different work from different teamates. For example, what parameters should be passed into a specific method of object A and what values should be returned. In this part, each class should have clear behaviors and relationship with others.
   3. Programming Part: In this part, we apply our design pattern and data flow logic into real implementation. In this case, we encounter some unexpected behaviors and interations within different parts. In this case, we need to further revise our data flow or design pattern to meet new requirements. But it may cause conflict with our previous design, which took a lot more time. Therefore, it is of great importance to get a well designed pattern and comprehensively defined data flow. This should be improved in our future teamwork.