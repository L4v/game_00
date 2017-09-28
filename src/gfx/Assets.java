package gfx;

import java.awt.image.BufferedImage;

public class Assets {

    private final static int WIDTH = 32, HEIGHT = 32;


    public static BufferedImage white, black;
    public static BufferedImage[] red_walking;// FOR COMPLEX ADD DOWN LEFT RIGHT UP
    public static BufferedImage[] camp_fire;
    public static BufferedImage[] btn_start;

    public static void init() {
        Spritesheet sheet = new Spritesheet(ImageLoader.loadImage("/textures/spritesheet.png"));
        Spritesheet flame_sheet = new Spritesheet(ImageLoader.loadImage("/textures/campfire.png"));

        red_walking = new BufferedImage[2];
        camp_fire = new BufferedImage[5];

        red_walking[0] = sheet.crop(WIDTH*2, 0, WIDTH, HEIGHT);
        red_walking[1] = sheet.crop(WIDTH*3,0,WIDTH,HEIGHT);

        camp_fire[0] = flame_sheet.crop(64, 0, 64, 64);
        camp_fire[1] = flame_sheet.crop(64*2, 0, 64, 64);
        camp_fire[2] = flame_sheet.crop(64*3, 0, 64, 64);
        camp_fire[3] = flame_sheet.crop(64*4, 0, 64, 64);
        camp_fire[4] = flame_sheet.crop(64*5, 0, 64, 64);

        white = sheet.crop(0, 0, WIDTH, HEIGHT);
        black = sheet.crop(WIDTH, 0, WIDTH, HEIGHT);

    }
}
