package process;

import org.junit.jupiter.api.Test;
import tests.TestsHelper;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ImageProcessorTest {

    @Test
    void testResize() throws Exception {
        BufferedImage img = ImageIO.read(new File("rabbit_divided.png"));
        BufferedImage actual = ImageIO.read(new File("src\\test\\actual\\png\\resize120110.png"));
        img = ImageProcessor.resize(img, 120, 110);
        assertTrue(TestsHelper.bufferedImagesEqual(img, actual));
    }

    @Test
    void testRotate() throws Exception {
        BufferedImage img = ImageIO.read(new File("rabbit_divided.png"));
        BufferedImage actual = ImageIO.read(new File("src\\test\\actual\\png\\rotate53.png"));
        img = ImageProcessor.rotate(img, 53);
        assertTrue(TestsHelper.bufferedImagesEqual(img, actual));
    }

    @Test
    void testMirrorX() throws Exception {
        BufferedImage img = ImageIO.read(new File("rabbit_divided.png"));
        BufferedImage actual = ImageIO.read(new File("src\\test\\actual\\png\\mirrorX.png"));
        img = ImageProcessor.mirrorX(img);
        assertTrue(TestsHelper.bufferedImagesEqual(img, actual));
    }

    @Test
    void testMirrorY() throws Exception{
        BufferedImage img = ImageIO.read(new File("rabbit_divided.png"));
        BufferedImage actual = ImageIO.read(new File("src\\test\\actual\\png\\mirrorY.png"));
        img = ImageProcessor.mirrorY(img);
        assertTrue(TestsHelper.bufferedImagesEqual(img, actual));
    }

    @Test
    void testParallelProcess() throws Exception {
        BufferedImage img1 = ImageIO.read(new File("rabbit_divided.png"));
        BufferedImage img2 = ImageIO.read(new File("rabbit_divided.png"));
        BufferedImage img3 = ImageIO.read(new File("rabbit_divided.png"));


        List<BufferedImage> result = ImageProcessor.parallelProcess(Arrays.asList(img1, img2, img3), Arrays.asList(
                (image) -> ImageProcessor.rotate(image, 53)
        ));

        BufferedImage actual = ImageIO.read(new File("src\\test\\actual\\png\\rotate53.png"));
        for (BufferedImage img : result) {
            assertTrue(TestsHelper.bufferedImagesEqual(img, actual));
        }

        result = ImageProcessor.parallelProcess(Arrays.asList(img1, img2, img3), Arrays.asList(
                (image) -> ImageProcessor.resize(image, 120, 110)
        ));

        actual = ImageIO.read(new File("src\\test\\actual\\png\\resize120110.png"));
        for (BufferedImage img : result) {
            assertTrue(TestsHelper.bufferedImagesEqual(img, actual));
        }

        result = ImageProcessor.parallelProcess(Arrays.asList(img1, img2, img3), Arrays.asList(
                (image) -> ImageProcessor.mirrorX(image)
        ));

        actual = ImageIO.read(new File("src\\test\\actual\\png\\mirrorX.png"));
        for (BufferedImage img : result) {
            assertTrue(TestsHelper.bufferedImagesEqual(img, actual));
        }

        result = ImageProcessor.parallelProcess(Arrays.asList(img1, img2, img3), Arrays.asList(
                (image) -> ImageProcessor.mirrorY(image)
        ));

        actual = ImageIO.read(new File("src\\test\\actual\\png\\mirrorY.png"));
        for (BufferedImage img : result) {
            assertTrue(TestsHelper.bufferedImagesEqual(img, actual));
        }

    }
}