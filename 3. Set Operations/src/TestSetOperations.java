import java.util.ArrayList;
import java.util.Arrays;

/**
 * Tests the following methods from the SetOperations class: union, intersect, complement.
 * Two example ArrayLists are used for testing and the output is printed to the console.
 *
 * @author  Tyler Mong
 * @version 1.0
 */
public class TestSetOperations
{
    /**
     * Tests the functionality of the SetOperations class methods.
     * Two ArrayLists are initialized, passed into each method, and the output is printed to the console.
     *
     * @param args  Command line arguments.
     */
    public static void main(String[] args)
    {
        // [Integer Tests]
        SetOperations<Integer> intTester = new SetOperations<>();

        // Initialize and print two lists.
        ArrayList<Integer> ints1 = new ArrayList<>(Arrays.asList(1, 2, 3, 3, 4, 6));
        ArrayList<Integer> ints2 = new ArrayList<>(Arrays.asList(3, 4, 5));
        System.out.println("Set 1: " + ints1);
        System.out.println("Set 2: " + ints2);

        // Test union functionality.
        ArrayList<Integer> unionInts = new ArrayList<>(intTester.union(ints1, ints2));
        System.out.println("\tUnion: " + unionInts);

        // Test intersect functionality.
        ArrayList<Integer> intersectInts = new ArrayList<>(intTester.intersect(ints1, ints2));
        System.out.println("\tIntersection: " + intersectInts);

        // Test complement functionality.
        ArrayList<Integer> complementInts = new ArrayList<>(intTester.complement(ints1, ints2));
        System.out.println("\tComplement: " + complementInts);
        System.out.println("\t\tSample: " + ints1 + ", Subset: " + ints2);



        // [Double Test]
        SetOperations<Double> doubleTester = new SetOperations<>();

        // Initialize and print two lists.
        ArrayList<Double> doubles1 = new ArrayList<>(Arrays.asList(1.01, 2.02, 3.03, 3.03, 4.04, 6.06));
        ArrayList<Double> doubles2 = new ArrayList<>(Arrays.asList(3.03, 4.04, 5.05));
        System.out.println("\nSet 1: " + doubles1);
        System.out.println("Set 2: " + doubles2);

        // Test union functionality.
        ArrayList<Double> unionDoubles = new ArrayList<>(doubleTester.union(doubles1, doubles2));
        System.out.println("\tUnion: " + unionDoubles);

        // Test intersect functionality.
        ArrayList<Double> intersectDoubles = new ArrayList<>(doubleTester.intersect(doubles1, doubles2));
        System.out.println("\tIntersection: " + intersectDoubles);

        // Test complement functionality.
        ArrayList<Double> complementDoubles = new ArrayList<>(doubleTester.complement(doubles1, doubles2));
        System.out.println("\tComplement: " + complementDoubles);
        System.out.println("\t\tSample: " + doubles1 + ", Subset: " + doubles2);



        // [String Test]
        SetOperations<String> stringTester = new SetOperations<>();

        // Initialize and print two lists.
        ArrayList<String> strings1 = new ArrayList<>(Arrays.asList("aaa", "bbb", "ccc", "ccc", "ddd", "fff"));
        ArrayList<String> strings2 = new ArrayList<>(Arrays.asList("ccc", "ddd", "eee"));
        System.out.println("\nSet 1: " + strings1);
        System.out.println("Set 2: " + strings2);

        // Test union functionality.
        ArrayList<String> unionStrings = new ArrayList<>(stringTester.union(strings1, strings2));
        System.out.println("\tUnion: " + unionStrings);

        // Test intersect functionality.
        ArrayList<String> intersectStrings = new ArrayList<>(stringTester.intersect(strings1, strings2));
        System.out.println("\tIntersection: " + intersectStrings);

        // Test complement functionality.
        ArrayList<String> complementStrings = new ArrayList<>(stringTester.complement(strings1, strings2));
        System.out.println("\tComplement: " + complementStrings);
        System.out.println("\t\tSample: " + strings1 + ", Subset: " + strings2);
    }
}
