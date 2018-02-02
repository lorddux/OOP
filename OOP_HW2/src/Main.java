import processor.ImageProcessor;
import processor.ImageToAscii;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Main {

    public static void main(String[] args) {
        try {
            BufferedImage img = ImageIO.read(new File("rabbit.jpg"));
            img = ImageProcessor.resize(img, 120, 110);
            img = ImageProcessor.mirrorX(img);
            img = ImageProcessor.rotate(img, 31);
            ImageToAscii.convertToAscii(img, "result.txt");
        } catch (IOException e) {
            System.out.println(e.toString());
        }
    }
}
