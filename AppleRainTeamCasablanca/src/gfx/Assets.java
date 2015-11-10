package gfx;

import java.awt.image.BufferedImage;
import java.util.HashMap;

public class Assets {

    private static final int width = 95, height = 130;

//    public static BufferedImage player1, player2;
    public static BufferedImage apple;
    public static BufferedImage basket;

    //Loads every resource needed for the game
    public static void init() {
        SpriteSheet sheet = new SpriteSheet(ImageLoader.loadImage("/textures/player.png"));

        apple = ImageLoader.loadImage("/textures/Apple.png");
        basket = ImageLoader.loadImage("/textures/Basket.png");

//        player1 = sheet.crop(0, 0, width, height);
//        player2 = sheet.crop(width, 0, width, height);
    }
}
