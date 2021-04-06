# Tic-Tac-Toe

## *Project Description*

This application allows the user to play the game tic-tac-toe against the computer. The player will always start and
play as "X", while the computer plays "O". The user will be someone who likes to play tic-tac-toe. This project
interests me because tic-tac-toe is an interesting game and I want to take the skills I have learnt and apply it into
this project, furthering my skills and knowledge as well.

## *User Stories*
As a user, I want to be able to...

- place an "X" at a specific position on the board
- have the computer make a move against me
- restart the game
- quit the game
- know if I won
- know if the computer won
- know if there was a tie
- save my game halfway through
- load my game from where I left off

## *Phase 4: Task 2*
I replaced the fields allPositions and availablePositions in the Board class from an ArrayList to a HashMap.

## *Phase 4: Task 3*
If I had more time to work on the project, I would make the following refactoring changes:

- Remove the fields position1 - position9 in the Board class as there is too much duplication in the these fields and 
refactor the methods pertaining to them
- The Board class has too many responsibilities; include a new class BoardManager to make all player moves and check for
 end game scenarios
- The Game class has both the console and graphic ui. Either completely get rid of the console ui or separate the 
graphic and console ui into different classes
- The PositionButton's constructor checks for end game scenarios, and as a result there are too many associations with 
the PositionButton class. Similar to the second point, maybe have a BoardManager/GameManager to check on end game
scenarios and reduce associations in the class