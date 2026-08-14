package bg.sofia.uni.fmi.mjt.fittrack.workout.filter;

import bg.sofia.uni.fmi.mjt.fittrack.workout.Workout;

public class NameWorkoutFilter implements WorkoutFilter {
    private final String keyword;
    private final boolean caseSensitive;

    private void validateKeyword(String keyword) {
        if (keyword == null || keyword.isBlank()) {
            throw new IllegalArgumentException("Filter keyword can't be empty / null!");
        }
    }

    public NameWorkoutFilter(String keyword, boolean caseSensitive) {
        validateKeyword(keyword);
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
        if (caseSensitive) {
            return workout.getName().contains(keyword);
        } else {
            return workout.getName().toUpperCase().contains(keyword.toUpperCase());
        }
    }
}
