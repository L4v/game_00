package gfx;

import entities.Entity;
import main.Game;

public class GameCamera {

    private Game game;
    private float xOff, yOff;

    public GameCamera(Game game, int xOff, int yOff){
        this.game = game;
        this.xOff = xOff;
        this.yOff = yOff;

    }

    public void centerOnEntity(Entity e){
        xOff = e.getX() - game.getWidth()/2 + e.getWidth()/2;
        yOff = e.getY() - game.getHeight()/2 + e.getHeight()/2;
    }

    public void move(int xAmt, int yAmt){
        xOff += xAmt;
        yOff += yAmt;
    }


    // GETTERS AND SETTERS
    public float getxOff() {
        return xOff;
    }

    public void setxOff(float xOff) {
        this.xOff = xOff;
    }

    public float getyOff() {
        return yOff;
    }

    public void setyOff(float yOff) {
        this.yOff = yOff;
    }
}
