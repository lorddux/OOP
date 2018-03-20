package image;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.awt.*;
import java.awt.image.BufferedImage;

import static org.junit.Assert.*;

public class PartitionedBufferedImageTest {
    private BufferedImage img;

    @Before
    public void setImage() {
        img = new BufferedImage(100, 200, BufferedImage.TYPE_INT_RGB);
        Graphics2D graphics = img.createGraphics();
        graphics.setPaint (new Color (255, 255, 255));
        graphics.fillRect (0, 0, img.getWidth(), img.getHeight());
        graphics.dispose();
    }

    @After
    public void tearDownImage() {
        img.flush();
    }

    @Test
    public void testPartitionsInWhiteImage() {
        PartitionedBufferedImage expectedImg = new PartitionedBufferedImage(img);
        assertTrue(expectedImg.getPartitions().size() == 1);
    }

    @Test
    public void testPartitionsWithOneLine() {
        Graphics2D graphics = img.createGraphics();
        graphics.setPaint (new Color (0, 0, 0));
        graphics.fillRect (50, 0, 1, 200);
        graphics.dispose();
        PartitionedBufferedImage expectedImg = new PartitionedBufferedImage(img);

        assertTrue(expectedImg.getPartitions().size() == 2);
    }

    @Test
    public void testLinesIsConcatenated() {
        Graphics2D graphics = img.createGraphics();
        graphics.setPaint (new Color (0, 0, 0));
        graphics.fillRect (50, 0, 2, 200);
        graphics.dispose();
        PartitionedBufferedImage expectedImg = new PartitionedBufferedImage(img);

        assertTrue(expectedImg.getPartitions().size() == 2);
    }

    @Test
    public void testGlueImage() {
        Graphics2D graphics = img.createGraphics();
        BufferedImage expectedImg = img;
        graphics.setPaint (new Color (0, 0, 0));
        graphics.fillRect (50, 0, 2, 200);
        graphics.dispose();
        PartitionedBufferedImage dividedImg = new PartitionedBufferedImage(img);
        BufferedImage actualImg = dividedImg.glueImage();

        assertTrue(compareImages(actualImg, expectedImg));
    }

    public static boolean compareImages(BufferedImage imgA, BufferedImage imgB) {
        if (imgA.getWidth() == imgB.getWidth() && imgA.getHeight() == imgB.getHeight()) {
            int width = imgA.getWidth();
            int height = imgA.getHeight();

            for (int y = 0; y < height; y++) {
                for (int x = 0; x < width; x++) {
                    if (imgA.getRGB(x, y) != imgB.getRGB(x, y)) {
                        return false;
                    }
                }
            }
        } else {
            return false;
        }

        return true;
    }
}