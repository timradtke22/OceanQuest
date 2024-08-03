package objects;

import gamestates.Playing;
import main.Game;
import sprites.Enemy;
import sprites.EnemyManager;
import sprites.Player;

import java.awt.image.BufferedImage;
import java.util.concurrent.CopyOnWriteArrayList;


import java.awt.event.KeyEvent;
import java.awt.*;

import static utilz.Constants.CannonBall.*;
import utilz.LoadSave;
public class ObjectManager {

	private Playing playing;
    private Projectile projectile;
    private Player player;
    private Enemy enemy;

	private BufferedImage cannonBallImg;
	private CopyOnWriteArrayList<Projectile> projectiles = new CopyOnWriteArrayList<>();

	public ObjectManager(Playing playing) {
		this.playing = playing;
		loadImage();
	}

    private void loadImage() {
        //first line makes sure no projectiles remain on restart
        projectiles.clear();
        cannonBallImg = LoadSave.GetSpriteArray(LoadSave.CANNON_BALL);
    }

    public void draw(Graphics g) {
        for (Projectile p : projectiles) {
            if(p.getActive()) {
				g.drawImage(cannonBallImg, (int) (p.getHitbox().x), (int) (p.getHitbox().y), BALL_WIDTH, BALL_HEIGHT, null);
            }
        }
    }

    public void update(Player player) {

        updateProjectiles(player);
    }

    private void updateProjectiles(Player player) {
        
		for (Projectile p : projectiles) {
			if (p.getActive()) {
                p.updatePos();

                if(playing.checkEnemyHit(p.getHitbox())) {
                    p.setActive(false);
                }

                if (p.getHitbox().x > Game.GAME_WIDTH) {
                    p.setActive(false);
                }
            }
        }
    }

    


    public void shootCannon(boolean shooting) {
        
        int dir = 1;
        if (shooting) {
            projectiles.add(new Projectile((int) playing.getPlayer().getHitbox().x + 50, (int) (playing.getPlayer().getHitbox().y + 60), dir));
        }
    }
}
