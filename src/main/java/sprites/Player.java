package sprites;

import static utilz.Constants.PlayerConstants.*;
import static utilz.Constants.PlayerConstants.getSpriteAmount;
import static utilz.HelpMethods.*;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import gamestates.Playing;
import main.Game;
import utilz.LoadSave;



public class Player extends Entity{

    private BufferedImage[][] animation;
    private int animationTick = 0, animationIndex = 0, animationSpeed = 30;
    private int playerAction = IDLE;
    private boolean moving = false;
    private boolean left, right, up, down;
    private float playerSpeed = 3.0f;
    private float xDrawOffset = 10 * Game.SCALE;
    private float yDrawOffset = 0 * Game.SCALE;

    private boolean attackChecked = false;

    private int health = 5;

    private Playing playing;
    public Player(float x, float y, int width, int height, Playing playing) {
        // This super is referring to saying that it is the same x and y as in the entity class
        // and to refer back to that, these x and y are those, which means its the parameters given
        super(x, y, width, height);
        this.playing = playing;
        loadAnimations();
        initHitbox(x, y, (int) (95 * Game.SCALE), (int) (85 * Game.SCALE));
    }   

    public void update() {
        updatePosition();
        playing.againstPlayer();
        updateAnimation();
        setAnimation();

        if (health <= 0) {
            playing.setGameOver(true);
        }
        
    }

    public void render(Graphics g) {
        g.drawImage(animation[playerAction][animationIndex], (int) (hitbox.x - xDrawOffset), (int) (hitbox.y - yDrawOffset), 110, 100, null);
        //drawHitbox(g);
    }

    // This is a method that will be used in each update within a second to change the image displayed
    // To create the illusion of an animation. getSpriteAmount is from the Constants

    private void updateAnimation() {
        animationTick++;
        if (animationTick >= animationSpeed) {
            animationTick = 0;
            animationIndex++;
            if (animationIndex >= getSpriteAmount(playerAction)) {
                animationIndex = 0;
                attackChecked = false;
            }
        }
    } 

    public void changeHealth(int value) {
        health -= value;
    }

    public int getHealth() {
        return health;
    }
        

    // Set animation is checking the player direction if moving = true and giving the proper 
    // first dimension of the animations array to show the sprite animating correctly

    private void setAnimation() {
        if (moving && up) {
            playerAction = BACK;
        } else if (moving && down) {
            playerAction = FRONT;
        } else {
            playerAction = IDLE;
        }
    }

    // Changes the coordinates of the sprite object based on user input if moving = true
    private void updatePosition() {

        // Doing this because unless one of the conditions are true, moving is false
        moving = false;

        if (!up && !down) {
            return;
        }
        
        //Temp storage of the speed for the can move here method
        float xSpeed = 0, ySpeed = 0;

        if (up) {
            ySpeed -= playerSpeed;
        }
        if (down) {
            ySpeed += playerSpeed;
        }

        if (CanMoveHere(hitbox.x+xSpeed, hitbox.y+ySpeed, hitbox.width, hitbox.height)) {
            hitbox.x += xSpeed;
            hitbox.y += ySpeed;
            moving = true;
        } 
    }

    // This method is creating a two dimensional array that will store all animations
    // The first dimension will store the different types of animations (sprite or action)
    // The second will pagnate through the images.

    // it gets an inputstream to load an image and also creates a buffered image object to apply the 
    // loop that will load the animation

    private void loadAnimations() {

        BufferedImage img = LoadSave.GetSpriteArray(LoadSave.PLAYER_ARRAY);

        animation = new BufferedImage[5][9];
        for (int j = 0; j < animation.length; j++) {
            for (int i = 0 ; i < animation[j].length; i++) {
                animation[j][i] = img.getSubimage((i*62), (j*70), 62, 70);
            }
        }
    }

    public void resetAll() {
        resetDirBooleans();
		moving = false;
		playerAction = IDLE;
		changeHealth(-5);;

		hitbox.x = x;
		hitbox.y = y;
    }   

    public void resetDirBooleans() {
        left = false;
        right = false;
        up = false;
        down = false;
    }


    // These are directional getters and setters for directional methods
    public boolean isLeft() {
        return left;
    }

    public void setLeft(boolean left) {
        this.left = left;
    }

    public boolean isRight() {
        return right;
    }

    public void setRight(boolean right) {
        this.right = right;
    }

    public boolean isUp() {
        return up;
    }

    public void setUp(boolean up) {
        this.up = up;
    }

    public boolean isDown() {
        return down;
    }

    public void setDown(boolean down) {
        this.down = down;
    }    
}
