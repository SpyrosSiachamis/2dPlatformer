package Assets.Entities;

import java.awt.*;
import java.awt.image.*;
import javax.swing.*;

/**
 * The Player class represents the user-controlled character in the game.
 * It extends the Entity class and maintains the player's state including position,
 * movement flags, and visual representation.
 * 
 * This class does not directly control player movement - instead, it provides state flags
 * that are read by the World and Controller classes to implement movement logic, physics,
 * and collision detection.
 */
public class Player extends Entity {
    /** Current horizontal position of the player */
    private double locX;
    
    /** Current vertical position of the player */
    private double locY;
    
    /** Horizontal and vertical movement speed (pixels per tick) */
    private int walkSpeed = 5;
    
    /** Initial upward velocity applied when jumping (pixels per tick) */
    private int jumpStrength;
    
    /** Current vertical velocity, affected by gravity and jumping */
    private double yVelocity = 0;
    
    /** Flag indicating if player should move left (set by input, read by Controller) */
    private boolean movingLeft = false;
    
    /** Flag indicating if player should move right (set by input, read by Controller) */
    private boolean movingRight = false;
    
    /** Flag indicating if player should move down (set by input, read by Controller) */
    private boolean movingDown = false;
    
    /** Flag indicating if player should move up (set by input, read by Controller) */
    private boolean movingUp = false;
    
    /** Image buffer for rendering the player character */
    BufferedImage entityIm = new BufferedImage(20, 20, BufferedImage.TYPE_INT_ARGB);
    
    /** Panel containing the player's visual representation */
    private JPanel entityPane = new JPanel(new BorderLayout());
    
    /** Flag indicating if player is touching the ground or platform */
    private boolean playerIsOnGround = false;

    /**
     * Constructs a new Player entity with specified position and movement parameters.
     * Initializes the player's visual representation as a red square.
     *
     * @param locX The initial horizontal position
     * @param locY The initial vertical position
     * @param jumpStrength The power of the player's jump (upward velocity)
     * @param isPlayable Whether this player is controlled by user input
     */
    public Player(int locX, int locY, int jumpStrength, boolean isPlayable) {
        super(isPlayable);
        this.locX = locX;
        this.locY = locY;
        this.jumpStrength = jumpStrength;
        
        // Create visual representation (red square)
        Graphics2D g2d = entityIm.createGraphics();
        entityPane.setSize(30, 30);
        g2d.setColor(Color.RED);
        g2d.fillRect(0, 0, 20, 20);
        g2d.dispose();
        
        // Set up the player's visual component
        JLabel playerLabel = new JLabel(new ImageIcon(entityIm));
        playerLabel.setBounds((int) locX, (int) locY, 20, 20);
        entityPane.add(playerLabel, BorderLayout.CENTER);
        entityPane.setLocation((int) locX, (int) locY);
    }

    /**
     * Gets the player's current horizontal position.
     *
     * @return The X coordinate as an integer
     */
    public int getLocX() {
        return (int) locX;
    }

    /**
     * Sets the player's horizontal position.
     * Used by the Controller to update position after movement calculations.
     * 
     * @param locX The new X coordinate
     */
    public void setLocX(double locX) {
        this.locX = locX;
    }

    /**
     * Gets the player's current vertical position.
     * 
     * @return The Y coordinate
     */
    public double getLocY() {
        return locY;
    }

    /**
     * Sets the player's vertical position.
     * Used by the Controller to update position after physics calculations.
     * 
     * @param locY The new Y coordinate
     */
    public void setLocY(double locY) {
        this.locY = locY;
    }

    /**
     * Gets the player's walking speed.
     * Used by the Controller to calculate movement distance.
     * 
     * @return The walking speed in pixels per tick
     */
    public int getWalkSpeed() {
        return walkSpeed;
    }

    /**
     * Sets the player's walking speed.
     * 
     * @param walkSpeed The new walking speed in pixels per tick
     */
    public void setWalkSpeed(int walkSpeed) {
        this.walkSpeed = walkSpeed;
    }

    /**
     * Gets the player's jump strength.
     * Used by the Controller to calculate initial jump velocity.
     * 
     * @return The jump strength (initial upward velocity)
     */
    public int getJumpStrength() {
        return jumpStrength;
    }

