import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.List;

import Entities.*;
public class World extends JFrame {

    private double g = .5;
    private boolean gravityEnabled = true;
    private double groundLevel = 420;
    private ArrayList<Integer> Borders;
    private List<Player> players;
    JPanel worldPane = new JPanel(null);
    Player player;
    public World(int width, int height, String title, Player player){
        this.player = player;
        setTitle(title);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setSize(width, height);
        setBackground(Color.BLACK);
        worldPane.setBackground(Color.BLACK);
        add(worldPane);
        worldPane.add(this.player.getEntityPane());
        setupKeyBindings();
        setLocationRelativeTo(null);
        setVisible(true);
        revalidate();
        repaint();
    }

    public double getG() {
        return g;
    }

    public void setG(double g) {
        this.g = g;
    }

    public double getGroundLevel() {
        return groundLevel;
    }

    public void setGroundLevel(int groundLevel) {
        this.groundLevel = groundLevel;
    }

    public List<Player> getPlayers() {
        return players;
    }

    public void setPlayers(List<Player> players) {
        this.players = players;
    }

    public JPanel getWorldPane() {
        return worldPane;
    }

    public void setWorldPane(JPanel worldPane) {
        this.worldPane = worldPane;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public boolean isGravityEnabled() {
        return gravityEnabled;
    }

    public void setGravityEnabled(boolean gravityEnabled) {
        this.gravityEnabled = gravityEnabled;
    }

    public void setGroundLevel(double groundLevel) {
        this.groundLevel = groundLevel;
    }

    private void setupKeyBindings() {
        InputMap inputMap = worldPane.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);
        ActionMap actionMap = worldPane.getActionMap();

        // Press A
        inputMap.put(KeyStroke.getKeyStroke("pressed A"), "pressLeft");
        actionMap.put("pressLeft", new AbstractAction() {
            public void actionPerformed(ActionEvent e) {
                player.setMovingLeft(true);
                System.out.println("Pressed A");
            }
        });

        // Release A
        inputMap.put(KeyStroke.getKeyStroke("released A"), "releaseLeft");
        actionMap.put("releaseLeft", new AbstractAction() {
            public void actionPerformed(ActionEvent e) {
                player.setMovingLeft(false);
                System.out.println("Released A");
            }
        });

        // Press D
        inputMap.put(KeyStroke.getKeyStroke("pressed D"), "pressRight");
        actionMap.put("pressRight", new AbstractAction() {
            public void actionPerformed(ActionEvent e) {
                player.setMovingRight(true);
                System.out.println("Pressed D");
            }
        });

        // Release D
        inputMap.put(KeyStroke.getKeyStroke("released D"), "releaseRight");
        actionMap.put("releaseRight", new AbstractAction() {
            public void actionPerformed(ActionEvent e) {
                player.setMovingRight(false);
                System.out.println("Released D");
            }
        });

        // Press W
        inputMap.put(KeyStroke.getKeyStroke("pressed W"), "pressUp");
        actionMap.put("pressUp", new AbstractAction() {
            public void actionPerformed(ActionEvent e) {
                player.setMovingUp(true);
                System.out.println("Pressed W");
            }
        });

        // Release w
        inputMap.put(KeyStroke.getKeyStroke("released W"), "releaseUp");
        actionMap.put("releaseUp", new AbstractAction() {
            public void actionPerformed(ActionEvent e) {
                player.setMovingUp(false);
                System.out.println("Released W");
            }
        });

        // Press S
        inputMap.put(KeyStroke.getKeyStroke("pressed S"), "pressDown");
        actionMap.put("pressDown", new AbstractAction() {
            public void actionPerformed(ActionEvent e) {
                player.setMovingDown(true);
                System.out.println("Pressed S");
            }
        });

        // Release S
        inputMap.put(KeyStroke.getKeyStroke("released S"), "releaseDown");
        actionMap.put("releaseDown", new AbstractAction() {
            public void actionPerformed(ActionEvent e) {
                player.setMovingDown(false);
                System.out.println("Released S");
            }
        });
    }

    public ArrayList<Integer> getBorders() {
        return Borders;
    }

    public void setBorders(ArrayList<Integer> borders) {
        Borders = borders;
    }
}
