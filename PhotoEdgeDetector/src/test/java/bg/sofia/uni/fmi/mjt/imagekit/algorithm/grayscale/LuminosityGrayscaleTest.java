package bg.sofia.uni.fmi.mjt.imagekit.algorithm.grayscale;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

public class LuminosityGrayscaleTest {
    private LuminosityGrayscale grayscaleAlg;
    private BufferedImage kittenBI;
    private BufferedImage result;

    @BeforeEach
    void setup() throws IOException {
        grayscaleAlg = new LuminosityGrayscale();

        File kittenPNG = new File("src/test/resources/validFolder/kitten.png");

        kittenBI = ImageIO.read(kittenPNG);

        result = grayscaleAlg.process(kittenBI);
    }

    @Test
    void processThrowsIllArgExc() {
        assertThrows(IllegalArgumentException.class, () -> grayscaleAlg.process(null), "Expected IllegalArgumentException when passed image is null!");
    }

    @Test
    void processReturnsImageOfCorrectType() {
        assertEquals(BufferedImage.TYPE_INT_RGB, result.getType());
    }

    @Test
    void processReturnsNewImage() {
        assertNotSame(kittenBI, result);
    }

    @Test
    void processReturnsImgWithCorrectDimensions() {
        assertEquals(kittenBI.getWidth(), result.getWidth());
        assertEquals(kittenBI.getHeight(), result.getHeight());
    }

    @Test
    void processReturnsCorrectGrayscaleImg() {
        BufferedImage newImg = new BufferedImage(3, 1, BufferedImage.TYPE_INT_RGB);

        Color red = new Color(100, 10, 10);
        Color green = new Color(10, 100, 10);
        Color blue = new Color(10, 10, 100);

        newImg.setRGB(0, 0, red.getRGB());
        newImg.setRGB(1, 0, green.getRGB());
        newImg.setRGB(2, 0, blue.getRGB());

        BufferedImage processedImg = grayscaleAlg.process(newImg);

        Color expectedRed = new Color(29, 29, 29);
        Color expectedGreen = new Color(75, 75, 75);
        Color expectedBlue = new Color(16, 16, 16);

        assertEquals(processedImg.getRGB(0, 0), expectedRed.getRGB());
        assertEquals(processedImg.getRGB(1, 0), expectedGreen.getRGB());
        assertEquals(processedImg.getRGB(2, 0), expectedBlue.getRGB());
    }
}
