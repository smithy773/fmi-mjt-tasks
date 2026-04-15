package bg.sofia.uni.fmi.mjt.show.elimination;

import bg.sofia.uni.fmi.mjt.show.ergenka.Ergenka;

public class LowestRatingEliminationRule implements EliminationRule {
    private boolean passesRating(Ergenka e, int lowRating) {
       return e.getRating() > lowRating;
    }
    public LowestRatingEliminationRule() {}

    public Ergenka[] eliminateErgenkas(Ergenka[] ergenkas) {
        int notEliminatedCount = 0;
        int resultIdx = 0;
        int lowestRating = Integer.MAX_VALUE;

        for (Ergenka ergenka : ergenkas) {
            if (ergenka.getRating() < lowestRating) {
                lowestRating = ergenka.getRating();
            }
        }

        for (Ergenka ergenka : ergenkas) {
            if (passesRating(ergenka, lowestRating)) {
                notEliminatedCount++;
            }
        }

        Ergenka[] result = new Ergenka[notEliminatedCount];

        for (Ergenka ergenka : ergenkas) {
            if (passesRating(ergenka, lowestRating)) {
                result[resultIdx++] = ergenka;
            }
        }

        return result;
        }
}
