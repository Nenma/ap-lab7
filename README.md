# ap-lab7

## Overview
The app contains:
- An application UI that only showcases a game between 2 RandomPlayers, but without any game logic implemented.
- The players take each take their turns correctly, in succession, but the token sets they randomly compose are not evaluated in any way.
- A daemon thread keeps track of the time from the start of the application correctly.

## Bonus
The tasks are:
- Consider the situation when other types of positional games should be allowed. *Game* should be an abstract class. Implement also the clique game.
**OR**
- Create a simple graphical user interface for the game, using JavaFX.

## Optional
The tasks are:
- Make sure that players wait their turns, using a *wait-notify* approach.
- Implement a *timekeeper* thread that runs concurrently with the player threads, as a *daemon*. This thread will display the running time of the game and it will stop the game if it exceeds a certain time limit. Try it using larger values for *n*.
- Consider the situation when each player might have a different strategy for extracting a number: automated (random or **smart**) or manual.
*Player* should be an abstract class having as subclasses: *RandomPlayer*, *SmartPlayer*, *ManualPlayer*.
A "smart" player should try to extend its largest arithmetic progression, while not allowing others to extend theirs. A manual player will use the keyboard. Implement all three strategies.

## Compulsory
The tasks are:
- Create the class *Token*. An instance of this class will hold a number from 1 to *m*. Consider the case when a token may be *blank*, meaning that it can take the place of any number.
- Create the class *Board*. An instance of this class will contain *n* tokens (you may consider the numbers from 1 to *n*).
- Create the class *Player*. Each player will have a name. This class will implement the *Runnable* interface. In the *run* method the player will repeatedly extract one token from the board.
- Create the *Game* class. **Simulate the game using a thread for each player**. Pay attention to the *synchronization* of the threads when extracting tokens from the board.