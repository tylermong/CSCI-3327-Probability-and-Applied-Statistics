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
            System.out.println(player1.getName() + " goes first.");
        }
        else
        {
            currentPlayer = player2;
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

        startGameLoop();
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

    public String flipCoin()
    {
        if (Math.random() < 0.5)
        {
            return "Heads";
        }
        return "Tails";
    }

    // TODO: add draw +1 card if opponent has no basic pokemon
    public void validateHand(Player player)
    {
        while (!player.getHand().hasBasicPokemon())
        {
            System.out.println(player.getName() + " does not have any Basic Pokémon in their hand.");
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
    public void setActivePokemon(Player player)
    {
        System.out.println("\n" + player.getName() + ", please select a Basic Pokémon to put in the Active position.");
        player.getHand().display();
        System.out.print(player.getName() + " selection: ");
        int activeIndex = in.nextInt();
        while (activeIndex < 0 || activeIndex >= player.getHand().getSize())
        {
            System.out.print("Invalid selection. Please choose again (1 - " + (player.getHand().getSize()) + "): ");
            activeIndex = in.nextInt() - 1;
        }
        Card activeCard = player.getHand().getCardAtIndex(activeIndex);
        player.getActive().addCard(activeCard);
        player.getHand().removeCard(activeCard);
        System.out.println(player.getName() + " put " + activeCard.getName() + " in the Active position.\n");
    }

    // TODO: Validate the cards are basic pokemon
    // TODO: Fix bug of automatically adding cards to the bench (for player 2)
    public void addBenchPokemon(Player player)
    {
        System.out.println(player.getName() + ", select up to " + (5 - player.getBench().getSize()) + " Basic Pokémon to put on the Bench.");
        player.getHand().display();
        System.out.print(player.getName() + " selection (comma-separated, e.g., 1, 2, 3): ");
        String input = in.next();
        String[] selections = input.split(",");

        // Validate the selections (by space on bench)
        while (selections.length > (5 - player.getBench().getSize()))
        {
            System.out.print("Invalid selection, too many Pokémon. Please choose again (up to " + (5 - player.getBench().getSize()) + "): ");
            input = in.next();
            selections = input.split(",");
        }
        
        // Validate the selections (by valid index)
        for (String selection : selections)
        {
            int index = Integer.parseInt(selection.trim()) - 1;
            while (index < 0 || index >= player.getHand().getSize())
            {
                System.out.println("Invalid selection, invalid index. Please choose again (1 - " + (player.getHand().getSize()) + "): ");
                input = in.next();
                selections = input.split(",");
                index = Integer.parseInt(selections[0].trim()) - 1;
            }
        }

        // Add the validated cards to the Bench
        for (String selection : selections)
        {
            int index = Integer.parseInt(selection.trim()) - 1;
            Card benchCard = player.getHand().getCardAtIndex(index);
            player.getBench().addCard(benchCard);
            player.getHand().removeCard(benchCard);
            System.out.println(player.getName() + " put " + benchCard.getName() + " on the Bench.");
        }
        System.out.println(player.getName() + " has " + player.getBench().getSize() + " Pokémon on the Bench.\n");
    }

    public void addPrizePokemon(Player player)
    {
        System.out.println(player.getName() + " adds 6 cards to their Prize pile.");
        for (int i = 0; i < 6; i++)
        {
            Card prizeCard = player.getDeck().drawCard();
            player.getPrizeCards().addCard(prizeCard);
        }
    }

    public void sleep(int ms)
    {
        try
        {
            Thread.sleep(ms);
        }
        catch (InterruptedException event)
        {
            System.out.println("Sleep interrupted: " + event.getMessage());
        }
    }

    public void startGame()
    {
        System.out.println("Battle begins!");
        sleep(1000);
        while (!isGameOver())
        {
            // Step 0: Check if the current player has an Active Pokémon. If not, select a new one.
            if (currentPlayer.getActive() == null)
            {
                System.out.println(currentPlayer.getName() + " has no Active Pokémon. Please select a new Active Pokémon.");
                currentPlayer.getBench().display();
                System.out.print(currentPlayer.getName() + " selection: ");
                int activeIndex = in.nextInt();
                while (activeIndex < 0 || activeIndex >= currentPlayer.getBench().getSize())
                {
                    System.out.print("Invalid selection. Please choose again (1 - " + (currentPlayer.getBench().getSize()) + "): ");
                    activeIndex = in.nextInt() - 1;
                }
                Card activeCard = currentPlayer.getBench().getCardAtIndex(activeIndex);
                currentPlayer.getActive().addCard(activeCard);
                currentPlayer.getBench().removeCard(activeCard);
                System.out.println(currentPlayer.getName() + " put " + activeCard.getName() + " in the Active position.\n");
                sleep(1000);
            }

            // Step 1: Draw a card.
            System.out.println(currentPlayer.getName() + "'s turn begins.");
            sleep(1000);
            System.out.println(currentPlayer.getName() + " draws a card.");
            Card drawnCard = currentPlayer.getDeck().drawCard();
            currentPlayer.getHand().addCard(drawnCard);
            System.out.println(currentPlayer.getName() + " drew " + drawnCard.getName() + ".");
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
            System.out.println(currentPlayer.getName() + ", select an action:");
            System.out.println("\t1. Put Basic Pokémon on Bench");
            System.out.println("\t2. Attach Energy card");
            System.out.println("\t3. Play Trainer card");
            System.out.println("\t4. Retreat Active Pokémon");
            System.out.println("\t0. Attack, then end turn");
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

                    System.out.println(currentPlayer.getName() + " ends their turn.");
                    sleep(1000);
                    currentPlayer = (currentPlayer == player1) ? player2 : player1;
                    break;
            }
        }
    }

    public boolean isGameOver()
    {
        /*
         * Win conditions: https://assets.pokemon.com/assets/cms2/pdf/trading-card-game/rulebook/sm7_rulebook_en.pdf
         * 1. Take all of your Prize cards.
         * 2. Knock Out all of your opponent’s in-play Pokémon.
         * If your opponent has no cards in their deck at the beginning of their turn
         */

        if (checkWinCondition1(player1) || checkWinCondition1(player2) ||
            checkWinCondition2(player1) || checkWinCondition2(player2) ||
            checkWinCondition3(player1) || checkWinCondition3(player2))
        {
            return true;
        }
        return false;
    }

    public boolean checkWinCondition1(Player player)
    {
        if (player.getPrizeCards().getSize() == 0)
        {
            System.out.println(player.getName() + " wins by taking all of their Prize cards!");
            return true;
        }
        return false;
    }

    public boolean checkWinCondition2(Player player)
    {
        if (player.getActive().getSize() == 0)
        {
            System.out.println(player.getName() + " wins by knocking out all of their opponent's Pokémon!");
            return true;
        }
        return false;
    }

    public boolean checkWinCondition3(Player player)
    {
        if (player.getDeck().getSize() == 0)
        {
            System.out.println(player.getName() + " wins by making their opponent run out of cards!");
            return true;
        }
        return false;
    }
}