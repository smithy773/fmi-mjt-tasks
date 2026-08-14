package file.step;

import org.example.file.File;
import org.example.file.step.SplitFile;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Set;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class SplitFileTest {
    private SplitFile sut;

    @BeforeEach
    void setUp() {
        sut = new SplitFile();
    }

    @Test
    void throwsProperExceptionIfNullInput() {
        assertThrows(IllegalArgumentException.class, () -> sut.process(null), "Expected IllegalArgumentException to be thrown if input is null");
    }

    @Test
    void ensureNoDuplicatesAreInSet() {
        String strCont = "A B C A B C";

        File input = new File(strCont);

        Set<File> result = sut.process(input);

        Set<File> expected = Set.of(new File("A"), new File("B"), new File("C"));

        assertEquals(expected, result, "Expected no duplicates (ABC) when input is (A B C A B C)");
    }
}
