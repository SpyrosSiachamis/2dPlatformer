# 2D Swing Game Engine

This is a simple 2D platformer game engine built with Java Swing. It serves as a passion project to enhance my skills in software architecture and game development.

## Features

- Simple physics system with gravity and collision detection
- Platform-based level design with standard and goal platforms
- Player movement with keyboard controls (WASD)
- Game state management with start, win, and lose screens
- Background music system
- Entity-based architecture for future extensibility

## Completed

- ✅ Added comprehensive documentation and comments throughout the codebase
- ✅ Implemented music system with looping background audio
- ✅ Built collision system for platforms and world boundaries
- ✅ Created basic UI elements (start menu, end screens)
- ✅ Fixed jumping system with gravity (Infinite jump, weird reaction when moving in different directions)

## TODO

- Make code more efficient
- Improve the if-else-if statements to make the code less clunky
- Add a UI for creating and loading worlds
- Add Camera System for scrolling levels
- Optimize character movement
- Improve jumping responsiveness

## Future Plans

- Support top-down 2D camera perspective (reason for the disable gravity system)
- Add sprite loader to use images for characters and buildings
- Add enemy NPC support with AI movement
- Make worlds larger than the current frame
- Implement a level editor (hence the "game engine" part of this project)
- Add sound effects for player actions and collisions

## Controls

- **W** - Jump (when on ground) / Move up (when gravity disabled)
- **A** - Move left
- **S** - Move down (when gravity disabled)
- **D** - Move right

## Architecture

The engine is built with a clear separation of concerns:

- **Entity** classes represent game objects (Player, Platforms)
- **Screen** classes handle UI and input (World, endScreen, loseScreen)
- **Controller** manages game logic, physics, and state

## Getting Started

1. Clone the repository
2. Open in your favorite Java IDE
3. Run the Main class to start the game
