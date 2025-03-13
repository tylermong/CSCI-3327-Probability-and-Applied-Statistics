import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Contains various statistical methods including: mean, median, mode, population/sample standard deviation.
 *
 * @author  Tyler Mong
 * @version 1.0
 */
public class StatsLibrary
{
    /**
     * Calculates the mean of an array of numbers.
     * @param nums  The array of numbers.
     * @return      The mean of the numbers.
     */
    public double getMean(double[] nums)
    {
        double total = 0;
        for (double num : nums)
        {
            total += num;
        }
        return total / nums.length;
    }

    /**
     * Calculates the median of an array of numbers.
     * @param nums  The array of numbers.
     * @return      The median of the numbers.
     */
    public double getMedian(double[] nums)
    {
        // Handles edge case where the array is null or empty.
        if (nums == null || nums.length == 0)
        {
            return Double.NaN;
        }
        
        // Sort the array.
        Arrays.sort(nums);
        
        int n = nums.length;
        
        // If it's even, then return average of both middle elements.
        if (n % 2 == 0)
        {
            return (nums[n / 2 - 1] + nums[n / 2]) / 2.0;
        }
        // Else, return the single middle element.
        else
        {
            return nums[n / 2];
        }
    }

    /**
     * Calculates the mode of an array of numbers.
     * @param nums  The array of numbers.
     * @return      The mode of the numbers.
     */
    public ArrayList<Double> getMode(double[] nums)
    {
        // Handles edge case where the array is null or empty.
        if (nums == null || nums.length == 0)
        {
            return new ArrayList<>();
        }

        // Number : Frequency
        HashMap<Double, Integer> numberFrequency = new HashMap<>();

        // For each time a num occurs:
        for (double num : nums)
        {
            // If the number is not in the map, then add it with a frequency of 1. Otherwise, increment the frequency.
            numberFrequency.put(num, numberFrequency.getOrDefault(num, 0) + 1);
        }

        // "maxFrequency" to store the greatest number of occurrences
        // "modes" to store an array of modes (to handle multiple modes)
        int maxFrequency = 0;
        ArrayList<Double> modes = new ArrayList<>();

        // For each number in the map:
        for (Map.Entry<Double, Integer> entry : numberFrequency.entrySet())
        {
            // If the current number has a greater frequency than the current max frequency,
            // then clear the list of modes, add the new mode to the list, and update the max frequency.
            if (entry.getValue() > maxFrequency)
            {
                modes.clear();
                modes.add(entry.getKey());
                maxFrequency = entry.getValue();
            }
            // Else if the current number's frequency is equal to the max frequency, then add it to the list of modes.
            else if (entry.getValue() == maxFrequency)
            {
                modes.add(entry.getKey());
            }
        }
        return modes;
    }

    /**
     * Calculates the population standard deviation of an array of numbers.
     * @param nums  The array of numbers.
     * @return      The population standard deviation of the numbers.
     */
    public double getPopulationStandardDeviation(double[] nums)
    {
        // Calculate the mean.
        double mean = getMean(nums);

        // Subtract the mean from each number.
        // Square each result.
        // Add all the results together.
        double total = 0;
        for (double num : nums)
        {
            num -= mean;
            num *= num;
            total += num;
        }

        // Divide the total by the number of elements in the array (N).
        double population = total / nums.length;

        // Return the square root of the result.
        return Math.sqrt(population);
    }

    /**
     * Calculates the sample standard deviation of an array of numbers.
     * @param nums  The array of numbers.
     * @return      The sample standard deviation of the numbers.
     */
    public double getSampleStandardDeviation(double[] nums)
    {
        // Calculate the mean.
        double mean = getMean(nums);

        // Subtract the mean from each number.
        // Square each result.
        // Add all the results together.
        double total = 0;
        for (double num : nums)
        {
            num -= mean;
            num *= num;
            total += num;
        }

        // Divide the total by the number of elements in the array minus 1 (N - 1).
        double sample = total / nums.length - 1;

        // Return the square root of the result.
        return Math.sqrt(sample);
    }

