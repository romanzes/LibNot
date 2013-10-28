package ru.footmade.libnot;

public abstract class AbstractNegationStrategy {
    /**
     * Does logical negation
     * @param var a value which should be negated. Must be either true or false.
     * @return true, if an argument is false; false, if an argument is true.
     */
    public abstract boolean not(boolean var);
}
