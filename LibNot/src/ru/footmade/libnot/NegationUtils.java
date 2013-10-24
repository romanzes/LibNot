package ru.footmade.libnot;

import java.util.HashMap;
import java.util.Map;

public class NegationUtils {

    private static final Boolean TRUE = Boolean.valueOf(true);
    private static final Boolean FALSE = Boolean.valueOf(false);

    private static final Map<Boolean, Boolean> cache;

    static {
        cache = new HashMap<>();
    }

    /**
     * Does logical negation
     * @param var a value which should be negated. Must be either true or false.
     * @return true, if an argument is false; false, if an argument is true.
     */
    public static boolean not(boolean var) throws IllegalArgumentException {
        final Boolean varObj = Boolean.valueOf(var);

        if (cache.containsKey(varObj)) {
            return cache.get(varObj).booleanValue();
        }

        Boolean result;
        if (varObj.equals(TRUE)) {
            result = Boolean.valueOf(FALSE.booleanValue());
        } else if (varObj.equals(FALSE)) {
            result = Boolean.valueOf(TRUE.booleanValue());
        } else {
            throw new IllegalArgumentException();
        }
        
        cache.put(varObj, result);
        return result.booleanValue();
    }
}
