package game;

import pile.piles.*;

public class Player
{
    private String name;
    private Deck deck;
    private Hand hand;
    private Active active;
    private Bench bench;
    private Prize prizeCards;
    private DiscardPile discardPile;
    
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

    public String getName()
    {
        return name;
    }
  
    public Deck getDeck()
    {
        return deck;
    }

    public void setDeck(Deck deck)
    {
        this.deck = deck;
    }
    
    public Hand getHand()
    {
        return hand;
    }
    
    public Prize getPrizeCards()
    {
        return prizeCards;
    }
    
    public DiscardPile getDiscardPile()
    {
        return discardPile;
    }

    public Active getActive()
    {
        return active;
    }

    public Bench getBench()
    {
        return bench;
    }
}