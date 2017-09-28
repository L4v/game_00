package entities.creatures;

import entities.Entity;
import main.Game;
import main.Handler;
import tiles.Tile;

public abstract class Creature extends Entity {

    public static final int DEFAULT_HEALTH = 100;
    public static final float DEFAULT_SPEED = 3.0f;
    public static final int DEFAULT_CREATURE_WIDTH = 32,
            DEFAULT_CREATURE_HEIGHT = 32;

    protected int hp;
    protected float speed;
    protected float xMove, yMove;


    public Creature(Handler handler, float x, float y, int width, int height) {
        super(handler, x, y, width, height);
        hp = DEFAULT_HEALTH;
        speed = DEFAULT_SPEED;
        xMove = 0;
        yMove = 0;

    }

    public void move() {
        if(!checkEntityCollisions(xMove, 0f))// TELLS WHERE WE'RE GOING TO MOVE, IF NO COLLISION >> MOVE
            moveX();
        if(!checkEntityCollisions(0f, yMove))
            moveY();
    }

    public void moveX() {
        if (xMove > 0) {// MOVING RIGHT
            int tx = (int) (x + xMove + bounds.x + bounds.width) / Tile.TILE_WIDTH;

            if (!collisionWitchTile(tx, (int) (y + bounds.y) / Tile.TILE_HEIGHT) &&
                    !collisionWitchTile(tx, (int) (y + bounds.y + bounds.height) / Tile.TILE_HEIGHT)) {
                x += xMove;
            }else{
                x = tx * Tile.TILE_WIDTH - bounds.x - bounds.width - 1;
            }

        } else if (xMove < 0) {// MOVING LEFT
            int tx = (int) (x + xMove + bounds.x) / Tile.TILE_WIDTH;

            if (!collisionWitchTile(tx, (int) (y + bounds.y) / Tile.TILE_HEIGHT) &&
                    !collisionWitchTile(tx, (int) (y + bounds.y + bounds.height) / Tile.TILE_HEIGHT)) {
                x += xMove;
            }else{
                x = tx*Tile.TILE_WIDTH + Tile.TILE_WIDTH - bounds.x;
            }
        }
    }

    public void moveY() {
        if (yMove < 0) {// MOVING UP
            int ty = (int) (y + yMove + bounds.y) / Tile.TILE_HEIGHT;

            if (!collisionWitchTile((int) (x + bounds.x) / Tile.TILE_WIDTH, ty) &&
                    !collisionWitchTile((int) (x + bounds.x + bounds.width) / Tile.TILE_WIDTH, ty)) {
                y += yMove;
            }else{
                y = ty* Tile.TILE_HEIGHT + Tile.TILE_HEIGHT - bounds.y;
            }

        } else if (yMove > 0) {// MOVING DOWN
            int ty = (int) (y + yMove + bounds.y + bounds.height) / Tile.TILE_HEIGHT;

            if (!collisionWitchTile((int) (x + bounds.x) / Tile.TILE_WIDTH, ty) &&
                    !collisionWitchTile((int) (x + bounds.x + bounds.width) / Tile.TILE_WIDTH, ty)) {
                y += yMove;
            }else{
                y = ty*Tile.TILE_HEIGHT - bounds.y - bounds.height - 1;
            }
        }
    }

    protected boolean collisionWitchTile(int x, int y) {
        return handler.getWorld().getTile(x, y).isSolid();
    }

    // GETTERS AND SETTERS
    public float getxMove() {
        return xMove;
    }

    public void setxMove(float xMove) {
        this.xMove = xMove;
    }

    public float getyMove() {
        return yMove;
    }

    public void setyMove(float yMove) {
        this.yMove = yMove;
    }

    public int getHp() {
        return hp;
    }

    public void setHp(int hp) {
        this.hp = hp;
    }

    public float getSpeed() {
        return speed;
    }

    public void setSpeed(float speed) {
        this.speed = speed;
    }
}
