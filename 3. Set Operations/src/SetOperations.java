import java.util.ArrayList;

/**
 * Contains methods for the following set operations: union, intersect, complement.
 *
 * @author  Tyler Mong
 * @version 1.0
 */
public class SetOperations<T>
{
    /**
     * Creates a set of all distinct values from two lists.
     *
     * @param list1 The first list of values.
     * @param list2 The second list of values.
     * @return      A list containing all distinct values from list1 and list2.
     */
    public ArrayList<T> union(ArrayList<T> list1, ArrayList<T> list2)
    {
        ArrayList<T> unionSet = new ArrayList<>();

        // Add all distinct values from list1 into the unionSet.
        for (T value : list1)
        {
            if (!unionSet.contains(value))
            {
                unionSet.add(value);
            }
        }

        // Now add all distinct values from list2 into the unionSet.
        for (T value : list2)
        {
            if (!unionSet.contains(value))
            {
                unionSet.add(value);
            }
        }

        return unionSet;
    }

    /**
     * Creates a set of all distinct overlapping (intersecting) values from two lists.
     * @param list1 The first list of values.
     * @param list2 The second list of values.
     * @return      A list containing all distinct intersecting values from list1 and list2.
     */
    public ArrayList<T> intersect(ArrayList<T> list1, ArrayList<T> list2)
    {
        ArrayList<T> intersectionSet = new ArrayList<>();

        /**
         * TODO:
         * This method is O(n^2), however a faster method could be implemented. If I sorted both lists and used a
         * two pointer solution, it would drop to O(n/logn + m/logm), with a trade off of less straighforward code.
         * This is significant for large sets, but given the use case for this course, it's not really needed, it would
         * be more so just a nice optimization to explore rather than a necessity.
         */

        // Compare each value in list1 to each value in list2.
        for (T set1Value : list1)
        {
            for (T set2Value : list2)
            {
                // Add the value if the two values match (intersect) and it's not already in the intersectionSet
                if (set1Value.equals(set2Value) && !intersectionSet.contains(set1Value))
                {
                    intersectionSet.add(set1Value);
                }
            }
        }

        return intersectionSet;
    }

    /**
     * Creates a set of complementary values given a sample and a subset.
     * The complement set will contain all values found in the sample, that are not within the subset.
     * @param sample    The sample set of values.
     * @param subset    The subset of values.
     * @return          A list containing the complement of the subset with the given sample.
     */
    public ArrayList<T> complement(ArrayList<T> sample, ArrayList<T> subset)
    {
        ArrayList<T> complementSet = new ArrayList<>();

        for (T sampleValue : sample)
        {
            // Add the value if it's not in subset (complement) and not already in the complementSet
            if (!subset.contains(sampleValue) && !complementSet.contains(sampleValue))
            {
                complementSet.add(sampleValue);
            }
        }

        return complementSet;
    }
}