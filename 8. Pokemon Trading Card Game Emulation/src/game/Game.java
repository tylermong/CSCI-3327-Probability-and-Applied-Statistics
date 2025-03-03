package game;

import card.Card;
import card.energy.EnergyCard;
import card.pokemon.PokemonCard;
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

        // TODO: "Each time your opponent shuffles their hand back into their deck because they had no Basic Pokémon, you may draw an extra card!"

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
            System.out.println("\n" + player.getName() + " does not have any Basic Pokémon in their hand.");
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

    private void setActivePokemon(Player player)
    {
        System.out.println("\n" + player.getName() + ", please select a Basic Pokémon to put in the Active position.");
        player.getHand().display();
        System.out.print(player.getName() + " selection: ");
        int activeIndex = in.nextInt() - 1;

        // Validate within range
        while (activeIndex < 0 || activeIndex >= player.getHand().getSize())
        {
            System.out.print("Invalid selection. Please choose again (1 - " + (player.getHand().getSize()) + "): ");
            activeIndex = in.nextInt() - 1;
        }

        // Validate the selected card is a Basic Pokémon
        Card activeCard = player.getHand().getCardAtIndex(activeIndex);
        if (!(activeCard instanceof PokemonCard))
        {
            System.out.println("Invalid selection. Please select a Basic Pokémon.");
            setActivePokemon(player);
            return;
        }

        in.nextLine(); // Consume the newline character
        
        // Add card to active pile and remove from hand
        player.getActive().addCard(activeCard);
        player.getHand().removeCard(activeCard);
        System.out.println(player.getName() + " put " + activeCard.getName() + " in the Active position.");
        sleep(1000);
    }

    // TODO: Validate the cards are basic pokemon
    // TODO: fix duplicating cards bug
    private void addBenchPokemon(Player player)
    {
        System.out.println("\n" + player.getName() + ", select up to " + (player.getBench().getMaxSize() - player.getBench().getSize()) + " Basic Pokémon to put on the Bench.");
        player.getHand().display();
        System.out.print(player.getName() + " selection (comma-separated, e.g. 1, 2, 3 or 0 to skip): ");

        // Get the selections from the player
        String input = in.nextLine().trim();

        if (input.isEmpty())                                                                                // Check if the input is empty
        {
            System.out.print("Invalid selection. Enter at least one selection or '0' to skip: ");
            addBenchPokemon(player);
            return;
        }
        else if (input.equals("0"))                                                                         // Check if the player wants to skip
        {
            System.out.println(player.getName() + " does not put any Pokémon on the Bench.");
            sleep(1000);
            return;
        }
        else if (input.length() > (player.getHand().getSize() * 2 - 1))                                     // check if the input is in range of the hand size
        {
            System.out.print("Invalid selection, too many Pokémon selected. Please choose again (up to " + player.getHand().getSize() + "): ");
            addBenchPokemon(player);
            return;
        }
        else if (input.length() > (player.getBench().getMaxSize() - player.getBench().getSize()) * 2 + 1)   // check if theres enough spots on the bench
        {
            System.out.print("Invalid selection, too many Pokémon selected. Please choose again (up to " + (player.getBench().getMaxSize() - player.getBench().getSize()) + "): ");
            addBenchPokemon(player);
            return;
        }
        // by this point the input is not empty, skip, or too long. so time to filter the input
        String[] selections = input.split(",");
        // now check if the selections are pokemon cards
        for (String selection : selections)
        {
            int index = Integer.parseInt(selection.trim()) - 1;
            Card card = player.getHand().getCardAtIndex(index);
            if (!(card instanceof PokemonCard))
            {
                System.out.print("Invalid selection, not a Basic Pokémon. Please choose again: ");
                addBenchPokemon(player);
                return;
            }
        }
        // now check if the pokemon cards are duplicates
        for (int i = 0; i < selections.length; i++)
        {
            for (int j = i + 1; j < selections.length; j++)
            {
                if (selections[i].equals(selections[j]))
                {
                    System.out.print("Invalid selection, duplicate Pokémon selected. Please choose again: ");
                    addBenchPokemon(player);
                    return;
                }
            }
        }

        // Finally add the validated cards to the Bench
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
            System.out.println("\t1. Put Basic Pokémon on Bench");
            System.out.println("\t2. Attach Energy card");
            System.out.println("\t3. Play Trainer card");                               // TODO
            System.out.println("\t4. Retreat Active Pokémon");                          // TODO
            System.out.println("\t0. Attack, then end turn");
            System.out.print(currentPlayer.getName() + " selection: ");
            int action = in.nextInt();

            while (action < 0 || action > 4)
            {
                System.out.print("Invalid selection. Please choose again (0 - 4): ");
                action = in.nextInt();
            }
            in.nextLine(); // Consume the newline character
            switch (action)
            {
                // 1. Put Basic Pokémon on Bench
                // TODO: only allow this once per turn
                case 1:
                    addBenchPokemon(currentPlayer);
                    break;
                
                // 2. Attach Energy card
                case 2:
                    attachEnergy(currentPlayer);
                    break;
                
                // 3. Play Trainer card
                case 3:
                    break;
                
                // 4. Retreat Active Pokémon
                case 4:
                    break;
                
                // 0. Attack, then end turn
                case 0:
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
            return true;
        }
        return false;
    }

    // 1. Take all of your Prize cards.
    private boolean checkWinCondition1()
    {
        if (currentPlayer.getPrizeCards().getSize() == 0)
        {
            System.out.println("\nGame over, " + opponent.getName() + " wins by taking all of their Prize cards!");
            return true;
        }
        return false;
    }

    // 2. Knock Out all of your opponent’s in-play Pokémon.
    private boolean checkWinCondition2()
    {
        if (currentPlayer.getActive().getSize() == 0 && currentPlayer.getBench().getSize() == 0)
        {
            System.out.println("\nGame over, " + opponent.getName() + " wins by knocking out all of " + currentPlayer.getName() + "'s Pokémon!");
            return true;
        }
        return false;
    }

    // 3. If your opponent has no cards in their deck at the beginning of their turn
    private boolean checkWinCondition3()
    {
        if (opponent.getDeck().getSize() == 0)
        {
            System.out.println("\nGame over, " + currentPlayer.getName() + " wins by making " + opponent.getName() + " run out of cards!");
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

    private void attachEnergy(Player currentPlayer)
    {
        // Player chooses a Pokémon to attach an Energy card to
        System.out.println("\n" + currentPlayer.getName() + ", select a Pokémon to attach an Energy card to:");
        currentPlayer.getActive().display();
        currentPlayer.getBench().display();
        System.out.print(currentPlayer.getName() + " selection: ");
        int pokemonIndex = in.nextInt() - 1;
        while (pokemonIndex < 0 || pokemonIndex >= currentPlayer.getActive().getSize() + currentPlayer.getBench().getSize())
        {
            System.out.print("Invalid selection. Please choose again (1 - " + (currentPlayer.getActive().getSize() + currentPlayer.getBench().getSize()) + "): ");
            pokemonIndex = in.nextInt() - 1;
        }

        // Player chooses an Energy card from their hand
        // TODO: validate the card is an Energy card
        // TODO: validate the pokemon can accept the energy
        System.out.println("\n" + currentPlayer.getName() + ", select an Energy card to attach:");
        currentPlayer.getHand().display();
        System.out.print(currentPlayer.getName() + " selection: ");
        int energyIndex = in.nextInt() - 1;
        while (energyIndex < 0 || energyIndex >= currentPlayer.getHand().getSize())
        {
            System.out.print("Invalid selection. Please choose again (1 - " + (currentPlayer.getHand().getSize()) + "): ");
            energyIndex = in.nextInt() - 1;
        }

        // Attach the Energy card to the selected Pokémon
        EnergyCard energyCard = (EnergyCard) currentPlayer.getHand().getCardAtIndex(energyIndex);
        currentPlayer.getHand().removeCard(energyCard);

        // Active Pokémon
        if (pokemonIndex < currentPlayer.getActive().getSize())
        {
            PokemonCard activeCard = (PokemonCard) currentPlayer.getActive().getCardAtIndex(pokemonIndex);
            activeCard.addEnergy(energyCard);
            System.out.println(currentPlayer.getName() + " attached " + energyCard.getName() + " to " + activeCard.getName() + ".");
        }
        // Bench Pokémon
        else
        {
            PokemonCard benchCard = (PokemonCard) currentPlayer.getBench().getCardAtIndex(pokemonIndex - currentPlayer.getActive().getSize());
            benchCard.addEnergy(energyCard);
            System.out.println(currentPlayer.getName() + " attached " + energyCard.getName() + " to " + benchCard.getName() + ".");
        }
        sleep(1000);
    }
}