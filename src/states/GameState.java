package states;

import entities.creatures.Player;
import gfx.Assets;
import main.Game;
import tiles.Tile;
import worlds.World;

import java.awt.*;

public class GameState extends State {

    private Player player;
    private World world;

    public GameState(Game game){
        super(game);
        player = new Player(game, 100, 100);
        world = new World("res/worlds/world_0.txt");

    }
    @Override
    public void update(){
        player.update();

    }
    @Override
    public void render(Graphics g){
        world.render(g);
        player.render(g);
    }
}
