package tiles;

import gfx.Assets;

import java.awt.image.BufferedImage;

public class WhiteTile extends Tile{
    public WhiteTile(int id) {
        super(Assets.white, id);
    }
    @Override
    public boolean isSolid(){
            return true;
    }
}
