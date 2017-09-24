package entities.creatures;

import gfx.Assets;

import java.awt.*;

public class Player extends Creature {
    public Player(float x, float y) {
        super(x, y);
    }

    @Override
    public void update() {

    }

    @Override
    public void render(Graphics g) {
        g.drawImage(Assets.red, (int)x, (int)y, null);
    }
}
