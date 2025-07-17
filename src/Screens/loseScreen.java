package Screens;

import java.awt.*;
import javax.swing.*;

/**
 * The loseScreen class represents the game over screen displayed when the player dies.
 * It creates a simple window with a message informing the player of their failure.
 * This screen appears when the player falls off the bottom of the world or meets
 * other losing conditions.
 */
public class loseScreen {
    /** The main window frame for the lose screen */
    JFrame frame;
    
    /** Panel containing the lose screen elements */
    JPanel panel;
    
    /**
     * Constructs a new lose screen with "You died!" message.
     * Creates and configures the window, sets up the layout, and adds visual elements.
     */
    public loseScreen() {
        // Initialize and configure the main window
        frame = new JFrame();
        frame.setLocationRelativeTo(null);  // Center on screen
        frame.setSize(440, 240);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        // Create and configure the main panel with black background
        panel = new JPanel(new BorderLayout());
        panel.setBackground(Color.BLACK);
        frame.add(panel);
        
        // Add "You died!" message in white text
        JLabel loss = new JLabel("You died!");
        loss.setForeground(Color.WHITE);
        panel.add(loss, BorderLayout.CENTER);
    }

    /**
     * Gets the main frame of the lose screen.
     * 
     * @return The JFrame containing the lose screen
     */
    public JFrame getFrame() {
        return frame;
    }

    /**
     * Sets the main frame of the lose screen.
     * 
     * @param frame The new JFrame to use
     */
    public void setFrame(JFrame frame) {
        this.frame = frame;
    }

    /**
     * Gets the main panel containing the lose screen elements.
     * 
     * @return The JPanel for the lose screen
     */
    public JPanel getPanel() {
        return panel;
    }

    /**
     * Sets the main panel for the lose screen.
     * 
     * @param panel The new JPanel to use
     */
    public void setPanel(JPanel panel) {
        this.panel = panel;
    }
}
