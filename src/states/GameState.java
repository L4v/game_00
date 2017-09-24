package states;

import entities.creatures.Player;
import gfx.Assets;

import java.awt.*;

public class GameState extends State {

    private Player player;

    public GameState(){
        player = new Player(100, 144);

    }
    @Override
    public void update(){
        player.update();

    }
    @Override
    public void render(Graphics g){
        player.render(g);
    }
}
