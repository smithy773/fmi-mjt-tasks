package org.example.file.step;

import org.example.file.File;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class UpperCaseFileTest {
    private UpperCaseFile sut;

    @BeforeEach
    void setUp() {
        sut = new UpperCaseFile();
    }

    @Test
    void throwsProperExceptionIfNullInput() {
        assertThrows(IllegalArgumentException.class, () -> sut.process(null), "Expected IllegalArgumentException when input file is null");
    }

    @Test
    void isUpdatedContentUppercase() {
        assertEquals("ABC", sut.process(new File("abc")).getContent());
    }

    @Test
    void returnsNewFile() {
        File input = new File("abc");

        assertNotSame(input, sut.process(input));
    }
}