import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * This class tests the methods in the StatsLibrary class.
 *
 * @author  Tyler Mong
 * @version 1.0
 */
public class TestStatsLibrary
{
    /**
     * Runs the tests for the StatsLibrary class. Prints the sets of numbers and the results of the tests.
     * @param args  Command line arguments.
     */
    public static void main(String[] args)
    {
        StatsLibrary stats = new StatsLibrary();

        // Test case with a set of in order numbers, containing some duplicates.
        double[] numbers = { 1, 2, 3, 4, 4, 5, 6, 6 };
        System.out.println("Set: " + Arrays.toString(numbers));
        System.out.println("Mean: " + stats.getMean(numbers));
        System.out.println("Median: " + stats.getMedian(numbers));
        System.out.println("Mode: " + stats.getMode(numbers));
        System.out.println("Population Standard Deviation: " + stats.getPopulationStandardDeviation(numbers));
        System.out.println("Sample Standard Deviation: " + stats.getSampleStandardDeviation(numbers));
        System.out.println("Variance: " + stats.getVariance(numbers));

        // Test case with an empty set.
        double[] emptySet = { };
        System.out.println("\nSet: " + Arrays.toString(emptySet));
        System.out.println("Mean: " + stats.getMean(emptySet));
        System.out.println("Median: " + stats.getMedian(emptySet));
        System.out.println("Mode: " + stats.getMode(emptySet));
        System.out.println("Population Standard Deviation: " + stats.getPopulationStandardDeviation(emptySet));
        System.out.println("Sample Standard Deviation: " + stats.getSampleStandardDeviation(emptySet));
        System.out.println("Variance: " + stats.getVariance(emptySet));

        // Test case with a set of unordered distinct numbers.
        double[] randomSet = { 13, 7, 11, 5 };
        System.out.println("\nSet: " + Arrays.toString(randomSet));
        System.out.println("Mean: " + stats.getMean(randomSet));
        System.out.println("Median: " + stats.getMedian(randomSet));
        System.out.println("Mode: " + stats.getMode(randomSet));
        System.out.println("Population Standard Deviation: " + stats.getPopulationStandardDeviation(randomSet));
        System.out.println("Sample Standard Deviation: " + stats.getSampleStandardDeviation(randomSet));
        System.out.println("Variance: " + stats.getVariance(randomSet));

        // Check probability axioms 1, 2, and 3.
        Map<String, Double> events = new HashMap<>() {{ put("A", 0.5); put("B", 0.3); put("C", 0.2); }};
        String[] mutuallyExclusiveEvents = { "A", "B" };
        System.out.println("\nEvents: " + events);
        System.out.println("Axiom 1: " + stats.checkAxiom1(events));
        System.out.println("Axiom 2: " + stats.checkAxiom2(events));
        System.out.println("Axiom 3: " + stats.checkAxiom3(events));
        System.out.println("Mutually Exclusive Event Union Probability " + Arrays.toString(mutuallyExclusiveEvents) + ": " + stats.getMutuallyExclusiveEventUnionProbability(events, mutuallyExclusiveEvents));
        
    }
}