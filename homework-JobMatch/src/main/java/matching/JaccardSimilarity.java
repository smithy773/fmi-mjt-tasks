package matching;

import model.entity.Skill;

import java.util.HashSet;
import java.util.Set;

public class JaccardSimilarity implements SimilarityStrategy {
    /**
     * Calculates similarity score between two skill sets.
     *
     * @param candidateSkills The skills possessed by a candidate
     * @param jobSkills       The skills required by a job
     * @return Similarity score in range [0, 1], where 1 means perfect match and 0 means no match
     * @throws IllegalArgumentException if either parameter is null
     */
    @Override
    public double calculateSimilarity(Set<Skill> candidateSkills, Set<Skill> jobSkills) {
        if (candidateSkills == null) {
            throw new IllegalArgumentException("Can't calculate similarity score - candidateSkills is null!");
        }

        if (jobSkills == null) {
            throw new IllegalArgumentException("Can't calculate similarity score - jobSkills is null!");
        }

        HashSet<String> jobSkillNames = new HashSet<>();
        HashSet<String> candidateSkillNames = new HashSet<>();

        for (Skill jobSkill : jobSkills) {
            jobSkillNames.add(jobSkill.name());
        }

        for (Skill candidateSkill : candidateSkills) {
            candidateSkillNames.add(candidateSkill.name());
        }

        Set<String> skillIntersection = new HashSet<>(jobSkillNames);

        skillIntersection.retainAll(candidateSkillNames);

        Set<String> skillUnion = new HashSet<>(jobSkillNames);

        skillUnion.addAll(candidateSkillNames);

        int intersectionVal = skillIntersection.size();
        int unionVal = skillUnion.size();;

        if (unionVal == 0) {
            return 0.0;
        }

        return (double) intersectionVal / unionVal;
    }
}
