package gfx;

import java.awt.image.BufferedImage;

public class Assets {

    private final static int WIDTH = 32, HEIGHT = 32;

    public static BufferedImage white, black, red;

    public static void init() {
        Spritesheet sheet = new Spritesheet(ImageLoader.loadImage("/textures/spritesheet.png"));

        white = sheet.crop(0, 0, WIDTH, HEIGHT);
        black = sheet.crop(WIDTH, 0, WIDTH, HEIGHT);
        red = sheet.crop(WIDTH*2, 0, WIDTH, HEIGHT);

    }
}
