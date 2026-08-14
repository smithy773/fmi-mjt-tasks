package file.step;

import org.example.file.File;
import org.example.file.step.PrintFiles;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class PrintFilesTest {
    private PrintFiles sut;

    @BeforeEach
    void setUp() {
        sut = new PrintFiles();
    }

    @Test
    void throwsProperExceptionIfNullInput() {
        assertThrows(IllegalArgumentException.class, () -> sut.process(null), "Expected IllegalArgumentException to be thrown if input collection is null");
    }

    @Test
    void printsAllFiles() {
        List<File> input = List.of(new File("123"));

        PrintStream original = System.out;

        ByteArrayOutputStream output = new ByteArrayOutputStream();

        try {
            System.setOut(new PrintStream(output));

            sut.process(input);

            String printed = output.toString();

            assertEquals("123" + System.lineSeparator(), printed);
        } finally {
            System.setOut(original);
        }
    }

    @Test
    void returnsUnchangedInput() {
        ArrayList<File> input = new ArrayList<>();

        input.add(new File("123"));

        assertSame(input, sut.process(input));
    }
}
