package model.entity;

import helper.ValidationHelper;

import java.util.Set;

public class Candidate {
    private final String name;
    private final String email;
    private final Set<Skill> skills;
    private final Education education;
    private final int yearsOfExperience;

    public Candidate(String name, String email, Set<Skill> skills, Education education, int yearsOfExperience) {
        verifyCandidate(name, email, skills, education, yearsOfExperience);

        this.name = name;
        this.email = email;
        this.skills = skills;
        this.education = education;
        this.yearsOfExperience = yearsOfExperience;
    }

    /**
     * Verifies that all arguments passed to Candidate constructor are valid. Uses ValidationHelper helper class.
     *
     * @param name candidate name (not null, not blank)
     * @param email candidate email (not null, not blank)
     * @param skills candidate skills in a Set<Skill> (not null, not empty)
     * @param education candidate education (Education enum)
     * @param yearsOfExperience candidate years of experience (>0)
     * @throws IllegalArgumentException if any one of the passed arguments is invalid
     */
    private void verifyCandidate(String name, String email, Set<Skill> skills, Education education, int yearsOfExperience) {
        if (!ValidationHelper.string(name)) {
            throw new IllegalArgumentException("Can't create candidate " + name + " - invalid name");
        }

        if (!ValidationHelper.string(email)) {
            throw new IllegalArgumentException("Can't create candidate with email: " + email + " - invalid email");
        }

        if (!ValidationHelper.set(skills)) {
            throw new IllegalArgumentException("Can't create candidate - " + skills + " - invalid skills");
        }

        if (!ValidationHelper.num(yearsOfExperience)) {
            throw new IllegalArgumentException("Can't create candidate - " + yearsOfExperience + " are invalid yearsOfExperience");
        }

        if (education == null) {
            throw new IllegalArgumentException("Can't create candidate - " + education + " is invalid education");
        }
    }

    public String getEmail() {
        return this.email;
    }
}
