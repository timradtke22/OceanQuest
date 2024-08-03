package Inputs;
import main.*;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import gamestates.Gamestate;



public class MouseInputs implements MouseListener, MouseMotionListener{

    //Create a gamepanel object and connect the inputs to the Input constructors
    private GamePanel gamepanel;

    public MouseInputs(GamePanel gamepanel) {
        this.gamepanel = gamepanel;
    }
    @Override
    public void mouseDragged(MouseEvent e) {
        
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        
    }

    @Override
    public void mousePressed(MouseEvent e) {
        switch(Gamestate.state) {
            case MENU:
                gamepanel.getGame().getMenu().mousePressed(e);
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        switch(Gamestate.state) {
            case MENU:
                gamepanel.getGame().getMenu().mouseReleased(e);
        }
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void mouseExited(MouseEvent e) {
        // TODO Auto-generated method stub
        
    }
    
}
