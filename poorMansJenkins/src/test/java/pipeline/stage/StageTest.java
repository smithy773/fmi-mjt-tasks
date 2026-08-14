package pipeline.stage;

import org.example.file.File;
import org.example.pipeline.stage.Stage;
import org.example.pipeline.step.Step;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InOrder;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class StageTest {
    private Stage<File, Integer> stage;

    @Mock
    private Step<File, File> step1;

    @Mock
    private Step<File, Integer> step2;

    @BeforeEach
    void setUp() {
        stage = Stage.start(step1).addStep(step2);
    }

    @Test
    void startThrowsProperExceptionIfNullInitialStep() {
        assertThrows(IllegalArgumentException.class,
            () -> Stage.start(null), "Expected IllegalArgumentException if initial step is null");
    }

    @Test
    void addStepThrowsProperExceptionIfNullStep() {
        assertThrows(IllegalArgumentException.class, () -> stage.addStep(null), "Expected IllegalArgumentException if added step is null");
    }

    @Test
    void executeCallsInitialAndAddedSteps() {
        File input = new File("abc");
        File processedInput = new File("ABC");

        when(step1.process(input)).thenReturn(processedInput);
        when(step2.process(processedInput)).thenReturn(3);

        stage.execute(input);

        verify(step1, times(1)).process(input);
        verify(step2, times(1)).process(processedInput);

        verifyNoMoreInteractions(step1, step2);
    }

    @Test
    void executeReturnsCorrectOutput() {
        File input = new File("abc");
        File processedInput = new File("ABC");

        when(step1.process(input)).thenReturn(processedInput);
        when(step2.process(processedInput)).thenReturn(3);

        assertEquals(3, stage.execute(input), "Expected Integer value of '3' when executing steps 1 and 2 (File input, Integer output)");
    }

    @Test
    void stepsExecutedInOrder() {
        InOrder inOrder = inOrder(step1, step2);

        File input = new File("abc");
        File processedInput = new File("ABC");

        when(step1.process(input)).thenReturn(processedInput);
        when(step2.process(processedInput)).thenReturn(3);

        stage.execute(input);

        inOrder.verify(step1).process(input);
        inOrder.verify(step2).process(processedInput);
    }
}
