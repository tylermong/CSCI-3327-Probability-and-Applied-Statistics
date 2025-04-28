package dev.tylermong.customhashmap;

/**
 * This class represents an entry in the custom hash map. It uses generics to allow for any type of key and value.
 * 
 * @param <K> the type of the key
 * @param <V> the type of the value
 */
public class Entry<K, V>
{
    /**
     * The key of the entry, which can be of any type.
     */
    private K key;

    /**
     * The value of the entry, which can be of any type.
     */
    private V value;

    /**
     * Constructs an entry with the specified key and value.
     * 
     * @param key   the key of the entry
     * @param value the value of the entry
     */
    public Entry(K key, V value)
    {
        this.key = key;
        this.value = value;
    }

    /**
     * Returns the key of the entry.
     * 
     * @return the key of the entry
     */
    public K getKey()
    {
        return key;
    }

    /**
     * Returns the value of the entry.
     * 
     * @return the value of the entry
     */
    public V getValue()
    {
        return value;
    }

    /**
     * Sets the value of the entry.
     * 
     * @param value the value to set
     */
    public void setValue(V value)
    {
        this.value = value;
    }
}