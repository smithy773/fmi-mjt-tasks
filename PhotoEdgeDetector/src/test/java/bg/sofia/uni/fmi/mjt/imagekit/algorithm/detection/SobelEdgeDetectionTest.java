package bg.sofia.uni.fmi.mjt.imagekit.algorithm.detection;

import bg.sofia.uni.fmi.mjt.imagekit.algorithm.grayscale.LuminosityGrayscale;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

public class SobelEdgeDetectionTest {
    private SobelEdgeDetection detection;
    private BufferedImage kittenBI;

    @BeforeEach
    void setup() throws IOException {
        detection = new SobelEdgeDetection(new LuminosityGrayscale());
        kittenBI = ImageIO.read(new File("src/test/resources/validFolder/kitten.png"));
    }

    @Test
    void processThrowsIllArgExcWhenImageNull() {
        assertThrows(IllegalArgumentException.class, () -> detection.process(null), "");
    }

    @Test
    void processReturnsImgOfCorrectType() {
        BufferedImage result = detection.process(kittenBI);

        assertEquals(BufferedImage.TYPE_INT_RGB, result.getType());
    }

    @Test
    void processReturnsNewImg() {
        assertNotSame(kittenBI, detection.process(kittenBI));
    }

    @Test
    void processReturnsCorrectlyProcessedImage() {
        BufferedImage newImg = new BufferedImage(2, 2, BufferedImage.TYPE_INT_RGB);

        newImg.setRGB(0, 0, new Color(30, 30, 30).getRGB());
        newImg.setRGB(1, 0, new Color(150, 150, 150).getRGB());
        newImg.setRGB(0, 1, new Color(100, 100, 100).getRGB());
        newImg.setRGB(1, 1, new Color(60, 60, 60).getRGB());

        BufferedImage processed = detection.process(newImg);

        BufferedImage manuallyProcessed = new BufferedImage(2, 2, BufferedImage.TYPE_INT_RGB);

        int val = (255 << 16) | (255 << 8) | 255;

        manuallyProcessed.setRGB(0, 0, val);
        manuallyProcessed.setRGB(1, 0, val);
        manuallyProcessed.setRGB(0, 1, val);
        manuallyProcessed.setRGB(1, 1, val);

        assertEquals(processed.getHeight(), manuallyProcessed.getHeight());
        assertEquals(processed.getWidth(), manuallyProcessed.getWidth());

        for (int x = 0; x < newImg.getWidth(); x++) {
            for (int y = 0; y < newImg.getHeight(); y++) {
                assertEquals(processed.getRGB(x, y), manuallyProcessed.getRGB(x, y));
            }
        }
    }
}
