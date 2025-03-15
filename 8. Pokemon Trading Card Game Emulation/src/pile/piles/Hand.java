package pile.piles;

import card.Card;
import pile.Pile;

/**
 * Represents the player's hand in the Pokémon Trading Card Game, where the player holds their cards.
 * This class extends the Pile class and provides methods for managing the player's hand, such as playing a card and checking for basic Pokémon.
 * 
 * @author  Tyler Mong
 * @version 1.0
 */
public class Hand extends Pile
{
    /**
     * Constructor for the Hand class. Initializes the player's hand.
     */
    public Hand()
    {
        super();
    }

    /**
     * Plays a card from the player's hand and removes it from the hand.
     * 
     * @param card  The card to be played.
     * @return      The played card.
     */
    public Card playCard(Card card)
    {
        removeCard(card);
        return card;
    }

    /**
     * Checks if the player's hand contains any basic Pokémon cards.
     * This is used in the game setup to check for a Mulligan.
     * @return  True if there are basic Pokémon cards, false otherwise.
     */
    public boolean hasBasicPokemon()
    {
        // For each card in the hand, check if it is a basic Pokémon card.
        for (Card card : cards)
        {
            // If a basic Pokémon card is found, return true.
            if (card.getType().equals("Pokémon"))
            {
                return true;
            }
        }
        
        // If no basic Pokémon cards are found, return false.
        return false;
    }
}