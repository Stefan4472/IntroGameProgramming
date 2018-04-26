import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.LinkedList;
import java.util.List;

public class GameDriver extends JFrame implements KeyListener {

    // dimensions of game window (px)
    private static final int SCREEN_WIDTH = 800, SCREEN_HEIGHT = 600;
    // number of ms delay between frames
    private static final int FRAME_DELAY = 33;
    // timestamp of last time game was updated. Used to tell how much time passed since the last frame
    private long lastUpdate;
    // sprites existing in the game
    private List<Sprite> sprites;
    // sprite controlled by the player/user. Receives key input
    private PlayerSprite playerSprite;
    // draws the game's background
    private Background background;

    public GameDriver() {
        // window set-up
        setSize(new Dimension(SCREEN_WIDTH, SCREEN_HEIGHT));
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setTitle("Intro Game Programming Demo");
        setVisible(true);

        // IMPORTANT!! register a listener to this JFrame window
        addKeyListener(this);

        // init PlayerSprite at (175, 175) with width 50 and color Cyan. Set screen bounds to 400px * 400px
        playerSprite = new PlayerSprite(175, 175);

        // init sprite list and add playerSprite
        sprites = new LinkedList<>();
        sprites.add(playerSprite);

        // init lastUpdate
        lastUpdate = System.currentTimeMillis();

        // init game background with image TODO
        background = new Background(SCREEN_WIDTH, SCREEN_HEIGHT);
    }

    // starts running the engine
    public void start() {
        // set up an ActionListener to invalidate the window every FRAME_DELAY milliseconds
        ActionListener taskPerformer = new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                // calculate how many milliseconds passed since last update
                long ms_passed = System.currentTimeMillis() - lastUpdate;

                // update game state
                updateState((int) ms_passed);  // have player-controlled sprite update itself

                // redraw the screen
                repaint();

                // update
                lastUpdate += ms_passed;
            }
        };

        // start the timer
        new Timer(FRAME_DELAY, taskPerformer).start();
    }

    // updates game state, advancing given number of milliseconds
    private void updateState(int ms) {
        // update sprites
        for (Sprite s : sprites) {
            s.update(ms);
        }

        // check collisions... this must be done in O(n^2) :'(
        int num_sprites = sprites.size();
        for (int i = 0; i < num_sprites; i++) {
            for (int j = i + 1; j < num_sprites; j++) {
                if (sprites.get(i).collides(sprites.get(j))) {
                    // handle collision both ways
                    sprites.get(i).handleCollision(sprites.get(j));
                    sprites.get(j).handleCollision(sprites.get(i));
                }
            }
        }
    }

    // refreshes the screen by painting over it, then draws the sprites
    public void paint(Graphics g) {
        // draw background to screen
        background.drawToCanvas((Graphics2D) g);
        // draw each sprite
        for (Sprite s : sprites) {
            s.drawToCanvas((Graphics2D) g);
        }
    }

    @Override  // handle keyTyped action: we can ignore it, but still need it for the interface
    public void keyTyped(KeyEvent e) {

    }

    @Override  // handle keyPressed action: send to playerSprite
    public void keyPressed(KeyEvent e) {
        System.out.println("Key Pressed");
        if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
            //exit = true;
        }
        playerSprite.handleKeyPressed(e);
    }

    @Override  // handle keyReleased action: send to playerSprite
    public void keyReleased(KeyEvent e) {
        System.out.println("Key Released");
        playerSprite.handleKeyReleased(e);
    }
}
