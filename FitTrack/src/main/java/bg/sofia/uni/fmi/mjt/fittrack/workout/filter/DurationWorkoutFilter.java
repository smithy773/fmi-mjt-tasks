package bg.sofia.uni.fmi.mjt.fittrack.workout.filter;

import bg.sofia.uni.fmi.mjt.fittrack.workout.Workout;

public class DurationWorkoutFilter implements WorkoutFilter{
    private int min;
    private int max;

    public DurationWorkoutFilter (int min, int max) {
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
        return false;
    }
}
