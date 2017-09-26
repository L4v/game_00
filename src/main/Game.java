package main;

import display.Display;
import gfx.Assets;
import gfx.ImageLoader;
import gfx.Spritesheet;
import input.KeyManager;
import states.GameState;
import states.MenuState;
import states.State;

import java.awt.*;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;

public class Game implements Runnable {

    private Display display;
    private Thread thread;

    // VARS
    private boolean running = false;
    public int width, height;
    public String title;

    private BufferStrategy bs;
    private Graphics g;

    // STATES
    private State gameState;
    private State menuState;

    // INPUT
    private KeyManager keyManager;


    public Game(String title, int width, int height) {
        this.width = width;
        this.height = height;
        this.title = title;
        keyManager = new KeyManager();
    }

    private void init() {
        display = new Display(title, width, height);
        display.getFrame().addKeyListener(keyManager);
        Assets.init();

        gameState = new GameState(this);
        menuState = new MenuState(this);
        State.setState(gameState);
    }


    private void update() {
        keyManager.update();

        if(State.getCurrState() != null)
            State.getCurrState().update();
    }

    private void render() {
        bs = display.getCanvas().getBufferStrategy();
        if (bs == null) {
            display.getCanvas().createBufferStrategy(3);
            return;
        }
        g = bs.getDrawGraphics();
        // CLEAR
        g.clearRect(0, 0, width, height);
        // DRAW

        if(State.getCurrState() != null)
            State.getCurrState().render(g);

        // END_DRAW*/
        bs.show();
        g.dispose();

    }


    public void run() {

        init();

        int fps = 60;
        double timePerUpdate = 1e9/fps;
        double delta = 0;
        long now;
        long lastTime = System.nanoTime();
        long timer = 0;
        int ticks = 0;

        while (running) {
            now = System.nanoTime();
            delta += (now-lastTime)/timePerUpdate;
            timer += now - lastTime;
            lastTime = now;

            if(delta >= 1) {
                update();
                render();
                ticks ++;
                delta --;
            }
            if(timer >= 1e9){

                ticks = 0;
                timer = 0;
            }
        }
        stop();

    }

    public KeyManager getKeyManager(){
        return keyManager;
    }

    public synchronized void start() {
        if (running)
            return;

        running = true;
        thread = new Thread(this);
        thread.start();
    }

    public synchronized void stop() {
        if (!running)
            return;

        running = false;

        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


}
