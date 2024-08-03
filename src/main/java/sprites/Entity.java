package sprites;

import java.awt.geom.Rectangle2D;
import java.awt.Color;
import java.awt.Graphics;

public abstract class Entity {

    protected float x, y;
    protected int width, height;
    protected Rectangle2D.Float hitbox;

    public Entity(float x, float y, int width, int height) {
        // using this tells the compiler that the variables in the class are the variables of the parameters
        // it is telling java to set the variables for the constructor
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    protected void drawHitbox(Graphics g) {
        g.setColor(Color.PINK);
        g.drawRect((int) hitbox.x, (int) hitbox.y, (int) hitbox.width, (int) hitbox.height);
    }

    protected void initHitbox(float x, float y, int width, int height) {
        hitbox = new Rectangle2D.Float(x, y, width, height);
    }

    /*Only want the player class to actually update the hitbox
    protected void updateHitbox() {
        hitbox.x = (int) x;
        hitbox.y = (int) y;
    }*/

    public Rectangle2D.Float getHitbox() {
        return hitbox;
    }
}

