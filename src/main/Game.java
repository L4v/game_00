package main;

import display.Display;

public class Game implements Runnable {

    private Display display;
    private Thread thread;

    private boolean running = false;
    public int width, height;
    public String title;

    public Game(String title, int width, int height) {
        this.width = width;
        this.height = height;
        this.title = title;

        display = new Display(title, width, height);

    }

    private void update() {

    }

    private void render() {

    }

    private void init() {

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
