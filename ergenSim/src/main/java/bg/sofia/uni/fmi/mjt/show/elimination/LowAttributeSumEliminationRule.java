package bg.sofia.uni.fmi.mjt.show.elimination;

import bg.sofia.uni.fmi.mjt.show.ergenka.Ergenka;

public class LowAttributeSumEliminationRule implements EliminationRule {
    private final int thresholdCont;
    private boolean passesThreshold(Ergenka e) {
        return e.getHumorLevel() + e.getRomanceLevel() >= thresholdCont;
    }

    public LowAttributeSumEliminationRule(int threshold) {
        thresholdCont = threshold;
    }

    public Ergenka[] eliminateErgenkas(Ergenka[] ergenkas) {
        int notEliminatedCount = 0;
        int resultIdx = 0;

        for (Ergenka ergenka : ergenkas) {
            if (passesThreshold(ergenka)) {
                notEliminatedCount++;
            }
        }

        Ergenka[] result = new Ergenka[notEliminatedCount];

        for (Ergenka ergenka : ergenkas) {
            if (passesThreshold(ergenka)) {
                result[resultIdx++] = ergenka;
            }
        }

        return result;
    }
}
