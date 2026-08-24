package model.entity;

import java.util.Set;

public class Candidate {
    private final String name;
    private final String email;
    private final Set<Skill> skills;
    private final Education education;
    private final int yearsOfExperience;



    public Candidate(String name, String email, Set<Skill> skills, Education education, int yearsOfExperience) {
        if (!verifyCandidate(name, email, skills, education, yearsOfExperience)) {
            throw new IllegalArgumentException("");
        }

        this.name = name;
        this.email = email;
        this.skills = skills;
        this.education = education;
        this.yearsOfExperience = yearsOfExperience;
    }

    /**
     * Verifies that all arguments passed to Candidate constructor are valid.
     *
     * @param name candidate name (not null, not blank)
     * @param email candidate email (not null, not blank)
     * @param skills candidate skills in a Set<Skill> (not null, not empty)
     * @param education candidate education (enum, [0, 5])
     * @param yearsOfExperience candidate years of experience (>0)
     * @return true if all passed arguments are valid, false if not
     */
    private boolean verifyCandidate(String name, String email, Set<Skill> skills, Education education, int yearsOfExperience) {


        return true;
    }
}
