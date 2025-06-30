package Entities;

import javax.swing.*;
import java.awt.*;
import java.awt.image.*;

public class Player extends Entity{
    private double locX;
    private double locY;
    private int walkSpeed = 5;
    private int jumpStrength = 5;
    private double yVelocity = 0;
    private boolean movingLeft = false;
    private boolean movingRight = false;
    private boolean movingDown = false;
    private boolean movingUp = false;
    BufferedImage entityIm = new BufferedImage(20,20,BufferedImage.TYPE_INT_ARGB);
    private JPanel entityPane = new JPanel(new BorderLayout());

    public Player(int locX, int locY,boolean isPlayable) {
        super(isPlayable);
        this.locX = locX;
        this.locY = locY;
        Graphics2D g2d = entityIm.createGraphics();
        entityPane.setSize(30,30);
        g2d.setColor(Color.RED);
        g2d.fillRect(0, 0, 20, 20);
        g2d.dispose();
        JLabel playerLabel = new JLabel(new ImageIcon(entityIm));
        playerLabel.setBounds((int) locX, (int) locY,20,20);
        entityPane.add(playerLabel,BorderLayout.CENTER);
        entityPane.setLocation((int) locX, (int) locY);
    }

    public int getLocX() {
        return (int) locX;
    }

    public void setLocX(double locX) {
        this.locX = locX;
    }

    public double getLocY() {
        return locY;
    }

    public void setLocY(double locY) {
        this.locY = locY;
    }

    public int getWalkSpeed() {
        return walkSpeed;
    }

    public void setWalkSpeed(int walkSpeed) {
        this.walkSpeed = walkSpeed;
    }

    public int getJumpStrength() {
        return jumpStrength;
    }

    public void setJumpStrength(int jumpStrength) {
        this.jumpStrength = jumpStrength;
    }

    public double getyVelocity() {
        return yVelocity;
    }

    public void setyVelocity(double yVelocity) {
        this.yVelocity = yVelocity;
    }

    public BufferedImage getEntityIm() {
        return entityIm;
    }

    public void setEntityIm(BufferedImage entityIm) {
        this.entityIm = entityIm;
    }

    public JPanel getEntityPane() {
        return entityPane;
    }

    public void setEntityPane(JPanel entityPane) {
        this.entityPane = entityPane;
    }

    public boolean isMovingLeft() {
        return movingLeft;
    }

    public void setMovingLeft(boolean movingLeft) {
        this.movingLeft = movingLeft;
    }

    public boolean isMovingRight() {
        return movingRight;
    }

    public void setMovingRight(boolean movingRight) {
        this.movingRight = movingRight;
    }

    public boolean isMovingDown() {
        return movingDown;
    }

    public void setMovingDown(boolean movingDown) {
        this.movingDown = movingDown;
    }

    public boolean isMovingUp() {
        return movingUp;
    }

    public void setMovingUp(boolean movingUp) {
        this.movingUp = movingUp;
    }
}
