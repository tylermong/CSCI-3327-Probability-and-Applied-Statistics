import java.util.LinkedList;

public class CustomHashMap<K, V> 
{
    private static final int INITIAL_CAPACITY = 16;
    private static final float LOAD_FACTOR = 0.75f;

    private LinkedList<Entry<K, V>>[] buckets;
    private int capacity;
    private int size;

    public CustomHashMap()
    {
        this.capacity = INITIAL_CAPACITY;
        this.size = 0;
        this.buckets = new LinkedList[capacity];

        for (int i = 0; i < capacity; i++)
        {
            buckets[i] = new LinkedList<>();
        }
    }

    public V put(K key, V value)
    {
        if ((float) (size + 1) / capacity > LOAD_FACTOR)
        {
            resize();
        }
        
        int index = simpleHash(key);
        LinkedList<Entry<K, V>> bucket = buckets[index];

        for (Entry<K, V> entry : bucket)
        {
            if ((key == null && entry.getKey() == null) || (key != null && key.equals(entry.getKey())))
            {
                V oldValue = entry.getValue();
                entry.setValue(value);
                return oldValue;
            }
        }

        bucket.add(new Entry<>(key, value));
        size++;
        return null;
    }

    public V get(K key)
    {
        int index = simpleHash(key);
        LinkedList<Entry<K, V>> bucket = buckets[index];
        
        for (Entry<K, V> entry : bucket)
        {
            if ((key == null && entry.getKey() == null) || (key != null && key.equals(entry.getKey())))
            {
                return entry.getValue();
            }
        }
        
        return null;
    }

    // TODO: Update this to use a better hash function
    private int simpleHash(K key)
    {
        if (key == null)
        {
            return 0;
        }
        
        return key.toString().length();
    }

    public boolean contains(K key)
    {
        int index = simpleHash(key);
        LinkedList<Entry<K, V>> bucket = buckets[index];
        
        for (Entry<K, V> entry : bucket)
        {
            if ((key == null && entry.getKey() == null) || (key != null && key.equals(entry.getKey())))
            {
                return true;
            }
        }
        
        return false;
    }

    private void resize()
    {
        LinkedList<Entry<K, V>>[] oldBuckets = buckets;

        capacity *= 2;

        buckets = new LinkedList[capacity];

        for (int i = 0; i < capacity; i++)
        {
            buckets[i] = new LinkedList<>();
        }

        size = 0;

        for (LinkedList<Entry<K, V>> bucket : oldBuckets)
        {
            for (Entry<K, V> entry : bucket)
            {
                put(entry.getKey(), entry.getValue());
            }
        }
    }

    public int size()
    {
        return size;
    }
}
