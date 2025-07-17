import Assets.Entities.Player;
import java.awt.*;
import java.io.FileNotFoundException;
import javax.swing.*;

/**
 * The StartMenu class represents the initial menu screen of the 2D platformer game.
 * It provides a simple user interface with the game title and a start button.
 * When the start button is clicked, it initializes the game by creating a new Player
 * and Controller instance, and plays the game's background music.
 */
public class StartMenu extends JFrame {
    /**
     * Constructs a new StartMenu with the game title and start button.
     * Sets up the GUI components and registers an action listener for the start button
     * that launches the game when clicked.
     */
    public StartMenu() {
        super("Start Menu");
        setSize(250,250);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        
        // Create main panel with black background
        JPanel panel = new JPanel();
        panel.setBackground(Color.BLACK);
        add(panel, BorderLayout.CENTER);
        panel.setLayout(new BorderLayout());
        
        // Create and configure game title label
        JLabel gameName = new JLabel("2D Platformer");
        gameName.setForeground(Color.WHITE);
        gameName.setHorizontalAlignment(SwingConstants.CENTER);
        gameName.setFont(new Font("Serif", Font.BOLD, 20));
        
        // Create start button
        JButton startButton = new JButton("Start Game");
        panel.add(startButton, BorderLayout.SOUTH);
        panel.add(gameName, BorderLayout.CENTER);
        
        // Register action listener to initialize and start the game when button is clicked
        startButton.addActionListener(e -> {
            dispose();  // Close the menu window
            
            // Create a new player at position (20,200) with size 10 and collision enabled
            Player player = new Player(20, 200, 10, true);
            
            Controller game = null;
            try {
                // Initialize game controller with window dimensions, world name, and player
                game = new Controller(720, 480, "World", true, true, player);
            } catch (FileNotFoundException ex) {
                throw new RuntimeException(ex);
            }
            
            // Start the game music
            game.playMusic();
        });
        
        // Center the window on screen and make it visible
        setLocationRelativeTo(null);
        setVisible(true);
    }
}
