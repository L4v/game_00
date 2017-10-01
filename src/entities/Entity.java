package entities;

import main.Game;
import main.Handler;

import java.awt.*;

public abstract class Entity {

    protected Handler handler;
    protected Rectangle bounds;

    public static final int DEFAULT_HEALTH = 100;
    protected float x, y;
    protected int width, height;
    protected int hp;

    protected boolean alive = true;

    public Entity(Handler handler, float x, float y, int width, int height) {
        this.handler = handler;
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        hp = DEFAULT_HEALTH;

        bounds = new Rectangle(0, 0, width, height);
    }


    public abstract void update();// TICKS THE ENTITY

    public abstract void render(Graphics g);// DRAWS THE ENTITY

    public abstract void die();// KILLS THE ENTITY

    public void hurt(int amt){// DECREASES HEALTH BY AMT
        hp -= amt;
        if(hp <= 0){
            alive = false;
            die();
        }
    }

    public boolean checkEntityCollisions(float xOff, float yOff) {
        for (Entity e : handler.getWorld().getEntityManager().getEntities()) {
            if(e.equals(this))
                continue;// SKIP THIS ENTITY
            if (e.getCollisionBounds(0f, 0f).intersects(getCollisionBounds(xOff, yOff)))
                return true;
        }
        return false;
    }

    public Rectangle getCollisionBounds(float xOff, float yOff) {
        return new Rectangle((int) (x + bounds.x + xOff), (int) (y + bounds.y + yOff), bounds.width, bounds.height);
    }

    // GETTERS AND SETTERS

    public int getHp() {
        return hp;
    }

    public void setHp(int hp) {
        this.hp = hp;
    }

    public boolean isAlive() {
        return alive;
    }

    public void setAlive(boolean alive) {
        this.alive = alive;
    }

    public float getX() {
        return x;
    }

    public void setX(float x) {
        this.x = x;
    }

    public float getY() {
        return y;
    }

    public void setY(float y) {
        this.y = y;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getCenterX(){
        return (int) (x - handler.getGameCamera().getxOff()+ width/2);
    }
    public int getCenterY(){
        return (int) (y + bounds.y - handler.getGameCamera().getyOff() + height/2);
    }

}
