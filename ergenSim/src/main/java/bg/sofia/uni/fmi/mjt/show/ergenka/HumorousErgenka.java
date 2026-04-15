package bg.sofia.uni.fmi.mjt.show.ergenka;

import bg.sofia.uni.fmi.mjt.show.date.DateEvent;

public class HumorousErgenka implements Ergenka {
    private final String nameCont;
    private final short ageCont;
    private final int romanceLevelCont;
    private final int humorLevelCont;
    private int ratingCont;

    public HumorousErgenka(String name, short age, int romanceLevel, int humorLevel, int rating) {
        nameCont = name;
        ageCont = age;
        romanceLevelCont = romanceLevel;
        humorLevelCont = humorLevel;
        ratingCont = rating;
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
        ratingCont = ((getHumorLevel() * 5) / dateEvent.getTensionLevel()) + (getRomanceLevel() / 3);

        if (dateEvent.getDuration() < 30) {
            ratingCont -= 2;
        } else if (dateEvent.getDuration() > 90) {
            ratingCont -= 3;
        }
    }
}

