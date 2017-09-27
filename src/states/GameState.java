package states;

import entities.creatures.Player;
import gfx.Assets;
import main.Game;
import main.Handler;
import tiles.Tile;
import worlds.World;

import java.awt.*;

public class GameState extends State {

    private Player player;
    private World world;

    public GameState(Handler handler) {
        super(handler);
        world = new World(handler, "res/worlds/world_0.txt");
        handler.setWorld(world);
        player = new Player(handler, 100, 100);

    }

    @Override
    public void update() {
        player.update();


    }

    @Override
    public void render(Graphics g) {
        world.render(g);
        player.render(g);
    }
}
