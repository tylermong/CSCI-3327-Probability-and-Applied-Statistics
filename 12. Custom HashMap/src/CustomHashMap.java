import java.util.ArrayList;
import java.util.LinkedList;

/**
 * A custom implementation of a {@code HashMap} data structure using an array of linked lists for each bucket. This
 * class handles generic key-value pairs and provides basic public methods such as: {@code put}, {@code get},
 * {@code contains}, and {@code size}. Internally, it has methods for hashing keys ({@code simpleHash}) and resizing the
 * map when the load factor exceeds a predefined threshold ({@code resize}).
 * 
 * @param   <K> the type of keys maintained by this map
 * @param   <V> the type of mapped values
 * @author      Tyler Mong
 * @version     1.0
 */
public class CustomHashMap<K, V>
{
    /**
     * The initial capacity of the HashMap.
     */
    private static final int INITIAL_CAPACITY = 16;

    /**
     * The load factor threshold for resizing the HashMap.
     */
    private static final float LOAD_FACTOR = 0.75f;

    /**
     * Buckets used to store the key-value pairs. Each bucket is a linked list of entries.
     */
    private ArrayList<LinkedList<Entry<K, V>>> buckets;

    /**
     * The capacity of the HashMap. By default, it is set to {@code INITIAL_CAPACITY}, which is 16. The capacity is
     * doubled when the load factor exceeds the threshold.
     */
    private int capacity;

    /**
     * The size of the {@code HashMap}, which is the number of key-value pairs currently stored in it. The size is
     * incremented when a new key-value pair is added and decremented when a key-value pair is removed. It is also used
     * to determine when to resize the HashMap.
     */
    private int size;

    /**
     * Constructs an empty {@code HashMap} with the default initial capacity of 16. The size is initialized to 0, and
     * each bucket is initialized as an empty linked list.
     */
    public CustomHashMap()
    {
        this.capacity = INITIAL_CAPACITY;
        this.size = 0;
        this.buckets = new ArrayList<>(capacity);

        for (int i = 0; i < capacity; i++)
        {
            buckets.add(new LinkedList<>()); // Initialize LinkedList<Entry<K, V>> for each bucket
        }
    }

    /**
     * Associates the specified value with the specified key in this {@code HashMap}. If the {@code HashMap} previously
     * contained a mapping for the key, the old value is replaced. Resizes the map if the load factor exceeds the
     * threshold.
     * 
     * @param  key   the key to be inserted into the {@code HashMap}
     * @param  value the value to be associated with the key
     * @return       the old value associated with the key, or null if the key was not already present
     */
    public V put(K key, V value)
    {
        // Check if the HashMap needs to be resized
        if ((float) (size + 1) / capacity > LOAD_FACTOR)
        {
            resize();
        }

        // Calculate the index and get the corresponding bucket
        int index = hash(key);
        LinkedList<Entry<K, V>> bucket = buckets.get(index);

        // Check each entry in the bucket for the key
        for (Entry<K, V> entry : bucket)
        {
            // Key exists, so replace it and return the old value
            if ((key == null && entry.getKey() == null) || (key != null && key.equals(entry.getKey())))
            {
                V oldValue = entry.getValue();
                entry.setValue(value);
                return oldValue;
            }
        }

        // Key does not exist, so create a new entry and add it to the bucket
        bucket.add(new Entry<>(key, value));
        size++;
        return null;
    }

    /**
     * Returns the value to which the specified key is mapped, or {@code null} if this map contains no mapping for the
     * key.
     * 
     * @param  key the key whose value is to be returned
     * @return     the value to which the key is mapped, or {@code null} if the key is not found in the {@code HashMap}
     */
    public V get(K key)
    {
        // Calculate the index and get the corresponding bucket
        int index = hash(key);
        LinkedList<Entry<K, V>> bucket = buckets.get(index);

        // Check each entry in the bucket for the key
        for (Entry<K, V> entry : bucket)
        {
            // Key is found, so return its value
            if ((key == null && entry.getKey() == null) || (key != null && key.equals(entry.getKey())))
            {
                return entry.getValue();
            }
        }

        // Key is not found, so return null
        return null;
    }

    /**
     * Hashes the specified key to an index in the buckets array.
     * 
     * @deprecated     This method uses a simple hashing algorithm, based on the length of the key, which causes a high
     *                 number of collisions. Use {@link #hash(Object)} instead for a more efficient hashing algorithm,
     *                 which implements Java's standard HashMap hashing algorithm.
     * 
     * @param      key the key to be hashed
     * @return         the length of the key, used as the index in the buckets array
     */
    @Deprecated(since = "1.0", forRemoval = false)
    @SuppressWarnings(
    { "java:S1133", "unused" })
    private int simpleHash(K key)
    {
        if (key == null)
        {
            return 0;
        }

        return key.toString().length();
    }

    /**
     * Hashes the specified key to an index in the buckets array. This implementation is based on Java's standard
     * HashMap hashing algorithm, which uses the key's hashCode method and applies a bitwise XOR operation to reduce
     * collisions.
     * 
     * @param  key
     * @return
     */
    private int hash(K key)
    {
        if (key == null)
        {
            return 0;
        }

        int hash = key.hashCode();
        return hash ^ (hash >>> 16);
    }

    /**
     * Returns true if this HashMap contains a mapping for the specified key.
     * 
     * @param  key the key whose presence in this HashMap is to be tested
     * @return     {@code true} if this map contains a mapping for the specified key, {@code false} otherwise
     */
    public boolean contains(K key)
    {
        // Calculate the index and get the corresponding bucket
        int index = hash(key);
        LinkedList<Entry<K, V>> bucket = buckets.get(index);

        // Check each entry in the bucket for the key
        for (Entry<K, V> entry : bucket)
        {
            // Key is found, so return true
            if ((key == null && entry.getKey() == null) || (key != null && key.equals(entry.getKey())))
            {
                return true;
            }
        }

        // Key is not found, so return false
        return false;
    }

    /**
     * Resizes the {@code HashMap} by doubling its capacity and rehashing all existing entries.
     */
    private void resize()
    {
        // Create a copy of the old buckets
        ArrayList<LinkedList<Entry<K, V>>> oldBuckets = buckets;

        // Create a new array with the new (doubled) capacity
        capacity *= 2;
        buckets = new ArrayList<>(capacity);

        // Initialize each bucket as an empty linked list
        for (int i = 0; i < capacity; i++)
        {
            buckets.add(new LinkedList<>()); // Initialize LinkedList<Entry<K, V>> for each bucket
        }

        // Reset size (this will be updated as we re-add entries)
        size = 0;

        // Rehash all existing entries into the new buckets
        for (LinkedList<Entry<K, V>> bucket : oldBuckets)
        {
            for (Entry<K, V> entry : bucket)
            {
                put(entry.getKey(), entry.getValue());
            }
        }
    }

    /**
     * Returns the number of key-value mappings in this {@code HashMap}.
     * 
     * @return the number of key-value mappings in this {@code HashMap}
     */
    public int size()
    {
        return size;
    }
}
