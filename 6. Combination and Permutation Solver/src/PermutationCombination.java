import java.math.BigInteger;

/**
 * Contains methods for the following operations: permutation, combination, factorial
 *
 * @author  Tyler Mong
 * @version 1.0
 */
public class PermutationCombination
{
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
     * Calculates the factorial of n by multiplying all integers from n to 1.
     *
     * @param n The number to calculate the factorial of.
     * @return  The factorial of n.
     */
    public BigInteger factorial(int n)
    {
        BigInteger result = BigInteger.ONE;
        for (int i = n; i >= 1; i--)
        {
            result = result.multiply(BigInteger.valueOf(i));
        }
        return result;
    }
}