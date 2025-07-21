package Screens;

import Assets.Entities.Platform;
import Assets.Entities.Player;
import Assets.Entities.endPlatform;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import javax.swing.*;


/**
 * The World class represents the game world where gameplay takes place.
 * It manages platforms, player movement, physics, and keyboard input.
 * This class serves as the primary container for all game elements and
 * handles the rendering and positioning of platforms and the player character.
 */
public class World extends JFrame {
    /** List of all platforms in the world */
    private List<Platform> platforms = new ArrayList<>();
    
    /** Gravity constant - determines fall acceleration */
    private double g = .5;
    
    /** Flag indicating if gravity physics should be applied */
    private boolean gravityEnabled = true;
    
    /** World boundaries */
    private ArrayList<Integer> Borders;
    
    /** List of all players in the world (for potential multiplayer) */
    private List<Player> players;

    private Camera camera = new Camera();
    /** Main panel containing all game elements */
    JPanel worldPane = new JPanel(null);

    JPanel pointsPane = new JPanel(null);
    JLabel points = new JLabel("Points: ");
    private Random rand = new Random();
    private int worldWidth, worldHeight;
    /** The primary player character */
    Player player;

    /**
     * Constructs a new game world with specified dimensions, title and player character.
     * Initializes platforms including the end goal platform, sets up window properties,
     * and configures keyboard controls.
     *
     * @param width Width of the game world window
     * @param height Height of the game world window
     * @param title Title of the game window
     * @param player The player character to place in the world
     */
    public World(int width, int height, String title, Player player){
        this.player = player;

        // Initialize platforms for the level layout
        addPlatform(new Platform(20,300,90,10));
        addPlatform(new Platform(140,240,90,10));
        addPlatform(new Platform(260,300,90,10));
        addPlatform(new Platform(400,300,90,10));
        addPlatform(new Platform(560,240,90,10));

        // Configure window properties
        setTitle(title);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setSize(width, height);
        setBackground(Color.BLACK);
        worldPane.setBackground(Color.BLACK);
        add(worldPane);
        points.setForeground(Color.WHITE);
        points.setBounds(10,10,100,20);
        pointsPane.setOpaque(false);
        pointsPane.setBounds(0,0,200,40);
        pointsPane.add(points);
        worldPane.add(pointsPane);


        // Add player to the world and configure input
        worldPane.add(this.player.getEntityPane());
        setupKeyBindings();
        
        // Center window and make it visible
        setLocationRelativeTo(null);
        setVisible(true);
        revalidate();
        repaint();
    }

    /**
     * Gets the gravity constant value.
     * 
     * @return The gravity constant
     */
    public double getG() {
        return g;
    }

    /**
     * Sets the gravity constant value.
     * Higher values cause faster falling acceleration.
     * 
     * @param g The new gravity constant value
     */
    public void setG(double g) {
        this.g = g;
    }

    /**
     * Gets the list of players in the world.
     * 
     * @return List of players
     */
    public List<Player> getPlayers() {
        return players;
    }

    /**
     * Sets the list of players in the world.
     * 
     * @param players The new list of players
     */
    public void setPlayers(List<Player> players) {
        this.players = players;
    }

    /**
     * Gets the main panel containing all game elements.
     * 
     * @return The world panel
     */
    public JPanel getWorldPane() {
        return worldPane;
    }

    /**
     * Sets the main panel containing all game elements.
     * 
     * @param worldPane The new world panel
     */
    public void setWorldPane(JPanel worldPane) {
        this.worldPane = worldPane;
    }

    /**
     * Gets the primary player character.
     * 
     * @return The player character
     */
    public Player getPlayer() {
        return player;
    }

    /**
     * Sets the primary player character.
     * 
     * @param player The new player character
     */
    public void setPlayer(Player player) {
        this.player = player;
    }

    /**
     * Checks if gravity physics are enabled.
     * 
     * @return true if gravity is enabled, false otherwise
     */
    public boolean isGravityEnabled() {
        return gravityEnabled;
    }

    /**
     * Sets whether gravity physics should be applied.
     * 
     * @param gravityEnabled true to enable gravity, false to disable it
     */
    public void setGravityEnabled(boolean gravityEnabled) {
        this.gravityEnabled = gravityEnabled;
    }

