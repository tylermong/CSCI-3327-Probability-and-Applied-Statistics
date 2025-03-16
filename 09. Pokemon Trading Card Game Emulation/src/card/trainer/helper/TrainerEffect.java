package card.trainer.helper;

import card.Card;
import pile.piles.Deck;
import pile.piles.DiscardPile;
import pile.piles.Hand;

/**
 * This class provides helper methods for Trainer cards in the Pokémon Trading Card Game.
 * It contains methods to draw cards from the deck and discard the player's hand to the discard pile.
 * 
 * @author  Tyler Mong
 * @version 1.0
 */
public class TrainerEffect
{
    /**
     * Draws a specified number of cards from the deck and adds them to the player's hand.
     * 
     * @param deck      The player's deck.
     * @param hand      The player's hand.
     * @param numCards  The number of cards to draw.
     */
    public static void drawCards(Deck deck, Hand hand, int numCards)
    {
        for (int i = 0; i < numCards; i++)
        {
            // Check if the deck is not empty before drawing a card to avoid errors.
            if (!deck.isEmpty())
            {
                hand.addCard(deck.drawCard());
            }
            // If the deck is empty, print a message and break the loop to avoid drawing more cards.
            else
            {
                System.out.println("Deck is empty, cannot draw more cards");
                break;
            }
        }
    }

    /**
     * Discards all cards in the player's hand and adds them to the discard pile.
     * 
     * @param hand          The player's hand.
     * @param discardPile   The player's discard pile.
     */
    public static void discardHand(Hand hand, DiscardPile discardPile)
    {
        // Remove cards until the hand is empty, adding each card to the discard pile.
        while (!hand.isEmpty())
        {
            Card card = hand.getCards().get(0);
            
            hand.removeCard(card);
            discardPile.addCard(card);
        }
    }
}