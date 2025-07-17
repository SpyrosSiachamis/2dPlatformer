package Assets.Entities;

import java.awt.*;
import java.awt.image.BufferedImage;
import javax.swing.*;

/**
 * The Entity class serves as the abstract base class for all game entities including
 * the player character and potential NPCs in future versions.
 * 
 * This class provides common attributes and functionality shared by all entities
 * such as position, movement capabilities, visual representation, and basic state.
 * By inheriting from this class, specialized entities like Player and future NPCs
 * can be implemented with consistent behavior and properties.
 */
public abstract class Entity {
    /** Current horizontal position of the entity */
    private double locX;
    
    /** Current vertical position of the entity */
    private double locY;
    
    /** Movement speed in pixels per tick */
    private int walkSpeed;
    
    /** Jump power (initial upward velocity) */
    private int jumpStrength;
    
    /** Current vertical velocity, affected by gravity and jumping */
    private double yVelocity;
    
    /** Flag indicating if this entity is controlled by the player */
    private boolean isPlayable = false;
    
    /** Flag indicating if this entity is an enemy (for future NPC implementation) */
    private boolean isEnemy = false;
    
    /** Image buffer for rendering the entity */
    private BufferedImage EntityIm;
    
    /** Panel containing the entity's visual representation */
    private JPanel EntityPane = new JPanel(new BorderLayout());

    /**
     * Constructs a new entity with the specified movement and position parameters.
     * This constructor would be used for creating detailed entities including NPCs.
     *
     * @param locX Initial horizontal position
     * @param locY Initial vertical position
     * @param walkSpeed Movement speed in pixels per tick
     * @param jumpStrength Jump power (initial upward velocity)
     * @param yVelocity Initial vertical velocity
     */
    public Entity(double locX, double locY, int walkSpeed, int jumpStrength, double yVelocity) {
        this.locX = locX;
        this.locY = locY;
        this.walkSpeed = walkSpeed;
        this.jumpStrength = jumpStrength;
        this.yVelocity = yVelocity;
    }

    /**
     * Simplified constructor that only sets whether the entity is player-controlled.
     * Currently used for the Player class but designed to support future NPC entities
     * that would have isPlayable set to false.
     *
     * @param isPlayable true if this entity is controlled by the player, false for NPCs/enemies
     */
    public Entity(boolean isPlayable) {
        this.isPlayable = isPlayable;
    }
    
    /**
     * Gets the current horizontal position of this entity.
     * 
     * @return The X coordinate
     */
    public int getLocX() {
        return (int) locX;
    }
    
    /**
     * Sets the horizontal position of this entity.
     * 
     * @param locX The new X coordinate
     */
    public void setLocX(double locX) {
        this.locX = locX;
    }
    
    /**
     * Gets the current vertical position of this entity.
     * 
     * @return The Y coordinate
     */
    public double getLocY() {
        return locY;
    }
    
    /**
     * Sets the vertical position of this entity.
     * 
     * @param locY The new Y coordinate
     */
    public void setLocY(double locY) {
        this.locY = locY;
    }
    
    /**
     * Checks if this entity is player-controlled.
     * Used to distinguish between the player character and NPCs/enemies.
     * 
     * @return true if this is a player-controlled entity, false otherwise
     */
    public boolean isPlayable() {
        return isPlayable;
    }
    
    /**
     * Sets whether this entity is player-controlled.
     * 
     * @param playable true to make this entity player-controlled, false for NPCs/enemies
     */
    public void setPlayable(boolean playable) {
        isPlayable = playable;
    }
    
    /**
     * Checks if this entity is an enemy.
     * Intended for future NPC implementation to distinguish hostile entities.
     * 
     * @return true if this entity is an enemy, false otherwise
     */
    public boolean isEnemy() {
        return isEnemy;
    }
    
    /**
     * Sets whether this entity is an enemy.
     * Used in future NPC implementation for enemy behavior.
     * 
     * @param enemy true to mark this entity as an enemy, false otherwise
     */
    public void setEnemy(boolean enemy) {
        isEnemy = enemy;
    }
}
