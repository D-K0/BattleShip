# BattleShip

A Java-based Battleship game developed as a final project for Grade 12 Computer Science at Grant Park High School.

## Overview
This project is a text-based Battleship game implemented in Java. It includes turn-based gameplay, AI opponent logic, multiple difficulty levels, board validation, and save/resume functionality. The application is structured using object-oriented programming principles and is organized into separate classes for the game flow, boards, menus, hit logic, and file persistence.

## Features
- Single-player Battleship gameplay
- Multiple computer difficulty levels
- Ship placement and board validation
- Turn-based attack system with hit tracking
- Saved game support for continuing previous sessions
- Console-based user interface
- Java class-based architecture with modular game components

## Technology
- Java
- BlueJ project structure
- Console input/output
- File I/O for saved game data

## Project structure
- `init/` — project source files, BlueJ metadata, and saved game records
- `tester.java` — main program entry point
- `Battle.java` — primary game loop and turn logic
- `Menu.java` — menu system and user interaction flow
- `ComputerBoard.java` — computer board state and ship placement logic
- `UserBoard.java` — player board state and ship placement logic
- `Hits.java` — AI targeting and possible-hit calculations
- `Input.java` — reading saved game data and board state
- `Output.java` — writing game state to files
- `Display.java` — console display and user-facing output

## Getting started
### With BlueJ
1. Open the `init` folder in BlueJ.
2. Run the `tester` class.

### Using the command line
```bash
cd init
javac *.java
java tester
```

## Educational value
This project demonstrates several core programming concepts, including:
- object-oriented design
- state management and game logic
- algorithmic decision-making for AI behavior
- user input validation
- file handling and persistence
- structured project organization

## Notes
This repository is intended as a Java programming project and serves as a demonstration of practical software development skills in a classroom setting.

## License
No license has been specified in the repository.
