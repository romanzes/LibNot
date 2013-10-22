package ru.footmade.libnot;

public class NegationUtils {

    private static final Boolean TRUE = Boolean.valueOf(true);
    private static final Boolean FALSE = Boolean.valueOf(false);

    /**
     * Does logical negation
     * @param var a value which should be negated. Must be either true or false.
     * @return true, if an argument is false; false, if an argument is true.
     */
    public static boolean not(boolean var) {
        Boolean varObj = Boolean.valueOf(var);
        if (varObj.equals(TRUE)) {
            return false;
        } else if (varObj.equals(FALSE)) {
            return true;
        } else {
            throw new IllegalArgumentException();
        }
    }
}
