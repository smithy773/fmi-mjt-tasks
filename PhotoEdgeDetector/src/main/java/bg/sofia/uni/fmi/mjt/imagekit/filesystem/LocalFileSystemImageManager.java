package bg.sofia.uni.fmi.mjt.imagekit.filesystem;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class LocalFileSystemImageManager implements FileSystemImageManager {
    /**
     * Loads a single image from the given file path.
     *
     * @param imageFile the file containing the image.
     * @return the loaded BufferedImage.
     * @throws IllegalArgumentException if the file is null
     * @throws IOException              if the file does not exist, is not a regular file or is not in one of the supported formats
     */
    @Override
    public BufferedImage loadImage(File imageFile) throws IOException {
        if (imageFile == null) {
            throw new IllegalArgumentException("Can't load image - passed imageFile is null!");
        }

        BufferedImage bi = ImageIO.read(imageFile);

        if (bi == null) {
            throw new IOException("Image file does not exist, is not a regular file or is not in a supported format!");
        }

        return bi;
    }

    /**
     * Loads all images from the specified directory.
     *
     * @param imagesDirectory the directory containing the images
     * @return A list of the BufferedImages representing the loaded images.
     * @throws IllegalArgumentException if the directory is null.
     * @throws IOException              if the directory does not exist, is not a directory or contains files that are not in one of the supported formats.
     */
    @Override
    public List<BufferedImage> loadImagesFromDirectory(File imagesDirectory) throws IOException {
        if (imagesDirectory == null) {
            throw new IllegalArgumentException("Can't load images from directory - passed imagesDirectory is null!");
        }

        try (DirectoryStream<Path> imgFileStream = Files.newDirectoryStream(imagesDirectory.toPath())) {

            List<BufferedImage> result = new ArrayList<>();

            for (Path image : imgFileStream) {
                result.add(loadImage(image.toFile()));
            }

            return result;
        }
    }

    /**
     * Saves the given image to the specified file path.
     *
     * @param image     the image to save.
     * @param imageFile the file to save the image to.
     * @throws IllegalArgumentException if the image or file is null.
     * @throws IOException              if the file already exists or the parent directory does not exist.
     */
    @Override
    public void saveImage(BufferedImage image, File imageFile) throws IOException {
        if (image == null) {
            throw new IllegalArgumentException("Can't save image - passed image is null!");
        }

        if (imageFile == null) {
            throw new IllegalArgumentException("Can't save image - passed imageFile is null!");
        }

        String fileName = imageFile.getName();
        int extensionDotIdx = fileName.lastIndexOf(".");
        String imgFormat = fileName.substring( extensionDotIdx + 1);

        if (extensionDotIdx < 0 || !ImageIO.write(image, imgFormat, imageFile)) {
            throw new IOException("Can't save image - invalid format!");
        }
    }
}
