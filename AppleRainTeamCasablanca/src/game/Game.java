package game;

import display.Display;
import gfx.Assets;
import gfx.ImageLoader;
import gfx.SpriteSheet;
import states.*;

import java.awt.*;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class Game implements Runnable {
    private Display display;
    public int width, height;
    public String title;
    private Integer lives;
    private Integer score;
    ArrayList<Apple> applist;
    private int appCount = 5;
    private boolean running = false;
    private Thread thread;

    private InputHandler inputHandler;
    private BufferStrategy bs;
    private Graphics g;

    private BufferedImage img;
    private SpriteSheet sh;

    //States
    private State gameState;
    private State menuState;
    private State settingsState;


    public static Apple apple;
    public static Basket basket;

    public Game(String title, int width, int height) {
        this.width = width;
        this.height = height;
        this.title = title;
        this.score = 0;
    }

    private void init() {

        display = new Display(this.title, this.width, this.height);
        img = ImageLoader.loadImage("/textures/Background.jpg");
        sh = new SpriteSheet(ImageLoader.loadImage("/textures/Basket.png"));

        applist = new ArrayList<Apple>();
        for (int i = 0; i < appCount; i++) {
            applist.add(apple.createRand());
        }

        this.inputHandler = new InputHandler(this.display);
        Assets.init();

        gameState = new GameState();
        menuState = new MenuState();
        settingsState = new SettingsState();

        StateManager.setState(gameState);

        apple = new Apple(4, 5, 2);
        basket = new Basket();
        lives=5;

    }


    private void tick() {
        if (StateManager.getState() != null) {
            StateManager.getState().tick();
        }
        basket.tick();


        for (int i = 0; i < appCount; i++) {
            if (applist.get(i).getY() > 600) {
                lives--;
                applist.remove(i);
                applist.add(i, Apple.createRand());
            }
            if(applist.get(i).Intersects(basket.getBoundingBox())){
                score++;
                applist.remove(i);
                applist.add(i, Apple.createRand());
            }
            applist.get(i).tick();

        }

        if (lives <= 0) {
            System.out.print("You died");
            stop();
        }

    }

    private void render() {
        this.bs = display.getCanvas().getBufferStrategy();
        if (bs == null) {
            display.getCanvas().createBufferStrategy(2);
            return;
        }
        g = bs.getDrawGraphics();
        g.clearRect(0, 0, this.width, this.height);

        g.drawImage(img, 0, 0, this.width, this.height, null);
        g.setColor(Color.BLUE);
        g.drawString("SCORE: "+score.toString(),100,100);
        g.drawString("LIVES: "+lives.toString(),100,50);
        basket.render(g);
        apple.render(g);
        for (Apple a : applist) {
            a.render(g);
        }
        //g.setColor(Color.red);
        if (StateManager.getState() != null) {
            StateManager.getState().render(this.g);
        }

        bs.show();
        g.dispose();
    }

    @Override
    public void run() {
        init();
        int fps = 30;

        double timePerTick = 1_000_000_000.0 / fps;
        double delta = 0;
        long now;
        long lastTime = System.nanoTime();
        long timer = 0;
        int ticks = 0;

        while (running) {

            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            tick();
            render();
        }
        stop();
    }

    public synchronized void start() {

        if (running) {
            return;
        }

        running = true;
        thread = new Thread(this);
        thread.start();
    }


    public synchronized void stop() {

        if (!running) {
            return;
        }
        running = false;

        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
