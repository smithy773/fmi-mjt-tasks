package bg.sofia.uni.fmi.mjt.imagekit.algorithm.detection;

import bg.sofia.uni.fmi.mjt.imagekit.algorithm.ImageAlgorithm;

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
        return null;
    }
}
