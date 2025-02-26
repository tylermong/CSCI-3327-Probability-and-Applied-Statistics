package card.trainer.helper;

import pile.piles.Deck;
import pile.piles.Hand;

public class TrainerEffect
{
    // TODO: add deck and hand once made
    public static void drawCards(Deck deck, Hand hand, int numCards)
    {
        // TODO: consider edge case of deck being empty (figure out what happens -- ie does they player run out of cards
        // or do they add cards back to the deck?)
        for (int i = 0; i < numCards; i++)
        {
            if (!deck.isEmpty())
            {
                hand.addCard(deck.drawCard());
            }
            else
            {
                System.out.println("Deck is empty, cannot draw more cards");
                break;
            }
        }
    }
}