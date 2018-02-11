import image.PartitionedBufferedImage;
import process.ImageProcessor;
import process.ImageToAsciiBuilder;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.Arrays;

public class Main {

    public static void main(String[] args) throws Exception {
        BufferedImage img = ImageIO.read(new File("rabbit_divided.jpg"));
//        img = ImageProcessor.resize(img, 120, 110);
//        img = ImageProcessor.mirrorX(img);
//        img = ImageProcessor.rotate(img, 31);
        PartitionedBufferedImage p = new PartitionedBufferedImage(img);
        p.setPartitions(ImageProcessor.parallelProcess(p.getPartitions(), Arrays.asList(
                (image) -> ImageProcessor.mirrorX(image),
                (image) -> ImageProcessor.mirrorY(image)
                ))
        );
        img = p.glueImage();
        ImageIO.write(img, "jpg", new File("image.jpg"));
        String asciiImage = ImageToAsciiBuilder.convertToAscii(img);
        OutputStream outputStream = new FileOutputStream("result.txt");
        outputStream.write(asciiImage.getBytes());
    }
}
