package sprites;

import java.awt.image.BufferedImage;

import main.Game;

import java.awt.*;

import utilz.LoadSave;

public class TileManager {
    BufferedImage[] WaterArray;
    BufferedImage[] BackgroundArray;

    private int animationIndex = 0,  animationTick = 0, animationSpeed = 30;

    public TileManager() {
        loadTiles();
    }

    private void loadTiles() {
        BufferedImage playimg = LoadSave.GetSpriteArray(LoadSave.WATER_TILES);
        BufferedImage backgroundimg = LoadSave.GetSpriteArray(LoadSave.BACKGROUND_TILES);
        BackgroundArray = new BufferedImage[3];
        WaterArray = new BufferedImage[3];
        
        for (int i = 0; i < WaterArray.length; i++) {
            WaterArray[i] = playimg.getSubimage(i*16, 0, 16, 16);
            BackgroundArray[i] = backgroundimg.getSubimage(i*16, 0, 16, 16);
        }
    }

    public void pDraw(Graphics g) {
        for (int i = 0 ; i < (Game.TILES_IN_HEIGHT * Game.SCALE); i++) {
            for (int j = 0; j < (Game.TILES_IN_WIDTH * Game.SCALE); j++) {
                g.drawImage(WaterArray[animationIndex], j * Game.TILES_SIZE, i * Game.TILES_SIZE, Game.TILES_SIZE, Game.TILES_SIZE, null);
            }
        }
    }

    public void uDraw(Graphics g) {
        for (int i = 0 ; i < (Game.TILES_IN_HEIGHT * Game.SCALE); i++) {
            for (int j = 0; j < (Game.TILES_IN_WIDTH * Game.SCALE); j++) {
                g.drawImage(BackgroundArray[animationIndex], j * Game.TILES_SIZE, i * Game.TILES_SIZE, Game.TILES_SIZE, Game.TILES_SIZE, null);
            }
        }
    }

    public void update() {
        updateAnimation();
    }

    private void updateAnimation() {
        animationTick++;
        if (animationTick >= animationSpeed) {
            animationTick = 0;
            animationIndex++;
            if (animationIndex >= 2) {
                animationIndex = 0;
            }
        }
    }    
}
