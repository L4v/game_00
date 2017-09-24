package entities.creatures;

import entities.Entity;

public abstract class Creature extends Entity{

    protected int hp;

    public Creature(float x, float y) {
        super(x, y);
        hp = 100;
    }
}
