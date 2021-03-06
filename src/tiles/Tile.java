package tiles;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Tile {

    // STATIC VARS
    public static Tile[] tiles = new Tile[256];
    public static Tile blackTile = new BlackTile(0);
    public static Tile whiteTile = new WhiteTile(1);

    // CLASS
    public static final int TILE_WIDTH = 32,
            TILE_HEIGHT = 32;

    protected BufferedImage texture;
    protected final int id;

    public Tile(BufferedImage texture, int id) {
        this.texture = texture;
        this.id = id;

        tiles[id] = this;

    }

    public void update() {

    }

    public boolean isSolid(){
        return false;
    }

    public void render(Graphics g, int x, int y) {
        g.drawImage(texture, x, y, TILE_WIDTH, TILE_HEIGHT, null);
    }

    // GETTERS
    public int getId() {
        return id;
    }
}
