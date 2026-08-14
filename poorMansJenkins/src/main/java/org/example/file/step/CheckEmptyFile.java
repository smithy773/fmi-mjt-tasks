package org.example.file.step;


import org.example.file.File;
import org.example.file.exception.EmptyFileException;
import org.example.pipeline.step.Step;

/**
 * A pipeline step that validates whether a {@link File} is empty.
 */
public class CheckEmptyFile implements Step<File, File> {

    /**
     * Validates that the input {@link File} is not empty.
     *
     * @param input the file to check
     * @return the same {@link File} if it is not empty
     * @throws EmptyFileException with message "Input file or its content is empty"
     *                              if the file is null or if the file content
     *                              is empty.
     */
    @Override
    public File process(File input) {
        if (input == null || input.getContent().isEmpty()) {
            throw new EmptyFileException("Input file or its content is empty");
        }

        return input;
    }

}