    /**
     * Sets up keyboard input bindings for controlling the player character.
     * Maps W, A, S, D keys to player movement actions.
     */
    private void setupKeyBindings() {
        InputMap inputMap = worldPane.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);
        ActionMap actionMap = worldPane.getActionMap();

        // Press A - Move left
        inputMap.put(KeyStroke.getKeyStroke("pressed A"), "pressLeft");
        actionMap.put("pressLeft", new AbstractAction() {
            public void actionPerformed(ActionEvent e) {
                player.setMovingLeft(true);
            }
        });

        // Release A - Stop moving left
        inputMap.put(KeyStroke.getKeyStroke("released A"), "releaseLeft");
        actionMap.put("releaseLeft", new AbstractAction() {
            public void actionPerformed(ActionEvent e) {
                player.setMovingLeft(false);
            }
        });

        // Press D - Move right
        inputMap.put(KeyStroke.getKeyStroke("pressed D"), "pressRight");
        actionMap.put("pressRight", new AbstractAction() {
            public void actionPerformed(ActionEvent e) {
                player.setMovingRight(true);
            }
        });

        // Release D - Stop moving right
        inputMap.put(KeyStroke.getKeyStroke("released D"), "releaseRight");
        actionMap.put("releaseRight", new AbstractAction() {
            public void actionPerformed(ActionEvent e) {
                player.setMovingRight(false);
            }
        });

        // Press W - Jump when on ground / Move up when gravity disabled
        inputMap.put(KeyStroke.getKeyStroke("pressed W"), "pressUp");
        actionMap.put("pressUp", new AbstractAction() {
            public void actionPerformed(ActionEvent e) {
                if (player.isOnGround(getPlatforms())) {
                    player.setyVelocity(-player.getJumpStrength()); // jump impulse upwards (negative velocity)
                    player.setOnGround(false);
                }
                player.setMovingUp(true);
            }
        });

        // Release W - Stop moving up
        inputMap.put(KeyStroke.getKeyStroke("released W"), "releaseUp");
        actionMap.put("releaseUp", new AbstractAction() {
            public void actionPerformed(ActionEvent e) {
                player.setMovingUp(false);
            }
        });

        // Press S - Move down
        inputMap.put(KeyStroke.getKeyStroke("pressed S"), "pressDown");
        actionMap.put("pressDown", new AbstractAction() {
            public void actionPerformed(ActionEvent e) {
                player.setMovingDown(true);
            }
        });

