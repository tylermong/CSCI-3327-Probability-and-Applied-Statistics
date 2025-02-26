package game;

import card.Card;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

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

    /**
     * Draws a card from the top of the deck.
     * @return  The card from the top of the deck.
     */
    public Card draw()
    {
        // List.remove removes the element at the specified index and returns it.
        return cards.remove(0);
    }
}