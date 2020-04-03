# ap-lab7

## Overview
At the moment, the app contains:
- A basic JavaFX structure for future tasks
- The classes *Token*, *Board*, *Player* and *Game* with their respective specifications:
  - The *Player* class implements Runnable and overrides the *run* method to extract a random token from the board
  - The *Board* class contains 2 synchronized methods: one for selecting a random token from the board and one for extracting it
  - The *Main* class generates random tokens for a Game instance and runs a separate thread for each player
- An *IllegalTokenValueException* exception class for when a negative value is asigned to a token

## Compulsory
The tasks are:
- Create the class *Token*. An instance of this class will hold a number from 1 to *m*. Consider the case when a token may be *blank*, meaning that it can take the place of any number.
- Create the class *Board*. An instance of this class will contain *n* tokens (you may consider the numbers from 1 to *n*).
- Create the class *Player*. Each player will have a name. This class will implement the *Runnable* interface. In the *run* method the player will repeatedly extract one token from the board.
- Create the *Game* class. **Simulate the game using a thread for each player**. Pay attention to the *synchronization* of the threads when extracting tokens from the board.