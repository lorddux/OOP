package process;

import org.junit.Test;

import java.awt.*;
import java.awt.image.BufferedImage;

import static java.lang.Math.min;
import static org.junit.Assert.*;

public class ImageToAsciiBuilderTest {
    private static final String ASCII_PALETTE = "MWNXK0Okxdolc:;,'...   ";

    @Test
    public void testToAscii() throws Exception {
        BufferedImage img = new BufferedImage(23, 1, BufferedImage.TYPE_INT_RGB);
        for (int i = 0, j = 0; i < 265; i += 12, j++) {
            i = min(i, 255);
            Color pixel = new Color(i, i, i);
            img.setRGB(j, 0, pixel.getRGB());
        }
        String actualAscii = ImageToAsciiBuilder.convertToAscii(img);

        assertEquals(ASCII_PALETTE.trim(), actualAscii);
    }
}