package bg.sofia.uni.fmi.mjt.show.elimination;

import bg.sofia.uni.fmi.mjt.show.ergenka.Ergenka;

import java.util.Objects;

public class PublicVoteEliminationRule implements EliminationRule {
    private final String[] votesCont;

    public PublicVoteEliminationRule(String[] votes) {
        votesCont = votes;
    }

    public Ergenka[] eliminateErgenkas(Ergenka[] ergenkas) {
        String ergenkaToEliminate = "";
        int counterFirstLoop = 0;
        int trueMajorityCounter = 0;
        int resultIdx = 0;

        for (String vote : votesCont) {
            if (counterFirstLoop == 0) {
                ergenkaToEliminate = vote;
                counterFirstLoop++;
            } else if (Objects.equals(ergenkaToEliminate, vote)) {
                counterFirstLoop++;
            } else {
                counterFirstLoop--;
            }
        }

        for (String vote : votesCont) {
            if (Objects.equals(vote, ergenkaToEliminate)) {
                trueMajorityCounter++;
            }
        }

        if (trueMajorityCounter > votesCont.length / 2) {
            Ergenka[] result = new Ergenka[ergenkas.length - 1];
            for (Ergenka ergenka : ergenkas) {
                if (!Objects.equals(ergenka.getName(),ergenkaToEliminate)) {
                    result[resultIdx++] = ergenka;
                };
            }
            return result;
        } else {
            return ergenkas;
        }
    }
}
