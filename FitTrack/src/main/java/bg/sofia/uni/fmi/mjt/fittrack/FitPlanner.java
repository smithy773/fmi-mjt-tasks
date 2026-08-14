package bg.sofia.uni.fmi.mjt.fittrack;

import bg.sofia.uni.fmi.mjt.fittrack.exception.OptimalPlanImpossibleException;
import bg.sofia.uni.fmi.mjt.fittrack.workout.Workout;
import bg.sofia.uni.fmi.mjt.fittrack.workout.WorkoutType;
import bg.sofia.uni.fmi.mjt.fittrack.workout.filter.WorkoutFilter;

import java.util.*;

public class FitPlanner implements FitPlannerAPI {
    private final Collection<Workout> workouts;

    private void validateWorkouts(Collection<Workout> workouts) {
        if (workouts == null) {
            throw new IllegalArgumentException("Provided workouts are null");
        }
    }

    public FitPlanner(Collection<Workout> availableWorkouts) {
        validateWorkouts(availableWorkouts);
        this.workouts = availableWorkouts;
    }

    public Collection<Workout> getWorkouts() {
        return this.workouts;
    }

    /**
     * Returns a list of workouts that match all provided filters.
     *
     * @param filters a list of filters to be applied.
     * @return a list of workouts that satisfy all filters.
     * @throws IllegalArgumentException if filters is null.
     */
    @Override
    public List<Workout> findWorkoutsByFilters(List<WorkoutFilter> filters) {
        // return an empty list if workouts is empty
        if (this.workouts.isEmpty()) {
            return Collections.emptyList();
        }

        // otdolu e purvonachalno prazen list, koito ne znam kakuv list trqbva da e - moje da e linkedlist, moje i arraylist nz - koeto e po-burzo
        List<Workout> result;

        // loop through workouts (if (workout.next()) {
        //      workout.val (ili kakvoto e)
        //      boolean passesFilter = true;
        //              workout.val go loopvame za vseki workout filter
        //              ako ne passne filter, setvame passesFilter na false
        //      ako passesFilter e false, continue
        //      ako passesFilter e true, slagame workout.val v result
        // }
        // kato vsichko tova mine - return result
        return List.of();
    }

    /**
     * Generates an optimal weekly workout plan that maximizes burned calories
     * while fitting within the specified total time limit.
     *
     * @param totalMinutes total available time (in minutes) for workouts during the week
     * @return a list of optimally selected workouts, sorted by calories, then by difficulty, in descending order.
     * Returns an empty list if totalMinutes is 0.
     * @throws OptimalPlanImpossibleException if a valid plan cannot be generated (e.g., all workouts exceed the time limit)
     * @throws IllegalArgumentException       if totalMinutes is negative
     */
    @Override
    public List<Workout> generateOptimalWeeklyPlan(int totalMinutes) throws OptimalPlanImpossibleException {
        // return an empty list if workouts is empty
        if (this.workouts.isEmpty()) {
            return Collections.emptyList();
        }
        // za tuk mi trqbva 0/1 knapsack algorithm, koito nz kak shte stane
        return List.of();
    }

    /**
     * Groups all available workouts by type.
     *
     * @return an unmodifiable Map where the key is WorkoutType and the value is a list of workouts of that type.
     */
    @Override
    public Map<WorkoutType, List<Workout>> getWorkoutsGroupedByType() {
        // return an empty map if workouts is empty
        if (this.workouts.isEmpty()) {
            return Collections.emptyMap();
        }
        // suzdavam Map s key workouttype i zad vseki key stoi list ot workout elementi
        // loopvam workouts {
        //      ako v map-a veche ima takuv workout type key -> kum negoviq list dobavqme tozi workout
        //      ako v map-a nqma takuv workout type key -> dobavqme toq key + dobavqme kum negoviq list toq workout
        // }
        // sled obhojdaneto vrushtame suzdadeniq map
        return Map.of();
    }

    /**
     * Returns a list of all workouts, sorted by burned calories in descending order.
     *
     * @return an unmodifiable list of workouts sorted by calories in descending order.
     */
    @Override
    public List<Workout> getWorkoutsSortedByCalories() {
        // return an empty list if workouts is empty
        if (this.workouts.isEmpty()) {
            return Collections.emptyList();
        }

        return List.of();
    }

    /**
     * Returns a list of all workouts, sorted by difficulty in ascending order.
     *
     * @return an unmodifiable list of workouts sorted by difficulty in ascending order.
     */
    @Override
    public List<Workout> getWorkoutsSortedByDifficulty() {
        // return an empty list if workouts is empty
        if (this.workouts.isEmpty()) {
            return Collections.emptyList();
        }

        return List.of();
    }

    /**
     * Returns an unmodifiable set of all available workouts.
     *
     * @return an unmodifiable Set containing all workouts.
     */
    @Override
    public Set<Workout> getUnmodifiableWorkoutSet() {
        // return an empty set if workouts is empty
        if (this.workouts.isEmpty()) {
            return Collections.emptySet();
        }

        return Set.of();
    }
}
