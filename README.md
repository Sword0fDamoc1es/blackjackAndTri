Black Jack.
Group Work: Mao Mao, Yizheng Xie.

In this program, we set up Black Jack game.
Using the parts of that game, we also established TriantaEna.

1. compile and run.
   1. 
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

3. discussion:
