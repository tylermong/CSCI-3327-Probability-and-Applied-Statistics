import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

/**
 * This class simulates the probability of drawing a hand with no Pokemon (a mulligan) in the Pokemon Trading Card Game.
 * The simulation runs for N rounds and calculates the probability of mulligans for each number of Pokemon in the deck.
 * 
 * @author  Tyler Mong
 * @version 1.0
 */
public class MulliganSimulator
{
    private final int DECK_SIZE = 60;
    private final int HAND_SIZE = 7;
    private int[] deck = new int[DECK_SIZE];
    private int[] hand = new int[HAND_SIZE];
    private final Random random = new Random();

    /**
     * Runs the simulation for N rounds and calculates the mulligan probability for each number of Pokemon in the deck.
     * @param numberOfRounds    The number of rounds to run the simulation.
     * @return                  A table (list of strings) containing the results of the simulation. 
     */
    public List<String> runSimulation(int numberOfRounds)
    {
        // A list to store the results of the simulation.
        List<String> results = new ArrayList<>();

        // Add the header to the results list.
        results.add(String.format("%-15s %-30s %-20s", "# Pokemon", "# Mulligans in " + numberOfRounds + " runs", "Probability (%)"));
        
        // Run the simulation for each number of Pokemon in the deck (0 to 60).
        for (int numberOfPokemonInDeck = 0; numberOfPokemonInDeck <= DECK_SIZE; numberOfPokemonInDeck++)
        {
            int mulliganCounter = 0;

            // Run the simulation for N rounds. Initialize the deck with the specified number of Pokemon, shuffle the
            // deck, draw a hand, and check if the hand is a mulligan. If it is, increment the mulligan counter.
            for (int i = 0; i < numberOfRounds; i++)
            {
                initializeDeck(numberOfPokemonInDeck);
                shuffleDeck();
                drawHand();
                if (isHandMulligan())
                {
                    mulliganCounter++;
                }
            }
            // Add the results to the results list.
            results.add(String.format("%-15d %-30d %.2f%%", numberOfPokemonInDeck, mulliganCounter, (double) mulliganCounter / numberOfRounds * 100));
        }
        return results;
    }

    /**
     * Initializes the deck with the specified number of Pokemon. The deck is an array of "cards" (integer values),
     * where 1 represents a Pokemon card and 0 represents a non-Pokemon card.
     * 
     * @param numberOfPokemonInDeck The number of Pokemon to put in the deck.
     */
    public void initializeDeck(int numberOfPokemonInDeck)
    {
        Arrays.fill(deck, 0, numberOfPokemonInDeck, 1);
        Arrays.fill(deck, numberOfPokemonInDeck, DECK_SIZE, 0);
    }

    /**
     * Shuffles the deck in place using the Fisher-Yates algorithm.
     */
    public void shuffleDeck()
    {
        for (int i = 0; i < DECK_SIZE; i++)
        {
            int randomIndex = random.nextInt(DECK_SIZE);
            int temp = deck[i];
            deck[i] = deck[randomIndex];
            deck[randomIndex] = temp;
        }
    }

    /**
     * Draws a hand from the deck, copying the first 7 cards from the deck into the hand.
     * It does not remove the cards from the deck, as the deck is not modified during the simulation.
     */
    public void drawHand()
    {
        System.arraycopy(deck, 0, hand, 0, HAND_SIZE);
    }

    /**
     * Checks if the hand is a mulligan (contains no Pokemon).
     * 
     * @return True if the hand is a mulligan, false otherwise.
     */
    public boolean isHandMulligan()
    {
        // If the hand contains any Pokemon (a card with a value of 1), then the hand is not a mulligan.
        for (int card : hand)
        {
            if (card == 1)
            {
                return false;
            }
        }
        return true;
    }
}