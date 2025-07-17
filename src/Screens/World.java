package Screens;

import Assets.Entities.Platform;
import Assets.Entities.Player;
import Assets.Entities.endPlatform;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.List;
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
    
    /** Main panel containing all game elements */
    JPanel worldPane = new JPanel(null);
    
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
        addEndPlatform(new endPlatform(560,300,50,10));
        
        // Configure window properties
        setTitle(title);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setSize(width, height);
        setBackground(Color.BLACK);
        worldPane.setBackground(Color.BLACK);
        add(worldPane);
        
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
                System.out.println("Pressed A");
            }
        });

        // Release A - Stop moving left
        inputMap.put(KeyStroke.getKeyStroke("released A"), "releaseLeft");
        actionMap.put("releaseLeft", new AbstractAction() {
            public void actionPerformed(ActionEvent e) {
                player.setMovingLeft(false);
                System.out.println("Released A");
            }
        });

        // Press D - Move right
        inputMap.put(KeyStroke.getKeyStroke("pressed D"), "pressRight");
        actionMap.put("pressRight", new AbstractAction() {
            public void actionPerformed(ActionEvent e) {
                player.setMovingRight(true);
                System.out.println("Pressed D");
            }
        });

        // Release D - Stop moving right
        inputMap.put(KeyStroke.getKeyStroke("released D"), "releaseRight");
        actionMap.put("releaseRight", new AbstractAction() {
            public void actionPerformed(ActionEvent e) {
                player.setMovingRight(false);
                System.out.println("Released D");
            }
        });

        // Press W - Jump when on ground / Move up when gravity disabled
        inputMap.put(KeyStroke.getKeyStroke("pressed W"), "pressUp");
        actionMap.put("pressUp", new AbstractAction() {
            public void actionPerformed(ActionEvent e) {
                if (player.isOnGround()) {
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
                System.out.println("Released W");
            }
        });

        // Press S - Move down
        inputMap.put(KeyStroke.getKeyStroke("pressed S"), "pressDown");
        actionMap.put("pressDown", new AbstractAction() {
            public void actionPerformed(ActionEvent e) {
                player.setMovingDown(true);
                System.out.println("Pressed S");
            }
        });

        // Release S - Stop moving down
        inputMap.put(KeyStroke.getKeyStroke("released S"), "releaseDown");
        actionMap.put("releaseDown", new AbstractAction() {
            public void actionPerformed(ActionEvent e) {
                player.setMovingDown(false);
                System.out.println("Released S");
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
}
