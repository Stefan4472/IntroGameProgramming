import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;

public class PlayerSprite extends Sprite {

    public PlayerSprite(float x, float y) {
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

    // handles a KeyEvent for a key pressed. Sets movement direction, animation being played, and speeds accordingly
    public void handleKeyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_RIGHT) {  // handle right arrow key
            currDir = Direction.RIGHT;
            speedX = 5.0f;  // move to the right
            currAnim = moveRightAnim;
        } else if (e.getKeyCode() == KeyEvent.VK_LEFT) {  // handle left arrow key
            currDir = Direction.LEFT;
            speedX = -5.0f;  // move to the left
            currAnim = moveLeftAnim;
        } else if (e.getKeyCode() == KeyEvent.VK_UP) {  // handle up arrow key
            currDir = Direction.UP;
            speedY = -5.0f;  // move up (y decreases!)
            currAnim = moveUpAnim;
        } else if (e.getKeyCode() == KeyEvent.VK_DOWN) {  // handle down arrow key
            currDir = Direction.DOWN;
            speedY = 5.0f;  // move down (y increases!)
            currAnim = moveDownAnim;
        }
    }

    // handles a KeyEvent for a key released. sets speedX and speedY accordingly
    public void handleKeyReleased(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_RIGHT || e.getKeyCode() == KeyEvent.VK_LEFT) {
            speedX = 0;  // user stopped pressing left/right arrow key. stop horizontal movement
        } else if (e.getKeyCode() == KeyEvent.VK_UP || e.getKeyCode() == KeyEvent.VK_DOWN) {
            speedY = 0;  // user stopped pressing up/down arrow key. stop vertical movement
        }
    }

    @Override
    void update(int ms) {
        // update animation
        currAnim.update(ms);

        // update coordinates
        x += speedX;
        y += speedY;

        // update hitbox coordinates
        hitbox.offset(speedX, speedY);
    }

    @Override
    void drawToCanvas(Graphics g) {
        // draw frame of current animation
        g.drawImage(currAnim.getFrame(), (int) x, (int) y, null);
        // draw hitbox in red
        g.setColor(Color.RED);
        g.drawRect((int) hitbox.getX(), (int) hitbox.getY(), (int) hitbox.getWidth(), (int) hitbox.getHeight());
    }

    @Override
    void handleCollision(Sprite s) {
        speedX = 0;
        speedY = 0;
    }
}
