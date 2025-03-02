package game;

import pile.piles.*;
import card.Card;

import java.util.Arrays;
import java.util.Scanner;

public class Game
{
    private Player player1, player2, currentPlayer, opponent;
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
        sleep(1000);
        System.out.println(player1.getName() + " and " + player2.getName() + " have shaken hands.\n");
        sleep(1000);

        // Step 2: Flip a coin. The winner of the coin flip decides which player goes first. 
        System.out.println("Flipping a coin to determine who goes first...");
        sleep(1000);
        coin = flipCoin();
        System.out.println("Coin flip result: " + coin);
        sleep(1000);
        if (coin.equals("Heads"))
        {
            currentPlayer = player1;
            opponent = player2;
            System.out.println(player1.getName() + " goes first.");
        }
        else
        {
            currentPlayer = player2;
            opponent = player1;
            System.out.println(player2.getName() + " goes first.");
        }
        sleep(1000);

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
        sleep(1000);

        System.out.println("\n" + player2.getName() + " shuffles their deck and draws 7 cards.");
        player2.getDeck().shuffle();
        for (int i = 0; i < 7; i++)
        {
            Card card = player2.getDeck().drawCard();
            player2.getHand().addCard(card);
        }
        validateHand(player2);
        sleep(1000);

        // Step 5: Put one of your Basic Pokémon face down as your Active Pokémon.
        setActivePokemon(player1);
        setActivePokemon(player2);

        // Step 6: Put up to 5 more Basic Pokémon face down on your Bench.
        addBenchPokemon(player1);
        addBenchPokemon(player2);

        // Step 7: Put the top 6 cards of your deck off to the side face down as your Prize cards.
        addPrizePokemon(player1);
        addPrizePokemon(player2);

