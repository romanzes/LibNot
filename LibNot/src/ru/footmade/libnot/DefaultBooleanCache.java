
package ru.footmade.libnot;

import java.util.HashMap;
import java.util.Map;

/**
 * HashMap-backed cache realization
 */
public class DefaultBooleanCache implements BooleanCache {
    private Map<Boolean, Boolean> cachedValues;
    
    public DefaultBooleanCache() {
        cachedValues = new HashMap<>();
    }

    @Override
    public void put(Boolean key, Boolean value) {
        cachedValues.put(key, value);
    }
    
    @Override
    public boolean containsKey(Boolean key) {
        return cachedValues.containsKey(key);
    }

    @Override
    public Boolean get(Boolean key) {
        return cachedValues.get(key);
    }
}
