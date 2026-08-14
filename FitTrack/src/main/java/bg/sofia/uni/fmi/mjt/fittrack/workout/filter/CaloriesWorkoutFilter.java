package bg.sofia.uni.fmi.mjt.fittrack.workout.filter;

import bg.sofia.uni.fmi.mjt.fittrack.workout.Workout;

public class CaloriesWorkoutFilter implements WorkoutFilter {
    private final int min;
    private final int max;

    private void validateMinMax(int min, int max) {
        if (min > max) {
            throw new IllegalArgumentException("Min can't be greater than max!");
        }

        if (min < 0 || max < 0) {
            throw new IllegalArgumentException("Min and max can't be less than 0!");
        }
    }

    public CaloriesWorkoutFilter (int min, int max) {
        validateMinMax(min, max);
        this.min = min;
        this.max = max;
    }

    /**
     * Checks whether a given workout matches the filter's conditions.
     *
     * @param workout the workout to check.
     * @return true if the workout matches the filter, false otherwise.
     */
    @Override
    public boolean matches(Workout workout) {
        return workout.getCaloriesBurned() >= this.min && workout.getCaloriesBurned() <= this.max;
    }
}
