package bg.sofia.uni.fmi.mjt.fittrack.workout;

import bg.sofia.uni.fmi.mjt.fittrack.exception.InvalidWorkoutException;

import java.util.Objects;

public final class StrengthWorkout implements Workout {
    private static final WorkoutType WORKOUT_TYPE;

    private static final int MIN_DIFFICULTY = 1;
    private static final int MAX_DIFFICULTY = 5;

    private final String name;
    private final int duration;
    private final int caloriesBurned;
    private final int difficulty;

    static {
        WORKOUT_TYPE = WorkoutType.STRENGTH;
    }

    private static void validateParams(String name, int duration, int caloriesBurned, int difficulty) {
        if (name == null || name.isBlank()) {
            throw new InvalidWorkoutException("Workout name can't be empty");
        }

        if (duration <= 0) {
            throw new InvalidWorkoutException("Workout duration must be a positive number (>0)");
        }

        if (caloriesBurned <= 0) {
            throw new InvalidWorkoutException("Calories burned must be a positive number (>0)");
        }

        if (difficulty < MIN_DIFFICULTY || difficulty > MAX_DIFFICULTY) {
            throw new InvalidWorkoutException("Workout difficulty can't be less than 1 or higher than 5!");
        }
    }

    public StrengthWorkout(String name, int duration, int caloriesBurned, int difficulty) {
        validateParams(name, duration, caloriesBurned, difficulty);
        this.name = name;
        this.duration = duration;
        this.caloriesBurned = caloriesBurned;
        this.difficulty = difficulty;
    }
    /**
     * Returns the name of the workout.
     *
     * @return the workout name.
     */
    @Override
    public String getName() {
        return this.name;
    }

    /**
     * Returns the duration of the workout in minutes.
     *
     * @return the duration in minutes.
     */
    @Override
    public int getDuration() {
        return this.duration;
    }

    /**
     * Returns the number of calories burned by performing the workout.
     *
     * @return the calories burned.
     */
    @Override
    public int getCaloriesBurned() {
        return this.caloriesBurned;
    }

    /**
     * Returns the difficulty of the workout (1 - easy, 5 - very hard).
     *
     * @return the difficulty.
     */
    @Override
    public int getDifficulty() {
        return this.difficulty;
    }

    /**
     * Returns the type of the workout.
     *
     * @return the workout type.
     */
    @Override
    public WorkoutType getType() {
        return WORKOUT_TYPE;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (o == null || this.getClass() != o.getClass()) {
            return false;
        }

        Workout otherWorkout = (Workout)o;
        return Objects.equals(this.name, otherWorkout.getName());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(name);
    }

    @Override
    public String toString() {
        return "StrengthWorkout{" +
                "name= " + name + '\'' +
                ", duration=" + duration + '\'' +
                ", caloriesBurned=" + caloriesBurned + '\'' +
                ", difficulty=" + difficulty + '\'' +
                "}";
    }
}
