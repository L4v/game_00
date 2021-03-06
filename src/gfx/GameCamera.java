package gfx;

import entities.Entity;
import main.Game;
import main.Handler;
import tiles.Tile;

public class GameCamera {

    private Handler handler;
    private float xOff, yOff;

    public GameCamera(Handler handler, int xOff, int yOff){
        this.handler = handler;
        this.xOff = xOff;
        this.yOff = yOff;

    }

    public void checkBlankSpace(){
        if(xOff < 0)
            xOff = 0;
        else if(xOff > handler.getWorld().getWidth() * Tile.TILE_WIDTH - handler.getWidth())
            xOff = handler.getWorld().getWidth() * Tile.TILE_WIDTH - handler.getWidth();
        if(yOff < 0)
            yOff = 0;
        else if(yOff > handler.getWorld().getHeight() * Tile.TILE_HEIGHT - handler.getHeight())
            yOff = handler.getWorld().getHeight() * Tile.TILE_HEIGHT - handler.getHeight();


    }

    public void centerOnEntity(Entity e){
        xOff = e.getX() - handler.getWidth()/2 + e.getWidth()/2;
        yOff = e.getY() - handler.getHeight()/2 + e.getHeight()/2;
        checkBlankSpace();
    }

    public void move(int xAmt, int yAmt){
        xOff += xAmt;
        yOff += yAmt;
        checkBlankSpace();
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
