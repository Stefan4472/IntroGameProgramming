import java.awt.*;
import java.awt.image.BufferedImage;

// Spritesheet that loops and displays each frame once
public class Spritesheet {

    private int counter = -1;       // current frame we're on (0-indexed)
    private int frameW, frameH;   // dimensions of frame (px)
    private int msPerFrame; // number of ms to show each frame
    private int msThisFrame; // number of ms this frame has been shown
    private int numFrames;          // number of frames in the sequence
    private BufferedImage img;      // the image itself
    private boolean paused = false; // whether animation is paused

    // initialize with image and the number of frames it contains
    public Spritesheet(BufferedImage img, int numFrames, int msPerFrame) {
        this.img = img;
        frameW = img.getWidth() / numFrames;  // calculate width of each sub-frame (px)
        frameH = img.getHeight();
        this.msPerFrame = msPerFrame;
        msThisFrame = 0;
        this.numFrames = numFrames;
    }

    // advance animation by given number of milliseconds
    public void update(int ms) {
        msThisFrame += ms;

        if (msThisFrame > msPerFrame) { // TODO: WHILE LOOP
            // increment counter. Use modulus to ensure it remains within the bounds of numFrames
            counter = (counter + 1) % numFrames;
            msThisFrame = msPerFrame - (msThisFrame - msPerFrame);
        }
    }

    public void pause() {
        paused = true;
    }

    public void play() {
        paused = false;
    }

    // sets frame count to zero
    public void reset() {
        counter = 0;
        msThisFrame = 0;
    }

    // returns current frame of animation
    public BufferedImage getFrame() {
        return img.getSubimage(counter * frameW, 0, frameW, frameH);
    }

    // return width of each frame in the spritesheet (px)
    public int getFrameW() {
        return frameW;
    }

    // return height of each frame in the spritesheet (px)
    public int getFrameH() {
        return frameH;
    }
}