        // Release S - Stop moving down
        inputMap.put(KeyStroke.getKeyStroke("released S"), "releaseDown");
        actionMap.put("releaseDown", new AbstractAction() {
            public void actionPerformed(ActionEvent e) {
                player.setMovingDown(false);
            }
        });
    }

    /**
     * Gets the list of all platforms in the world.
     * 
     * @return The list of platforms
     */
    public List<Platform> getPlatforms() {
        return platforms;
    }

    /**
     * Sets the list of platforms in the world.
     * 
     * @param platforms The new list of platforms
     */
    public void setPlatforms(List<Platform> platforms) {
        this.platforms = platforms;
    }

    /**
     * Adds a standard platform to the world.
     * Creates visual representation of the platform and adds it to the world panel.
     * 
     * @param platform The platform to add
     */
    public void addPlatform(Platform platform){
        // Create visual representation of platform
        Graphics2D g2d = platform.getPlatIm().createGraphics();
        g2d.setColor(Color.WHITE);
        g2d.fillRect(0, 0, platform.getBounds().width, platform.getBounds().height);
        g2d.dispose();
        
        // Configure platform visual components
        platform.getPlatPanel().setSize(platform.getBounds().width, platform.getBounds().height);
        platform.setPlatLabel(new JLabel(new ImageIcon(platform.getPlatIm())));
        platform.getPlatLabel().setLocation(platform.getBounds().getLocation());
        JLabel platLabel = platform.getPlatLabel();
        JPanel platPanel = platform.getPlatPanel();
        platPanel.add(platLabel);
        platPanel.setLocation(platform.getBounds().getLocation());
        platform.setPlatLabel(platLabel);
        
        // Add platform to game world
        platforms.add(platform);
        worldPane.add(platform.getPlatPanel());
        worldPane.repaint();
    }
    
    /**
     * Adds an end platform (goal platform) to the world.
     * Similar to standard platforms but with different visual appearance.
     * When the player reaches this platform, they win the level.
     * 
     * @param platform The end platform to add
     */
    public void addEndPlatform(endPlatform platform){
        // Create visual representation of end platform (green color)
        Graphics2D g2d = platform.getPlatIm().createGraphics();
        g2d.setColor(Color.GREEN);
        g2d.fillRect(0, 0, platform.getBounds().width, platform.getBounds().height);
        g2d.dispose();
        
        // Configure platform visual components
        platform.getPlatPanel().setSize(platform.getBounds().width, platform.getBounds().height);
        platform.setPlatLabel(new JLabel(new ImageIcon(platform.getPlatIm())));
        platform.getPlatLabel().setLocation(platform.getBounds().getLocation());
        JLabel platLabel = platform.getPlatLabel();
        JPanel platPanel = platform.getPlatPanel();
        platPanel.add(platLabel);
        platPanel.setLocation(platform.getBounds().getLocation());
        platform.setPlatLabel(platLabel);
        
        // Add end platform to game world
        platforms.add(platform);
        worldPane.add(platform.getPlatPanel());
        worldPane.repaint();
    }

    public class Camera{
        private int x;
        private int y;

        public void update()
        {
            x = Math.max(0,player.getLocX() - getWidth() / 15);
        }

        public int getX() {
            return x;
        }

        public void setX(int x) {
            this.x = x;
        }

        public int getY() {
            return y;
        }

        public void setY(int y) {
            this.y = y;
        }

        public Camera() {
        }
    }

    public void applyCameraOffset() {
        int offsetX = camera.getX();
        player.getEntityPane().setLocation(player.getLocX() - offsetX, (int) player.getLocY());
        for (Platform p : platforms) {
            p.getPlatPanel().setLocation(p.getBounds().x - offsetX, p.getBounds().y);
        }
    }

    public Camera getCamera() {
        return camera;
    }

    public void setCamera(Camera camera) {
        this.camera = camera;
    }

    public void updatePlatforms() {
        int offsetX = camera.getX();

        for (Platform p : platforms) {
            if (p.isActive()) {  // assuming you added isActive()
                // Move platform visually relative to camera
                // The platform's bounds.x is its world position
                // So we check if it's off screen relative to camera offset
                if (p.getBounds().getLocation().x + p.getBounds().width - offsetX < 0) {
                    p.setActive(false);
                }
            }
        }

        // Recycle inactive platforms
        for (Platform p : platforms) {
            if (!p.isActive()) {
                Platform last = findLastActivePlatform();
                if (last != null) {
                    int gap = 50 + rand.nextInt(100);
                    int newX = last.getBounds().x + last.getBounds().width + gap;

                    int heightOffset = rand.nextInt(51) - 25; // ±25 px vertical offset
                    int newY = clamp(last.getBounds().y + heightOffset, 100, worldPane.getHeight() - 100);

                    // Update platform bounds and visual location
                    Rectangle bounds = p.getBounds();
                    bounds.setLocation(newX, newY);
                    p.setBounds(bounds);
                    p.getPlatPanel().setLocation(newX - offsetX, newY);

                    p.setActive(true);
                }
            }
        }
    }

    private Platform findLastActivePlatform() {
        Platform last = null;
        for (Platform p : platforms) {
            if (p.isActive()) {
                if (last == null || p.getBounds().x > last.getBounds().x) {
                    last = p;
                }
            }
        }
        return last;
    }
    private int clamp(int x, int min, int max) {
        return Math.max(min, Math.min(max, x));
    }

    public int getWorldHeight() {
        return worldHeight;
    }

    public void setWorldHeight(int worldHeight) {
        this.worldHeight = worldHeight;
    }

    public int getWorldWidth() {
        return worldWidth;
    }

    public void setWorldWidth(int worldWidth) {
        this.worldWidth = worldWidth;
    }

    public JLabel getPoints() {
        return points;
    }

    public void setPoints(JLabel points) {
        this.points = points;
    }
}
