package game;

import pile.piles.*;

/**
 * This class represents a player in the Pokémon Trading Card Game.
 * It contains the player's name, deck, hand, active Pokémon, benched Pokémon, prize cards, and discard pile.
 * It provides methods to access and modify these attributes.
 * 
 * @author  Tyler Mong
 * @version 1.0
 */
public class Player
{
    /**
     * The name of the player.
     */
    private String name;
    
    /**
     * The player's deck of cards.
     */
    private Deck deck;
    
    /**
     * The player's hand of cards.
     */
    private Hand hand;
    
    /**
     * The player's active Pokémon.
     */
    private Active active;
    
    /**
     * The player's benched Pokémon.
     */
    private Bench bench;
    
    /**
     * The player's prize cards.
     */
    private Prize prizeCards;
    
    /**
     * The player's discard pile.
     */
    private DiscardPile discardPile;
    
    /**
     * Constructor to initialize a player with a name and card piles.
     * 
     * @param name  The name of the player.
     */
    public Player(String name)
    {
        this.name = name;
        this.deck = new Deck();
        this.hand = new Hand();
        this.active = new Active();
        this.bench = new Bench();
        this.prizeCards = new Prize();
        this.discardPile = new DiscardPile();
    }
    
    /**
     * Returns the player's name.
     * 
     * @return  The player's name.
     */
    public String getName()
    {
        return name;
    }

    /**
     * Returns the player's deck.
     * 
     * @return  The player's deck.
     */
    public Deck getDeck()
    {
        return deck;
    }

    /**
     * Sets the player's deck.
     * 
     * @param deck  The deck to set for the player.
     */
    public void setDeck(Deck deck)
    {
        this.deck = deck;
    }

    /**
     * Returns the player's hand.
     * 
     * @return  The player's hand.
     */
    public Hand getHand()
    {
        return hand;
    }

    /**
     * Returns the player's prize cards.
     * 
     * @return  The player's prize cards.
     */
    public Prize getPrizeCards()
    {
        return prizeCards;
    }

    /**
     * Returns the player's discard pile.
     * 
     * @return  The player's discard pile.
     */
    public DiscardPile getDiscardPile()
    {
        return discardPile;
    }

    /**
     * Returns the player's active Pokémon.
     *  
     * @return  The player's active Pokémon.
     */
    public Active getActive()
    {
        return active;
    }

    /**
     * Returns the player's benched Pokémon.
     * 
     * @return  The player's benched Pokémon.
     */
    public Bench getBench()
    {
        return bench;
    }
}