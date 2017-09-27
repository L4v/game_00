package gfx;

import java.awt.image.BufferedImage;

public class Assets {

    private final static int WIDTH = 32, HEIGHT = 32;

    public static BufferedImage white, black;
    public static BufferedImage[] red_walking;// FOR COMPLEX ADD DOWN LEFT RIGHT UP

    public static void init() {
        Spritesheet sheet = new Spritesheet(ImageLoader.loadImage("/textures/spritesheet.png"));

        red_walking = new BufferedImage[2];

        red_walking[0] = sheet.crop(WIDTH*2, 0, WIDTH, HEIGHT);
        red_walking[1] = sheet.crop(WIDTH*3,0,WIDTH,HEIGHT);

        white = sheet.crop(0, 0, WIDTH, HEIGHT);
        black = sheet.crop(WIDTH, 0, WIDTH, HEIGHT);

    }
}
