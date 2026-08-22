package bg.sofia.uni.fmi.mjt.imagekit.algorithm.detection;

import bg.sofia.uni.fmi.mjt.imagekit.algorithm.ImageAlgorithm;

import java.awt.*;
import java.awt.image.BufferedImage;

public class SobelEdgeDetection implements EdgeDetectionAlgorithm {
    private final ImageAlgorithm imgAlg;


    public SobelEdgeDetection(ImageAlgorithm grayscaleAlgorithm) {
        imgAlg = grayscaleAlgorithm;
    }

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

        BufferedImage grayscaleImg = imgAlg.process(image);

        int imgW = grayscaleImg.getWidth();
        int imgH = grayscaleImg.getHeight();

        BufferedImage output = new BufferedImage(imgW, imgH, BufferedImage.TYPE_INT_RGB);

        for (int x = 0; x < imgW; x++) {
            for (int y = 0; y < imgH; y++) {

                int topLeft = x > 0 && y > 0 ? new Color(grayscaleImg.getRGB(x - 1, y - 1)).getRed() : 0;
                int top = y > 0 ? new Color(grayscaleImg.getRGB(x, y - 1)).getRed() : 0;
                int topRight = x + 1 != imgW && y > 0 ? new Color(grayscaleImg.getRGB(x + 1, y - 1)).getRed() : 0;
                int left = x > 0 ? new Color(grayscaleImg.getRGB(x - 1, y)).getRed() : 0;
                int right = x + 1 != imgW ? new Color(grayscaleImg.getRGB(x + 1, y)).getRed() : 0;
                int bottomLeft = x > 0 && y + 1 != imgH ? new Color(grayscaleImg.getRGB(x - 1, y + 1)).getRed() : 0;
                int bottom = y + 1 != imgH ? new Color(grayscaleImg.getRGB(x, y + 1)).getRed() : 0;
                int bottomRight = x + 1 != imgW && y + 1 != imgH ? new Color(grayscaleImg.getRGB(x + 1, y + 1)).getRed() : 0;

                int Gx =
                        (-1 * topLeft)
                        + topRight
                        + (-2 * left)
                        + (2 * right)
                        + (-1 * bottomLeft)
                        + bottomRight;

                int Gy =
                        (-1 * topLeft)
                        + (-2 * top)
                        + (-1 * topRight)
                        + bottomLeft
                        + (2 * bottom)
                        + bottomRight;

                int pixelValue = Math.min(255, (int) Math.round(Math.sqrt(Math.pow(Gx, 2) + Math.pow(Gy, 2))));

                int rgb = (pixelValue << 16) | (pixelValue << 8) | pixelValue;

                output.setRGB(x, y, rgb);
            }
        }

        return output;
    }
}