    /**
     * Calculates the variance of an array of numbers.
     * @param nums  The array of numbers.
     * @return      The variance of the numbers.
     */
    public double getVariance(double[] nums)
    {
        // Step 1: Find the sample mean
        double mean = getMean(nums);

        // Step 2: Subtract the mean from each data point
        // Step 3: Square each of the deviations
        // Step 4: Sum the squared deviations
        // Step 5: Divide the sum of the squared deviations by n - 1
        double sum = 0;
        for (double num : nums)
        {
            sum += Math.pow(num - mean, 2);
        }
        return sum / (nums.length - 1);
    }

    /**
     * Checks if the probability axiom 1 (P(A) >= 0) is satisfied.
     * @param events    The map of events and their probability.
     * @return          True if the axiom is satisfied, false otherwise.
     */
    public boolean checkAxiom1(Map<String, Double> events)
    {
        for (Map.Entry<String, Double> entry : events.entrySet())
        {
            if (entry.getValue() < 0)
            {
                return false;
            }
        }
        return true;
    }

    /**
     * Checks if the probability axiom 2 (P(S) = 1) is satisfied.
     * @param events    The map of events and their probability.
     * @return          True if the axiom is satisfied, false otherwise.
     */
    public boolean checkAxiom2(Map<String, Double> events)
    {
        double sum = 0;
        for (Map.Entry<String, Double> entry : events.entrySet())
        {
            sum += entry.getValue();
        }
        return sum == 1;
    }

    /**
     * Checks if the probability axiom 3 (P(A_1 ∪ A_2 ∪ ... ∪ A_n) = sum(P(A_i))) is satisfied.
     * @param events    The map of events and their probability.
     * @return          True if the axiom is satisfied, false otherwise.
     */
    // TODO: Implement the method.
    public boolean checkAxiom3(Map<String, Double> events)
    {
        double sum = 0;
        for (Map.Entry<String, Double> entry : events.entrySet())
        {
            sum += entry.getValue();
        }
        return sum == 1;
    }

    /**
     * Calculates the probability of the union of mutually exclusive events.
     * @param events        The map of events and their probability.
     * @param eventNames    The names of the events to calculate the union of.
     * @return              The probability of the union of the events.
     */
    public double getMutuallyExclusiveEventUnionProbability(Map<String, Double> events, String[] eventNames)
    {
        double sum = 0;
        for (String eventName : eventNames)
        {
            sum += events.get(eventName);
        }
        return sum;
    }

    /**
     * Calculates the conditional probability of event A given event B (P(A|B)).
     * @param jointProbability      The probability of both events A and B occurring (P(A ∩ B)).
     * @param conditionProbability  The probability of event B occurring (P(B)).
     * @return                      The conditional probability of event A given event B (P(A|B)).
     */
    public double getConditionalProbability(double jointProbability, double conditionProbability)
    {
        if (conditionProbability == 0)
        {
            return Double.NaN;
        }
        return jointProbability / conditionProbability;
    }

    /**
     * Checks if two events are independent.
     * @param probabilityOfA    The probability of event A occurring (P(A)).
     * @param probabilityOfB    The probability of event B occurring (P(B)).
     * @param jointProbability  The probability of both events A and B occurring (P(A ∩ B)).
     * @return                  True if the events are independent, false otherwise.
     */
    public boolean checkIndependence(double probabilityOfA, double probabilityOfB, double jointProbability)
    {
        return probabilityOfA * probabilityOfB == jointProbability;
    }

