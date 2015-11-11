package game;

import display.Display;
import states.GameState;
import states.StateManager;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class InputHandler implements KeyListener {

    public InputHandler(Display display) {
        display.getCanvas().addKeyListener(this);
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int keyCode = e.getKeyCode();

        if ((keyCode == KeyEvent.VK_UP)) {
            if (GameState.basket.goingLeft) {
                GameState.basket.isJumpingL = true;
            } else if (GameState.basket.goingRight) {
                GameState.basket.isJumpingR = true;
            } else
                GameState.basket.isJumping = true;
        }
        if (keyCode == KeyEvent.VK_DOWN) {
            if (GameState.basket.goingLeft) {
                GameState.basket.isSlideLeft = true;
            } else if (GameState.basket.goingRight) {
                GameState.basket.isSlideRight = true;
            }
        }
        if (keyCode == KeyEvent.VK_LEFT) {
            GameState.basket.goingLeft = true;
        }
        if (keyCode == KeyEvent.VK_RIGHT) {
            GameState.basket.goingRight = true;
        }
        if (keyCode == KeyEvent.VK_ESCAPE) {
            StateManager.setState(Game.menuState);
           // System.exit(0);
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }


    @Override
    public void keyReleased(KeyEvent e) {
        int keyCode = e.getKeyCode();

        if (keyCode == KeyEvent.VK_UP) {
            GameState.basket.goingUp = false;
        }
        if (keyCode == KeyEvent.VK_DOWN) {
            GameState.basket.goingDown = false;
        }
        if (keyCode == KeyEvent.VK_LEFT) {
            GameState.basket.goingLeft = false;
        }
        if (keyCode == KeyEvent.VK_RIGHT) {
            GameState.basket.goingRight = false;
        }
    }
}
