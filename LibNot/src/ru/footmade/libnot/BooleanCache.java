
package ru.footmade.libnot;

/**
 * @author roman.petrenko
 */
public interface BooleanCache {
    public void put(Boolean key, Boolean value);
    public Boolean get(Boolean key);
    public boolean containsKey(Boolean key);
}
