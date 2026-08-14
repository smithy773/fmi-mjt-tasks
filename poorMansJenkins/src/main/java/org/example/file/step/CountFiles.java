package org.example.file.step;

import org.example.file.File;
import org.example.pipeline.step.Step;
import java.util.Collection;

/**
 * A pipeline step that counts the number of {@link File} objects in a collection.
 */
public class CountFiles implements Step<Collection<File>, Integer> {
    /**
     * Returns the number or {@link File} objects in the input collection.
     *
     * @param input the collection of files to count;
     * @return the number of files in the collection
     *
     * @throws IllegalArgumentException if the input collection is null
     */
    @Override
    public Integer process(Collection<File> input) {
        if (input == null) {
            throw new IllegalArgumentException("Input collection cannot be null");
        }
        return input.size();
    }
}
