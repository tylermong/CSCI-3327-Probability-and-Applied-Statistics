package pile;

import card.Card;
import java.util.ArrayList;
import java.util.List;

public abstract class Pile
{
    protected List<Card> cards;

    public Pile()
    {
        this.cards = new ArrayList<>();
    }

    public void addCard(Card card)
    {
        cards.add(card);
    }

    public void removeCard(Card card)
    {
        cards.remove(0);
    }
}