import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

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

        // Check probability axioms 1, 2, and 3, find probability of mutually exclusive events, and conditional probability
        Map<String, Double> events = new HashMap<>() {{ put("A", 0.5); put("B", 0.3); put("C", 0.2); }};
        String[] mutuallyExclusiveEvents = { "A", "B" };
        System.out.println("\nEvents: " + events);
        System.out.println("Axiom 1: " + stats.checkAxiom1(events));
        System.out.println("Axiom 2: " + stats.checkAxiom2(events));
        System.out.println("Axiom 3: " + stats.checkAxiom3(events));
        System.out.println("Mutually Exclusive Event Union Probability " + Arrays.toString(mutuallyExclusiveEvents) + ": " + stats.getMutuallyExclusiveEventUnionProbability(events, mutuallyExclusiveEvents));
        System.out.println("P(A|B): " + stats.getConditionalProbability(0.2, 0.5));
        
        // Check independence
        double P_A = 0.5, P_B = 0.5, P_A_Union_B = 0.25;
        System.out.println("Independence Check [P(A) = " + P_A + ", P(B) = " + P_B + ", P(A∩B) = " + P_A_Union_B + "]: " + stats.checkIndependence(P_A, P_B, P_A_Union_B));
        
        double P_A2 = 0.5, P_B2 = 0.25, P_A_Union_B2 = 0.25;
        System.out.println("Independence Check [P(A) = " + P_A2 + ", P(B) = " + P_B2 + ", P(A∩B) = " + P_A_Union_B2 + "]: " + stats.checkIndependence(P_A2, P_B2, P_A_Union_B2));
        
        // Check partition
        Set<Integer> set = new HashSet<>(Arrays.asList(1, 2, 3, 4, 5, 6));
        System.out.println("\nSet: " + set);
        
        Set<Set<Integer>> validPartition = new HashSet<>();
        validPartition.add(new HashSet<>(Arrays.asList(1, 2)));
        validPartition.add(new HashSet<>(Arrays.asList(3, 4)));
        validPartition.add(new HashSet<>(Arrays.asList(5, 6)));
        System.out.println("Is Partition? " + validPartition + ": " + stats.checkPartition(set, validPartition));

        Set<Set<Integer>> invalidPartition1 = new HashSet<>();
        invalidPartition1.add(new HashSet<>(Arrays.asList(1, 2)));
        invalidPartition1.add(new HashSet<>(Arrays.asList(3, 4)));
        invalidPartition1.add(new HashSet<>(Arrays.asList(5)));
        System.out.println("Is Partition? " + invalidPartition1 + ": " + stats.checkPartition(set, invalidPartition1));

        Set<Set<Integer>> invalidPartition2 = new HashSet<>();
        invalidPartition2.add(new HashSet<>(Arrays.asList(1, 2, 3)));
        invalidPartition2.add(new HashSet<>(Arrays.asList(3, 4)));
        invalidPartition2.add(new HashSet<>(Arrays.asList(5, 6)));
        System.out.println("Is Partition? " + invalidPartition2 + ": " + stats.checkPartition(set, invalidPartition2));

        // Test expected values, variances, and standard deviation of random variables
        Map<Integer, Double> probabilities = new HashMap<>() {{ put(1, 0.2); put(2, 0.5); put(3, 0.3); }};
        System.out.println("\nProbabilities: " + probabilities);
        System.out.println("Expected Value: " + String.format("%.2f", stats.getExpectedValueOfRandomVariable(probabilities)));
        System.out.println("Variance: " + String.format("%.2f", stats.getVarianceOfRandomVariable(probabilities)));
        System.out.println("Standard Deviation: " + String.format("%.2f", stats.getStandardDeviationOfRandomVariable(probabilities)));

        // Factorial test
        int value = 5;
        System.out.println("\nFactorial of " + value + ": " + stats.factorial(value));

        // Binomial distribution test
        int n = 10, y = 6;
        double p = 0.8;
        System.out.printf("Binomial Distribution [P(Y = %d) for n = %d, p = %.2f]: %.5f\n", y, n, p, stats.getBinomialDistribution(n, y, p));
    }
}