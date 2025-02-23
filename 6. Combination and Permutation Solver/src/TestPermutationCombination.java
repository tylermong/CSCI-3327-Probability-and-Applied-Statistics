/**
 * Tests the following methods from the PermutationCombination class: permutation, combination.
 *
 * @author  Tyler Mong
 * @version 1.0
 */
public class TestPermutationCombination
{
    /**
     * Tests the functionality of the PermutationCombination class methods using sample data.
     * A small and large example are used and the output is printed to the console.
     *
     * @param args  Command line arguments.
     */
    public static void main(String[] args)
    {
        PermutationCombination solver = new PermutationCombination();

        // Expected results: 5P2 = 20, 5C2 = 10
        System.out.println("5P2: " + solver.permutation(5, 2));
        System.out.println("5C2: " + solver.combination(5, 2));

        // Expected results: 50P10 = 37,276,043,023,296,000, 50C10 = 10,272,278,170
        System.out.println("50P10: " + solver.permutation(50, 10));
        System.out.println("50C10: " + solver.combination(50, 10));
    }
}