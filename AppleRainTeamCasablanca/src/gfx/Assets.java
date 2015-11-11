package gfx;

import display.Display;

import java.awt.image.BufferedImage;
import java.util.HashMap;

public class Assets {

    public static BufferedImage apple;
    public static BufferedImage basket;

    //Loads every resource needed for the game
    public static void init() {

        apple = ImageLoader.loadImage("/textures/Apple.png");
        basket = ImageLoader.loadImage("/textures/Basket.png");
    }
}
