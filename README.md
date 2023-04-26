# Java-jump-n-run
## Technical Software Spec for Basic Jump and Run 2D Game

## Overview
The goal of this project is to create a basic jump and run 2D game using JavaFX, SceneBuilder and Intellij. The game will have a player character that can move left and right, jump, and interact with objects in the game world. The game will also have enemies that the player must avoid or defeat in order to progress through the levels.

## Requirements
- The game must be built using JavaFX, SceneBuilder and Intellij.
- The game must have a player character that can move left and right, jump, and interact with objects in the game world.
- The game must have enemies that the player must avoid or defeat in order to progress through the levels.
- The game must have multiple levels with increasing difficulty.
- The game must have a scoring system that tracks the player's progress through the levels.
- The game must have sound effects and music.

## Architecture
The game will be built using the Model-View-Controller (MVC) architecture. The model will contain the game logic and data, the view will contain the graphical user interface, and the controller will handle user input and update the model and view accordingly.

### Model
The model will contain the following classes:
- `Game`: This class will contain the game logic and data, including the player character, enemies, objects in the game world, and the current level.
- `Player`: This class will represent the player character and contain its position, velocity, and other attributes.
- `Enemy`: This class will represent the enemies in the game and contain their position, velocity, and other attributes.
- `Level`: This class will represent a level in the game and contain the layout of the game world, including the position of objects, enemies, and the player character.

### View
The view will contain the graphical user interface using JavaFX and SceneBuilder. The following screens will be implemented:
- `MainMenu`: This screen will display the main menu of the game, including options to start a new game, view high scores, and exit the game.
- `GameScreen`: This screen will display the game world and the player character, enemies, and objects in it.
- `GameOverScreen`: This screen will display the player's score and options to restart the game or return to the main menu.

### Controller
The controller will handle user input and update the model and view accordingly. The following classes will be implemented:
- `MainMenuController`: This class will handle user input on the main menu screen, including starting a new game, viewing high scores, and exiting the game.
- `GameController`: This class will handle user input during gameplay, including moving the player character, jumping, and interacting with objects in the game world.
- `GameOverController`: This class will handle user input on the game over screen, including restarting the game or returning to the main menu.

## Implementation
The following steps will be taken to implement the game:
1. Create a new JavaFX project in Intellij.
2. Create the model classes (`Game`, `Player`, `Enemy`, and `Level`) and implement the game logic and data.
3. Create the view screens (`MainMenu`, `GameScreen`, and `GameOverScreen`) using SceneBuilder and implement the graphical user interface.
4. Create the controller classes (`MainMenuController`, `GameController`, and `GameOverController`) and implement the user input handling and updating of the model and view.
5. Implement the scoring system and sound effects and music.
6. Test the game thoroughly and fix any bugs or issues.

## Conclusion
This technical software spec outlines the requirements, architecture, and implementation plan for a basic jump and run 2D game using JavaFX, SceneBuilder and Intellij. The game will have a player character that can move left and right, jump, and interact with objects in the game world, as well as enemies that the player must avoid or defeat in order to progress through the levels. The game will also have multiple levels with increasing difficulty, a scoring system, and sound effects and music.
