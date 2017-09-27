package states;

import main.Game;
import main.Handler;

import java.awt.*;

public abstract class State {

    // STATE MANAGER
    private static State currState = null;

    public static void setState(State state){
        currState = state;
    }

    public static State getCurrState(){
        return currState;
    }


    // CLASS
    protected Handler handler;

    public State(Handler handler){
        this.handler = handler;
    }

    public abstract void update();
    public abstract void render(Graphics g);


}
