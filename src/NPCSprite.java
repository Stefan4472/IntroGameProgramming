import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class NPCSprite extends Sprite {

    // initializes sprite with top-left at (x, y)
    public NPCSprite(float x, float y) {
        super(x, y, new Hitbox(x, y, 64, 64));

        try {
            // load images and create spritesheets
            moveLeftAnim = new Spritesheet(ImageIO.read(new File("graphics/npc-left.png")), 4, 100);
            moveRightAnim = new Spritesheet(ImageIO.read(new File("graphics/npc-right.png")), 4, 100);
            moveUpAnim = new Spritesheet(ImageIO.read(new File("graphics/npc-up.png")), 4, 100);
            moveDownAnim = new Spritesheet(ImageIO.read(new File("graphics/npc-down.png")), 4, 100);

            // set direction to down
            currDir = Direction.DOWN;
            currAnim = moveDownAnim;
        } catch (IOException e) {
            System.out.println("Couldn't load an image");
            e.printStackTrace();
            System.exit(1);
        }
    }

    @Override
    void update(int ms) {
        // update animation
        currAnim.update(ms);

        // update hitbox coordinates
        hitbox.offset(speedX, speedY);
    }

    @Override
    void drawToCanvas(Graphics g) {
        // draw frame of current animation
        g.drawImage(currAnim.getFrame(), (int) x, (int) y, null);
    }

    @Override
    void handleCollision(Sprite s) {
        speedX = 0;
        speedY = 0;

    }
}
