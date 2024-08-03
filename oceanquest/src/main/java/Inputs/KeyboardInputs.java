package Inputs;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import gamestates.Gamestate;
//main import
import main.*;


public class KeyboardInputs implements KeyListener {
    
    //Create a gamepanel object and connect the inputs to the Input constructors
    private GamePanel gamepanel;

    public KeyboardInputs(GamePanel gamepanel) {
        this.gamepanel = gamepanel;
    }



    @Override
    public void keyTyped(KeyEvent e) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void keyPressed(KeyEvent e) {
        switch(Gamestate.state) {
            case MENU:
            gamepanel.getGame().getMenu().keyPressed(e);
            case PLAYING:
                gamepanel.getGame().getPlaying().keyPressed(e);
            default:
                break;
        }
        
    }

    @Override
    public void keyReleased(KeyEvent e) {
        switch(Gamestate.state) {
            case MENU:
            gamepanel.getGame().getMenu().keyReleased(e);
            case PLAYING:
                gamepanel.getGame().getPlaying().keyReleased(e);
            default:
                break;
        }
    }
}
    

