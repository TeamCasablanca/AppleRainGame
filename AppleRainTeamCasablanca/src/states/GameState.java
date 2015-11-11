package states;

import display.Display;
import game.Apple;
import game.Basket;
import game.Game;

import java.awt.*;
import java.util.ArrayList;

public class GameState extends State {

    public static Basket basket = new Basket();
    private Integer lives = 5;
    public static ArrayList<Apple> appleList;
    private Integer score = 0;
    private int newScore = score;
    public static int inAppCount = 5;
    private int appCount = inAppCount;

    public GameState() {
    }

    @Override
    public void tick() {

        basket.tick();

        for (int i = 0; i < inAppCount; i++) {
            if (appleList.get(i).getY() > Display.height) {
                lives--;
                if (lives == 0) {
                    Game.end();
                }
                appleList.remove(i);
                appleList.add(i, Apple.createRand());
            }
            // Checking catching
            if (appleList.get(i).Intersects(basket.getBoundingBox())) {
                score++;
                if (score - newScore > 5) {
                    newScore = score;
                    lives++;
                    appCount++;
                }
                appleList.remove(i);
                appleList.add(i, Apple.createRand());
            }
            //tick
            appleList.get(i).tick();
        }

        if (appCount > inAppCount) {
            Display.shake();
            appleList.add(Apple.createRand());
            inAppCount = appCount;
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
        g.setFont(new Font("Verdana", Font.PLAIN, 20));
        g.drawString("SCORE: " + score.toString(), 12, 590);
        g.drawString("LIVES: " + lives.toString(), 680, 590);

        if (lives < 1) {
            g.setFont(new Font("Verdana", Font.BOLD, 40));
            g.setColor(new Color(180, 180, 180, 150));
            g.fill3DRect(100, 100, 600, 200, true);
            g.setColor(new Color(171, 44, 44));
            g.drawString("YOU ARE FIRED!!!", 200, 200);
        }
        if (score > 100) {
            Game.end();
            g.setFont(new Font("Verdana", Font.BOLD, 40));
            g.setColor(new Color(180, 180, 180, 150));
            g.fill3DRect(100, 100, 600, 200, true);
            g.setColor(new Color(5, 171, 4));
            g.drawString("YOU ARE HIRED!!!", 200, 200);
        }
    }
}