    /**
     * Sets the player's jump strength.
     * 
     * @param jumpStrength The new jump strength
     */
    public void setJumpStrength(int jumpStrength) {
        this.jumpStrength = jumpStrength;
    }

    /**
     * Gets the player's current vertical velocity.
     * Positive values indicate downward movement, negative values indicate upward movement.
     * 
     * @return The current vertical velocity
     */
    public double getyVelocity() {
        return yVelocity;
    }

    /**
     * Sets the player's vertical velocity.
     * Used by the Controller to apply gravity and jump effects.
     * 
     * @param yVelocity The new vertical velocity
     */
    public void setyVelocity(double yVelocity) {
        this.yVelocity = yVelocity;
    }

    /**
     * Gets the player's image buffer.
     * 
     * @return The image used to represent the player
     */
    public BufferedImage getEntityIm() {
        return entityIm;
    }

    /**
     * Sets the player's image buffer.
     * 
     * @param entityIm The new image to represent the player
     */
    public void setEntityIm(BufferedImage entityIm) {
        this.entityIm = entityIm;
    }

    /**
     * Gets the player's visual component panel.
     * 
     * @return The panel containing the player's visual representation
     */
    public JPanel getEntityPane() {
        return entityPane;
    }

    /**
     * Sets the player's visual component panel.
     * 
     * @param entityPane The new panel for the player
     */
    public void setEntityPane(JPanel entityPane) {
        this.entityPane = entityPane;
    }

    /**
     * Checks if the player's left movement flag is set.
     * This flag is read by the Controller to determine if the player should move left.
     * 
     * @return true if the left movement flag is set, false otherwise
     */
    public boolean isMovingLeft() {
        return movingLeft;
    }

    /**
     * Sets the player's left movement flag.
     * This is typically set by keyboard input handlers in the World class.
     * 
     * @param movingLeft true to indicate left movement, false to stop
     */
    public void setMovingLeft(boolean movingLeft) {
        this.movingLeft = movingLeft;
    }

    /**
     * Checks if the player's right movement flag is set.
     * This flag is read by the Controller to determine if the player should move right.
     * 
     * @return true if the right movement flag is set, false otherwise
     */
    public boolean isMovingRight() {
        return movingRight;
    }

    /**
     * Sets the player's right movement flag.
     * This is typically set by keyboard input handlers in the World class.
     * 
     * @param movingRight true to indicate right movement, false to stop
     */
    public void setMovingRight(boolean movingRight) {
        this.movingRight = movingRight;
    }

    /**
     * Checks if the player's down movement flag is set.
     * This flag is read by the Controller to determine if the player should move down.
     * 
     * @return true if the down movement flag is set, false otherwise
     */
    public boolean isMovingDown() {
        return movingDown;
    }

    /**
     * Sets the player's down movement flag.
     * This is typically set by keyboard input handlers in the World class.
     * 
     * @param movingDown true to indicate downward movement, false to stop
     */
    public void setMovingDown(boolean movingDown) {
        this.movingDown = movingDown;
    }

    /**
     * Checks if the player's up movement flag is set.
     * This flag is read by the Controller to determine if the player should move up.
     * 
     * @return true if the up movement flag is set, false otherwise
     */
    public boolean isMovingUp() {
        return movingUp;
    }

    /**
     * Sets the player's up movement flag.
     * This is typically set by keyboard input handlers in the World class.
     * 
     * @param movingUp true to indicate upward movement, false to stop
     */
    public void setMovingUp(boolean movingUp) {
        this.movingUp = movingUp;
    }

    /**
     * Checks if the player is currently on the ground or platform.
     * Used by the Controller to determine if the player can jump.
     * 
     * @return true if on ground, false if in air
     */
    public boolean isOnGround() {
        return playerIsOnGround;
    }

    /**
     * Sets whether the player is on the ground.
     * This is updated by the Controller based on collision detection.
     * 
     * @param onGround true if player is on ground, false if in air
     */
    public void setOnGround(boolean onGround) {
        this.playerIsOnGround = onGround;
    }
}
