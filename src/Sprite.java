import java.awt.*;

// Parent class for all Sprites
public abstract class Sprite {

    // x- and y-coordinates of upper-left corner of the sprite. Use float for sub-pixel precision.
    protected float x, y;
    // speed in x- and y-directions of sprite
    float speedX = 0, speedY = 0;
    // rectangular region on canvas specifying where the sprite can be hit
    protected Hitbox hitbox;
    // direction sprite is moving in, if any
    Direction currDir = Direction.NONE;

    // spritesheet animations for movement in given directions
    protected Spritesheet moveLeftAnim, moveRightAnim, moveUpAnim, moveDownAnim;

    // current animation being played
    protected Spritesheet currAnim;

    // constants describing which directions a sprite can move in
    public enum Direction {
        UP, DOWN, LEFT, RIGHT, NONE;
    }

    // constructor requiring only the coordinates of the sprite
    public Sprite(float x, float y, Hitbox hitbox) {
        this.x = x;
        this.y = y;
        this.hitbox = hitbox;
    }

    // returns whether this Sprite collides with the given Sprite, i.e. whether their Hitboxes intersect.
    public boolean collides(Sprite s) {
        return hitbox.intersects(s.hitbox);
    }

    // updates the state of the sprite by the number of milliseconds
    abstract void update(int ms);

    // draws the sprite to the screen/graphics object (implemented in subclass)
    abstract void drawToCanvas(Graphics g);

    // executes logic when a collision is detected with given Sprite s
    abstract void handleCollision(Sprite s);
}