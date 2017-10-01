package input;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyManager implements KeyListener{

    private boolean[] keys;
    // MOVEMENT
    public boolean up, down, left, right;
    // COMBAT
    public boolean attack, fire;

    public KeyManager(){
        keys = new boolean[256];
    }

    public void update(){
        // MOVEMENT
        up = keys[KeyEvent.VK_W];
        down = keys[KeyEvent.VK_S];
        left = keys[KeyEvent.VK_A];
        right = keys[KeyEvent.VK_D];
        // COMBAT
        attack = keys[KeyEvent.VK_SPACE];
        fire = keys[KeyEvent.VK_F];

    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        keys[e.getKeyCode()] = true;
    }

    @Override
    public void keyReleased(KeyEvent e) {
        keys[e.getKeyCode()] = false;
    }
}
