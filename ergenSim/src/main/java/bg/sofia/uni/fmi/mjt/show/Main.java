package bg.sofia.uni.fmi.mjt.show;

import bg.sofia.uni.fmi.mjt.show.date.DateEvent;
import bg.sofia.uni.fmi.mjt.show.elimination.EliminationRule;
import bg.sofia.uni.fmi.mjt.show.elimination.LowAttributeSumEliminationRule;
import bg.sofia.uni.fmi.mjt.show.elimination.LowestRatingEliminationRule;
import bg.sofia.uni.fmi.mjt.show.elimination.PublicVoteEliminationRule;
import bg.sofia.uni.fmi.mjt.show.ergenka.Ergenka;
import bg.sofia.uni.fmi.mjt.show.ergenka.HumorousErgenka;
import bg.sofia.uni.fmi.mjt.show.ergenka.RomanticErgenka;

import java.util.Arrays;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    static void main() {
        RomanticErgenka veronika = new RomanticErgenka("Veronika", (short) 25, 8, 4, 8, "Fakulteta");
        RomanticErgenka marcheto = new RomanticErgenka("Marcheto", (short) 32, 10, 10, 10, "Kopitoto");
        HumorousErgenka simona = new HumorousErgenka("Simona", (short) 20, 2, 7, 7);

        LowestRatingEliminationRule lowestRating = new LowestRatingEliminationRule();
        LowAttributeSumEliminationRule lowAttribute = new LowAttributeSumEliminationRule(10);
        PublicVoteEliminationRule publicVote = new PublicVoteEliminationRule(new String[]{"Veronika", "Veronika", "Veronika", "Simona", "Marcheto"});

        EliminationRule[] eliminationRules = {lowestRating, lowAttribute, publicVote};

        ShowAPIImpl showAPI = new ShowAPIImpl(new Ergenka[]{veronika, marcheto, simona}, eliminationRules);

        DateEvent date = new DateEvent("Kopitoto", 4, 60);

        showAPI.playRound(date);

        Ergenka[] ergenkasRemaining = showAPI.getErgenkas();

        for (Ergenka ergenka : ergenkasRemaining) {
            System.out.println(ergenka.getName());
        }
    }
}
