import java.util.Arrays;

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

        // Test case with an empty set.
        double[] emptySet = { };
        System.out.println("\nSet: " + Arrays.toString(emptySet));
        System.out.println("Mean: " + stats.getMean(emptySet));
        System.out.println("Median: " + stats.getMedian(emptySet));
        System.out.println("Mode: " + stats.getMode(emptySet));
        System.out.println("Population Standard Deviation: " + stats.getPopulationStandardDeviation(emptySet));
        System.out.println("Sample Standard Deviation: " + stats.getSampleStandardDeviation(emptySet));

        // Test case with a set of unordered distinct numbers.
        double[] randomSet = { 13, 7, 11, 5 };
        System.out.println("\nSet: " + Arrays.toString(randomSet));
        System.out.println("Mean: " + stats.getMean(randomSet));
        System.out.println("Median: " + stats.getMedian(randomSet));
        System.out.println("Mode: " + stats.getMode(randomSet));
        System.out.println("Population Standard Deviation: " + stats.getPopulationStandardDeviation(randomSet));
        System.out.println("Standard Standard Deviation: " + stats.getSampleStandardDeviation(randomSet));
    }
}