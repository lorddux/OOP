package image;

import org.junit.jupiter.api.Test;
import process.ImageProcessor;
import tests.TestsHelper;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class PartitionedBufferedImageTest {

    @Test
    void testGlueImage() throws Exception {
        BufferedImage source = ImageIO.read(new File("rabbit_divided.png"));
        PartitionedBufferedImage partitionedBufferedImage = new PartitionedBufferedImage(source);
        BufferedImage img = partitionedBufferedImage.glueImage();
        BufferedImage actual = ImageIO.read(new File("rabbit_divided.png"));
        assertTrue(TestsHelper.bufferedImagesEqual(img, actual));

        partitionedBufferedImage = new PartitionedBufferedImage(source);
        partitionedBufferedImage.setPartitions(ImageProcessor.parallelProcess(partitionedBufferedImage.getPartitions(), Arrays.asList(
                (image) -> ImageProcessor.mirrorX(image)
                ))
        );
        img = partitionedBufferedImage.glueImage();
        actual = ImageIO.read(new File("src\\test\\actual\\png\\parallelMirrorX.png"));
        assertTrue(TestsHelper.bufferedImagesEqual(img, actual));

        partitionedBufferedImage = new PartitionedBufferedImage(source);
        partitionedBufferedImage.setPartitions(ImageProcessor.parallelProcess(partitionedBufferedImage.getPartitions(), Arrays.asList(
                (image) -> ImageProcessor.mirrorY(image)
                ))
        );
        img = partitionedBufferedImage.glueImage();
        actual = ImageIO.read(new File("src\\test\\actual\\png\\parallelMirrorY.png"));
        assertTrue(TestsHelper.bufferedImagesEqual(img, actual));

        partitionedBufferedImage = new PartitionedBufferedImage(source);
        partitionedBufferedImage.setPartitions(ImageProcessor.parallelProcess(partitionedBufferedImage.getPartitions(), Arrays.asList(
                (image) -> ImageProcessor.rotate(image, 90)
                ))
        );
        img = partitionedBufferedImage.glueImage();
        actual = ImageIO.read(new File("src\\test\\actual\\png\\parallelRotate90.png"));
        assertTrue(TestsHelper.bufferedImagesEqual(img, actual));

        partitionedBufferedImage = new PartitionedBufferedImage(source);
        partitionedBufferedImage.setPartitions(ImageProcessor.parallelProcess(partitionedBufferedImage.getPartitions(), Arrays.asList(
                (image) -> ImageProcessor.rotate(image, 180)
                ))
        );
        img = partitionedBufferedImage.glueImage();
        actual = ImageIO.read(new File("src\\test\\actual\\png\\parallelRotate180.png"));
        assertTrue(TestsHelper.bufferedImagesEqual(img, actual));
    }
}