package processor;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.*;

public class ImageToAscii {

    private static final String ASCII_PALETTE = "MWNXK0Okxdolc:;,'...   ";
    private static int PALETTE_SIZE = ASCII_PALETTE.length();

    public static void convertToAscii(BufferedImage img, String destinationFileName) throws IOException {
        OutputStream outputStream = new FileOutputStream(destinationFileName);

        for (int i = 0; i < img.getHeight(); i++) {
            for (int j = 0; j < img.getWidth(); j++) {
                Color pixColor = new Color(img.getRGB(j, i));
                double pixValue = colorToValue(pixColor);
                outputStream.write(getPaletteChar(pixValue));
            }
            outputStream.write('\n');
        }
    }

    private static char getPaletteChar(double g) {
        return ASCII_PALETTE.charAt(
                (int)((g / 255) * (PALETTE_SIZE - 1))
        );
    }

    private static double colorToValue(Color pixColor) {
        return pixColor.getRed() * 0.30 + pixColor.getBlue() * 0.59 + pixColor.getGreen() * 0.11;
    }
}