package main;

import display.Display;

import java.awt.*;
import java.awt.image.BufferStrategy;

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

    private void update() {

    }

    private void render() {
        bs = display.getCanvas().getBufferStrategy();
        if(bs == null){
            display.getCanvas().createBufferStrategy(3);
            return;
        }
        g = bs.getDrawGraphics();
        // DRAW

        g.fillRect(0, 0, width, height);

        // END_DRAW
        bs.show();
        g.dispose();

    }

    private void init() {
        display = new Display(title, width, height);

    }

    public void run() {
        init();

        while (running) {
            update();
            render();
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
