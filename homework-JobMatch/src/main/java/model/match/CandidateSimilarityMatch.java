package model.match;

import helper.ValidationHelper;
import model.entity.Candidate;

public class CandidateSimilarityMatch {
    private final Candidate targetCandidate;
    private final Candidate similarCandidate;
    private final double similarityScore;

    public CandidateSimilarityMatch(Candidate targetCandidate, Candidate similarCandidate, double similarityScore) {
        verifyCandidateSimilarityMatch(targetCandidate, similarCandidate, similarityScore);

        this.targetCandidate = targetCandidate;
        this.similarCandidate = similarCandidate;
        this.similarityScore = similarityScore;
    }

    private void verifyCandidateSimilarityMatch(Candidate targetCandidate, Candidate similarCandidate, double similarityScore) {
        if (targetCandidate == null) {
            throw new IllegalArgumentException("Can't create CandidateSimilarityMatch - targetCandidate is null!");
        }

        if (similarCandidate == null) {
            throw new IllegalArgumentException("Can't create CandidateSimilarityMatch - similarCandidate is null!");
        }

        if (!ValidationHelper.similarityScore(similarityScore)) {
            throw new IllegalArgumentException("Can't create CandidateSimilarityMatch - similarityScore is invalid!");
        }
    }
}