    /**
     * Checks if the subsets form a partition of the set.
     * @param set       The set to partition.
     * @param subsets   The subsets that form the partition.
     * @return          True if the subsets form a partition of the set, false otherwise.
     */
    public boolean checkPartition(Set<Integer> set, Set<Set<Integer>> subsets)
    {
        // Step 1: Check if the union of the subsets is equal to the set.
        Set<Integer> union = new HashSet<>();
        for (Set<Integer> subset : subsets)
        {
            union.addAll(subset);
        }
    
        // If the union of the subsets is not equal to the set, then return false.
        if (!union.equals(set))
        {
            return false;
        }

        // Step 2: Check if the subsets are disjoint (i.e., their intersection is empty).
        for (Set<Integer> subset1 : subsets)
        {
            for (Set<Integer> subset2 : subsets)
            {
                if (subset1 != subset2 && !Collections.disjoint(subset1, subset2))
                {
                    return false;
                }
            }
        }

        // At this point, the subsets are a partition of the set, so return true.
        return true;
    }

    /**
     * Calculates the expected value of a discrete random variable.
     * @param probabilities The map of outcomes and their probabilities.
     * @return              The expected value of the random variable.
     */
    public double getExpectedValueOfRandomVariable(Map<Integer, Double> probabilities)
    {
        double expectedValue = 0;

        // For each outcome and its probability, multiply the outcome by its probability and add it to the expected value.
        for (Map.Entry<Integer, Double> entry : probabilities.entrySet())
        {
            expectedValue += entry.getKey() * entry.getValue();
        }

        // Return the expected value.
        return expectedValue;
    }

    /**
     * Calculates the variance of a discrete random variable.
     * @param probabilities The map of outcomes and their probabilities.
     * @return              The variance of the random variable.
     */
    public double getVarianceOfRandomVariable(Map<Integer, Double> probabilities)
    {
        // Calculate the mean (μ) of the random variable
        double mean = getExpectedValueOfRandomVariable(probabilities);
        
        // Calculate V(Y) = E[(Y - μ)^2]
        double variance = 0;
        for (Map.Entry<Integer, Double> entry : probabilities.entrySet())
        {
            int outcome = entry.getKey();
            double probability = entry.getValue();
            variance += Math.pow(outcome - mean, 2) * probability;
        }

        // Return the variance of the random variable
        return variance;
    }

    /**
     * Calculates the standard deviation of a discrete random variable.
     * @param probabilities The map of outcomes and their probabilities.
     * @return              The standard deviation of the random variable.
     */
    public double getStandardDeviationOfRandomVariable(Map<Integer, Double> probabilities)
    {
        // Calculate the variance of the random variable
        double variance = getVarianceOfRandomVariable(probabilities);

        // Return the square root of the variance to get the standard deviation
        return Math.sqrt(variance);
    }

    /**
     * Calculates the binomial distribution probability for given parameters.
     * @param n The number of trials.
     * @param k The number of successes.
     * @param p The probability of success on each trial.
     * @return  The binomial distribution probability.
     */
    public double getBinomialProbability(int n, int y, double p)
    {
        // Calculate the binomial coefficient (nCy).
        double binomialCoefficient = factorial(n).divide(factorial(y).multiply(factorial(n - y))).doubleValue();

        // Calculate the probability of success raised to the power of y (p^y).
        double successProbability = Math.pow(p, y);

        // Calculate the probability of failure raised to the power of n - y (q^(n-y)).
        double failureProbability = Math.pow(1 - p, n - y);

        // Return the binomial distribution probability (nCy * p^y * q^(n-y)).
        return binomialCoefficient * successProbability * failureProbability;
    }

    /**
     * Calculates the expected value of a binomial distribution.
     * @param n The number of trials.
     * @param p The probability of success on each trial.
     * @return  The expected value of the binomial distribution (E(Y)).
     */
    public double getBinomialExpectedValue(int n, double p)
    {
        return n * p;
    }

    /**
     * Calculates the variance of a binomial distribution.
     * @param n The number of trials.
     * @param p The probability of success on each trial.
     * @return  The variance of the binomial distribution (V(Y)).
     */
    public double getBinomialVariance(int n, double p)
    {
        return n * p * (1 - p);
    }

