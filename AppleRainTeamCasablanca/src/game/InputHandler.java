package game;

import display.Display;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class InputHandler implements KeyListener {

    public InputHandler(Display display) {
        display.getCanvas().addKeyListener(this);
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int keyCode = e.getKeyCode();

//        if (keyCode == KeyEvent.VK_UP) {
//            Game.basket.goingUp = true;
//        }
//        if (keyCode == KeyEvent.VK_DOWN) {
//            Game.basket.goingDown = true;
//        }
        if (keyCode == KeyEvent.VK_LEFT) {
            Game.basket.goingLeft = true;
        }
        if (keyCode == KeyEvent.VK_RIGHT) {
            Game.basket.goingRight = true;
        }
        if (keyCode == KeyEvent.VK_ESCAPE) {
            System.exit(0);
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }


    @Override
    public void keyReleased(KeyEvent e) {
        int keyCode = e.getKeyCode();

        if (keyCode == KeyEvent.VK_UP) {
            Game.basket.goingUp = false;
        }
        if (keyCode == KeyEvent.VK_DOWN) {
            Game.basket.goingDown = false;
        }
        if (keyCode == KeyEvent.VK_LEFT) {
            Game.basket.goingLeft = false;
        }
        if (keyCode == KeyEvent.VK_RIGHT) {
            Game.basket.goingRight = false;
        }
    }
}
