package game;

import pile.piles.*;

public class Game
{
    private Deck player1Deck, player2Deck;
    private Hand player1Hand, player2Hand;
    private Prize player1Prize, player2Prize;
    private DiscardPile player1DiscardPile, player2DiscardPile;

    public Game()
    {
        player1Deck = new Deck();
        player2Deck = new Deck();
        player1Hand = new Hand();
        player2Hand = new Hand();
        player1Prize = new Prize();
        player2Prize = new Prize();
        player1DiscardPile = new DiscardPile();
        player2DiscardPile = new DiscardPile();
    }

    public void startGame()
    {
        player1Deck.shuffle();
        player2Deck.shuffle();
    }
}