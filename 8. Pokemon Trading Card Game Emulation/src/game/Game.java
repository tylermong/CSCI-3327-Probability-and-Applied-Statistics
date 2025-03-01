package game;

import pile.piles.*;
import card.Card;
import java.util.Scanner;

public class Game
{
    private Player player1, player2, currentPlayer;
    private String coin;
    private static final Scanner in = new Scanner(System.in);

    public Game()
    {
        player1 = new Player("Player 1");
        player2 = new Player("Player 2");
        
        selectDeck(player1);
        selectDeck(player2);
    }

    public void setupGame()
    {
        // Step 1: Shake hands with your opponent.
        System.out.println("Shaking hands...");
        System.out.println(player1.getName() + " and " + player2.getName() + " have shaken hands.\n");

        // Step 2: Flip a coin. The winner of the coin flip decides which player goes first. 
        System.out.println("Flipping a coin to determine who goes first...");
        coin = flipCoin();
        System.out.println("Coin flip result: " + coin);
        if (coin.equals("Heads"))
        {
            currentPlayer = player1;
            System.out.println(player1.getName() + " goes first.");
        }
        else
        {
            currentPlayer = player2;
            System.out.println(player2.getName() + " goes first.");
        }

        // Step 3: Shuffle your 60-card deck and draw the top 7 cards.
        // Step 4: Check to see if you have any Basic Pokémon in your hand.
        System.out.println("\n" + player1.getName() + " shuffles their deck and draws 7 cards.");
        player1.getDeck().shuffle();
        for (int i = 0; i < 7; i++)
        {
            Card card = player1.getDeck().drawCard();
            player1.getHand().addCard(card);
        }
        validateHand(player1);

        System.out.println("\n" + player2.getName() + " shuffles their deck and draws 7 cards.");
        player2.getDeck().shuffle();
        for (int i = 0; i < 7; i++)
        {
            Card card = player2.getDeck().drawCard();
            player2.getHand().addCard(card);
        }
        validateHand(player2);
    }

    public String flipCoin()
    {
        if (Math.random() < 0.5)
        {
            return "Heads";
        }
        return "Tails";
    }

    public void validateHand(Player player)
    {
        while (!player.getHand().hasBasicPokemon())
        {
            System.out.println(player.getName() + " does not have any Basic Pokémon in their hand."
            + " They add all cards back to their deck, shuffle it, and draw 7 new cards.");

            // Return all cards in hand to the deck
            for (int i = 0; i < player.getHand().getSize(); i++)
            {
                Card card = player.getHand().drawCard();
                player.getDeck().addCard(card);
            }

            // Shuffle the deck and draw 7 new cards
            System.out.println(player.getName() + " shuffles their deck again and draws 7 new cards.");
            player.getDeck().shuffle();
            for (int i = 0; i < 7; i++)
            {
                Card card = player.getDeck().drawCard();
                player.getHand().addCard(card);
            }
        }
    }

    private void selectDeck(Player player)
    {
        DeckBuilder.initializePrebuiltDecks();
        String[] decks = DeckBuilder.getAllPrebuiltDecks();

        // Get deck selection from the player
        System.out.println("Select a deck for " + player.getName() + ": ");
        for (int i = 0; i < decks.length; i++)
        {
            System.out.println("\t" + (i + 1) + ". " + decks[i]);
        }
        System.out.print(player.getName() + " selection: ");
        int deckChoice = in.nextInt() - 1;

        // Validate the selection
        while (deckChoice < 0 || deckChoice >= decks.length)
        {
            System.out.print("Invalid selection. Please choose again (1 - " + decks.length + "): ");
            deckChoice = in.nextInt() - 1;
        }
        
        // Set the selected deck for the player
        System.out.println(player.getName() + " selected " + decks[deckChoice] + " deck.\n");
        player.setDeck(DeckBuilder.getPrebuiltDeck(decks[deckChoice]));
    }
}