    /**
     * Calculates the standard deviation of a binomial distribution.
     * @param n The number of trials.
     * @param p The probability of success on each trial.
     * @return  The standard deviation of the binomial distribution (σ(Y)).
     */
    public double getBinomialStandardDeviation(int n, double p)
    {
        return Math.sqrt(getBinomialVariance(n, p));
    }

    /**
     * Calculates the geometric distribution probability for given parameters.
     * @param y The number of trials until the first success.
     * @param p The probability of success on each trial.
     * @return  The geometric distribution probability of the first success on the y-th trial.
     */
    public double getGeometricProbability(int y, double p)
    {
        // Calculate the probability of failure raised to the power of y - 1 ((1 - p)^(y-1)).
        double failureProbability = Math.pow(1 - p, y - 1);

        double successProbability = p;
        
        // Return the geometric distribution probability (q^(y-1) * p).
        return failureProbability * successProbability;
    }

    /**
     * Calculates the expected value of a geometric distribution.
     * @param p The probability of success on each trial.
     * @return  The expected value of the geometric distribution (E(Y)).
     */
    public double getGeometricExpectedValue(double p)
    {
        return 1 / p;
    }

    /**
     * Calculates the variance of a geometric distribution.
     * @param p The probability of success on each trial.
     * @return  The variance of the geometric distribution (V(Y)).
     */
    public double getGeometricVariance(double p)
    {
        return (1 - p) / (p * p);
    }

    /**
     * Calculates the standard deviation of a geometric distribution.
     * @param p The probability of success on each trial.
     * @return  The standard deviation of the geometric distribution (σ(Y)).
     */
    public double getGeometricStandardDeviation(double p)
    {
        return Math.sqrt(getGeometricVariance(p));
    }

    /**
     * Calculates the cumulative distribution function (CDF) for a geometric distribution (P(X <= n)).
     * This function returns the probability that the first success occurs on or before the n-th trial.
     * @param n The number of trials until the first success.
     * @param p The probability of success on each trial.
     * @return  The cumulative distribution function (CDF) of the geometric distribution (P(X <= n)).
     */
    public double getGeometricCDF(int n, double p)
    {
        return 1 - Math.pow(1 - p, n);
    }

    /**
     * Calculates the shifted CDF for a geometric distribution (P(X < n)).
     * This function returns the probability that the first success occurs before the n-th trial.
     * @param n The number of trials until the first success.
     * @param p The probability of success on each trial.
     * @return  The shifted CDF for the geometric distribution (P(X < n)).
     */
    public double getGeometricShiftedCDF(int n, double p)
    {
        return 1 - Math.pow(1 - p, n - 1);
    }

    /**
     * Calculates the complementary CDF (CCDF) for a geometric distribution (P(X >= n)).
     * This function returns the probability that the first success occurs on or after the n-th trial.
     * @param n The number of trials until the first success.
     * @param p The probability of success on each trial.
     * @return  The complementary CDF (CCDF) of the geometric distribution (P(X >= n)).
     */
    public double getGeometricCCDF(int n, double p)
    {
        return Math.pow(1 - p, n - 1);
    }

    /**
     * Calculates the shifted CCDF for a geometric distribution (P(X > n)).
     * This function returns the probability that the first success occurs after the n-th trial.
     * @param n The number of trials until the first success.
     * @param p The probability of success on each trial.
     * @return  The shifted CCDF for the geometric distribution (P(X > n)).
     */
    public double getGeometricShiftedCCDF(int n, double p)
    {
        return Math.pow(1 - p, n);
    }

    /**
     * Calculates the hypergeometric distribution probability for given parameters.
     * @param N The population size (total number of items).
     * @param n The sample size (number of items drawn).
     * @param r The number of successes in the population.
     * @param y The number of observed successes in the sample.
     * @return  The hypergeometric distribution probability.
     */
    public double getHypergeometricProbability(int N, int n, int r, int y)
    {
        double numerator = combination(r, y).doubleValue() * combination(N - r, n - y).doubleValue();
        double denominator = combination(N, n).doubleValue();
        
        return numerator / denominator;
    }

