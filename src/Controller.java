import javax.swing.*;
import Entities.*;

import java.awt.*;
import java.util.ArrayList;

public class Controller {
    private boolean worldBorders = false;
    private int tickCounter = 0;
    private String Title;
    private int Width;
    private int Height;
    private ArrayList<Entity> entityObjects = new ArrayList<>();
    Player player;
    private World world;
    Timer timer = new Timer(16, e -> {
        player = world.getPlayer();
        entityObjects.add(player);
        if (worldBorders && !world.isGravityEnabled()) {
            if (player.getEntityPane().getY() > world.getHeight() - 60) {
                player.setLocY(world.getHeight() - 60);
                player.getEntityPane().setLocation(player.getLocX(), world.getHeight() - 60);
                System.out.println("Hit Ground Level");
            }
            if (player.getEntityPane().getY() < -0.2) {
                player.setLocY(0);
                player.getEntityPane().setLocation(player.getLocX(), 0);
            }
            if (player.getEntityPane().getX() > world.getWidth() - 30) {
                player.setLocX(world.getWidth() - 30);
                player.getEntityPane().setLocation(world.getWidth() - 30, (int) player.getLocY());
            }
            if (player.getEntityPane().getX() < 0) {
                player.setLocX(0);
                player.getEntityPane().setLocation(player.getLocX(), (int) player.getLocY());
            }
        }
        else if (worldBorders && world.isGravityEnabled()) {
            if (player.getEntityPane().getY() == world.getHeight() - 60) {
                player.setyVelocity(0);
                System.out.println("Hit Ground Level");
            } else if (player.getEntityPane().getY() > world.getHeight() - 60) {
                player.setyVelocity(0);
                player.setLocY(world.getHeight() - 60);
                player.getEntityPane().setLocation(player.getLocX(), world.getHeight() - 60);
            } else if (player.getEntityPane().getY() < -0.2) {
                player.setLocY(1);
                player.setyVelocity(0);
                player.getEntityPane().setLocation(player.getLocX(), 0);
            } else {
                player.setLocY((int) (player.getLocY() + player.getyVelocity()));
                player.setyVelocity(player.getyVelocity() + world.getG());
                player.getEntityPane().setLocation(player.getLocX(), (int) player.getLocY());
            }
            if (player.getEntityPane().getX() > world.getWidth() - 30) {
                player.setLocX(world.getWidth() - 30);
                player.getEntityPane().setLocation(world.getWidth() - 30, (int) player.getLocY());
            }
            if (player.getEntityPane().getX() < 0) {
                player.setLocX(0);
                player.getEntityPane().setLocation(player.getLocX(), (int) player.getLocY());
            }
        }
        else if (!worldBorders && world.isGravityEnabled()){
            player.setLocY((int) (player.getLocY() + player.getyVelocity()));
            player.setyVelocity(player.getyVelocity() + world.getG());
            player.getEntityPane().setLocation(player.getLocX(), (int) player.getLocY());
        }
        if (player.isMovingLeft()) {
            Point p = player.getEntityPane().getLocation();
            player.setLocX((int) p.getX());
            player.getEntityPane().setLocation(p.x - player.getWalkSpeed(), p.y);
        }
        if (player.isMovingRight()) {
            Point p = player.getEntityPane().getLocation();
            player.setLocX((int) p.getX());
            player.getEntityPane().setLocation(p.x + player.getWalkSpeed(), p.y);
        }
        if (player.isMovingDown() && !world.isGravityEnabled()) {
            player.setLocY(player.getLocY() + player.getWalkSpeed());
            player.getEntityPane().setLocation((int) player.getLocX(), (int) player.getLocY());
        }
        if (player.isMovingDown() && player.isMovingRight() &&!world.isGravityEnabled()) {
            player.setLocX(player.getLocX() + player.getWalkSpeed()/2.5);
            player.setLocY(player.getLocY() + player.getWalkSpeed()/2.5);
            player.getEntityPane().setLocation((int) player.getLocX(), (int) player.getLocY());
        }
        if (player.isMovingDown() && player.isMovingLeft() &&!world.isGravityEnabled()) {
            player.setLocX(player.getLocX() - player.getWalkSpeed()/2.5);
            player.setLocY(player.getLocY() + player.getWalkSpeed()/2.5);
            player.getEntityPane().setLocation((int) player.getLocX(), (int) player.getLocY());
        }
        if (player.isMovingUp() && !world.isGravityEnabled()) {
            player.setLocY(player.getLocY() - player.getWalkSpeed());
            player.getEntityPane().setLocation((int) player.getLocX(), (int) player.getLocY());
        }
        if (player.isMovingUp() && player.isMovingRight() &&!world.isGravityEnabled()) {
            player.setLocX(player.getLocX() + player.getWalkSpeed()/2.5);
            player.setLocY(player.getLocY() - player.getWalkSpeed()/2.5);
            player.getEntityPane().setLocation((int) player.getLocX(), (int) player.getLocY());
        }
        if (player.isMovingUp() && player.isMovingLeft() &&!world.isGravityEnabled()) {
            player.setLocX(player.getLocX() - player.getWalkSpeed()/2.5);
            player.setLocY(player.getLocY() - player.getWalkSpeed()/2.5);
            player.getEntityPane().setLocation((int) player.getLocX(), (int) player.getLocY());
        }
        if (player.isMovingUp() && world.isGravityEnabled()) {
            player.setLocY(player.getLocY() - player.getWalkSpeed());
            player.setyVelocity(player.getyVelocity() - player.getJumpStrength());
            player.getEntityPane().setLocation((int) player.getLocX(), (int) player.getLocY());
        }
        if (player.isMovingUp() && player.isMovingRight() && world.isGravityEnabled()) {
            player.setLocX(player.getLocX() + player.getWalkSpeed());
            player.setLocY(player.getLocY() - player.getJumpStrength());
            player.setyVelocity(player.getyVelocity() - player.getJumpStrength());
            player.getEntityPane().setLocation((int) player.getLocX(), (int) player.getLocY());
        }
        if (player.isMovingUp() && player.isMovingLeft() && world.isGravityEnabled()) {
            player.setLocX(player.getLocX() - player.getWalkSpeed());
            player.setLocY(player.getLocY() - player.getJumpStrength());
            player.setyVelocity(player.getyVelocity() - player.getJumpStrength());
            player.getEntityPane().setLocation((int) player.getLocX(), (int) player.getLocY());
        }
        world.repaint();
        tickCounter++;
        if (tickCounter%97 == 0)
        {
            System.out.println("Player Position: " + player.getLocX() + " " + player.getLocY());
        }
    });

    public Controller(int width,int height, String title,boolean enableGravity, boolean worldBorders, Player player) {
        this.Width = width;
        this.Height = height;
        this.Title = title;
        this.worldBorders = worldBorders;
        this.player = player;
        this.world = new World(Width,Height, Title, player);
        world.repaint();
        world.setGravityEnabled(enableGravity);
        timer.start();
    }

    void Restart() {
        player.setLocX(0);
        player.setLocY(0);
        player.getEntityPane().setLocation(player.getLocX(), (int) player.getLocY());
    }
}
