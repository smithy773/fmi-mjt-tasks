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

        List<Integer> candidateSkillVector = new ArrayList<>();
        List<Integer> jobSkillVector = new ArrayList<>();

        for (int i = 0; i < sortedUnion.size(); i++) {
            String currSkill = sortedUnion.get(i);
            int candidateSkillVal = 0;
            int jobSkillVal = 0;

            if (candidateSkillMap.containsKey(currSkill)) {
                candidateSkillVal = candidateSkillMap.get(currSkill);
            }

            if (jobSkillMap.containsKey(currSkill)) {
                jobSkillVal = jobSkillMap.get(currSkill);
            }

            candidateSkillVector.add(candidateSkillVal);
            jobSkillVector.add(jobSkillVal);
        }

        int dotProduct = 0;
        int ApowSum = 0;
        int BpowSum = 0;

        for (int i = 0; i < sortedUnion.size(); i++) {
            int candidateLevel = candidateSkillVector.get(i);
            int jobLevel = jobSkillVector.get(i);

            dotProduct += (candidateLevel * jobLevel);

            ApowSum += (int) Math.pow(candidateLevel, 2);
            BpowSum += (int) Math.pow(jobLevel, 2);
        }

        double A = Math.sqrt(ApowSum);
        double B = Math.sqrt(BpowSum);

        if (A == 0 && B == 0) {
            return 0;
        }

        return dotProduct / (A * B);
    }
}
