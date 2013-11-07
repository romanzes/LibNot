
package ru.footmade.libnot;

/**
 * @author roman.petrenko
 */
public class NegationStrategyFactory {
    public static final int FLAG_DEFAULT = 0x00000000;
    public static final int FLAG_CACHED = 0x00000001;
    public static final int FLAG_CLOUD = 0x00000002;
    
    /**
     * @return default negation strategy
     * @see #createNegationStrategy(int)
     */
    public AbstractNegationStrategy createNegationStrategy() {
        return createNegationStrategy(FLAG_DEFAULT);
    }
    
    /**
     * Creates appropriate negation strategy.
     * @param flags. Features to be present in the strategy. Can be combined using binary OR operator.
     * @return negation strategy using specified features.
     */
    public AbstractNegationStrategy createNegationStrategy(int flags) {
        if ((flags & FLAG_CLOUD) == FLAG_CLOUD) {
            throw new IllegalArgumentException("Not implemented yet");
        }
        
        if ((flags & FLAG_CACHED) == FLAG_CACHED) {
            return new CachedNegationStrategy();
        }
        else {
            return new DefaultNegationStrategy();
        }
    }
}
