package sprites;

import gamestates.Playing;
import utilz.LoadSave;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.awt.Graphics;
import java.awt.geom.Rectangle2D;

import static utilz.Constants.Enemy.*;

public class EnemyManager {
    
    private Playing playing;
    private ArrayList<Enemy> enemies;
    private BufferedImage[][] enemyArr;
    private int xOffset = 0, yOffset = 0;
    private int enemyCounter;
    

    public EnemyManager(Playing playing) {
        this.playing = playing;
        loadEnemyImages();
        addEnemies();
        
    }

    private void addEnemies() {
        enemies = LoadSave.getEnemies();
    }

    //Change w for
    public void update() {
        for (Enemy e: enemies) {
            if(e.isActive()) {
                e.update();  
            }
        }

        if (enemyCounter == 20) {
            playing.setGameWon(true);
            enemyCounter = 0;
        }
    }

    public void draw(Graphics g) {
        drawEnemy(g);
    }

    public void resetAllEnemies() {
        for (Enemy e : enemies) {
            e.resetEnemy();
        }
    }

    public void drawEnemy(Graphics g) {
        for (Enemy e: enemies) {
            if (e.isActive()) {
                g.drawImage(enemyArr[e.getEnemyAction()][e.getEnemyIndex()], (int) (e.getHitbox().x - xOffset), (int) (e.getHitbox().y - yOffset), ENEMY_WIDTH, ENEMY_HEIGHT, null);
                //.drawHitbox(g);
            }
        }
    }

    public void loadEnemyImages() {
        enemyArr = new BufferedImage[5][9];
        BufferedImage temp = LoadSave.GetSpriteArray(LoadSave.ENEMY_ARRAY);
        for (int j = 0; j < enemyArr.length; j++) {
            for (int i = 0 ; i < enemyArr[j].length; i++) {
                enemyArr[j][i] = temp.getSubimage((i*62) + 284, (j*70), 62, 70);
            }
        }
    }

    //needed to turn boolean to properly shut off cannonball from updating and drawing
    public boolean checkEnemyHit(Rectangle2D.Float attackbox) {
        for(Enemy e:enemies) {
            if (e.isActive()) {
                if (attackbox.intersects(e.getHitbox())) {
                    e.hurt();
                    enemyCounter++;
                    System.out.println(enemyCounter);
                    return true;
                }
            }
        }
        return false;
    }

    public void againstPlayer() {
        for (Enemy e:enemies) {
            if(e.isActive()) {
                if((e.getHitbox().x + e.getHitbox().width) < 0) {
                    e.setActive(false);
                    playing.getPlayer().changeHealth(1);
                    System.out.println(playing.getPlayer().getHealth());
                } else if (checkEnemyHit(playing.getPlayer().getHitbox())) {
                    e.setActive(false);
                    playing.getPlayer().changeHealth(1);
                    System.out.println(playing.getPlayer().getHealth());
                }

            }
        }
    }
}

