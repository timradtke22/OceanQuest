package ui;

import gamestates.Gamestate;
import main.Game;
import utilz.LoadSave;
import static utilz.Constants.UI.Buttons.*;
import java.awt.Graphics;
import java.awt.Rectangle;

import java.awt.image.BufferedImage;
public class MenuButton {
    private int xPos, yPos, index;
    private int xOffCenter = 200/2, yOffCenter = 100/2;
    private Gamestate state;
    private BufferedImage[] imgs;
    private boolean mousePressed;
    private Rectangle bounds;

    public MenuButton(int xPos, int yPos, Gamestate state) {
        this.xPos = xPos;
        this.yPos = yPos;
        this.state = state;
        loadImgs();
        initBounds();
    }

    //How to tell if mouse is in or pressed the button 
    private void initBounds() {
        bounds = new Rectangle(xPos - xOffCenter, yPos - yOffCenter, 200, 100);

    }

    private void loadImgs() {
        imgs = new BufferedImage[2];
        BufferedImage temp = LoadSave.GetSpriteArray(LoadSave.MENU_BUTTON);
        for (int i = 0; i < imgs.length; i++) {
            imgs[i] = temp.getSubimage(i*33, 0, 27, 17);
        }
    }

    public void draw(Graphics g) {
        g.drawImage(imgs[index], xPos - xOffCenter, yPos - yOffCenter, 200, 100, null);

    }

    public void update() {
        index = 0;
        if (mousePressed) {
            index = 1;
        }
    }

    public boolean isMousePressed() {
        return mousePressed;
    }

    public void setMousePressed(boolean mousePressed) {
        this.mousePressed = mousePressed;
    }

    public void applyGamestate() {
        Gamestate.state = state;
    }

    public void resetBools() {
        mousePressed = false;
    }

    public Rectangle getBounds() {
        return bounds;
    }
    
}

