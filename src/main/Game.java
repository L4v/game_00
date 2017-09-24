package main;

import display.Display;
import gfx.Assets;
import gfx.ImageLoader;
import gfx.Spritesheet;

import java.awt.*;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;

public class Game implements Runnable {

    private Display display;
    private Thread thread;

    private boolean running = false;
    public int width, height;
    public String title;

    private BufferStrategy bs;
    private Graphics g;


    public Game(String title, int width, int height) {
        this.width = width;
        this.height = height;
        this.title = title;


    }

    private void init() {
        display = new Display(title, width, height);
        Assets.init();
    }

    int x = 0;

    private void update() {
        x += 1;
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
        g.drawImage(Assets.black, x, 30, null);
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
