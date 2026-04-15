package bg.sofia.uni.fmi.mjt.show;

import bg.sofia.uni.fmi.mjt.show.date.DateEvent;
import bg.sofia.uni.fmi.mjt.show.elimination.EliminationRule;
import bg.sofia.uni.fmi.mjt.show.ergenka.Ergenka;

public class ShowAPIImpl implements ShowAPI {
    private Ergenka[] ergenkasCont;
    private final EliminationRule[] eliminationRules;

    public ShowAPIImpl(Ergenka[] ergenkas, EliminationRule[] defaultEliminationRules) {
        ergenkasCont = ergenkas;
        eliminationRules = defaultEliminationRules;
    }

    public Ergenka[] getErgenkas() {
        return ergenkasCont;
    }

    public void playRound(DateEvent dateEvent) {
        for (Ergenka ergenka : ergenkasCont) {
            organizeDate(ergenka, dateEvent);
        }

        eliminateErgenkas(eliminationRules);
    }

    public void eliminateErgenkas(EliminationRule[] eliminationRules) {
        for (EliminationRule rule : eliminationRules) {
            ergenkasCont = rule.eliminateErgenkas(ergenkasCont);
        }
    }

    public void organizeDate(Ergenka ergenka, DateEvent dateEvent) {
        ergenka.reactToDate(dateEvent);
    }
}
