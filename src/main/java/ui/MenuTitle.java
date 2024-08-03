package ui;

import java.awt.*;
import java.awt.image.BufferedImage;
import utilz.LoadSave;

public class MenuTitle {
    private int xPos, yPos;
    private BufferedImage img;

    public MenuTitle(int xPos, int yPos) {
        this.xPos = xPos;
        this.yPos = yPos;
        loadImgs();
    }

    private void loadImgs() {
        BufferedImage temp = LoadSave.GetSpriteArray(LoadSave.MENU_TITLE);
        img = temp.getSubimage(0,0,781,79);
    }

    public void draw(Graphics g) {
       g.drawImage(img, xPos - 350, yPos - 180, 700, 120, null);
    }

}
