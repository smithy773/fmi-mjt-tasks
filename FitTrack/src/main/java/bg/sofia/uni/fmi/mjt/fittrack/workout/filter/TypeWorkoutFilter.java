package bg.sofia.uni.fmi.mjt.fittrack.workout.filter;

import bg.sofia.uni.fmi.mjt.fittrack.workout.Workout;
import bg.sofia.uni.fmi.mjt.fittrack.workout.WorkoutType;

import java.util.Arrays;
import java.util.List;

public class TypeWorkoutFilter implements WorkoutFilter {
    private final WorkoutType type;

    private void validateType(WorkoutType type) {
        if (type == null) {
            throw new IllegalArgumentException("Workout type can't be empty / null!");
        }
    }

    public TypeWorkoutFilter (WorkoutType type) {
        validateType(type);
        this.type = type;
    }

    /**
     * Checks whether a given workout matches the filter's conditions.
     *
     * @param workout the workout to check.
     * @return true if the workout matches the filter, false otherwise.
     */
    @Override
    public boolean matches(Workout workout) {
        return workout.getType() == this.type;
    }
}
