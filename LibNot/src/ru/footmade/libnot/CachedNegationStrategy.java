package ru.footmade.libnot;

public class CachedNegationStrategy extends DefaultNegationStrategy {
    private BooleanCache _cache;
    
    public CachedNegationStrategy() {
        this(new DefaultBooleanCache());
    }
    
    public CachedNegationStrategy(BooleanCache cache) {
        _cache = cache;
    }
    
    public void setCache(BooleanCache newCache) {
        _cache = newCache;
    }
    
    @Override
    public boolean not(boolean var) {
        if (_cache == null) {
            return super.not(var);
        } else if (_cache != null) {
            final Boolean varObj = Boolean.valueOf(var);

            if (_cache.containsKey(varObj)) {
                return _cache.get(varObj).booleanValue();
            }

            Boolean result = super.not(var);

            _cache.put(varObj, result);
            return result.booleanValue();
        } else {
            throw new IllegalArgumentException("Schrodinger's cache!");
        }
    }
}
