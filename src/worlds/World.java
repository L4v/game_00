package worlds;

import entities.EntityManager;
import entities.creatures.Player;
import entities.statics.CampFire;
import main.Handler;
import tiles.Tile;
import utils.Utils;

import java.awt.*;

public class World {

    private Handler handler;
    private int width, height;
    private int spawnX, spawnY;
    private int[][] tiles;

    // ENTITIES
    private EntityManager entityManager;

    public World(Handler handler, String path) {
        this.handler = handler;

        entityManager = new EntityManager(handler, new Player(handler, 100, 100));
        entityManager.addEntity(new CampFire(handler, 100, 100));

        loadWorld(path);

        entityManager.getPlayer().setX(spawnX);
        entityManager.getPlayer().setY(spawnY);
    }


    public void update() {
        entityManager.update();
    }

    public void render(Graphics g) {
        int xStart = (int) Math.max(0, handler.getGameCamera().getxOff() / Tile.TILE_WIDTH);// Tile from which you start rendering
        int xEnd = (int) Math.min(width, (handler.getGameCamera().getxOff() + handler.getWidth()) / Tile.TILE_WIDTH + 1);
        int yStart = (int) Math.max(0, handler.getGameCamera().getyOff() / Tile.TILE_HEIGHT);
        int yEnd = (int) Math.min(height, (handler.getGameCamera().getyOff() + handler.getHeight()) / Tile.TILE_HEIGHT + 1);

        for (int y = yStart; y < yEnd; y++) {
            for (int x = xStart; x < xEnd; x++) {
                getTile(x, y).render(g, (int) (x * Tile.TILE_WIDTH - handler.getGameCamera().getxOff()),
                        (int) (y * Tile.TILE_HEIGHT - handler.getGameCamera().getyOff()));
            }
        }
        // ENTITY RENDER
        entityManager.render(g);
    }

    public Tile getTile(int x, int y) {
        if (x < 0 || y < 0 || x >= width || y >= height)
            return Tile.blackTile;

        Tile t = Tile.tiles[tiles[x][y]];
        if (t == null)
            return Tile.blackTile;
        return t;
    }

    public void loadWorld(String path) {
        String file = Utils.loadFileString(path);
        String[] tokens = file.split("\\s+");
        width = Utils.parseInt(tokens[0]);
        height = Utils.parseInt(tokens[1]);
        spawnX = Utils.parseInt(tokens[2]);
        spawnY = Utils.parseInt(tokens[3]);

        tiles = new int[width][height];
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                tiles[x][y] = Utils.parseInt(tokens[(x + y * width) + 4]);
            }
        }

    }

    // GETTERS
    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public EntityManager getEntityManager() {
        return entityManager;
    }

}
