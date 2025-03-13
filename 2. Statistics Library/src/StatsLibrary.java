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
     * @param jointProbability  The probability of both events A and B occurring (P(A ∩ B)).
     * @param probabilityOfB    The probability of event B occurring (P(B)).
     * @return                  The conditional probability of event A given event B (P(A|B)).
     */
    public double getConditionalProbability(double jointProbability, double probabilityOfB)
    {
        if (probabilityOfB == 0)
        {
            return Double.NaN;
        }
        return jointProbability / probabilityOfB;
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

    /**
     * Calculates the binomial distribution probability for given parameters.
     * @param n The number of trials.
     * @param k The number of successes.
     * @param p The probability of success on each trial.
     * @return  The binomial distribution probability.
     */
    public double getBinomialDistribution(int n, int y, double p)
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
}