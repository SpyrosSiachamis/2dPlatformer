import java.io.FileNotFoundException;

/**
 * Main class serves as the entry point for the 2D platformer game.
 * This class initializes the game by creating the start menu,
 * which then allows the player to begin gameplay.
 */
public class Main {
    
    /**
     * The main method that launches the application.
     * Creates a new StartMenu instance which displays the initial game interface.
     * 
     * @param args Command line arguments (not used)
     * @throws FileNotFoundException If required game files cannot be found during initialization
     */
    public static void main(String[] args) throws FileNotFoundException {
        // Create and display the start menu, which serves as the initial game screen
        StartMenu startMenu = new StartMenu();
    }
}