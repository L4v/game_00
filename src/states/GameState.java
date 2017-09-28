package states;

import entities.creatures.Player;
import entities.statics.CampFire;
import gfx.Assets;
import main.Game;
import main.Handler;
import tiles.Tile;
import worlds.World;

import java.awt.*;

public class GameState extends State {

    private World world;

    public GameState(Handler handler) {
        super(handler);
        world = new World(handler, "res/worlds/world_0.txt");
        handler.setWorld(world);

    }

    @Override
    public void update() {
        world.update();
    }

    @Override
    public void render(Graphics g) {
        world.render(g);
    }
}
