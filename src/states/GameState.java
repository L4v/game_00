package states;

import entities.creatures.Player;
import gfx.Assets;
import main.Game;
import tiles.Tile;

import java.awt.*;

public class GameState extends State {

    private Player player;

    public GameState(Game game){
        super(game);
        player = new Player(game, 100, 100);

    }
    @Override
    public void update(){
        player.update();

    }
    @Override
    public void render(Graphics g){
        player.render(g);
        Tile.tiles[1].render(g, 0,0);
    }
}
