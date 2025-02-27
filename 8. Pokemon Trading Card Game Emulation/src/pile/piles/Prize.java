package pile.piles;

import card.Card;
import pile.Pile;

public class Prize extends Pile
{
    public Prize()
    {
        super();
    }

    public void addCard(Card card)
    {
        cards.add(card);
    }

    public Card drawCard()
    {
        return cards.remove(0);
    }
}