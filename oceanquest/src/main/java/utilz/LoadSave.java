package utilz;

import java.awt.image.BufferedImage;
import java.io.*;
import java.util.ArrayList;
import java.util.Random;

import javax.imageio.ImageIO;

import main.Game;
import sprites.Enemy;
import static utilz.Constants.Enemy.*;


public class LoadSave {
    
    public static final String PLAYER_ARRAY = "newships.png";
    public static final String MENU_BUTTON = "playbutton.png";
    public static final String MENU_TITLE = "oceanquesttitle.png";
    public static final String WATER_TILES = "watertile.png";
    public static final String BACKGROUND_TILES = "background.png";
    public static final String ENEMY_ARRAY = "enemyship.png";
    public static final String EXPLOSION = "explosion.png";
    public static final String CANNON_BALL = "cannonball.png";
    private static final Random rand = new Random();
    

    public static BufferedImage GetSpriteArray(String fileName) {
        //setting as null because since declaration is in try catch it won't be valid unless intialized
        BufferedImage img = null;

        //do instead of getclass because it is static
        InputStream is = LoadSave.class.getResourceAsStream("/" + fileName);
        
        try {
            img = ImageIO.read(is);

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                is.close();
            } catch(IOException e) {
                e.printStackTrace();
            }
        }
        return img;
    }

    public static ArrayList<Enemy> getEnemies() {
        ArrayList<Enemy> list = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            int ypoint = rand.nextInt(300) + 25;
            list.add(new Enemy((500 + (ENEMY_WIDTH * 2 * (i + 1)) - 1), ypoint,(int) (ENEMY_WIDTH * Game.SCALE), (int) (ENEMY_HEIGHT * Game.SCALE)));
        } 
        return list;
    }
}
