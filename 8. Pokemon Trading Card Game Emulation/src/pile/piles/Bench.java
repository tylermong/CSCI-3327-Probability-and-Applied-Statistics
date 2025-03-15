package pile.piles;

import pile.Pile;

/**
 * Represents a bench in the Pokémon Trading Card Game, where the player's benched Pokémon are placed.
 * The bench can hold a maximum of 5 Pokémon cards.
 * This class extends the Pile class and provides a method for getting the maximum bench size.
 * 
 * @author  Tyler Mong
 * @version 1.0
 */
public class Bench extends Pile
{
    // Constant to define the maximum number of Pokémon cards that can be placed on the bench. This is set to 5 as per the game rules.
    private static final int MAX_SIZE = 5;

    /**
     * Constructor for the Bench class. Initializes the bench pile for the player.
     */
    public Bench()
    {
        super();
    }

    /**
     * Returns the maximum number of Pokémon cards that can be placed on the bench.
     * @return  The maximum size of the bench, which is a constant value of 5.
     */
    public int getMaxSize()
    {
        return MAX_SIZE;
    }
}