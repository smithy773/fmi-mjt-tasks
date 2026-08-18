package bg.sofia.uni.fmi.mjt.imagekit.filesystem;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class LocalFileSystemImageManagerTest {
    private final LocalFileSystemImageManager imgManager = new LocalFileSystemImageManager();
    private File nonExistingFile;
    private File wrongFormatFile;
    private File kittenPNG;

    @BeforeEach
    void setup() {
        nonExistingFile = new File("src/test/resources/nonexisting.jpg");
        wrongFormatFile = new File("src/test/resources/invalidFolder/coolText.txt");
        kittenPNG = new File("src/test/resources/validFolder/kitten.png");
    }

    @Test
    void loadImageThrowsIllArgExcWhenNullFile() {
        assertThrows(IllegalArgumentException.class, () -> imgManager.loadImage(null),"Expected IllegalArgumentException to be thrown when loadImage() is passed null");
    }

    @Test
    void loadImageThrowsIOExcWhenNonExistingFile() {
        assertThrows(IOException.class, () -> imgManager.loadImage(nonExistingFile), "Expected IOException when loading an image file that does not exist");
    }

    @Test
    void loadImageThrowsIOExcWhenFormatNotSupported() {
        assertThrows(IOException.class, () -> imgManager.loadImage(wrongFormatFile), "Expected IOException when loading a file with a wrong format");
    }

    @Test
    void loadImageReturnsCorrectBufferedImage() throws IOException {
        BufferedImage kittenBI1 = ImageIO.read(kittenPNG);
        BufferedImage kittenBI2 = imgManager.loadImage(kittenPNG);

        assertEquals(kittenBI1.getHeight(), kittenBI2.getHeight(), "Expected height of images to be equal");
        assertEquals(kittenBI1.getWidth(), kittenBI2.getWidth(), "Expected width of images to be equal");

        for (int y = 0; y < kittenBI1.getHeight(); y++) {
            for (int x = 0; x < kittenBI1.getWidth(); x++) {
                int expected = kittenBI1.getRGB(x, y);
                int actual = kittenBI2.getRGB(x, y);

                assertEquals(expected, actual, "Expected same RGB values from kittenBI1 and kittenBI2 when loading the same image");
            }
        }
    }

    @Test
    void loadImagesFromDirThrowsIllArgExcWhenNullDir() {

    }

    @Test
    void loadImagesFromDirThrowsIOExcWhenDirNotExist() {

    }

    @Test
    void loadImagesFromDirThrowsIOExcWhenDirFileFormatNotSupp() {

    }

    @Test
    void loadImagesFromDirThrowsIOExcWhenDirNotADir() {

    }

    @Test
    void loadImagesFromDirReturnsListOfBufferedImages() {

    }

    @Test
    void saveImageThrowsIllArgExcWhenImgNull() {

    }

    @Test
    void saveImageThrowsIllArgExcWhenFileNull() {

    }

    @Test
    void saveImageThrowsIOExcWhenFileAlreadyExists() {

    }

    @Test
    void saveImageThrowsIOExcWhenParentDirNotExist() {

    }
}
