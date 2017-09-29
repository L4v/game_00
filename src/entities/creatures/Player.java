package entities.creatures;

import entities.EntityManager;
import entities.statics.CampFire;
import gfx.Animation;
import gfx.Assets;
import main.Game;
import main.Handler;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.geom.Line2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class Player extends Creature {

    // ANIMATIONS
    private Animation animWalk;//ADD DOWN LEFT RIGHT UP IF NEEDED

    public Player(Handler handler, float x, float y) {
        super(handler, x, y, Creature.DEFAULT_CREATURE_WIDTH, Creature.DEFAULT_CREATURE_HEIGHT);
        // BOUNDS FOR COLLISION
        bounds.x = 0;
        bounds.y = 0;
        bounds.width = width - 1;
        bounds.height = height - 1;

        // ANIMATIONS
        animWalk = new Animation(500, Assets.red_walking);
    }

    @Override
    public void update() {
        // ANIMATIONS
        animWalk.update();
        // MOVEMENT
        getInput();
        move();
        handler.getGameCamera().centerOnEntity(this);
    }

    private void getInput() {
        xMove = 0;
        yMove = 0;

        if (handler.getKeyManager().up)
            yMove = -speed;
        if (handler.getKeyManager().down)
            yMove = speed;
        if (handler.getKeyManager().left)
            xMove = -speed;
        if (handler.getKeyManager().right)
            xMove = speed;
    }

    @Override
    public void render(Graphics g) {
        g.drawImage(getCurrAnimationFrame(), (int) (x - handler.getGameCamera().getxOff()), (int) (y - handler.getGameCamera().getyOff()), width, height, null);
        // COLLISION BOX
        /*g.setColor(Color.GREEN);
        g.fillRect((int)(x + bounds.x - handler.getGameCamera().getxOff()),
                (int)(y + bounds.y - handler.getGameCamera().getyOff()), bounds.width, bounds.height);
                */
        // TESTS
        int segments = 12;
        double deg, rad;
        int x1 = getCenterX();
        int y1 = getCenterY();
        double degInc = 360 / segments;
        deg = 0;
        int r = 64;
        int xCent = x1 + getWidth() / 2, yCent = y1 + getWidth() / 2;
        for (int i = 0; i < segments; i++) {
            x1 = xCent;
            y1 = yCent;

            rad = Math.toRadians(deg);
            x1 = (int) (Math.round(x1 + (r * Math.cos(rad))));
            y1 = (int) (Math.round(y1 + (r * Math.sin(rad))));
            g.drawLine(xCent, yCent, x1, y1);

            deg += degInc;
        }
        // TESTE
    }

    private BufferedImage getCurrAnimationFrame() {
        if (xMove < 0)// MOVING LEFT
            return animWalk.getCurFrame();// ADD ANIMATION FOR MOVING LEFT
        else if (xMove > 0)// MOVING RIGHT
            return animWalk.getCurFrame();// ADD ANIMATION FOR MOVING RIGHT
        else if (yMove < 0)// MOVING UP
            return animWalk.getCurFrame();// ADD ANIMATION FOR MOVING UP
        else if (yMove > 0)// MOVING DOWN
            return animWalk.getCurFrame();// ADD ANIMATION FOR MOVING DOWN
        return animWalk.getCurFrame();
    }

}
