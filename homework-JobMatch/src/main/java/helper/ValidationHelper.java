package helper;

import java.util.Set;

public class ValidationHelper {
    public static boolean string (String input) {
        return input != null && !input.isBlank();
    }

    public static boolean num (int num) {
        return num >= 0;
    }

    public static boolean num (double num) {
        return num >= 0;
    }

    public static boolean set (Set<?> set) {
        return !(set == null) && !set.isEmpty();
    }

    public static boolean similarityScore (double num) {
        return num >= 0 && num <= 1;
    }

    public static boolean skillLevel (int level) {
        return level >= 0 && level <= 5;
    }
}
