package model.match;

import helper.ValidationHelper;
import model.entity.Candidate;
import model.entity.JobPosting;

public class CandidateJobMatch {
    private final Candidate candidate;
    private final JobPosting jobPosting;
    private final double similarityScore;

    public CandidateJobMatch(Candidate candidate, JobPosting jobPosting, double similarityScore) {
        verifyCandidateJobMatch(candidate, jobPosting, similarityScore);

        this.candidate = candidate;
        this.jobPosting = jobPosting;
        this.similarityScore = similarityScore;
    }

    private void verifyCandidateJobMatch (Candidate candidate, JobPosting jobPosting, double similarityScore) {
        if (candidate == null) {
            throw new IllegalArgumentException("Can't create CandidateJobMatch - candidate is null!");
        }

        if (jobPosting == null) {
            throw new IllegalArgumentException("Can't create CandidateJobMatch - jobPosting is null!");
        }

        if (!ValidationHelper.similarityScore(similarityScore)) {
            throw new IllegalArgumentException("Can't create CandidateJobMatch - similarityScore is invalid!");
        }
    }
}
