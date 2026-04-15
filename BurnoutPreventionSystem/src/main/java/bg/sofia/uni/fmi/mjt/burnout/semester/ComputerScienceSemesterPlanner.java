package bg.sofia.uni.fmi.mjt.burnout.semester;

import bg.sofia.uni.fmi.mjt.burnout.subject.SubjectRequirement;
import bg.sofia.uni.fmi.mjt.burnout.subject.UniversitySubject;

public final class ComputerScienceSemesterPlanner extends AbstractSemesterPlanner {

    /**
     *
     * @param subjects               the array of all subjects that a student can enroll in a given semester
     * @param subjectRequirements    the array of requirements for the subjects enrolled for the category
     * @param minimalAmountOfCredits minimum amount of credits enrolled for the category
     * @return balanced subject list according to SoftwareEngineering or ComputerScience SemesterPlanner balancing requirements
     */
    @Override
    protected UniversitySubject[] balanceSubjects(UniversitySubject[] subjects, SubjectRequirement[] subjectRequirements, int minimalAmountOfCredits) {
        boolean[] selectedSubjects = new boolean[subjects.length];
        int currCredits = 0;
        int resultArrLength = 0;
        int resultIdx = 0;



        while (currCredits < minimalAmountOfCredits) {
            int highestRating = 0;
            int highestRatingSubjectIdx = -1;

            for (int i = 0; i < subjects.length; i++) {
                UniversitySubject subject = subjects[i];
                if (subject.rating() > highestRating && !selectedSubjects[i]) {
                    highestRating = subject.rating();
                    highestRatingSubjectIdx = i;
                }
            }

            if (highestRatingSubjectIdx == -1) {
                break;
            }

            selectedSubjects[highestRatingSubjectIdx] = true;
            currCredits += subjects[highestRatingSubjectIdx].credits();
            resultArrLength++;
        }

        UniversitySubject[] result = new UniversitySubject[resultArrLength];

        for (int j = 0; j < subjects.length; j++) {
            if (selectedSubjects[j]) {
                result[resultIdx] = subjects[j];
                resultIdx++;
            }
        }

        return result;
    }
}
