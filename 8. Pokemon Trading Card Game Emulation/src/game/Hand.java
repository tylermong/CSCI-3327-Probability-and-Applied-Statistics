package game;

import card.Card;
import java.util.ArrayList;
import java.util.List;

public class Hand
{
    private List<Card> cards;

    public Hand()
    {
        this.cards = new ArrayList<>();
    }

    public void addCard(Card card)
    {
        cards.add(card);
    }

    public void playCard(Card card)
    {
        cards.remove(card);
    }

    public List<Card> getCards()
    {
        return cards;
    }
}