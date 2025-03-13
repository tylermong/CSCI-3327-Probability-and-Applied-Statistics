import java.util.ArrayList;
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

        // Set operations test
        // [Integer Tests]

        // Initialize and print two lists.
        ArrayList<Integer> ints1 = new ArrayList<>(Arrays.asList(1, 2, 3, 3, 4, 6));
        ArrayList<Integer> ints2 = new ArrayList<>(Arrays.asList(3, 4, 5));
        System.out.println("\nSet 1: " + ints1);
        System.out.println("Set 2: " + ints2);

        // Test union functionality.
        ArrayList<Integer> unionInts = new ArrayList<>(stats.union(ints1, ints2));
        System.out.println("\tUnion: " + unionInts);

        // Test intersect functionality.
        ArrayList<Integer> intersectInts = new ArrayList<>(stats.intersect(ints1, ints2));
        System.out.println("\tIntersection: " + intersectInts);

        // Test complement functionality.
        ArrayList<Integer> complementInts = new ArrayList<>(stats.complement(ints1, ints2));
        System.out.println("\tComplement: " + complementInts);
        System.out.println("\t\tSample: " + ints1 + ", Subset: " + ints2);

        // [Double Test]
        // Initialize and print two lists.
        ArrayList<Double> doubles1 = new ArrayList<>(Arrays.asList(1.01, 2.02, 3.03, 3.03, 4.04, 6.06));
        ArrayList<Double> doubles2 = new ArrayList<>(Arrays.asList(3.03, 4.04, 5.05));
        System.out.println("\nSet 1: " + doubles1);
        System.out.println("Set 2: " + doubles2);

        // Test union functionality.
        ArrayList<Double> unionDoubles = new ArrayList<>(stats.union(doubles1, doubles2));
        System.out.println("\tUnion: " + unionDoubles);

        // Test intersect functionality.
        ArrayList<Double> intersectDoubles = new ArrayList<>(stats.intersect(doubles1, doubles2));
        System.out.println("\tIntersection: " + intersectDoubles);

        // Test complement functionality.
        ArrayList<Double> complementDoubles = new ArrayList<>(stats.complement(doubles1, doubles2));
        System.out.println("\tComplement: " + complementDoubles);
        System.out.println("\t\tSample: " + doubles1 + ", Subset: " + doubles2);

        // [String Test]
        // Initialize and print two lists.
        ArrayList<String> strings1 = new ArrayList<>(Arrays.asList("aaa", "bbb", "ccc", "ccc", "ddd", "fff"));
        ArrayList<String> strings2 = new ArrayList<>(Arrays.asList("ccc", "ddd", "eee"));
        System.out.println("\nSet 1: " + strings1);
        System.out.println("Set 2: " + strings2);

        // Test union functionality.
        ArrayList<String> unionStrings = new ArrayList<>(stats.union(strings1, strings2));
        System.out.println("\tUnion: " + unionStrings);

        // Test intersect functionality.
        ArrayList<String> intersectStrings = new ArrayList<>(stats.intersect(strings1, strings2));
        System.out.println("\tIntersection: " + intersectStrings);

        // Test complement functionality.
        ArrayList<String> complementStrings = new ArrayList<>(stats.complement(strings1, strings2));
        System.out.println("\tComplement: " + complementStrings);
        System.out.println("\t\tSample: " + strings1 + ", Subset: " + strings2);

        // Test permutation and combination
        int r = 3; int n = 5;
        System.out.println("\nPermutation [n = " + n + ", r = " + r + "]: " + stats.permutation(n, r));
        System.out.println("Combination [n = " + n + ", r = " + r + "]: " + stats.combination(n, r));

        // Test M * N
        int m = 3; n = 4;
        System.out.println("\nM * N [m = " + m + ", n = " + n + "]: " + stats.getMN(m, n));

        // Test multinomial coefficient
        int[] counts = { 2, 3, 5 }; n = 10;
        System.out.println("\nMultinomial Coefficient [n = " + n + ", counts = " + Arrays.toString(counts) + "]: " + stats.getMultinomialCoefficient(n, counts));

        // Test intersection and union probability for independent and dependent events
        double probabilityOfA = 0.5, probabilityOfB = 0.3, probabilityOfBGivenA = 0.2;

        double independentIntersection = stats.getIndependentIntersectionProbability(probabilityOfA, probabilityOfB);
        System.out.printf("\nIndependent Intersection Probability [P(A) = %.2f, P(B) = %.2f]: %.2f\n", probabilityOfA, probabilityOfB, independentIntersection);

        double independentUnion = stats.getUnionProbability(probabilityOfA, probabilityOfB, independentIntersection);
        System.out.printf("Independent Union Probability [P(A) = %.2f, P(B) = %.2f]: %.2f\n", probabilityOfA, probabilityOfB, independentUnion);

        double dependentIntersection = stats.getDependentIntersectionProbability(probabilityOfA, probabilityOfBGivenA);
        System.out.printf("Dependent Intersection Probability [P(A) = %.2f, P(B|A) = %.2f]: %.2f\n", probabilityOfA, probabilityOfBGivenA, dependentIntersection);

        double dependentUnion = stats.getUnionProbability(probabilityOfA, probabilityOfB, dependentIntersection);
        System.out.printf("Dependent Union Probability [P(A) = %.2f, P(B) = %.2f]: %.2f\n", probabilityOfA, probabilityOfB, dependentUnion);

        // Test law of total probability and Bayes' theorem
        double[] probabilitiesAGivenBi = {0.8, 0.6, 0.7};
        double[] probabilitiesBi = {0.5, 0.3, 0.2};
        double totalProbability = stats.lawOfTotalProbability(probabilitiesAGivenBi, probabilitiesBi);
        System.out.printf("\nTotal Probability [P(A)]: %.2f\n", totalProbability);
        
        probabilityOfA = 0.01; probabilityOfB = 0.99; probabilityOfBGivenA = 0.99;
        double bayesProbability = stats.bayesTheorem(probabilityOfA, probabilityOfB, probabilityOfBGivenA);
        System.out.printf("Bayes' Theorem Result [P(A|B)]: %.2f\n", bayesProbability);

        // Binomial distribution test
        n = 10; int y = 6; double p = 0.8;
        System.out.printf("\nBinomial Distribution [P(Y = %d) for n = %d, p = %.2f]: %.5f\n", y, n, p, stats.getBinomialProbability(n, y, p));
        System.out.printf("Binomial Distribution Expected Value [E(Y) for n = %d, p = %.2f]: %.2f\n", n, p, stats.getBinomialExpectedValue(n, p));
        System.out.printf("Binomial Distribution Variance [V(Y) for n = %d, p = %.2f]: %.2f\n", n, p, stats.getBinomialVariance(n, p));
        System.out.printf("Binomial Distribution Standard Deviation [σ(Y) for n = %d, p = %.2f]: %.2f\n", n, p, stats.getBinomialStandardDeviation(n, p));

        // Geometric distribution test
        System.out.printf("\nGeometric Distribution [P(Y = %d) for p = %.2f]: %.5f\n", y, p, stats.getGeometricProbability(y, p));
        System.out.printf("Geometric Distribution Expected Value [E(Y) for p = %.2f]: %.2f\n", p, stats.getGeometricExpectedValue(p));
        System.out.printf("Geometric Distribution Variance [V(Y) for p = %.2f]: %.2f\n", p, stats.getGeometricVariance(p));
        System.out.printf("Geometric Distribution Standard Deviation [σ(Y) for p = %.2f]: %.2f\n", p, stats.getGeometricStandardDeviation(p));

        System.out.printf("Geometric Distribution CDF [P(X <= n) for n = %d, p = %.2f]: %.5f\n", y, p, stats.getGeometricCDF(y, p));
        System.out.printf("Geometric Distribution Shifted CDF [P(X < n) for n = %d, p = %.2f]: %.5f\n", y, p, stats.getGeometricShiftedCDF(y, p));
        System.out.printf("Geometric Distribution CCDF [P(X >= n) for n = %d, p = %.2f]: %.5f\n", y, p, stats.getGeometricCCDF(y, p));
        System.out.printf("Geometric Distribution Shifted CCDF [P(X > n) for n = %d, p = %.2f]: %.5f\n", y, p, stats.getGeometricShiftedCCDF(y, p));
    }
}