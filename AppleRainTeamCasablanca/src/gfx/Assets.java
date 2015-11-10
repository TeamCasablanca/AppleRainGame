package gfx;

import java.awt.image.BufferedImage;
import java.util.HashMap;

public class Assets {

    private static final int width = 95, height = 130;

    public static BufferedImage apple;
    public static BufferedImage basket;

    public static void init() {

        apple = ImageLoader.loadImage("/textures/Apple.png");
        basket = ImageLoader.loadImage("/textures/Basket.png");

    }
}
