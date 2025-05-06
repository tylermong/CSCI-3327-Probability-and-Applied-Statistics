import java.math.BigInteger;

/**
 * This class provides methods to calculate various statistical properties.
 */
public class StatsLibrary
{
    // Calculates Poisson distribution for a given lambda and y
    public double poissonPMF(double lambda, int y)
    {
        return Math.pow(lambda, y) * Math.exp(-lambda) / factorial(y).doubleValue();
    }

    // Calculates E(Y) for Poisson distribution
    public double poissonExpectedValue(double lambda)
    {
        return lambda;
    }

    // Calculates V(Y) for Poisson distribution
    public double poissonVariance(double lambda)
    {
        return lambda;
    }

    // Calculates lower bound using Tchebysheff's theorem
    public double tchebysheffsTheorem(double k)
    {
        return 1 - (1 / Math.pow(k, 2));
    }

    // Calculates uniform distribution for a given a and b
    public double uniformDistribution(double a, double b)
    {
        if (a <= b)
        {
            return 1 / (b - a);
        }
        else
        {
            return 0;
        }
    }

    // Calculates the E(Y) for uniform distribution
    public double uniformExpectedValue(double a, double b)
    {
        return (a + b) / 2;
    }

    // Calculates the V(Y) for uniform distribution
    public double uniformVariance(double a, double b)
    {
        return Math.pow(b - a, 2) / 12;
    }

    // Calculates the E(Y) for a gamma distribution
    public double gammaExpectedValue(double alpha, double beta)
    {
        return alpha / beta;
    }

    // Calculates the V(Y) for a gamma distribution
    public double gammaVariance(double alpha, double beta)
    {
        return alpha / Math.pow(beta, 2);
    }

    // Calculates E(Y) for a chi-square distribution
    public double chiSquareExpectedValue(double v)
    {
        return v;
    }

    // Calculates V(Y) for a chi-square distribution
    public double chiSquareVariance(double v)
    {
        return 2 * v;
    }

    // Calculates the E(Y) for a exponential distribution
    public double exponentialExpectedValue(int beta)
    {
        return beta;
    }

    // Calculates the V(Y) for a exponential distribution
    public double exponentialVariance(int beta)
    {
        return Math.pow(beta, 2);
    }

    // Calculates the factorial of a number
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
