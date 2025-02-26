package pile.piles;

import card.Card;
import pile.Pile;

public class Hand extends Pile
{
    public Hand()
    {
        super();
    }

    public Card playCard(Card card)
    {
        removeCard(card);
        return card;
    }
}