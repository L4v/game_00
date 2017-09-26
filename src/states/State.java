package states;

import main.Game;

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
    protected Game game;

    public State(Game game){
        this.game = game;
    }

    public abstract void update();
    public abstract void render(Graphics g);


}
