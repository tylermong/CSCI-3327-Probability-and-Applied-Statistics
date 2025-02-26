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

    /**
     * Draws a card from the top of the deck.
     * @return  The card from the top of the deck.
     */
    public Card draw()
    {
        // List.remove returns the element previously at the specified position, in this case the card on top.
        return cards.remove(0);
    }
}