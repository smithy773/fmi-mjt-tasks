package bg.sofia.uni.fmi.mjt.imagekit.algorithm.grayscale;

import java.awt.*;
import java.awt.image.BufferedImage;

public class LuminosityGrayscale implements GrayscaleAlgorithm {
    final double RED_MULTIPLY = 0.21;
    final double GREEN_MULTIPLY = 0.72;
    final double BLUE_MULTIPLY = 0.07;

    /**
     * Applies the image processing algorithm to the given image.
     *
     * @param image the image to be processed
     * @return BufferedImage the processed image of type (TYPE_INT_RGB)
     * @throws IllegalArgumentException if the image is null
     */
    @Override
    public BufferedImage process(BufferedImage image) {
        if (image == null) {
            throw new IllegalArgumentException("Can't process image - image is null!");
        }

        BufferedImage output = new BufferedImage(image.getWidth(), image.getHeight(), BufferedImage.TYPE_INT_RGB);


        for (int y = 0; y < output.getHeight(); y++) {
            for (int x = 0; x < output.getWidth(); x++) {
                Color currentRGB = new Color(image.getRGB(x, y));

                int blue = currentRGB.getBlue();
                int green = currentRGB.getGreen();
                int red = currentRGB.getRed();

                int grayVal = Math.clamp(Math.round(RED_MULTIPLY * red + GREEN_MULTIPLY * green + BLUE_MULTIPLY * blue), 0, 255);

                Color newRGB = new Color(grayVal, grayVal, grayVal);

                output.setRGB(x, y, newRGB.getRGB());
            }
        }

        return output;
    }
}
