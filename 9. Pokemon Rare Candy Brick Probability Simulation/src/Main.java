import java.util.List;

/**
 * This class runs the simulation of the Pokemon Rare Candy Brick Probability. It initializes the simulator and runs the
 * simulation for a specified number of rounds.
 * 
 * @author  Tyler Mong
 * @version 1.0
 */
public class Main
{
    /**
     * Runs the simulation and prints the results to the console.
     * @param args  Command line arguments.
     */
    public static void main(String[] args)
    {
        MulliganSimulator simulator = new MulliganSimulator();
        int numberOfRounds = 100000;
        
        List<String> results = simulator.runSimulation(numberOfRounds);
        results.forEach(System.out::println);
    }
}