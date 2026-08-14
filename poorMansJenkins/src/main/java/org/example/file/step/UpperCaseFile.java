package org.example.file.step;

import org.example.file.File;
import org.example.pipeline.step.Step;

/**
 * A pipeline step that transforms the content of a {@link File} to uppercase.
 * This step modifies the {@link File} in place by converting its content to uppercase.
 */
public class UpperCaseFile implements Step<File, File> {

    /**
     * Converts the content of the given {@link File} to uppercase.
     *
     * @param input the input data to process
     * @return a new {@link File} object with updated content
     *
     * @throws IllegalArgumentException if the input file or its content is null
     */
    @Override
    public File process(File input) {
        if (input == null) {
            throw new IllegalArgumentException("Input file is null");
        }

        return new File(input.getContent().toUpperCase());
    }
}
