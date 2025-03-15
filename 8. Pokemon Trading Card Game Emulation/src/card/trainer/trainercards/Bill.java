package card.trainer.trainercards;

import card.trainer.TrainerCard;
import card.trainer.helper.TrainerEffect;
import pile.piles.Deck;
import pile.piles.DiscardPile;
import pile.piles.Hand;

/**
 * This class represents the Trainer card "Bill" in the Pokémon Trading Card Game.
 * It extends the TrainerCard class and provides an overridden method to use the unique effect of this card.
 * The effect of this card is to draw 2 cards from the player's deck.
 * 
 * @author  Tyler Mong
 * @version 1.0
 */
public class Bill extends TrainerCard
{
    /**
     * Constructor to initialize the "Bill" trainer card with its name and description.
     * This constructor calls the superclass constructor to set the name and description of the card.
     */
    public Bill()
    {
        super("Bill", "Draw 2 cards.");
    }

    /**
     * Uses the effect of the "Bill" trainer card, which is to draw 2 cards from the player's deck.
     * Prints a message indicating the effect being used and calls the TrainerEffect class to perform the action.
     * 
     * @param deck          The player's deck.
     * @param hand          The player's hand.
     * @param discardPile   The discard pile where discarded cards will be placed (not used in this effect).
     */
    @Override
    public void useEffect(Deck deck, Hand hand, DiscardPile discardPile)
    {
        System.out.println("Using effect: Draw 2 cards.");
        TrainerEffect.drawCards(deck, hand, 2);
    }
}