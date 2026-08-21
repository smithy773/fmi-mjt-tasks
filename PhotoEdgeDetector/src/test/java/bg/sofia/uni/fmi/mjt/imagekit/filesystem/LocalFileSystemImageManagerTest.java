package bg.sofia.uni.fmi.mjt.imagekit.filesystem;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.*;

public class LocalFileSystemImageManagerTest {
    private final LocalFileSystemImageManager imgManager = new LocalFileSystemImageManager();
    private File nonExistingFile;
    private File wrongFormatFile;
    private File kittenPNG;
    private File validFolderPath;
    private File invalidFolderPath;


    @BeforeEach
    void setup() {
        nonExistingFile = new File("src/test/resources/nonexisting.jpg");
        wrongFormatFile = new File("src/test/resources/invalidFolder/coolText.txt");
        kittenPNG = new File("src/test/resources/validFolder/kitten.png");
        validFolderPath = new File("src/test/resources/validFolder");
        invalidFolderPath = new File("src/test/resources/invalidFolder");
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
        assertThrows(IllegalArgumentException.class, () -> imgManager.loadImagesFromDirectory(null), "Expected IllegalArgumentException when loadImagesFromDirectory is passed null");
    }

    @Test
    void loadImagesFromDirThrowsIOExcWhenDirNotExist() {
        assertThrows(IOException.class, () -> imgManager.loadImagesFromDirectory(new File("src/test/resources/invalidFolder/NonExistingFolder")), "Expected IOException when passed directory doesn't exist");
    }

    @Test
    void loadImagesFromDirThrowsIOExcWhenDirFileFormatNotSupp() {
        assertThrows(IOException.class, () -> imgManager.loadImagesFromDirectory(invalidFolderPath), "Expected IOException when directory contains file that is in a non-supported format");
    }

    @Test
    void loadImagesFromDirThrowsIOExcWhenDirNotADir() {
        assertThrows(IOException.class, () -> imgManager.loadImagesFromDirectory(kittenPNG), "Expected IOException when passed directory is not a directory");
    }

    @Test
    void loadImagesFromDirReturnsListOfBufferedImages() throws IOException {
        List<BufferedImage> list = imgManager.loadImagesFromDirectory(validFolderPath);

        int validFolderImgCount = Objects.requireNonNull(validFolderPath.listFiles()).length;

        assertEquals(validFolderImgCount, list.size(), "Expected the directory's img count to be equal to the loaded list's size");

        for (BufferedImage BI : list) {
           assertNotNull(BI, "Expected all loaded images from the list to not be null");
        }
    }

    @Test
    void saveImageThrowsIllArgExcWhenImgNull() {
        assertThrows(IllegalArgumentException.class, () -> imgManager.saveImage(null, invalidFolderPath), "Expected IllegalArgumentException when null is passed as the image to save");
    }

    @Test
    void saveImageThrowsIllArgExcWhenFileNull() throws IOException {
        BufferedImage kitten = imgManager.loadImage(kittenPNG);

        assertThrows(IllegalArgumentException.class, () -> imgManager.saveImage(kitten, null), "Expected IllegalArgumentException when path to save image to is null");
    }

    @Test
    void saveImageThrowsIOExcWhenFileAlreadyExists() throws IOException {
        BufferedImage kitten = imgManager.loadImage(kittenPNG);

        assertTrue(kittenPNG.exists());
        assertThrows(IOException.class, () -> imgManager.saveImage(kitten, kittenPNG));
    }

    @Test
    void saveImageThrowsIOExcWhenParentDirNotExist() throws IOException {
        BufferedImage kitten = imgManager.loadImage(kittenPNG);

        assertThrows(IOException.class, () -> imgManager.saveImage(kitten, new File("src/test/resources/fakeFolder/kitten.png")), "Expected IOException when parent folder where image file should be saved to does not exist");
    }

    @Test
    void saveImageSavesImageToFile() throws IOException {
        BufferedImage kitten = imgManager.loadImage(kittenPNG);
        File kittenPNG2 = new File("src/test/resources/invalidFolder/kitten.png");

        assertFalse(kittenPNG2.exists());

        imgManager.saveImage(kitten, kittenPNG2);

        assertTrue(kittenPNG2.exists());
        assertTrue(kittenPNG2.delete());
    }
}
