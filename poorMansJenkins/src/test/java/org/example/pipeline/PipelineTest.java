package org.example.pipeline;

import org.example.file.File;
import org.example.pipeline.stage.Stage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InOrder;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class PipelineTest {
    private Cache cache;
    private Pipeline<File, File> pipeline;
    private final File file = new File("abc");
    private final File processedFile = new File("ABC");

    @Mock
    private Stage<File, File> stage1;

    @Mock
    private Stage<File, Integer> stage2;

    @BeforeEach
    void setup() {
        List<Stage<?, ?>> stages1 = new ArrayList<>();

        stages1.add(stage1);

        cache = new Cache();

        pipeline = new Pipeline<>(stages1, cache);
    }

    @Test
    void startThrowsIllArgErrWhenNull() {
        assertThrows(IllegalArgumentException.class, () -> Pipeline.start(null), "Expected IllegalArgumentException when Pipeline.start is passed null for initialStage");
    }

    @Test
    void startReturnsCorrectPipeline() {
        Pipeline<File, File> pipeline2 = Pipeline.start(stage1);

        when(stage1.execute(file)).thenReturn(processedFile);

        assertEquals(processedFile, pipeline2.execute(file), "Expected start to return a new Pipeline with a list of stage1 and an empty Cache");
    }

    @Test
    void executeCachesResultAndSkipsStagesOnSecondExecution() {
        assertFalse(cache.containsKey(file));
        pipeline.addStage(stage2);

        when(stage1.execute(file)).thenReturn(processedFile);
        when(stage2.execute(processedFile)).thenReturn(3);

        pipeline.execute(file);
        pipeline.execute(file);

        verify(stage1, times(1)).execute(file);
        verify(stage2, times(1)).execute(processedFile);

        verifyNoMoreInteractions(stage1, stage2);
    }

    @Test
    void addStageAddsCorrectStage() {
        Pipeline<File, Integer> pipeline2 = pipeline.addStage(stage2);

        when(stage1.execute(file)).thenReturn(processedFile);
        when(stage2.execute(processedFile)).thenReturn(3);

        pipeline2.execute(file);

        verify(stage2).execute(processedFile);
        assertEquals(3, pipeline2.execute(file), "Expected return of 3 when file is passed as input to a pipeline with last stage being of type <File, Integer>");
    }

    @Test
    void addStageThrowsIllArgErrIfNullStage() {
        assertThrows(IllegalArgumentException.class, () -> pipeline.addStage(null), "Expected IllegalArgumentException if null is passed to addStage");
    }

    @Test
    void executeReturnsFinalStageOutput() {
        when(stage1.execute(file)).thenReturn(processedFile);

        assertSame(processedFile, pipeline.execute(file));

        verify(stage1).execute(file);
    }

    @Test
    void executePassesOutputFromOneStageToNext() {
        Pipeline<File, Integer> pipeline1 = pipeline.addStage(stage2);

        when(stage1.execute(file)).thenReturn(processedFile);
        when(stage2.execute(processedFile)).thenReturn(3);

        pipeline1.execute(file);

        verify(stage1).execute(file);
        verify(stage2).execute(processedFile);
    }

    @Test
    void stagesExecuteInOrder() {
        Pipeline<File, Integer> pipeline1 = pipeline.addStage(stage2);

        when(stage1.execute(file)).thenReturn(processedFile);
        when(stage2.execute(processedFile)).thenReturn(3);

        pipeline1.execute(file);

        InOrder inOrder = inOrder(stage1, stage2);

        inOrder.verify(stage1).execute(file);
        inOrder.verify(stage2).execute(processedFile);
    }
}
