package entities.statics;

import gfx.Animation;
import gfx.Assets;
import main.Handler;
import tiles.Tile;

import java.awt.*;
import java.awt.image.BufferedImage;

public class CampFire extends StaticEntity {

    private Animation animFire;

    public CampFire(Handler handler, float x, float y){
        super(handler, x, y, Tile.TILE_WIDTH*2, Tile.TILE_HEIGHT*2);
        // BOUNDS FOR COLLISION
        bounds.x = 14;
        bounds.y = 48;
        bounds.width = width - 32;
        bounds.height = height - 48;

        animFire = new Animation(150, Assets.camp_fire);
    }

    @Override
    public void update() {
        animFire.update();
    }

    @Override
    public void die(){
        
    }

    @Override
    public void render(Graphics g) {
        g.drawImage(getCurrAnimationFrame(), (int)(x - handler.getGameCamera().getxOff()), (int)(y - handler.getGameCamera().getyOff()), width, height, null);
        // COLLISION BOX
        /*g.setColor(Color.GREEN);
        g.drawRect((int)(x + bounds.x - handler.getGameCamera().getxOff()),
                (int)(y + bounds.y - handler.getGameCamera().getyOff()), bounds.width, bounds.height);
*/
    }
    private BufferedImage getCurrAnimationFrame(){
        return animFire.getCurFrame();
    }
}
