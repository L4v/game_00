package entities.creatures;

import entities.Entity;
import gfx.Animation;
import gfx.Assets;
import main.Handler;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Player extends Creature {

    // ANIMATIONS
    private Animation animWalk;//ADD DOWN LEFT RIGHT UP IF NEEDED
    // COMBAT TIMER
    private long lastAttackTimer, attackCooldown = 800, attackTimer = attackCooldown;

    public Player(Handler handler, float x, float y) {
        super(handler, x, y, Creature.DEFAULT_CREATURE_WIDTH, Creature.DEFAULT_CREATURE_HEIGHT);
        // BOUNDS FOR COLLISION
        bounds.x = 0;
        bounds.y = 0;
        bounds.width = width - 2;
        bounds.height = height - 2;

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
        // COMBAT
        checkAttacks();
    }

    private void checkAttacks() {
        attackTimer += System.currentTimeMillis() - lastAttackTimer;
        lastAttackTimer = System.currentTimeMillis();
        if(attackTimer < attackCooldown)
            return;

        Rectangle cb = getCollisionBounds(0, 0);// COLLISION BOUNDS RECTANGLE
        Rectangle ar = new Rectangle();// ATTACK RECTANGLE ( TEMP )
        int arSize = 20;// SIZE OF AR ( RANGE )
        ar.width = ar.height = arSize;

        if (handler.getKeyManager().attack) {
            if (dir == UP) {// ATTACKING UP
                ar.x = cb.x + cb.width / 2 - arSize / 2;
                ar.y = cb.y - arSize;
                System.out.println("Attacking UP");
            } else if (dir == DOWN) {// ATTACKING DOWN
                ar.x = cb.x + cb.width / 2 - arSize / 2;
                ar.y = cb.y + cb.height;
                System.out.println("Attacking DOWN");
            } else if (dir == LEFT) {// ATTACKING LEFT
                ar.x = cb.x - arSize;
                ar.y = cb.y + cb.height / 2 - arSize / 2;
                System.out.println("Attacking LEFT");
            } else if (dir == RIGHT) {// ATTACKING RIGHT
                ar.x = cb.x + cb.width;
                ar.y = cb.y + cb.height / 2 - arSize / 2;
                System.out.println("Attacking RIGHT");
            } else {
                return;
            }

            for (Entity e : handler.getWorld().getEntityManager().getEntities()) {
                if(e.equals(this))
                    continue;
                if(e.getCollisionBounds(0,0).intersects(ar)){
                    e.hurt(10);
                    return;
                }
                attackTimer = 0;
            }

        }
    }


    @Override
    public void die() {
        System.out.println("You accepted the whaling call of The Void, enter the inevitable darkness.");
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
