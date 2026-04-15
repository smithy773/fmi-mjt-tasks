package bg.sofia.uni.fmi.mjt.burnout.semester;

import bg.sofia.uni.fmi.mjt.burnout.subject.Category;
import bg.sofia.uni.fmi.mjt.burnout.subject.SubjectRequirement;
import bg.sofia.uni.fmi.mjt.burnout.subject.UniversitySubject;

import java.util.Arrays;

public final class SoftwareEngineeringSemesterPlanner extends AbstractSemesterPlanner {
    /**
     *
     * @param subjects               the array of all subjects that a student can enroll in a given semester
     * @param subjectRequirements    the array of requirements for the subjects enrolled for the category
     * @param minimalAmountOfCredits minimum amount of credits enrolled for the category
     * @return balanced subject list with the least amount of subjects per requirements
     */
    @Override
    protected UniversitySubject[] balanceSubjects(UniversitySubject[] subjects, SubjectRequirement[] subjectRequirements, int minimalAmountOfCredits) {
          boolean[] selectedSubjects = new boolean[subjects.length];
          int currCredits = 0;
          int resultArrLength = 0;
          int resultIdx = 0;

          for (SubjectRequirement subjReq : subjectRequirements) {
              Category category = subjReq.category();
              int minAmountEnrolled = subjReq.minAmountEnrolled();

              for (int i = 0; i < minAmountEnrolled; i++) {
                  int highestCredits = 0;
                  int highestCreditSubjectIdx = -1;

                  for (int j = 0; j < subjects.length; j++) {
                      UniversitySubject subject = subjects[j];
                      if (subject.category() == category && subject.credits() > highestCredits && !selectedSubjects[j]) {
                          highestCredits = subject.credits();
                          highestCreditSubjectIdx = j;
                      }
                  }

                  if (highestCreditSubjectIdx == -1) {
                      throw new RuntimeException("University subjects do not contain subjects with required categories as per subject requirements!");
                  }

                  selectedSubjects[highestCreditSubjectIdx] = true;
                  currCredits += subjects[highestCreditSubjectIdx].credits();
                  resultArrLength++;
              }
          }

          while (currCredits < minimalAmountOfCredits) {
              int highestCredits = 0;
              int highestCreditSubjectIdx = -1;

              for (int i = 0; i < subjects.length; i++) {
                  UniversitySubject subject = subjects[i];
                  if (subject.credits() > highestCredits && !selectedSubjects[i]) {
                      highestCredits = subject.credits();
                      highestCreditSubjectIdx = i;
                  }
              }

              if (highestCreditSubjectIdx == -1) {
                  break;
              }

              selectedSubjects[highestCreditSubjectIdx] = true;
              currCredits += subjects[highestCreditSubjectIdx].credits();
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
