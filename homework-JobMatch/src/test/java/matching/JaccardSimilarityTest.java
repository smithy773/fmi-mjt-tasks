package matching;

import model.entity.Skill;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

public class JaccardSimilarityTest {
    private final SimilarityStrategy jaccard = new JaccardSimilarity();

    @Test
    void calculateSimilarityThrowsIllArgExcWhenEitherParameterNull() {
        Skill javaSkill = new Skill("Java", 1);

        assertThrows(IllegalArgumentException.class, () -> jaccard.calculateSimilarity(null, Set.of(javaSkill)), "Expected IllegalArgumentException when candidateSkills is null!");

        assertThrows(IllegalArgumentException.class, () -> jaccard.calculateSimilarity(Set.of(javaSkill), null), "Expected IllegalArgumentException when jobSkills is null!");
    }

    @Test
    void calculateSimilarityReturns0IfUnionVal0() {
        Set<Skill> candidateSkills = Set.of();

        Set<Skill> jobSkills = Set.of();

        assertEquals(0.0, jaccard.calculateSimilarity(candidateSkills, jobSkills), "Expected 0.0 if UnionVal is 0 (meaning both skill sets are empty)");
    }

    @Test
    void calculateSimilarityReturnsCorrectSimilarityScoreIfIntersectionVal0() {
        Set<Skill> candidateSkills = Set.of(new Skill("JavaScript", 1), new Skill("ReactJS", 2));

        Set<Skill> jobSkills = Set.of(new Skill("Java", 1), new Skill("Python", 2));

        assertEquals(0.0, jaccard.calculateSimilarity(candidateSkills, jobSkills), "Expected 0.0 if intersectionVal is 0");
    }

    @Test
    void calculateSimilarityJaccardNotUsingSkillLevel() {
        Set<Skill> candidateSkills = Set.of(new Skill("Java", 1));

        Set<Skill> jobSkills = Set.of(new Skill("Java", 5));

        assertEquals(1.0, jaccard.calculateSimilarity(candidateSkills, jobSkills), "Expected 1.0 when candidateSkills match jobSkills perfectly, even if level is different (due to Jaccard logic)");
    }

    @Test
    void calculateSimilarityReturnsCorrectSimilarityScore() {
        Set<Skill> candidateSkills = Set.of(new Skill("Java", 1), new Skill("ReactJS", 2));

        Set<Skill> jobSkills = Set.of(new Skill("Java", 1), new Skill("Python", 2));

        assertEquals((double) 1 / 3, jaccard.calculateSimilarity(candidateSkills, jobSkills), "Expected 0.33 (rounded) when intersectionVal is 1 and unionVal is 3");
    }
}