package game;

import java.util.Random;

public class AppleFactory {
    private int randomX;
    private int randomY;

    public AppleFactory() {
        Random ran = new Random();

        this.randomX = ran.nextInt(795)+5;
        this.randomY = ran.nextInt(10)+5;
    }

    public Apple run(){
      while (true) {
          Apple apple = new Apple(this.randomX, this.randomY);
          return apple;
      }

    }

}
