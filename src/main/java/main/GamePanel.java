package main;

import javax.swing.JPanel;
import java.awt.Graphics;
import java.awt.Dimension;

//importing the static width and height
import static main.Game.GAME_HEIGHT;
import static main.Game.GAME_WIDTH;

//Input import
import Inputs.*;


/* The gamepanel has all the pieces of movement, input actions collected here (actual input is in 
another class) and it also handles movement and direction as of now as well as animation */

public class GamePanel extends JPanel{
    
    private MouseInputs mouseInputs;
    private Game game;
    //The gamepanel constructor is used to load in all of 

    public GamePanel(Game game) {
        // Game is passed in as a parameter so don't need to initalize, the class is being passed in rather than
        // an instance created
        this.game = game;
        mouseInputs = new MouseInputs(this);
        setPanelSize();
        addMouseListener(mouseInputs);
        addMouseMotionListener(mouseInputs);
        addKeyListener(new KeyboardInputs(this));    
    }


    // This method sets the dimensions of the GamePanel
    private void setPanelSize() {
        Dimension size = new Dimension(GAME_WIDTH ,GAME_HEIGHT);
        setPreferredSize(size);
    }

    // An auto generated method that draws all objects needed with the graphics package passed in
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        game.render(g);
        //subImg = img.getSubimage(2*60, 0*70, 60, 70);
    
    }

    public Game getGame() {
        return game;
    }


}



