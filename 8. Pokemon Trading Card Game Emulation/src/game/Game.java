package game;

import pile.piles.*;

public class Game
{
    private Player player1, player2, currentPlayer;

    public Game()
    {
        player1 = new Player("Player 1");
        player2 = new Player("Player 2");
        
        selectDecks();
    }

    private void selectDecks()
    {
        DeckBuilder.initializePrebuiltDecks();
        String[] decks = DeckBuilder.getAllPrebuiltDecks();
        System.out.println("Select a deck for Player 1:");
        for (int i = 0; i < decks.length; i++)
        {
            System.out.println((i + 1) + ". " + decks[i]);
        }

    }
}