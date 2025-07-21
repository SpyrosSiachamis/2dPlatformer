package Assets.Entities;

import java.awt.*;
import java.awt.image.BufferedImage;
import javax.swing.*;

/**
 * The Platform class represents a solid surface in the game world that the player can stand on.
 * Platforms serve as the foundation for level design, providing surfaces for player movement
 * and obstacles for navigation. They can be regular platforms or special end platforms that
 * trigger level completion when reached by the player.
 * 
 * Each platform has a rectangular collision boundary and visual representation that is
 * rendered in the game world.
 */
public class Platform
{
    /** The rectangular collision bounds of this platform */
    private Rectangle bounds;
    
    /** The image used to visually represent this platform */
    private BufferedImage platIm;
    
    /** Panel containing the platform's visual elements */
    private JPanel platPanel = new JPanel();
    
    /** Label displaying the platform's image */
    private JLabel platLabel = new JLabel();
    
    /** Flag indicating if this is an end platform (level goal) */
    boolean isEndPlat = false;

    private boolean isActive = true;

    private boolean isGroundPlat;
    /**
     * Constructs a new platform with the specified position and dimensions.
     * Initializes the platform's collision bounds and visual representation.
     *
     * @param x The x-coordinate of the platform's top-left corner
     * @param y The y-coordinate of the platform's top-left corner
     * @param width The width of the platform
     * @param height The height of the platform
     */
    public Platform(int x, int y, int width, int height) {
        bounds = new Rectangle(x, y, width, height);
        platIm = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        this.platIm = platIm;
        this.platPanel = platPanel;
        this.isGroundPlat = isGroundPlat;
    }

    /**
     * Gets the rectangular collision bounds of this platform.
     * Used by the Controller for collision detection with the player.
     * 
     * @return The Rectangle representing this platform's collision area
     */
    public Rectangle getBounds() {
        return bounds;
    }

    /**
     * Sets the rectangular collision bounds of this platform.
     * 
     * @param bounds The new collision bounds for this platform
     */
    public void setBounds(Rectangle bounds) {
        this.bounds = bounds;
    }

    /**
     * Gets the image buffer used to visually represent this platform.
     * 
     * @return The platform's image
     */
    public BufferedImage getPlatIm() {
        return platIm;
    }

    /**
     * Sets the image used to visually represent this platform.
     * 
     * @param platIm The new image to use for this platform
     */
    public void setPlatIm(BufferedImage platIm) {
        this.platIm = platIm;
    }

    /**
     * Gets the panel containing this platform's visual elements.
     * 
     * @return The platform's panel
     */
    public JPanel getPlatPanel() {
        return platPanel;
    }

    /**
     * Sets the panel containing this platform's visual elements.
     * 
     * @param platPanel The new panel for this platform
     */
    public void setPlatPanel(JPanel platPanel) {
        this.platPanel = platPanel;
    }

    /**
     * Gets the label displaying this platform's image.
     * 
     * @return The platform's label
     */
    public JLabel getPlatLabel() {
        return platLabel;
    }

    /**
     * Sets the label displaying this platform's image.
     * 
     * @param platLabel The new label for this platform
     */
    public void setPlatLabel(JLabel platLabel) {
        this.platLabel = platLabel;
    }

    /**
     * Checks if this is an end platform (level goal).
     * End platforms trigger level completion when touched by the player.
     * 
     * @return true if this is an end platform, false otherwise
     */
    public boolean isEndPlat() {
        return isEndPlat;
    }

    /**
     * Sets whether this is an end platform (level goal).
     * 
     * @param endPlat true to make this an end platform, false for a regular platform
     */
    public void setEndPlat(boolean endPlat) {
        isEndPlat = endPlat;
    }


    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean b) {
        isActive = b;
    }

}
