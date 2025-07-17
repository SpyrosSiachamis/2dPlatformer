import Assets.Entities.Platform;
import Assets.Entities.Player;
import Screens.World;
import Screens.endScreen;
import Screens.loseScreen;
import java.awt.*;
import java.io.FileNotFoundException;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.*;

/**
 * The Controller class serves as the main game engine, managing game logic, physics,
 * collision detection, and animation for the 2D platformer game.
 * It handles the game loop, player movement, gravity, platform interactions,
 * and world boundaries.
 */
public class Controller {
    // For measuring timer interval
    private long lastLoopTime = System.currentTimeMillis();
    /** Controls whether the player can move beyond world boundaries */
    private boolean worldBorders;
    
    /** Counter for tracking game ticks/frames */
    private int tickCounter = 0;
    
    /** Game window title */
    private String Title;
    
    /** Game window width */
    private int Width;
    
    /** Game window height */
    private int Height;
    
    /** Background music resource path */
    private final String musicResourcePath = "/Assets/Music/Funny_Bit.wav";
    
    /** Audio clip for game music */
    private Clip clip;
    
    /** The player character controlled by the user */
    Player player;
    
    /** The game world containing all entities and platforms */
    private World world;
    
    /** Flag indicating if player has touched the bottom of the world (losing condition) */
    private boolean ground = false;
    
    /** Counter to prevent multiple end screens */
    int i = 0;
    
    /**
     * The main game loop timer that runs at approximately 60 FPS (16ms intervals).
     * Handles physics calculations, collision detection, and screen updates.
     */
    Timer gameLoop = new Timer(16, e -> {
        long loopStart = System.nanoTime();
        long now = System.currentTimeMillis();
        long interval = now - lastLoopTime;
        lastLoopTime = now;
        player = world.getPlayer();

        double newLocX = player.getLocX();
        double newLocY = player.getLocY();
        double vy = player.getyVelocity();
        
        // Horizontal movement
        if (player.isMovingLeft()) {
            newLocX -= player.getWalkSpeed();
        }
        if (player.isMovingRight()) {
            newLocX += player.getWalkSpeed();
        }

        // Apply gravity if enabled
        if (world.isGravityEnabled()) {
            vy += world.getG();  // Gravity accelerates downward
            newLocY += vy;       // Apply vertical velocity
        } else {
            // No gravity: free up/down movement
            if (player.isMovingUp()) {
                newLocY -= player.getWalkSpeed();
            }
            if (player.isMovingDown()) {
                newLocY += player.getWalkSpeed();
            }
        }

        // --- Collision detection ---

        boolean landed = false;
        Rectangle playerRect = new Rectangle((int)newLocX, (int)newLocY, 20, 20); // Assuming player size 20x20
        
        // Check collision with platforms
        for (Platform plat : world.getPlatforms()) {
            Rectangle platRect = plat.getBounds();

            if (playerRect.intersects(platRect)) {
                int playerOldBottom = (int)(player.getLocY() + 20);
                int playerOldTop = (int)(player.getLocY());
                int platformTop = platRect.y;
                int platformBottom = platRect.y + platRect.height;

                // Check if player is falling onto platform from above
                if (playerOldBottom <= platformTop && vy > 0) {
                    newLocY = platformTop - 20;  // Place player on top of platform
                    vy = 0;
                    landed = true;
                    
                    // Check for end platform (win condition)
                    if (plat.isEndPlat()) {
                        world.dispose();
                        if(i<1){
                            endScreen win = new endScreen();
                            win.getFrame().setVisible(true);
                            i++;
                        }
                    }
                    break;
                }
                
                // Check if player collides with platform from under
                if (playerOldTop >= platformBottom && vy < 0) {
                    newLocY = platformBottom;
                    vy = 0;
                    break;
                }
            }
        }

        // Ground collision (world bottom - lose condition)
        int groundLevel = world.getHeight()-65;
        if (newLocY >= groundLevel) {
            newLocY = groundLevel;
            vy = 0;
            landed = true;
            ground = true;
        }

        // Prevent moving out of horizontal bounds if worldBorders is enabled
        if (newLocX < 0) {
            newLocX = 0;
        }
        if (newLocX > world.getWidth() - 45) {
            newLocX = world.getWidth() - 45;
        }

        // Update player onGround status
        player.setOnGround(landed);

        // Update player position and velocity
        player.setLocX(newLocX);
        player.setLocY(newLocY);
        player.setyVelocity(vy);
        player.getEntityPane().setLocation((int)newLocX, (int)newLocY);

        // Refresh the game display
        world.repaint();

        long loopEnd = System.nanoTime();
        long loopDuration = (loopEnd - loopStart) / 1000000; // ms
        System.out.println("[PERF] Game loop duration: " + loopDuration + " ms");
        System.out.println("[PERF] Timer interval: " + interval + " ms");
        tickCounter++;
        System.out.println("Player Position: " + player.getLocX() + " " + player.getLocY());
        
        // Handle lose condition
        if (ground == true) {
            world.dispose();
            if(i<1){
                loseScreen lose = new loseScreen();
                lose.getFrame().setVisible(true);
                i++;
            }
        }
    });

    /**
     * Creates a new game controller with specified parameters.
     * Initializes the game world, player, and starts the game loop.
     *
     * @param width Width of the game window
     * @param height Height of the game window
     * @param title Title of the game window
     * @param enableGravity Whether gravity physics should be enabled
     * @param worldBorders Whether world border collision should be enabled
     * @param player The player character
     * @throws FileNotFoundException If required game files cannot be found
     */
    public Controller(int width, int height, String title, boolean enableGravity, boolean worldBorders, Player player) throws FileNotFoundException {
        this.Width = width;
        this.Height = height;
        this.Title = title;
        this.worldBorders = worldBorders;
        this.player = player;
        this.world = new World(Width, Height, Title, player);
        world.repaint();
        world.setGravityEnabled(enableGravity);

        // Create a ground platform at the bottom of the world
        Platform ground = new Platform(0, world.getHeight() - 40, world.getWidth(), 40);
        world.addPlatform(ground);

        // Start the game loop
        gameLoop.start();
    }

    /**
     * Resets the player position to the origin (0,0).
     * Used to restart the game or reposition the player after events.
     */
    void Restart() {
        player.setLocX(0);
        player.setLocY(0);
        player.getEntityPane().setLocation((int) player.getLocX(), (int) player.getLocY());
    }

    /**
     * Starts playing the game's background music in a continuous loop.
     * Uses classpath resources to ensure compatibility when running from a JAR file.
     * 
     * @throws RuntimeException If there's an error playing the music file
     */
    public void playMusic() {
        try {
            // Use getResourceAsStream to load from classpath (works in JAR files)
            java.io.InputStream musicStream = getClass().getResourceAsStream(musicResourcePath);
            if (musicStream == null) {
                System.err.println("Warning: Could not find music file: " + musicResourcePath);
                return; // Continue without music rather than crashing
            }
            AudioInputStream m = AudioSystem.getAudioInputStream(musicStream);
            clip = AudioSystem.getClip();
            clip.open(m);
            clip.start();
            clip.loop(Clip.LOOP_CONTINUOUSLY);
        } catch (Exception e) {
            System.err.println("Error playing music: " + e.getMessage());
            // Don't throw runtime exception for music - game should continue without music
        }
    }
}
