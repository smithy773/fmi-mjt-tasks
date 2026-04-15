package bg.sofia.uni.fmi.mjt.fittrack.workout;

public final class CardioWorkout implements Workout {
    private final String name;
    private final int duration;
    private final int caloriesBurned;
    private final int difficulty;

    public CardioWorkout(String name, int duration, int caloriesBurned, int difficulty) {
        if () {}
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
        return WorkoutType.CARDIO;
    }
}
