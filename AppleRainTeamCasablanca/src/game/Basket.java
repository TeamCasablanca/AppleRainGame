package game;

import display.Display;
import gfx.Assets;

import java.awt.*;

public class Basket {
    private int x, y;
    private int velocity;
    private int width, height;
    public static boolean isJumpingR = false;
    public static boolean isJumpingL = false;
    public static boolean isJumping = false;
    public static boolean isSlideLeft = false;
    public static boolean isSlideRight = false;

    private int jumpHigh = 350;
    private int slideSpeedMult = 7;

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
        this.velocity = 15;

        this.boundingBox = new Rectangle(this.width, this.height);


        goingLeft = false;
        goingRight = false;
    }

    //Update the movement of the player
    public void tick() {
        if (isJumpingL) {
            if (upp) {
                mvUp();
                mvL();
                if (this.y < jumpHigh) {
                    upp = false;
                }
            } else {
                mvDn();
                mvL();
            }
            if (this.y > 495) {
                upp = true;
                isJumpingL = false;
            }
            boundBoxUpdate();
            return;
        }
        if (isJumpingR) {
            if (upp) {
                mvUp();
                mvR();
                if (this.y < jumpHigh) {
                    upp = false;
                }
            } else {
                mvDn();
                mvR();
            }
            if (this.y > 495) {
                upp = true;
                isJumpingR = false;
            }
            boundBoxUpdate();
            return;
        }
        if (isJumping) {
            if (upp) {
                mvUp();
                if (this.y < jumpHigh) {
                    upp = false;
                }
            } else {
                mvDn();
            }
            if (this.y > 495) {
                upp = true;
                isJumping = false;
            }
            boundBoxUpdate();
            return;
        }
        if (isSlideLeft) {
            for (int i = 0; i < slideSpeedMult; i++) mvL();
            isSlideLeft = false;
        }
        if (isSlideRight) {
            for (int i = 0; i < slideSpeedMult; i++) mvR();
            isSlideRight = false;
        }

        if (goingLeft) {
            mvL();
        }
        if (goingRight) {
            mvR();
        }
        boundBoxUpdate();
    }

    //Draws the player

    public void render(Graphics g) {
        g.drawImage(Assets.basket, this.x, this.y, null);
    }


    public void mvR() {
        if (this.x < Display.width - this.width)
            this.x += this.velocity;
    }

    public void mvL() {
        if (this.x > 1)
            this.x -= this.velocity;
    }

    public void mvUp() {
        this.y -= this.velocity;
    }

    public void mvDn() {
        if (this.y > 495) {
            this.y = 495;
        } else
            this.y += this.velocity;
    }

    public void boundBoxUpdate() {
        //Update the bounding box's position
        this.boundingBox.setBounds(this.x, this.y, this.width, this.height);
    }
}
