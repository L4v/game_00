package main;

import display.Display;

public class Launcher {


    public static void main(String args[]) {
        System.setProperty("sun.java2d.opengl", "true");
        Game game= new Game("game_00", 640, 480);

        game.start();
    }

}