    /** 
     * Calculates the expected value of a hypergeometric distribution.
     * @param N The population size (total number of items).
     * @param n The sample size (number of items drawn).
     * @param r The number of successes in the population.
     * @return  The expected value of the hypergeometric distribution (E(Y)).
     */
    public double getHypergeometricExpectedValue(int N, int n, int r)
    {
        return (double) n * r / N;
    }

    /**
     * Calculates the variance of a hypergeometric distribution.
     * @param N The population size (total number of items).
     * @param n The sample size (number of items drawn).
     * @param r The number of successes in the population.
     * @return  The variance of the hypergeometric distribution (V(Y)).
     */
    public double getHypergeometricVariance(int N, int n, int r)
    {
        double part1 = getHypergeometricExpectedValue(N, n, r);
        double part2 = (double) (N - n) / (N - 1);
        double part3 = (double) (N - r) / N;
        return part1 * part2 * part3;
    }

    /**
     * Calculates the standard deviation of a hypergeometric distribution.
     * @param N The population size (total number of items).
     * @param n The sample size (number of items drawn).
     * @param r The number of successes in the population.
     * @return  The standard deviation of the hypergeometric distribution (σ(Y)).
     */
    public double getHypergeometricStandardDeviation(int N, int n, int r)
    {
        return Math.sqrt(getHypergeometricVariance(N, n, r));
    }

    /**
     * Calculates the negative binomial distribution probability for given parameters.
     * @param p The probability of success on each trial.
     * @param y The number of attempts.
     * @param r The number of successes.
     * @return  The negative binomial distribution probability.
     */
    public double getNegativeBinomialProbability(int p, int y, int r)
    {
        double part1 = combination(y - 1, r - 1).doubleValue();
        double part2 = Math.pow(p, r);
        double part3 = Math.pow(1 - p, y - r);

        return part1 * part2 * part3;
    }

    /**
     * Calculates the expected value of a negative binomial distribution.
     * @param p The probability of success on each trial.
     * @param r The number of successes.
     * @return  The expected value of the negative binomial distribution (E(Y)).
     */
    public double getNegativeBinomialExpectedValue(int p, int r)
    {
        return (double) r / p;
    }

    /**
     * Creates a set of all distinct values from two lists.
     *
     * @param list1 The first list of values.
     * @param list2 The second list of values.
     * @return      A list containing all distinct values from list1 and list2.
     */
    public <T> ArrayList<T> union(ArrayList<T> list1, ArrayList<T> list2)
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
    public <T> ArrayList<T> intersect(ArrayList<T> list1, ArrayList<T> list2)
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
    public <T> ArrayList<T> complement(ArrayList<T> sample, ArrayList<T> subset)
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

    /**
     * Computes nPr (number of permutations of n items taken r at a time).
     * P(n, r) = n! / (n - r)!.
     *
     * @param n The total number of items.
     * @param r The number of items to choose.
     * @return  The number of permutations.
     */
    public BigInteger permutation(int n, int r)
    {
        return factorial(n).divide(factorial(n - r));
    }

    /**
     * Computes nCr (number of combinations of n items taken r at a time).
     * P(n, r) = n! / (r! * (n - r)!).
     *
     * @param n The total number of items.
     * @param r The number of items to choose.
     * @return  The number of combinations.
     */
    public BigInteger combination(int n, int r)
    {
        return factorial(n).divide(factorial(r).multiply(factorial(n - r)));
    }

    /**
     * Calculates m * n (mn rule)
     * @param m The first value to multiply.
     * @param n The second value to multiply.
     * @return  The product of m and n.
     */
    public double getMN(double m, double n)
    {
        return m * n;
    }

