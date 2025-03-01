package game;

import pile.piles.*;
import java.util.Scanner;

public class Game
{
    private Player player1, player2, currentPlayer;
    private static final Scanner in = new Scanner(System.in);

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
        int player1DeckChoice = in.nextInt() - 1;
        player1.setDeck(DeckBuilder.getPrebuiltDeck(decks[player1DeckChoice]));

        System.out.println("Select a deck for Player 2:");
        for (int i = 0; i < decks.length; i++)
        {
            System.out.println((i + 1) + ". " + decks[i]);
        }
        int player2DeckChoice = in.nextInt() - 1;
        player2.setDeck(DeckBuilder.getPrebuiltDeck(decks[player2DeckChoice]));
    }
}