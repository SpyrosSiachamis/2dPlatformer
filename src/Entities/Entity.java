package Entities;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public abstract class Entity {
    private double locX;
    private double locY;
    private int walkSpeed;
    private int jumpStrength;
    private double yVelocity;
    private boolean isPlayable = false;
    private boolean isEnemy = false;
    private BufferedImage EntityIm;
    private JPanel EntityPane = new JPanel(new BorderLayout());

    public Entity(double locX, double locY, int walkSpeed, int jumpStrength, double yVelocity) {
        this.locX = locX;
        this.locY = locY;
        this.walkSpeed = walkSpeed;
        this.jumpStrength = jumpStrength;
        this.yVelocity = yVelocity;
    }

    public Entity(boolean isPlayable) {}
}
