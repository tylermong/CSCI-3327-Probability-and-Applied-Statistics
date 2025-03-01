package pile;

import card.Card;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public abstract class Pile
{
    protected List<Card> cards;
    private int size;

    public Pile()
    {
        this.cards = new ArrayList<>();
        this.size = 0;
    }

    public void addCard(Card card)
    {
        cards.add(card);
        size++;
    }

    public void removeCard(Card card)
    {
        cards.remove(card);
    }

    public Card drawCard()
    {
        if (isEmpty())
        {
            return null;
        }
        Card card = cards.remove(cards.size() - 1);
        size--;
        return card;
    }

    public void shuffle()
    {
        Collections.shuffle(cards);
    }

    public Card getCardAtIndex(int index)
    {
        return cards.get(index);
    }

    public List<Card> getCards()
    {
        return cards;
    }

    public int getSize()
    {
        return size;
    }

    public boolean isEmpty()
    {
        return cards.isEmpty();
    }
}