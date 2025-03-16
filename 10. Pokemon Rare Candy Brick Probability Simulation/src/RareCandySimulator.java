import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

/**
 * This class computes the probability of bricking with N (1-4) rare candies in a deck, by simulating a deck, hand, and
 * prize pile.
 */
public class RareCandySimulator
{
    // Constants to represent the size of the deck, hand, prizes, and the maximum number of rare candies.
    private final int DECK_SIZE = 60;
    private final int HAND_SIZE = 7;
    private final int PRIZE_SIZE = 6;
    private final int MAX_RARE_CANDIES = 4;

    // Arrays to represent the deck, hand, and prizes.
    private int[] deck = new int[DECK_SIZE];
    private int[] hand = new int[HAND_SIZE];
    private int[] prizes = new int[PRIZE_SIZE];

    // Random object to shuffle the deck.
    private final Random random = new Random();

    /**
     * Runs a simulation to determine the probability of bricking with N (1-4) rare candies.
     * 
     * @param numberOfRounds    The number of rounds to run the simulation.
     * @return                  A list of results for each number of rare candies.
     */
    public List<String> runSimulation(int numberOfRounds)
    {
        List<String> results = new ArrayList<>();

        // Add the header to the results list.
        results.add(String.format("%-15s %-25s %-20s", "# Rare Candy", "# Bricks in " + numberOfRounds + " runs", "Probability (%)"));

        // Loop through each number of rare candies (1-4) and run the simulation.
        for (int numberOfRareCandyInDeck = 1; numberOfRareCandyInDeck <= MAX_RARE_CANDIES; numberOfRareCandyInDeck++)
        {
            int brickCounter = 0;

            // Run the simulation for the specified number of rounds.
            for (int i = 0; i < numberOfRounds; i++)
            {
                // Initialize the deck, shuffle it, draw a hand, and deal prizes.
                // Then check if the hand is bricked and increment the counter if it is.
                initializeDeck(numberOfRareCandyInDeck);
                shuffleDeck();
                drawCard(HAND_SIZE);
                dealPrizes();
                if (isRareCandyBrick(numberOfRareCandyInDeck))
                {
                    brickCounter++;
                }
            }
            // Add the results to the results list.
            results.add(String.format("%-15d %-25d %.2f%%", numberOfRareCandyInDeck, brickCounter, (double) brickCounter / numberOfRounds * 100));
        }
        return results;
    }

    /** 
     * Initializes the deck with the specified number of rare candies. The deck is an array of "cards" (integer values),
     * where 1 represents a Rare Candy card and 0 represents a non-Rare Candy card.
     * 
     * @param numberOfRareCandyInDeck   The number of rare candies to include in the deck.
     */
    public void initializeDeck(int numberOfRareCandyInDeck)
    {
        // Fill the deck with N rare candies (1s) and the rest with other cards (0s).
        Arrays.fill(deck, 0, numberOfRareCandyInDeck, 1);
        Arrays.fill(deck, numberOfRareCandyInDeck, DECK_SIZE, 0);
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
     * Draws N cards from the top of the deck.
     */
    public void drawCard(int numberOfCardsToDraw)
    {
        // Copy the first N cards from the deck to the hand, shift the remaining cards up, and clear the last N positions.
        System.arraycopy(deck, 0, hand, 0, numberOfCardsToDraw);
        System.arraycopy(deck, numberOfCardsToDraw, deck, 0, DECK_SIZE - numberOfCardsToDraw);
        Arrays.fill(deck, DECK_SIZE - numberOfCardsToDraw, DECK_SIZE, -1);
    }

    /**
     * Deals 6 cards to the prize pile.
     */
    public void dealPrizes()
    {
        // Copy the first 6 cards from the deck to the prizes, shift the remaining cards up, and clear the last 6 positions.
        System.arraycopy(deck, 0, prizes, 0, prizes.length);
        System.arraycopy(deck, prizes.length, deck, 0, DECK_SIZE - prizes.length);
        Arrays.fill(deck, DECK_SIZE - PRIZE_SIZE, DECK_SIZE, -1);
    }

    /**
     * Checks if the rare candies are bricked (all N rare candies are in the prize pile).
     * 
     * @param numberOfRareCandyInDeck   The number of rare candies in the deck.
     * @return                          True if the rare candies are bricked, false otherwise.
     */
    public boolean isRareCandyBrick(int numberOfRareCandyInDeck)
    {
        int numberOfRareCandyInPrize = 0;

        // Count the number of rare candies in the prize pile.
        for (int i = 0; i < PRIZE_SIZE; i++)
        {
            if (prizes[i] == 1)
            {
                numberOfRareCandyInPrize++;
            }
        }
        // Return true if the number of rare candies in the prize pile is equal to the number of rare candies in the deck.
        // Otherwise, return false.
        return numberOfRareCandyInPrize == numberOfRareCandyInDeck;
    }
}