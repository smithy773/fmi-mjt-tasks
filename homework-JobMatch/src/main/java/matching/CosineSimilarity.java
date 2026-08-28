package matching;

import model.entity.Skill;

import java.util.*;

public class CosineSimilarity implements SimilarityStrategy {
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
            throw new IllegalArgumentException("Can't calculate similarity - candidateSkills are null!");
        }

        if (jobSkills == null) {
            throw new IllegalArgumentException("Can't calculate similarity - jobSkills are null!");
        }

        Map<String, Integer> candidateSkillMap = new HashMap<>();
        Map<String, Integer> jobSkillMap = new HashMap<>();

        for (Skill candidateSkill : candidateSkills) {
            candidateSkillMap.put(candidateSkill.name(), candidateSkill.level());
        }

        for (Skill jobSkill : jobSkills) {
            jobSkillMap.put(jobSkill.name(), jobSkill.level());
        }

        HashSet<String> skillUnion = new HashSet<>(jobSkillMap.keySet());
        skillUnion.addAll(candidateSkillMap.keySet());

        List<String> sortedUnion = skillUnion.stream().sorted(String::compareTo).toList();

        int dotProduct = 0;
        int ApowSum = 0;
        int BpowSum = 0;

        for (String currSkill : sortedUnion) {
            int candidateSkillLevel = 0;
            int jobSkillLevel = 0;

            if (candidateSkillMap.containsKey(currSkill)) {
                candidateSkillLevel = candidateSkillMap.get(currSkill);
            }

            if (jobSkillMap.containsKey(currSkill)) {
                jobSkillLevel = jobSkillMap.get(currSkill);
            }

            dotProduct += candidateSkillLevel * jobSkillLevel;

            ApowSum += candidateSkillLevel * candidateSkillLevel;
            BpowSum += jobSkillLevel * jobSkillLevel;
        }

        double A = Math.sqrt(ApowSum);
        double B = Math.sqrt(BpowSum);

        if (A == 0 || B == 0) {
            return 0.0;
        }

        return dotProduct / (A * B);
    }
}
