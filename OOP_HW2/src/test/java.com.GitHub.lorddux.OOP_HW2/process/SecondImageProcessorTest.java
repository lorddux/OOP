package process;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

public class ImageProcessorTest {
    private BufferedImage expectedImg;

    @Before
    public void setImage() {
        expectedImg = new BufferedImage(100, 200, BufferedImage.TYPE_INT_RGB);
        Color c = new Color(0, 0, 255);
        expectedImg.setRGB(0, 0, c.getRGB());
    }

    @After
    public void tearDownImage() {
        expectedImg.flush();
    }

    @Test
    public void testResize() {
        BufferedImage actualImg = ImageProcessor.resize(expectedImg, 50, 50);

        assertTrue(actualImg.getHeight() == 50);
        assertTrue(actualImg.getWidth() == 50);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testNegativeResize() {
        ImageProcessor.resize(expectedImg, -50, 50);
    }

    @Test
    public void testRotate() {
        BufferedImage actualImg = expectedImg;

        actualImg = ImageProcessor.rotate(actualImg, 360);

        assertTrue(compareImages(actualImg, expectedImg));
    }

    @Test
    public void testMirrorX() {
        BufferedImage actualImg = expectedImg;
        for (int i = 0; i < 2; i++) {
            actualImg = ImageProcessor.mirrorX(actualImg);
        }

        assertTrue(compareImages(actualImg, expectedImg));
    }

    @Test
    public void testMirrorY() {
        BufferedImage actualImg = expectedImg;
        for (int i = 0; i < 2; i++) {
            actualImg = ImageProcessor.mirrorY(actualImg);
        }

        assertTrue(compareImages(actualImg, expectedImg));
    }

    @Test
    public void testParallelProcess() throws Exception {
        BufferedImage actualImg1 = expectedImg;
        BufferedImage actualImg2 = expectedImg;

        List<BufferedImage> process = ImageProcessor.parallelProcess(Arrays.asList(actualImg1, actualImg2),
                Arrays.asList((image) -> ImageProcessor.rotate(image, 360)));

        for (BufferedImage img : process) {
            assertTrue(compareImages(img, expectedImg));
        }
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