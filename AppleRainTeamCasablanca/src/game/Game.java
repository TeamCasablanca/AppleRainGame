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

    public int width, height;
    public String title;

    private Display display;

    static boolean running = false;
    private int sleepTime = 50;
    private Thread thread;
    private InputHandler inputHandler;
    private BufferStrategy bs;
    private Graphics g;
    public static BufferedImage img;
    //States only one :(
    private State gameState;

    public Game(String title, int width, int height) {
        this.width = width;
        this.height = height;
        this.title = title;
    }

    //Initializes all the graphics and it will get
    //everything ready for our game
    private void init() {

        //Initializing a new display.Display object
        display = new Display(this.title, this.width, this.height);
        img = ImageLoader.loadImage("/textures/Background.jpg");
        GameState.appleList = new ArrayList<>();
        for (int i = 0; i < GameState.inAppCount; i++) {
            GameState.appleList.add(Apple.createRand());
        }

        this.inputHandler = new InputHandler(this.display);
        Assets.init();

        //Initializing all the states
        gameState = new GameState();
        //Setting the currentState to gameState because we do not have
        //any more states set up
        StateManager.setState(gameState);

    }

    //The method that will update all the variables
    private void tick() {
        //Checks if a State exists and tick()
        if (StateManager.getState() != null) {
            StateManager.getState().tick();
        }
    }

    public static void end() {
        running = false;
    }

    //The method that will draw everything on the canvas
    private void render() {
        //Setting the bufferStrategy to be the one used in our canvas
        //Gets the number of buffers that the canvas should use.
        this.bs = display.getCanvas().getBufferStrategy();
        //If our bufferStrategy doesn't know how many buffers to use
        //we create some manually
        if (bs == null) {
            //Create 2 buffers
            display.getCanvas().createBufferStrategy(2);
            //returns out of the method to prevent errors
            return;
        }
        //Instantiates the graphics related to the bufferStrategy
        g = bs.getDrawGraphics();
        //Clear the screen at every frame
        g.clearRect(0, 0, this.width, this.height);
        //Beginning of drawing things on the screen

        //Checks if a State exists and render()
        if (StateManager.getState() != null) {
            StateManager.getState().render(this.g);
        }

        //End of drawing objects

        //Enables the buffer
        bs.show();
        //Shows everything stored in the Graphics object
        g.dispose();
    }

    //Implementing the interface's method
    @Override
    public void run() {
        init();

        while (running) {

            try {
                Thread.sleep(sleepTime);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            tick();
            render();
        }

        //Calls the stop method to ensure everything has been stopped
        stop();
    }

    //Creating a start method for the Thread to start our game
    //Synchronized is used because our method is working with threads
    //so we ensure ourselves that nothing will go bad
    public synchronized void start() {
        //If the game is running exit the method
        //This is done in order to prevent the game to initialize
        //more than enough threads
        if (running) {
            return;
        }
        //Setting the while-game-loop to run
        running = true;
        //Initialize the thread that will work with "this" class (game.Game)
        thread = new Thread(this);
        //The start method will call start the new thread and it will call
        //the run method in our class
        thread.start();
    }

    //Creating a stop method for the Thread to stop our game
    public synchronized void stop() {
        //If the game is not running exit the method
        //This is done to prevent the game from stopping a
        //non-existing thread and cause errors
        if (!running) {
            return;
        }
        running = false;
        //The join method stops the current method from executing and it
        //must be surrounded in try-catch in order to work
        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
