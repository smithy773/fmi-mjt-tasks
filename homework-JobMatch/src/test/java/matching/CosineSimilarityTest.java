package matching;

import model.entity.Skill;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class CosineSimilarityTest {
    private final SimilarityStrategy cosine = new CosineSimilarity();

    @Test
    void calculateSimilarityThrowsIllArgExcWhenEitherParameterNull() {
        Skill javaSkill = new Skill("Java", 1);

        assertThrows(IllegalArgumentException.class, () -> cosine.calculateSimilarity(null, Set.of(javaSkill)), "Expected IllegalArgumentException when candidateSkills is null!");

        assertThrows(IllegalArgumentException.class, () -> cosine.calculateSimilarity(Set.of(javaSkill), null), "Expected IllegalArgumentException when jobSkills is null!");
    }

    @Test
    void calculateSimilarityReturns0IfAnyVector0() {
        Set<Skill> candidateSkills = Set.of(new Skill("JavaScript", 1), new Skill("ReactJS", 2));

        Set<Skill> jobSkills = Set.of(new Skill("Java", 1), new Skill("Python", 2));


    }

    @Test
    void calculateSimilarityReturnsCorrectValue() {

    }
}
