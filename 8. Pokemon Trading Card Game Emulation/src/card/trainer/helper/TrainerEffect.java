package card.trainer.helper;

import pile.piles.Deck;
import pile.piles.Hand;

public class TrainerEffect
{
    public static void drawCards(Deck deck, Hand hand, int numCards)
    {
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