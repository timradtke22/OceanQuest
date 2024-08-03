package sprites;


import static utilz.Constants.Enemy.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Random;

import static utilz.HelpMethods.*;


public class Enemy extends Entity{
    private Random rand = new Random();
    private int animationTick, animationSpeed = 30, animationIndex;
    private int enemy_action = IDLE, enemyState = 0;
    private float enemySpeed = rand.nextFloat(.7f) + .3f;
    private int UP = 0, DOWN = 1, LEFT = 2, RIGHT = 3;
    private int enemyYdir = rand.nextInt(1);
    private int enemyXdir = LEFT;
    private boolean enter = true;
    private int maxHealth; 
    private boolean active = true;


    public Enemy(float x, float y, int width, int height) {
        super(x, y, width, height);
        initHitbox(x, y, width, height);
        this.maxHealth = getMaxHealth();
    }

    public void hurt() {
        newState(DEAD);
    }

    public void newState(int enemyState) {
        if(enemyState == 1) {
            enemy_action = DEAD;
        }
    }



    private void updateAnimation() {
        animationTick++;
        if (animationTick >= animationSpeed) {
            animationTick = 0;
            animationIndex++;
            if (animationIndex >= getSpriteAmount(enemy_action)) {
                animationIndex = 0;
                if (enemy_action == DEAD) {
                    active = false;
                }
            }
        }
    } 

    private void updatePos() {

    // This gives the driving in entry animation, once the enemy is in the gamewindow, it stops and does, normal
    // movement
        if (enter) {
            hitbox.x -= 1.5f;
            if (CanMoveHere(hitbox.x - .2f, hitbox.y, ENEMY_WIDTH, ENEMY_HEIGHT)) {
                enter = false;
            }
        } else {      
            switch(enemy_action) {
                case IDLE:
                    float xSpeed = 0, ySpeed = 0;

                    if (enemyYdir == DOWN) {
                        ySpeed += enemySpeed;
                    } else {
                        ySpeed -= enemySpeed;
                    }

                    if (enemyXdir == LEFT) {
                        xSpeed -= 1.5f;
                    } else {
                        xSpeed += 1.5f;
                    }


                    if (CanMoveHere(hitbox.x, hitbox.y + ySpeed, hitbox.width, hitbox.height)) {
                        hitbox.y += ySpeed;
                    } else {
                        changeEnemyYdir();
                    }

                    hitbox.x += xSpeed;
                    break;
            }
        }

    }

    private void changeEnemyYdir() {
        if (enemyYdir == DOWN) {
            enemyYdir = UP;
        } else {
            enemyYdir = DOWN;
        }

        
    }


    public void update() {
        updateAnimation();
        updatePos();
    }
    


    public int getEnemyIndex() {
        return animationIndex;
    }

    public int getEnemyAction() {
        return enemy_action;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public boolean isActive() {
        return active;
    }

    public void resetEnemy() {
        hitbox.x = x;
		hitbox.y = y;
		newState(IDLE);
		active = true;
    } 
}
