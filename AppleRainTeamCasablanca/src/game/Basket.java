package game;

import gfx.Assets;

import java.awt.*;

public class Basket {
    private int x, y;
    private int velocity;
    private int width, height;
    private int health;
    public static boolean isJumpingR = false;
    public static boolean isJumpingL = false;
    public static boolean isJumping = false;

    private boolean upp = true;


    private Rectangle boundingBox;

    public static boolean goingUp;
    public static boolean goingDown;
    public static boolean goingLeft;
    public static boolean goingRight;

    public Rectangle getBoundingBox() {
        return boundingBox;
    }

    public Basket() {
        this.x = 325;
        this.y = 495;
        this.width = 150;
        this.height = 103;
        this.health = 50;
        this.velocity = 15;

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
        if (this.boundingBox.contains(r) || r.contains(this.boundingBox)) {
            return true;
        }
        return false;
    }

    //Update the movement of the player
    public void tick() {
        if (isJumpingL) {
            if (upp) {
                mvUp();
                mvL();
                if (this.y < 350) {
                    upp = false;
                }
            } else {
                mvDn();
                mvL();
            }
            if (this.y == 495) {
                upp = true;
                isJumpingL = false;
            }
            return;
        }
        if (isJumpingR) {
            if (upp) {
                mvUp();
                mvR();
                if (this.y < 350) {
                    upp = false;
                }
            } else {
                mvDn();
                mvR();
            }
            if (this.y == 495) {
                upp = true;
                isJumpingR = false;
            }
            return;
        }
        if (isJumping) {
            if (upp) {
                mvUp();
                if (this.y < 350) {
                    upp = false;
                }
            } else {
                mvDn();
            }
            if (this.y == 495) {
                upp = true;
                isJumping = false;
            }
            return;
        }

//        if(goingUp) {
//            this.y -= this.velocity;
//        }
//        if(goingDown) {
//            this.y += this.velocity;
//        }
        if (goingLeft)

        {
            mvL();
        }

        if (goingRight)

        {
            mvR();
        }
        //Update the bounding box's position
        this.boundingBox.setBounds(this.x, this.y, this.width, this.height);
    }

    //Draws the player

    public void render(Graphics g) {
        g.drawImage(Assets.basket, this.x, this.y, null);
    }

    public void jump() {

    }

    public void mvR() {
        this.x += this.velocity;
    }

    public void mvL() {
        this.x -= this.velocity;
    }

    public void mvUp() {
        this.y -= this.velocity;
    }

    public void mvDn() {
        this.y += this.velocity;
    }
}
