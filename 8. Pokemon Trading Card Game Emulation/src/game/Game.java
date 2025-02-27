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
        // Shuffles the decks
        System.out.println("[System] Starting game...");
        System.out.println("[System] Shuffling decks...");
        player1Deck.shuffle();
        player2Deck.shuffle();

        // Draw initial hand
        System.out.println("[System] Drawing initial hands...");
        for (int i = 0; i < 7; i++)
        {
            player1Hand.addCard(player1Deck.drawCard());
            player2Hand.addCard(player2Deck.drawCard());
        }

        // Place prize cards
        System.out.println("[System] Placing prize cards...");
        for (int i = 0; i < 6; i++)
        {
            player1Prize.addCard(player1Deck.drawCard());
            player2Prize.addCard(player2Deck.drawCard());
        }
        System.out.println("[System] Game started successfully.");
    }
}