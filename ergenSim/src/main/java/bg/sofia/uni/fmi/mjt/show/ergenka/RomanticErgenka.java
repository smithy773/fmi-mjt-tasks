package bg.sofia.uni.fmi.mjt.show.ergenka;

import bg.sofia.uni.fmi.mjt.show.date.DateEvent;

import java.util.Objects;

public class RomanticErgenka implements Ergenka {
    private static final int ROMANCE_LVL_RATE = 7;


    private final String nameCont;
    private final short ageCont;
    private final int romanceLevelCont;
    private final int humorLevelCont;
    private int ratingCont;
    private final String favoriteDateLocationCont;

    public RomanticErgenka(String name, short age, int romanceLevel, int humorLevel, int rating, String favoriteDateLocation) {
        nameCont = name;
        ageCont = age;
        romanceLevelCont = romanceLevel;
        humorLevelCont = humorLevel;
        ratingCont = rating;
        favoriteDateLocationCont = favoriteDateLocation;
    }

    public String getName() {
        return nameCont;
    }

    public short getAge() {
        return ageCont;
    }

    public int getRomanceLevel() {
        return romanceLevelCont;
    }

    public int getHumorLevel() {
        return humorLevelCont;
    }

    public int getRating() {
        return ratingCont;
    }

    public void reactToDate(DateEvent dateEvent) {
        ratingCont = ((getRomanceLevel() * ROMANCE_LVL_RATE) / dateEvent.getTensionLevel()) + (getHumorLevel() / 3);

        if (Objects.equals(dateEvent.getLocation(), favoriteDateLocationCont)) {
            ratingCont += 5;
        }

        if (dateEvent.getDuration() < 30) {
            ratingCont -= 3;
        } else if (dateEvent.getDuration() > 120) {
            ratingCont -= 2;
        }
    }


}
