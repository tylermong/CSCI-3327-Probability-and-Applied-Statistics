package card.trainer;

import card.Card;
import pile.piles.Deck;
import pile.piles.DiscardPile;
import pile.piles.Hand;

/**
 * This class represents a Trainer card in the Pokémon Trading Card Game.
 * It extends the Card class and provides methods to get the description of the card and use its effect.
 * It is an abstract class that serves as a base for specific trainer cards.
 * 
 * @author  Tyler Mong
 * @version 1.0
 */
public abstract class TrainerCard extends Card
{
    // Stores the description of the trainer card.
    private String description;

    /**
     * Constructor to initialize a trainer card with a name and description.
     * This constructor is called by the subclasses to create a new trainer card.
     * 
     * @param name          The name of the trainer card.
     * @param description   The description of the trainer card, explaining its effect.
     */
    public TrainerCard(String name, String description)
    {
        super(name, "Trainer");
        this.description = description;
    }

    /**
     * Returns the description of the trainer card.
     * @return  The description of the trainer card, explaining its effect.
     */
    public String getDescription()
    {
        return description;
    }

    /**
     * Abstract method to use the effect of the trainer card.
     * 
     * @param deck          The player's deck.
     * @param hand          The player's hand.
     * @param discardPile   The player's discard pile (null if not applicable).
     */
    public abstract void useEffect(Deck deck, Hand hand, DiscardPile discardPile);

    /**
     * Overloaded method to use the effect of the trainer card without a discard pile.
     * This method calls the abstract useEffect method with a null discard pile.
     * 
     * @param deck  The player's deck.
     * @param hand  The player's hand.
     */
    public void useEffect(Deck deck, Hand hand)
    {
        useEffect(deck, hand, null);
    }
}