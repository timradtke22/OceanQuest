package gamestates;

import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

import main.Game;
import sprites.TileManager;
import ui.*;

public class Menu extends State implements Statemethods{

    private MenuButton[] buttons = new MenuButton[1];
    private MenuTitle title;
    private TileManager tiles;

    public Menu(Game game) {
        super(game);
        initClasses();
        loadButtons();
    }

    private void loadButtons() {
        buttons[0] = new MenuButton(Game.GAME_WIDTH/2, Game.GAME_HEIGHT/2, Gamestate.PLAYING);
    }

    private void initClasses() {
        title = new MenuTitle(Game.GAME_WIDTH/2,Game.GAME_HEIGHT/2);
        tiles = new TileManager();
    }

    @Override
    public void update() {
        tiles.update();
        for (MenuButton mb : buttons) {
            mb.update();
        }
        

    }

    @Override
    public void draw(Graphics g) {
        tiles.uDraw(g);
        for (MenuButton mb : buttons) {
            mb.draw(g);
        }
        title.draw(g);

     
        
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void mousePressed(MouseEvent e) {
        for (MenuButton mb : buttons) {
            if (isIn(e, mb)) {
                mb.setMousePressed(true);
                break;
            }
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        for (MenuButton mb : buttons) {
            if (isIn(e, mb)) {
                if (mb.isMousePressed()) {
                    mb.applyGamestate();
                    break;
                }
            }
        }
        resetButtons();
    }

    private void resetButtons() {
        for (MenuButton mb : buttons) {
            mb.resetBools();
        }
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void keyPressed(KeyEvent e) {
    }

    @Override
    public void keyReleased(KeyEvent e) {
        // TODO Auto-generated method stub
        
    }
    
}
