import java.util.Arrays;

/**
 * This class calculates the population and sample standard deviation from a set of values.
 * 
 * @author  Tyler Mong
 * @version 1.0
 */
public class Main
{
    /**
     * Runs the calculation of the population and sample standard deviation.
     * 
     * @param args  The command-line arguments.
     */
    public static void main(String[] args)
    {
        // Set of values to calculate the standard deviation
        double[] values = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};

        // Step 1: Find the mean - Add up the population and divide by # of values (N)
        double total = 0;
        for (int i = 0; i < values.length; i++)
            total += values[i];
        double mean = total / values.length;

        // Step 2: Find each value's deviation from the mean - Subtract the mean from the value
        // Step 3: Square each deviation
        // Step 4: Add the squares
        double[] deviation = new double[values.length];
        total = 0;
        for (int i = 0; i < values.length; i++)
        {
            deviation[i] = values[i] - mean;
            deviation[i] *= deviation[i];
            total += deviation[i];
        }

        // Step 5: Find variance - for population divide by N, for sample divide by N - 1
        double sampleVariance = total / (values.length - 1);
        double populationVariance = total / values.length;

        // Step 6: Square root the variance
        double sampleStdDev = Math.sqrt(sampleVariance);
        double populationStdDev = Math.sqrt(populationVariance);

        // prints the (sample) population and the results
        System.out.println(Arrays.toString(values));
        System.out.println("Sample Standard Deviation: " + sampleStdDev);
        System.out.println("Population Standard Deviation: " + populationStdDev);
    }
}