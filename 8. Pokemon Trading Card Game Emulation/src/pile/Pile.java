package pile;

import card.Card;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * This class represents a generic pile of cards in the Pokémon Trading Card Game.
 * It provides methods to add, remove, draw, shuffle, get card(s), get size, check if empty, and display the cards in the pile.
 * It is an abstract class that serves as a base for specific pile types such as Deck, Hand, Active, Bench, Prize, and DiscardPile.
 * 
 * @author  Tyler Mong
 * @version 1.0
 */
public abstract class Pile
{
    /**
     * The list of cards in the pile.
     */
    protected List<Card> cards;
    
    /**
     * The size of the pile (the number of cards in the pile).
     */
    private int size;

    /**
     * Constructor to initialize a pile, creating an empty list of cards and setting the size to 0.
     * This constructor is called by the subclasses to create a new pile of cards.
     */
    public Pile()
    {
        this.cards = new ArrayList<>();
        this.size = 0;
    }

    /**
     * Adds a card to the pile and increments the size of the pile.
     * 
     * @param card  The card to be added to the pile.
     */
    public void addCard(Card card)
    {
        cards.add(card);
        size++;
    }

    /**
     * Removes a card from the pile and decrements the size of the pile.
     * 
     * @param card  The card to be removed from the pile.
     */
    public void removeCard(Card card)
    {
        cards.remove(card);
        size--;
    }

    /**
     * Draws a card from the top of the pile and decrements the size of the pile it is drawn from.
     * 
     * @return  The card drawn from the top of the pile, or null if the pile is empty.
     */
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

    /**
     * Shuffles the cards in the pile using the provided Collections.shuffle() method to randomize the order of the cards.
     */
    public void shuffle()
    {
        Collections.shuffle(cards);
    }

    /**
     * Returns the card at the specified index in the pile.
     * 
     * @param index The index of the card to be retrieved from the pile.
     * @return      The card at the specified index in the pile.
     */
    public Card getCardAtIndex(int index)
    {
        return cards.get(index);
    }

    /**
     * Returns the list of cards in the pile.
     * 
     * @return  The list of cards in the pile.
     */
    public List<Card> getCards()
    {
        return cards;
    }

    /**
     * Returns the size of the pile (the number of cards in the pile).
     * 
     * @return  The size of the pile.
     */
    public int getSize()
    {
        return size;
    }

    /**
     * Checks if the pile is empty (i.e., has no cards).
     * 
     * @return  True if the pile is empty, false otherwise.
     */
    public boolean isEmpty()
    {
        return cards.isEmpty();
    }

    /**
     * Displays the cards in the pile and their indices in a numbered list format.
     */
    public void display()
    {
        for (int i = 0; i < cards.size(); i++)
        {
            System.out.println("\t" + (i + 1) + ". " + cards.get(i).getName());
        }
    }

    /**
     * Displays the cards in the pile and their indices in a numbered list format, starting from index 2.
     * This method is used specifically in the Game.attachEnergy(Player) method to display benched Pokémon cards, accounting for the active Pokémon at index 1.
     */
    public void secondaryDisplay()
    {
        for (int i = 0; i < cards.size(); i++)
        {
            System.out.println("\t" + (i + 2) + ". " + cards.get(i).getName());
        }
    }
}