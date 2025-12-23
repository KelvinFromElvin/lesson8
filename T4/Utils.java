package T4;

import java.util.UUID;

// @FunctionalInterface
// interface matchItemFunction {
//     <T> boolean cmpItem(T item1, T item2);
// }

public class Utils {
    public static String initDefaultStringValue(String valueToCheck, String defaultValue) {
        if (valueToCheck == null || valueToCheck == "") {
            return defaultValue;
        }

        return valueToCheck;
    }

    public static String generateUUID() {
        return UUID.randomUUID().toString();
    }
}
