package game;

import pile.piles.*;
import card.pokemon.PokemonCard;

public class Player
{
    private Deck deck;
    private Hand hand;
    private Prize prizeCards;
    private DiscardPile discardPile;
    
    public Player(String name)
    {
        this.deck = new Deck();
        this.hand = new Hand();
        this.prizeCards = new Prize();
        this.discardPile = new DiscardPile();
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
}