package bg.sofia.uni.fmi.mjt.burnout.semester;

import bg.sofia.uni.fmi.mjt.burnout.exception.CryToStudentsDepartmentException;
import bg.sofia.uni.fmi.mjt.burnout.exception.InvalidSubjectRequirementsException;
import bg.sofia.uni.fmi.mjt.burnout.exception.DisappointmentException;
import bg.sofia.uni.fmi.mjt.burnout.plan.SemesterPlan;
import bg.sofia.uni.fmi.mjt.burnout.subject.Category;
import bg.sofia.uni.fmi.mjt.burnout.subject.SubjectRequirement;
import bg.sofia.uni.fmi.mjt.burnout.subject.UniversitySubject;

import java.util.Objects;

public abstract sealed class AbstractSemesterPlanner implements SemesterPlannerAPI permits ComputerScienceSemesterPlanner, SoftwareEngineeringSemesterPlanner {
    /**
     *
     * @param subjects the array of all subjects that a student can enroll in a given semester
     * @param subjectRequirements the array of requirements for the subjects enrolled for the category
     * @param minimalAmountOfCredits minimum amount of credits enrolled for the category
     * @return balanced subject list according to SoftwareEngineering or ComputerScience SemesterPlanner balancing requirements
     */
    protected abstract UniversitySubject[] balanceSubjects(UniversitySubject[] subjects, SubjectRequirement[] subjectRequirements, int minimalAmountOfCredits);

    /**
     * Validates the semester plan isn't missing and doesn't contain duplicates
     *
     * @param semesterPlan the current semester plan
     */
    protected void validatePlan(SemesterPlan semesterPlan) {
        if (semesterPlan == null) {
            throw new IllegalArgumentException();
        }

        SubjectRequirement[] requirements = semesterPlan.subjectRequirements();

        for (int i = 0; i < requirements.length; i++) {
            for (int j = i + 1; j < requirements.length; j++) {
                if (requirements[i].category() == requirements[j].category()) {
                    throw new InvalidSubjectRequirementsException("You can't have duplicate categories for your subjects!");
                }
            }
        }
    }

    /**
     * Calculates the subject combination for this semester type based on the subjectRequirements.
     *
     * @param semesterPlan the current semester plan needed for the calculation
     * @return the subject list that balances credits, study time, and requirements
     * @throws CryToStudentsDepartmentException    when a student cannot cover his semester credits.
     * @throws IllegalArgumentException            if the semesterPlan is missing or is null
     * @throws InvalidSubjectRequirementsException if the subjectRequirements contain duplicate categories
     */
    public UniversitySubject[] calculateSubjectList(SemesterPlan semesterPlan) throws InvalidSubjectRequirementsException {
        validatePlan(semesterPlan);

        return balanceSubjects(semesterPlan.subjects(),semesterPlan.subjectRequirements(),semesterPlan.minimalAmountOfCredits());
    }

    /**
     * Calculates the amount of jars grandma will send you
     *
     * @param subjects         the subjects to calculate jar count for
     * @param maximumSlackTime the rest days grandma gave as limit before stopping the jar food deliveries
     * @param semesterDuration the duration of the semester in days
     * @return the number of jars grandma sends that are needed for survival
     * @throws IllegalArgumentException if the subjects are missing or null, or maximumSlackTime/semesterDuration are not positive integers
     * @throws DisappointmentException  if you cannot make grandma happy.
     */
    public int calculateJarCount(UniversitySubject[] subjects, int maximumSlackTime, int semesterDuration) {
        if (subjects == null || subjects.length == 0 || maximumSlackTime <= 0 || semesterDuration <= 0) {
            throw new IllegalArgumentException("subjects, maximumSlackTime and semesterDuration can't be null or a non-positive number!");
        }

        int restDays = 0;
        int studyDays = 0;

        for (UniversitySubject subject : subjects) {
            double coefficient = 0;

            switch (subject.category()) {
                case MATH -> coefficient = 0.2;
                case PROGRAMMING -> coefficient = 0.1;
                case THEORY -> coefficient = 0.15;
                case PRACTICAL -> coefficient = 0.05;
            }

            restDays += (int) Math.ceil((subject.neededStudyTime() * coefficient));
            studyDays += subject.neededStudyTime();
        }

        if (restDays > maximumSlackTime) {
            throw new DisappointmentException("You disappointed grandma... Stop slacking and get to work!");
        }

        int jars = studyDays / 5;

        if (studyDays + restDays > semesterDuration) {
            return jars * 2;
        } else {
            return jars;
        }
    }
}


