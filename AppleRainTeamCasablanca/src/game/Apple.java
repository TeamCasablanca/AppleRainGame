package game;

import gfx.Assets;

import java.awt.*;

public class Apple {
    public void setY(int y) {
        this.y += 2;
    }

    public void setX(int x) {
        this.x = x;
    }

    private int x, y;
    private int velocity;
    private int width, height;
    private int health;

    private Rectangle boundingBox;

    public static boolean goingUp;
    public static boolean goingDown;
    public static boolean goingLeft;
    public static boolean goingRight;

    public Apple(int x, int y) {
        this.x = x;
        this.y = y;
        this.width = 35;
        this.height = 35;
        this.health = 50;
        this.velocity = 4;
        this.boundingBox = new Rectangle(this.width, this.height);

//        goingUp = false;
//        goingDown = false;
        goingLeft = false;
        goingRight = false;
    }

    public int getHealth() {
        return this.health;
    }

    //Checks if the player intersects with something
    public boolean Intersects(Rectangle r) {
        if(this.boundingBox.contains(r) || r.contains(this.boundingBox)) {
            return true;
        }
        return false;
    }


        public Rectangle boundingBox(){
         return this.boundingBox;
        }
    //Update the movement of the player
    public void tick() {
        //Update the bounding box's position
        this.boundingBox.setBounds(this.x, this.y, this.width, this.height);

//        if(goingUp) {
//            this.y -= this.velocity;
//        }
//        if(goingDown) {
//            this.y += this.velocity;
//        }

//            this.x -= this.velocity;


            this.y += this.velocity;

    }

    //Draws the player
    public void render(Graphics g) {
        g.drawImage(Assets.apple, this.x, this.y, null);
    }
}