        startGame();
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
        sleep(250);
    }

    private String flipCoin()
    {
        if (Math.random() < 0.5)
        {
            return "Heads";
        }
        return "Tails";
    }

    // TODO: add draw +1 card if opponent has no basic pokemon
    private void validateHand(Player player)
    {
        while (!player.getHand().hasBasicPokemon())
        {
            System.out.println(player.getName() + " does not have any Basic Pokémon in their hand.");
            sleep(1000);
            System.out.println("Revealing hand...");
            sleep(1000);
            player.getHand().display();
            sleep(1000);
            System.out.println(player.getName() + " adds all cards back to their deck, shuffles it, and draws 7 new cards.");

            // Return all cards in hand to the deck, shuffle, and draw 7 new cards
            int handSize = player.getHand().getSize();
            for (int i = 0; i < handSize; i++)
            {
                Card card = player.getHand().drawCard();
                player.getDeck().addCard(card);
            }
            player.getDeck().shuffle();
            for (int i = 0; i < 7; i++)
            {
                Card card = player.getDeck().drawCard();
                player.getHand().addCard(card);
            }
        }
    }

    // TODO: Validate the card is a basic pokemon
    private void setActivePokemon(Player player)
    {
        System.out.println("\n" + player.getName() + ", please select a Basic Pokémon to put in the Active position.");
        player.getHand().display();
        System.out.print(player.getName() + " selection: ");
        int activeIndex = in.nextInt() - 1;
        while (activeIndex < 0 || activeIndex >= player.getHand().getSize())
        {
            System.out.print("Invalid selection. Please choose again (1 - " + (player.getHand().getSize()) + "): ");
            activeIndex = in.nextInt() - 1;
        }
        Card activeCard = player.getHand().getCardAtIndex(activeIndex);
        player.getActive().addCard(activeCard);
        player.getHand().removeCard(activeCard);
        System.out.println(player.getName() + " put " + activeCard.getName() + " in the Active position.");
        sleep(1000);
    }

    // TODO: Validate the cards are basic pokemon
    // TODO: Fix automatic selection for player1
    private void addBenchPokemon(Player player)
    {
        System.out.println("\n" + player.getName() + ", select up to " + (5 - player.getBench().getSize()) + " Basic Pokémon to put on the Bench.");
        player.getHand().display();
        System.out.print(player.getName() + " selection (comma-separated, e.g. 1, 2, 3 or 0 to skip): ");

        // Get the selections from the player
        String input = in.nextLine().trim();
        while (input.isEmpty())
        {
            System.out.print("Invalid selection. Enter at least one selection or '0' to skip: ");
            input = in.nextLine().trim();
        }

        if (input.equals("0"))
        {
            System.out.println(player.getName() + " does not put any Pokémon on the Bench.");
            sleep(1000);
            return;
        }

        String[] selections = input.split(",");

        // Validate the selections (by space on bench)
        while (selections.length > (5 - player.getBench().getSize()))
        {
            System.out.print("Invalid selection, too many Pokémon. Please choose again (up to " + (5 - player.getBench().getSize()) + "): ");
            input = in.nextLine();
            selections = input.split(",");
        }
        
        // Validate the selections (by valid index)
        for (String selection : selections)
        {
            int index = Integer.parseInt(selection.trim()) - 1;
            while (index < 0 || index >= player.getHand().getSize())
            {
                System.out.println("Invalid selection, invalid index. Please choose again (1 - " + (player.getHand().getSize()) + "): ");
                input = in.nextLine();
                selections = input.split(",");
                index = Integer.parseInt(selections[0].trim()) - 1;
            }
        }

        // Add the validated cards to the Bench
        for (int i = 0; i < selections.length; i++)
        {
            int index = Integer.parseInt(selections[i].trim()) - 1;
            Card benchCard = player.getHand().getCardAtIndex(index - i);
            player.getBench().addCard(benchCard);
            player.getHand().removeCard(benchCard);
            System.out.println(player.getName() + " put " + benchCard.getName() + " on the Bench.");
        }
        System.out.println(player.getName() + " has " + player.getBench().getSize() + " Pokémon on the Bench.");
    }

    private void addPrizePokemon(Player player)
    {
        System.out.println("\n" + player.getName() + " adds 6 cards to their Prize pile.");
        for (int i = 0; i < 6; i++)
        {
            Card prizeCard = player.getDeck().drawCard();
            player.getPrizeCards().addCard(prizeCard);
        }
        sleep(1000);
    }

    private void sleep(int ms)
    {
        try
        {
            Thread.sleep(ms / 1000);
        }
        catch (InterruptedException event)
        {
            System.out.println("Sleep interrupted: " + event.getMessage());
        }
    }

    public void startGame()
    {
        System.out.println("\nThe battle begins!");
        sleep(1000);
        while (!isGameOver())
        {
            // Step 1: Draw a card.
            System.out.println("\n" + currentPlayer.getName() + "'s turn begins.");
            sleep(1000);
            System.out.println(currentPlayer.getName() + " draws a card...");
            sleep(1000);
            Card drawnCard = currentPlayer.getDeck().drawCard();
            currentPlayer.getHand().addCard(drawnCard);
            System.out.println(currentPlayer.getName() + " drew a " + drawnCard.getName() + ".");
            sleep(1000);

            /*
             * Step 2: Do any of the following actions in any order:
             * - Put Basic Pokémon cards from your hand onto your Bench (as many times as you want).
             * - Evolve your Pokémon (as many times as you want).
             * - Attach an Energy card from your hand to one of your Pokémon (once per turn)
             * - Play Trainer cards (as many as you want, but only one Supporter card and one Stadium card per turn).
             * - Retreat your Active Pokémon (only once per turn).
             * - Use Abilities (as many as you want).
             * 
             * Step 3: Attack. Then, end your turn.
             */
            System.out.println("\n" + currentPlayer.getName() + ", select an action:");
            System.out.println("\t1. Put Basic Pokémon on Bench");                      // TODO
            System.out.println("\t2. Attach Energy card");                              // TOOD
            System.out.println("\t3. Play Trainer card");                               // TODO
            System.out.println("\t4. Retreat Active Pokémon");                          // TODO
            System.out.println("\t0. Attack, then end turn");                           //TOOD
            System.out.print(currentPlayer.getName() + " selection: ");
            int action = in.nextInt();
            while (action < 0 || action > 4)
            {
                System.out.print("Invalid selection. Please choose again (0 - 4): ");
                action = in.nextInt();
            }
            switch (action)
            {
                case 1:
                    // 1. Put Basic Pokémon on Bench
                    break;
                case 2:
                    // 2. Attach Energy card
                    break;
                case 3:
                    // 3. Play Trainer card
                    break;
                case 4:
                    // 4. Retreat Active Pokémon
                    break;
                case 0:
                    // 0. Attack, then end turn
                    // <Attack>
                    // call attack method
                    System.out.println("\n" + currentPlayer.getName() + " attacks " + opponent.getName() + "'s Active Pokémon.");
                    sleep(1000);
                    boolean knockoutOccurred = currentPlayer.getActive().attack(opponent.getActive());
                    if (knockoutOccurred)
                    {
                        handleKnockout(currentPlayer, opponent);
                    }
                    System.out.println(currentPlayer.getName() + " ends their turn.");
                    sleep(1000);

                    // Switch players
                    currentPlayer = (currentPlayer == player1) ? player2 : player1;
                    opponent = (opponent == player1) ? player2 : player1;
                    break;
            }

            if (currentPlayer.getActive().getSize() == 0)
            {
                handleNoActivePokemon(currentPlayer);
            }
        }
    }

    private boolean isGameOver()
    {
        /*
         * Win conditions: https://assets.pokemon.com/assets/cms2/pdf/trading-card-game/rulebook/sm7_rulebook_en.pdf
         * 1. Take all of your Prize cards.
         * 2. Knock Out all of your opponent’s in-play Pokémon.
         * 3. If your opponent has no cards in their deck at the beginning of their turn
         */

        if (checkWinCondition1() || checkWinCondition2() || checkWinCondition3())
        {
            System.out.println("\nGame Over! " + currentPlayer.getName() + " wins!");
            return true;
        }
        return false;
    }

    // 1. Take all of your Prize cards.
    private boolean checkWinCondition1()
    {
        if (currentPlayer.getPrizeCards().getSize() == 0)
        {
            System.out.println(currentPlayer.getName() + " wins by taking all of their Prize cards!");
            return true;
        }
        return false;
    }

    // 2. Knock Out all of your opponent’s in-play Pokémon.
    private boolean checkWinCondition2()
    {
        if (opponent.getActive().getSize() == 0 && opponent.getBench().getSize() == 0)
        {
            System.out.println(currentPlayer.getName() + " wins by knocking out all of their opponent's Pokémon!");
            return true;
        }
        return false;
    }

    // 3. If your opponent has no cards in their deck at the beginning of their turn
    private boolean checkWinCondition3()
    {
        if (opponent.getDeck().getSize() == 0)
        {
            System.out.println(currentPlayer.getName() + " wins by making their opponent run out of cards!");
            return true;
        }
        return false;
    }

    private void handleKnockout(Player currentPlayer, Player opponent)
    {
        Card knockedOutCard = opponent.getActive().drawCard();
        opponent.getDiscardPile().addCard(knockedOutCard);
        System.out.println("\n" + knockedOutCard.getName() + " was moved to " + opponent.getName() + "'s discard pile.");
        sleep(1000);

        Card prizeCard = currentPlayer.getPrizeCards().drawCard();
        currentPlayer.getHand().addCard(prizeCard);
        System.out.println(currentPlayer.getName() + " drew a prize card: " + prizeCard.getName() + ".");
        sleep(1000);
    }

    // If the current player has no Active Pokémon, they must select a new one from their Bench.
    private void handleNoActivePokemon(Player currentPlayer)
    {
        // Check if the current player has any Pokémon on the Bench
        if (currentPlayer.getBench().getSize() == 0)
        {
            System.out.println(currentPlayer.getName() + " has no Pokémon on the Bench.");
            return;
        }

        // If they do, prompt them to select one and move it to the Active position
        System.out.println("\n" + currentPlayer.getName() + " has no Active Pokémon. Please select a new Active Pokémon from your Bench.");
        currentPlayer.getBench().display();
        System.out.print(currentPlayer.getName() + " selection: ");
        int activeIndex = in.nextInt() - 1;
        while (activeIndex < 0 || activeIndex >= currentPlayer.getBench().getSize())
        {
            System.out.print("Invalid selection. Please choose again (1 - " + (currentPlayer.getBench().getSize()) + "): ");
            activeIndex = in.nextInt() - 1;
        }
        Card activeCard = currentPlayer.getBench().getCardAtIndex(activeIndex);
        currentPlayer.getActive().addCard(activeCard);
        currentPlayer.getBench().removeCard(activeCard);
        System.out.println(currentPlayer.getName() + " put " + activeCard.getName() + " in the Active position.");
        sleep(1000);
    }
}