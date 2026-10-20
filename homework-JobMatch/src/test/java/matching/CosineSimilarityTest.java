package matching;

import model.entity.Skill;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

public class CosineSimilarityTest {
    private final SimilarityStrategy cosine = new CosineSimilarity();

    @Test
    void calculateSimilarityThrowsIllArgExcWhenEitherParameterNull() {
        Skill javaSkill = new Skill("Java", 1);

        assertThrows(IllegalArgumentException.class, () -> cosine.calculateSimilarity(null, Set.of(javaSkill)), "Expected IllegalArgumentException when candidateSkills is null!");

        assertThrows(IllegalArgumentException.class, () -> cosine.calculateSimilarity(Set.of(javaSkill), null), "Expected IllegalArgumentException when jobSkills is null!");
    }

    @Test
    void calculateSimilarityReturns0IfParameterSkillSetEmpty() {
        Set<Skill> candidateSkills = Set.of(new Skill("JavaScript", 1), new Skill("ReactJS", 2));

        Set<Skill> jobSkills = Set.of(new Skill("Java", 1), new Skill("Python", 2));

        assertEquals(0.0, cosine.calculateSimilarity(candidateSkills, Set.of()), "Expected calculateSimilarity to return 0 when jobSkills is empty");

        assertEquals(0.0, cosine.calculateSimilarity(Set.of(), jobSkills), "Expected calculateSimilarity to return 0 when candidateSkills is empty");

        assertEquals(0.0, cosine.calculateSimilarity(Set.of(), Set.of()), "Expected calculateSimilarity to return 0 when both parameters are empty");
    }

    @Test
    void calculateSimilarityCosineUsingSkillLevel() {
        Set<Skill> candidateSkills = Set.of(new Skill("Java", 1), new Skill("ReactJS", 3));

        Set<Skill> candidateSkillsEqual = Set.of(new Skill("Java", 5), new Skill("ReactJS", 2));

        Set<Skill> jobSkills = Set.of(new Skill("Java", 5), new Skill("ReactJS", 2));

        assertNotEquals(1.0, cosine.calculateSimilarity(candidateSkills, jobSkills), "Expected not 1 when candidateSkills match jobSkills in name, as level is different (due to Cosine logic)");

        assertEquals(1.0, cosine.calculateSimilarity(candidateSkillsEqual, jobSkills), "Expected  1 when candidateSkills match jobSkills in name and level (due to Cosine logic)");
    }

    @Test
    void calculateSimilarityReturnsCorrectSimilarityScore() {
        Set<Skill> candidateSkills = Set.of(new Skill("Java", 1), new Skill("ReactJS", 2));

        Set<Skill> candidateSkillsNoIntersect = Set.of(new Skill("JavaScript", 1), new Skill("ReactJS", 2));

        Set<Skill> jobSkills = Set.of(new Skill("Java", 1), new Skill("Python", 2));

        Set<Skill> jobSkillsNoIntersect = Set.of(new Skill("Java", 1), new Skill("Python", 2));

        assertEquals(Math.round((1 / (Math.sqrt(5) * Math.sqrt(5))) * 100.00) / 100.00, cosine.calculateSimilarity(candidateSkills, jobSkills), "Expected 0.2 (rounded) when using candidateSkills and jobSkills as provided");

        assertEquals(0.0, cosine.calculateSimilarity(candidateSkillsNoIntersect, jobSkillsNoIntersect), "Expected 0.0 if no skills intersect is 0");
    }
}
