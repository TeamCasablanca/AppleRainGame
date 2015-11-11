package states;


import display.Display;
import game.Apple;
import game.Basket;
import game.Game;
import gfx.Assets;

import java.awt.*;
import java.util.ArrayList;

public class GameState extends State {

    public static Basket basket=new Basket();
    private Integer lives=5;
    public static ArrayList<Apple> appleList;
    private Integer score=0;
    public static int inAppCount = 5;
    private int appCount = inAppCount;

    public GameState() {

    }

    @Override
    public void tick() {

        basket.tick();

        for (int i = 0; i < inAppCount; i++) {
            if (appleList.get(i).getY() > 600) {
                lives--;
                //  Display. display.shake();
                appleList.remove(i);
                appleList.add(i, Apple.createRand());
            }
            // Checking catching
            if (appleList.get(i).Intersects(basket.getBoundingBox())) {
                score++;
                appleList.remove(i);
                appleList.add(i, Apple.createRand());
            }
            //tick
            appleList.get(i).tick();
        }
        if (appCount > inAppCount) {
            appleList.add(Apple.createRand());
            inAppCount = appCount;
        }

        if (lives < 1) {
            Game.end();
        }
    }

    @Override
    public void render(Graphics g) {
        g.drawImage(Game.img, 0, 0, 800, 600, null);

        basket.render(g);
        for (Apple a : appleList) {
            a.render(g);
        }

        g.setColor(new Color(180, 180, 180, 150));
        g.fill3DRect(5, 562, 790, 35, true);
        g.setColor(Color.GRAY);
        g.setFont(new Font("Verdana",Font.PLAIN,20));
        g.drawString("SCORE: " + score.toString(), 12, 590);
        g.drawString("LIVES: " + lives.toString(), 680, 590);

        if (lives < 1) {
            g.setFont(new Font("Verdana",Font.BOLD,40));
            g.setColor(new Color(180, 180, 180, 150));
            g.fill3DRect(100, 100, 600, 200, true);
            g.setColor(new Color(171, 44, 44));
            g.drawString("YOU ARE FIRED!!!", 200, 200);
            System.out.print("You died");
        }
    }
}
