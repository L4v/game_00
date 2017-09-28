package states;

import main.Game;
import main.Handler;

import java.awt.*;

public class MenuState extends State {

    public MenuState(Handler handler){
        super(handler);

    }

    @Override
    public void update() {
        if(handler.getMouseManager().isLeftPress())
            State.setState(handler.getGame().gameState);
    }

    @Override
    public void render(Graphics g) {
        g.setColor(Color.GREEN);
        g.fillRect(handler.getMouseManager().getMouseX(), handler.getMouseManager().getMouseY(), 4, 4);
    }
}
