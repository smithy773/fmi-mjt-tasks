package bg.sofia.uni.fmi.mjt.fittrack.workout.filter;

import bg.sofia.uni.fmi.mjt.fittrack.workout.Workout;

public class NameWorkoutFilter implements WorkoutFilter {
    private String keyword;
    private boolean caseSensitive;

    public NameWorkoutFilter(String keyword, boolean caseSensitive) {
        this.keyword = keyword;
        this.caseSensitive = caseSensitive;
    }

    /**
     * Checks whether a given workout matches the filter's conditions.
     *
     * @param workout the workout to check.
     * @return true if the workout matches the filter, false otherwise.
     */
    @Override
    public boolean matches(Workout workout) {
        return false;
    }
}
