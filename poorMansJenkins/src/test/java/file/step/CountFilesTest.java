package file.step;

import org.example.file.File;
import org.example.file.step.CountFiles;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class CountFilesTest {
    @Test
    void throwsProperExceptionIfNullInput() {
        CountFiles sut = new CountFiles();

        assertThrows(IllegalArgumentException.class, () -> sut.process(null), "Expected IllegalArgumentException thrown when input is null");
    }

    @Test
    void returnedSizeEqualsSize() {
        CountFiles sut = new CountFiles();

        ArrayList<File> input = new ArrayList<>();

        input.add(new File("123"));
        input.add(new File("456"));

        assertEquals(input.size(),sut.process(input));
    }
}
