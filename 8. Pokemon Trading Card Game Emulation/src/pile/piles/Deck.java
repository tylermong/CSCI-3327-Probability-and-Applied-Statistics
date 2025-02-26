package pile.piles;

import pile.Pile;
import card.Card;
import java.util.Collections;

public class Deck extends Pile
{
    public Deck()
    {
        super();
    }

    public void shuffle()
    {
        Collections.shuffle(cards);
    }

    public Card drawCard()
    {
        return cards.remove(0);
    }
}