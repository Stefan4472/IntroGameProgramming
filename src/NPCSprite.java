import java.awt.*;

public class NPCSprite extends Sprite {

    // initializes sprite with top-left at (x, y)
    public NPCSprite(float x, float y) {
        super(x, y, new Hitbox(x, y, 64, 64));
    }

    @Override
    void update(int ms) {

    }

    @Override
    void drawToCanvas(Graphics g) {

    }

    @Override
    void handleCollision(Sprite s) {

    }
}
