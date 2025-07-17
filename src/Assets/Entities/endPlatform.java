package Assets.Entities;

/**
 * The endPlatform class represents a special type of platform that marks the goal/finish
 * of a game level. When the player character makes contact with an endPlatform, 
 * it triggers level completion and displays the victory screen.
 * 
 * This class extends the regular Platform class, inheriting all its properties and behaviors,
 * but automatically sets the isEndPlat flag to true during construction to differentiate it
 * from regular platforms. In the game, endPlatforms are typically rendered with a green color
 * to visually distinguish them as goal platforms.
 */
public class endPlatform extends Platform {
    
    /**
     * Constructs a new end platform with the specified position and dimensions.
     * Initializes the platform's collision bounds and visual representation,
     * and marks it as an end platform by setting the isEndPlat flag to true.
     *
     * @param x The x-coordinate of the platform's top-left corner
     * @param y The y-coordinate of the platform's top-left corner
     * @param width The width of the platform
     * @param height The height of the platform
     */
    public endPlatform(int x, int y, int width, int height) {
        super(x, y, width, height);
        setEndPlat(true); // Mark this as an end platform
    }
}
