package Screens;

import java.awt.*;
import javax.swing.*;

/**
 * The endScreen class represents the victory screen displayed when the player completes the game.
 * It creates a simple window with a congratulatory message informing the player of their success.
 * This screen appears when the player reaches the green end platform at the end of the level.
 */
public class endScreen {
    /** The main window frame for the victory screen */
    JFrame frame;
    
    /** Panel containing the victory screen elements */
    JPanel panel;
    
    /** Timer for potential countdown or animations */
    Timer timer;
    
    /** Countdown variable (default 5 seconds) */
    int i = 5;
    
    /**
     * Constructs a new victory screen with "You reached the end!" message.
     * Creates and configures the window, sets up the layout, and adds visual elements.
     */
    public endScreen() {
        // Initialize and configure the main window
        frame = new JFrame();
        frame.setLocationRelativeTo(null);  // Center on screen
        frame.setSize(240,120);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        // Create and configure the main panel with black background
        panel = new JPanel(new BorderLayout());
        panel.setBackground(Color.BLACK);
        frame.add(panel);
        
        // Add victory message in white text
        JLabel victory = new JLabel("You reached the end!");
        victory.setHorizontalAlignment(SwingConstants.CENTER);
        victory.setForeground(Color.WHITE);
        panel.add(victory, BorderLayout.CENTER);
    }

    /**
     * Gets the main frame of the victory screen.
     * 
     * @return The JFrame containing the victory screen
     */
    public JFrame getFrame() {
        return frame;
    }

    /**
     * Sets the main frame of the victory screen.
     * 
     * @param frame The new JFrame to use
     */
    public void setFrame(JFrame frame) {
        this.frame = frame;
    }

    /**
     * Gets the main panel containing the victory screen elements.
     * 
     * @return The JPanel for the victory screen
     */
    public JPanel getPanel() {
        return panel;
    }

    /**
     * Sets the main panel for the victory screen.
     * 
     * @param panel The new JPanel to use
     */
    public void setPanel(JPanel panel) {
        this.panel = panel;
    }
}
