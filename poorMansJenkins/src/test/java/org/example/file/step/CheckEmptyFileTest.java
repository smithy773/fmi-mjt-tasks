package org.example.file.step;

import org.example.file.File;
import org.example.file.exception.EmptyFileException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class CheckEmptyFileTest {

    @Test
    void emptyFileThrowsEmptyFileException() {
        File input = new File("");

        CheckEmptyFile checker = new CheckEmptyFile();

        assertThrows(EmptyFileException.class, () -> checker.process(input), "Expected EmptyFileException to be thrown when input content is empty");
    }

    @Test
    void nullFileThrowsEmptyFileException() {
        CheckEmptyFile checker = new CheckEmptyFile();

        assertThrows(EmptyFileException.class, () -> checker.process(null), "Expected EmptyFileException to be thrown when input is null");

    }

    @Test
    void processReturnsSameFile() {
        File input = new File("abc");

        CheckEmptyFile checker = new CheckEmptyFile();

        assertSame(input, checker.process(input), "Expected CheckEmptyFile's process method to return the same input file");
    }
}
