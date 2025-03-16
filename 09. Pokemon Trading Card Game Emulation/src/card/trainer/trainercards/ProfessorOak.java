package card.trainer.trainercards;

import card.trainer.TrainerCard;
import card.trainer.helper.TrainerEffect;
import pile.piles.Deck;
import pile.piles.DiscardPile;
import pile.piles.Hand;

/**
 * This class represents the Trainer card "Professor Oak" in the Pokémon Trading Card Game.
 * It extends the TrainerCard class and provides an overridden method to use the unique effect of this card.
 * The effect of this card is to discard the player's hand and then draw 7 cards from the player's deck.
 * 
 * @author  Tyler Mong
 * @version 1.0
 */
public class ProfessorOak extends TrainerCard
{
    /**
     * Constructor to initialize the "Professor Oak" trainer card with its name and description.
     * This constructor calls the superclass constructor to set the name and description of the card.
     */
    public ProfessorOak()
    {
        super("Professor Oak", "Discard your hand, then draw 7 cards.");
    }

    /**
     * Uses the effect of the "Professor Oak" trainer card, which is to discard the player's hand and then draw 7 cards from the player's deck.
     * Prints a message indicating the effect being used and calls the TrainerEffect class to perform the actions.
     * 
     * @param deck          The player's deck.
     * @param hand          The player's hand.
     * @param discardPile   The player's discard pile.
     */
    @Override
    public void useEffect(Deck deck, Hand hand, DiscardPile discardPile)
    {
        System.out.println("Using effect: Discard your hand, then draw 7 cards.");
        TrainerEffect.discardHand(hand, discardPile);
        TrainerEffect.drawCards(deck, hand, 7);
    }
}