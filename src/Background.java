import java.awt.*;
import java.awt.image.BufferedImage;

public class Background {

    // width and height (px) of background
    private int width, height;

    public Background(int width, int height) {
        this.width = width;
        this.height = height;
    }

    public void setImage(BufferedImage backgroundImg) {

    }

    // draws background to the given Graphics2D object
    // this can be improved to show an image background, or a scrolling background
    public void drawToCanvas(Graphics2D g) {
        g.setColor(Color.GREEN);
        g.fillRect(0, 0, width, height);
    }
}