    /**
     * Calculates the multinomial coefficient for a given n and an array of counts.
     * @param n         The total number of items (n).
     * @param counts    The array of counts for each category (n_1, n_2, ..., n_r).
     * @return          The multinomial coefficient (n! / (n_1! * n_2! * ... * n_r!)).
     */
    public double getMultinomialCoefficient(int n, int[] counts)
    {
        // Calculate the factorial of n
        BigInteger numerator = factorial(n);

        // Calculate the product of the factorials of the counts
        BigInteger denominator = BigInteger.ONE;
        for (int count : counts)
        {
            denominator = denominator.multiply(factorial(count));
        }

        // Return the multinomial coefficient
        return numerator.divide(denominator).doubleValue();
    }

    /**
     * Calculates the probability of the intersection of two independent events.
     * @param probabilityOfA    The probability of event A occurring (P(A)).
     * @param probabilityOfB    The probability of event B occurring (P(B)).
     * @return                  The probability of the intersection of A and B (P(A ∩ B)) for independent events.
     */
    public double getIndependentIntersectionProbability(double probabilityOfA, double probabilityOfB)
    {
        return probabilityOfA * probabilityOfB;
    }

    /**
     * Calculates the probability of the intersection of two dependent events.
     * @param probabilityOfA        The probability of event A occurring (P(A)).
     * @param probabilityOfBGivenA  The conditional probability of event B given A (P(B|A)).
     * @return                      The probability of the intersection of A and B (P(A ∩ B)) for dependent events.
     */
    public double getDependentIntersectionProbability(double probabilityOfA, double probabilityOfBGivenA)
    {
        return probabilityOfA * probabilityOfBGivenA;
    }

    /**
     * Calculates the probability of the union of two events using the inclusion-exclusion principle.
     * @param probabilityOfA            The probability of event A occurring (P(A)).
     * @param probabilityOfB            The probability of event B occurring (P(B)).
     * @param intersectionProbability   The probability of the intersection of A and B (P(A ∩ B)).
     * @return                          The probability of the union of A and B (P(A ∪ B)).
     */
    public double getUnionProbability(double probabilityOfA, double probabilityOfB, double intersectionProbability)
    {
        return probabilityOfA + probabilityOfB - intersectionProbability;
    }

    /**
     * Calculates the total probability of an event A using the law of total probability.
     * @param probabilitiesAGivenBi An array of probabilities of event A given each event Bi (P(A|B_i)).
     * @param probabilitiesBi       An array of probabilities of each event Bi (P(B_i)).
     * @return                      The total probability of event A.
     */
    public double lawOfTotalProbability(double[] probabilitiesAGivenBi, double[] probabilitiesBi)
    {
        double totalProbability = 0;

        // Sum the product of P(A|B_i) * P(B_i) for each I
        for (int i = 0; i < probabilitiesAGivenBi.length; i++)
        {
            totalProbability += probabilitiesAGivenBi[i] * probabilitiesBi[i];
        }

        // Return the total probability of event A
        return totalProbability;
    }

    /**
     * Calculates the posterior probability of event A given event B using Bayes' theorem (P(A|B)).
     * @param probabilityOfA        The probability of event A occurring (P(A)).
     * @param probabilityOfB        The probability of event B occurring (P(B)).
     * @param probabilityOfBGivenA  The probability of event B given A (P(B|A)).
     * @return                      The probability of event A given event B (P(A|B)).
     */
    public double bayesTheorem(double probabilityOfA, double probabilityOfB, double probabilityOfBGivenA)
    {
        return (probabilityOfBGivenA * probabilityOfA) / probabilityOfB;
    }

    /**
     * Calculates the factorial of a number.
     * @param n The number to calculate the factorial of.
     * @return  The factorial of the number.
     */
    public BigInteger factorial(int n)
    {
        BigInteger result = BigInteger.ONE;
        for (int i = n; i > 1; i--)
        {
            result = result.multiply(BigInteger.valueOf(i));
        }
        return result;
    }
}