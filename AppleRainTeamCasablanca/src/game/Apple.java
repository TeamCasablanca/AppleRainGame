package game;

import gfx.Assets;

import java.awt.*;
import java.util.Random;

public class Apple {

    private int x, y;
    private int velocity;
    private int width, height;
    private static Random ran = new Random();

    private Rectangle boundingBox;

    public Apple(int x, int y,int vel) {
        this.x = x;
        this.y = y;
        this.width = 35;
        this.height = 35;
        this.velocity = vel;
        this.boundingBox = new Rectangle(this.width, this.height);
    }
    //Checks if the player intersects with something
    public boolean Intersects(Rectangle r) {
        if (this.boundingBox.contains(r) || r.contains(this.boundingBox)) {
            return true;
        }
        return false;
    }
    //Update the movement of the player
    public void tick() {
        //Update the bounding box's position
        this.boundingBox.setBounds(this.x, this.y, this.width, this.height);
        this.y += this.velocity;
    }
    //Draws the player
    public void render(Graphics g) {
        g.drawImage(Assets.apple, this.x, this.y, null);
    }

    public static Apple createRand() {

        int x = ran.nextInt(795) + 5;
        int y = ran.nextInt(10) + 5;
        int vel=ran.nextInt(7)+2;
        Apple apple = new Apple(x, y,vel);
        return apple;
    }
    public int getY() {
        return y;
    }
}
