# BattleShip

A Java-based Battleship game with a computer opponent, multiple difficulty levels, ship placement logic, and save/resume support.

## About this project
- Built by: [Your Name]
- Project type: [Personal project / academic project / portfolio project]
- Year: [202x]

## Overview
This repository contains a classic Battleship game implemented in Java. The project includes a command-line interface, turn-based gameplay, board validation, enemy AI behavior, and saved game functionality.

## Key features
- Single-player Battleship gameplay
- Computer opponent with multiple difficulty settings
- Manual ship placement and board validation
- Turn-based attack system and hit tracking
- Save and resume support for ongoing games
- Java object-oriented architecture with separate classes for game logic, boards, and menus
- File-based persistence for saved game records and user data

## Tech stack
- Java
- BlueJ project structure
- Console-based user interface
- File I/O for saved game state

## Project structure
- `init/` — Java source files, BlueJ metadata, and saved game data
- `tester.java` — main entry point for the application
- `Battle.java` — core gameplay loop
- `Menu.java` — the game menus and user interaction flow
- `ComputerBoard.java` — computer board and ship placement logic
- `UserBoard.java` — player board and ship placement logic
- `Hits.java` — AI targeting logic and possible-hit calculations
- `Input.java` — loading saved game data and records
- `Output.java` — writing game state to files
- `Display.java` — console rendering and user-facing output

## Getting started
### Option 1: BlueJ
1. Open the `init` folder in BlueJ.
2. Run the `tester` class.

### Option 2: Command line
```bash
cd init
javac *.java
java tester
```

## Why this project is useful for a CV
This project demonstrates:
- Object-oriented programming in Java
- Game logic and state management
- AI decision-making logic
- File handling and persistence
- User interaction and input validation
- Problem-solving in a structured, modular codebase

## Notes
This repository is currently structured as a Java game project and is suitable for showcasing practical programming skills in a portfolio or CV context.

## License
This project does not currently declare a license in the repository.
