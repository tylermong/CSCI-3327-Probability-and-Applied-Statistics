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

        System.out.println("Poisson");
        System.out.println(stats.poissonPMF(1, 0));
        System.out.println(stats.poissonPMF(1, 1));
        System.out.println(stats.poissonPMF(1, 2));
        System.out.println(stats.poissonExpectedValue(2));
        System.out.println(stats.poissonVariance(2));

        System.out.println("------------------------------------");
        
        System.out.println("Tchebysheff's");
        System.out.println(stats.tchebysheffsTheorem(2));
        System.out.println(stats.tchebysheffsTheorem(3));
        System.out.println(stats.tchebysheffsTheorem(4));

        System.out.println("------------------------------------");
        
        System.out.println("Uniform");
        System.out.println(stats.uniformDistribution(0, 10));
        System.out.println(stats.uniformExpectedValue(0, 5));
        System.out.println(stats.uniformVariance(0, 5));
        System.out.println("\n" + stats.uniformDistribution(5, 15));
        System.out.println(stats.uniformExpectedValue(5, 15));
        System.out.println(stats.uniformVariance(5, 15));

        System.out.println("------------------------------------");
       
        System.out.println("Gamma");
        System.out.println(stats.gammaExpectedValue(1, 2));
        System.out.println(stats.gammaVariance(1, 2));
        System.out.println("\n" + stats.gammaExpectedValue(1, 3));
        System.out.println(stats.gammaVariance(1, 3));
        
        System.out.println("------------------------------------");
        
        System.out.println("Chi-Square");
        System.out.println(stats.chiSquareExpectedValue(2));
        System.out.println(stats.chiSquareVariance(2));
        
        System.out.println("------------------------------------");
        
        System.out.println("Exponential");
        System.out.println(stats.exponentialExpectedValue(2));
        System.out.println(stats.exponentialVariance(2));
    }
}