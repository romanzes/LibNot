package ru.footmade.libnot;

import java.util.HashMap;
import java.util.Map;

public class CachedNegationStrategy extends DefaultNegationStrategy {
    private static final Map<Boolean, Boolean> cache;

    static {
        cache = new HashMap<>();
    }
    
    @Override
    public boolean not(boolean var) {
        final Boolean varObj = Boolean.valueOf(var);

        if (cache.containsKey(varObj)) {
            return cache.get(varObj).booleanValue();
        }

        Boolean result = super.not(var);
        
        cache.put(varObj, result);
        return result.booleanValue();
    }
}
