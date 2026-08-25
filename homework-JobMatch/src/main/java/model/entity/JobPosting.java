package model.entity;

import helper.ValidationHelper;

import java.util.Set;

public class JobPosting {
    private final String id;
    private final String title;
    private final String employerEmail;
    private final Set<Skill> requiredSkills;
    private final Education requiredEducation;
    private final int requiredYearsOfExperience;
    private final double salary;

    public JobPosting(String id, String title, String employerEmail, Set<Skill> requiredSkills, Education requiredEducation, int requiredYearsOfExperience, double salary) {
        verifyJobPosting(id, title, employerEmail, requiredSkills, requiredEducation, requiredYearsOfExperience, salary);

        this.id = id;
        this.title = title;
        this.employerEmail = employerEmail;
        this.requiredSkills = requiredSkills;
        this.requiredEducation = requiredEducation;
        this.requiredYearsOfExperience = requiredYearsOfExperience;
        this.salary = salary;
    }

    private void verifyJobPosting(String id, String title, String employerEmail, Set<Skill> requiredSkills, Education requiredEducation, int requiredYearsOfExperience, double salary) {
        if (!ValidationHelper.string(id)) {
            throw new IllegalArgumentException("Can't create JobPosting - invalid id");
        }

        if (!ValidationHelper.string(title)) {
            throw new IllegalArgumentException("Can't create JobPosting - invalid title");
        }

        if (!ValidationHelper.string(employerEmail)) {
            throw new IllegalArgumentException("Can't create JobPosting - invalid employerEmail");
        }

        if (!ValidationHelper.set(requiredSkills)) {
            throw new IllegalArgumentException("Can't create JobPosting - invalid requiredSkills");
        }

        if (!(requiredEducation == null)) {
            throw new IllegalArgumentException("Can't create JobPosting - invalid requiredEducation");
        }

        if (!ValidationHelper.num(requiredYearsOfExperience)) {
            throw new IllegalArgumentException("Can't create JobPosting - invalid requiredYearsOfExperience");
        }

        if (!ValidationHelper.num(salary)) {
            throw new IllegalArgumentException("Can't create JobPosting - invalid salary");
        }
    }


}
