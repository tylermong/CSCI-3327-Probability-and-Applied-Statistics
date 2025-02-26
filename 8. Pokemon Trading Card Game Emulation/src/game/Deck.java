package game;

import card.Card;
import java.util.List;
import java.util.ArrayList;
import java.util.Collections;

public class Deck
{
    private List<Card> cards;
    
    public Deck(List<Card> cards)
    {
        this.cards = new ArrayList<>(cards);
        shuffle();
    }

    public void shuffle()
    {
        Collections.shuffle(cards);
    }

    public Card draw()
    {
        Card card = cards.get(0);
        cards.remove(0);
        return card;
    }
}