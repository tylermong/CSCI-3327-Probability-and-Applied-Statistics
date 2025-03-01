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
        
        selectDeck(player1);
        selectDeck(player2);
    }

    private void selectDeck(Player player)
    {
        DeckBuilder.initializePrebuiltDecks();
        String[] decks = DeckBuilder.getAllPrebuiltDecks();

        System.out.println("Select a deck for " + player.getName() + ": ");
        for (int i = 0; i < decks.length; i++)
        {
            System.out.println((i + 1) + ". " + decks[i]);
        }
        int deckChoice = in.nextInt() - 1;
        player.setDeck(DeckBuilder.getPrebuiltDeck(decks[deckChoice]));
    }
}