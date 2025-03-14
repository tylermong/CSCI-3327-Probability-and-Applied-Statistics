package game;

import card.Card;
import card.energy.EnergyCard;
import card.pokemon.PokemonCard;
import card.trainer.TrainerCard;

import java.util.Scanner;

public class Game
{
    private Player player1, player2, currentPlayer, opponent;
    private String coin;
    private static final Scanner in = new Scanner(System.in);
    private boolean playerHasCompletedAction2ThisTurn, playerHasCompletedAction4ThisTurn, isTurnOver;

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
        int player1Mulligans = validateHand(player1);
        sleep(1000);

        System.out.println("\n" + player2.getName() + " shuffles their deck and draws 7 cards.");
        player2.getDeck().shuffle();
        for (int i = 0; i < 7; i++)
        {
            Card card = player2.getDeck().drawCard();
            player2.getHand().addCard(card);
        }
        int player2Mulligans = validateHand(player2);
        sleep(1000);

        // Handle bonus draws for mulligans
        if (player1Mulligans > 0)
        {
            handleBonusDraws(player2, player1Mulligans);
        }
        
        if (player2Mulligans > 0)
        {
            handleBonusDraws(player1, player2Mulligans);
        }

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

    private int validateHand(Player player)
    {
        int mulliganCounter = 0;
        while (!player.getHand().hasBasicPokemon())
        {
            mulliganCounter++;
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
        return mulliganCounter;
    }

    private void handleBonusDraws(Player currentPlayer, int opponentMulliganCount)
    {
        // Handle bonus draws for opponent's mulligans
        System.out.println("\n" + currentPlayer.getName() + " gets to draw " + opponentMulliganCount + " extra card(s) for their opponent's mulligan.");
        for (int i = 0; i < opponentMulliganCount; i++)
        {
            Card bonusCard = currentPlayer.getDeck().drawCard();
            currentPlayer.getHand().addCard(bonusCard);
        }
        sleep(1000);
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

    // TODO: fix duplicating cards bug (occurs when selecting invalid cards i think -- might be fixed, need to test)
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

            // Reset flags
            playerHasCompletedAction2ThisTurn = false;
            playerHasCompletedAction4ThisTurn = false;
            isTurnOver = false;

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
            while (!isTurnOver)
            {
                System.out.println("\n" + currentPlayer.getName() + ", select an action:");
                System.out.println("\t1. Put Basic Pokémon on Bench");
                System.out.println("\t2. Attach Energy card");
                System.out.println("\t3. Play Trainer card");
                System.out.println("\t4. Retreat Active Pokémon");                          // TODO
                System.out.println("\t5. Show hand");
                System.out.println("\t6. Show active Pokémon");
                System.out.println("\t7. Show bench Pokémon");
                System.out.println("\t0. Attack, then end turn");
                System.out.print(currentPlayer.getName() + " selection: ");
                int action = in.nextInt();

                while (action < 0 || action > 7)
                {
                    System.out.print("Invalid selection. Please choose again (0 - 7 ): ");
                    action = in.nextInt();
                }
                in.nextLine(); // Consume the newline character
                switch (action)
                {
                    // 1. Put Basic Pokémon on Bench
                    case 1:
                        addBenchPokemon(currentPlayer);
                        break;

                    // 2. Attach Energy card
                    case 2:
                        if (playerHasCompletedAction2ThisTurn)
                        {
                            System.out.println("You have already attached an Energy card this turn.");
                            break;
                        }
                        playerHasCompletedAction2ThisTurn = attachEnergy(currentPlayer);
                        break;
                    
                    // 3. Play Trainer card
                    case 3:
                        playTrainerCard(currentPlayer);
                        break;
                    
                    // 4. Retreat Active Pokémon
                    case 4:
                        if (playerHasCompletedAction4ThisTurn)
                        {
                            System.out.println("You have already retreated your Active Pokémon this turn.");
                            break;
                        }
                        playerHasCompletedAction4ThisTurn = retreatActivePokemon(currentPlayer);
                        break;

                    // 5. Show hand
                    case 5:
                        System.out.println("\n" + currentPlayer.getName() + "'s hand:");
                        currentPlayer.getHand().display();
                        break;

                    // 6. Show active Pokémon
                    case 6:
                        System.out.println("\n" + currentPlayer.getName() + "'s Active Pokémon:");
                        currentPlayer.getActive().display();
                        break;
                    
                    // 7. Show bench Pokémon
                    case 7:
                        System.out.println("\n" + currentPlayer.getName() + "'s Bench Pokémon:");
                        currentPlayer.getBench().display();
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
                        isTurnOver = true;
                        sleep(1000);

                        // Switch players
                        currentPlayer = (currentPlayer == player1) ? player2 : player1;
                        opponent = (opponent == player1) ? player2 : player1;
                        break;
                }
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

    private boolean attachEnergy(Player currentPlayer)
    {
        // TODO: Fix bug where indexs overlap (active = 1, bench = 1, 2, ...) -> (active = 1, bench = 2, 3, ...)
        // Player chooses a Pokémon to attach an Energy card to
        System.out.println("\n" + currentPlayer.getName() + ", select a Pokémon to attach an Energy card to (0 to skip):");
        currentPlayer.getActive().display();
        currentPlayer.getBench().secondaryDisplay();
        System.out.print(currentPlayer.getName() + " selection: ");
        int pokemonIndex = in.nextInt() - 1;

        // Check if the player wants to skip attaching an Energy card
        if (pokemonIndex == -1) 
        {
            System.out.println(currentPlayer.getName() + " does not attach any Energy card.");
            sleep(1000);
            return false;
        }

        // Validate the selection is within range of the active and bench Pokémon
        while (pokemonIndex < 0 || pokemonIndex >= currentPlayer.getActive().getSize() + currentPlayer.getBench().getSize())
        {
            System.out.print("Invalid selection. Please choose again (1 - " + (currentPlayer.getActive().getSize() + currentPlayer.getBench().getSize()) + "): ");
            pokemonIndex = in.nextInt() - 1;
        }

        // Player chooses an Energy card from their hand
        System.out.println("\n" + currentPlayer.getName() + ", select an Energy card to attach (0 to skip):");
        currentPlayer.getHand().display();
        System.out.print(currentPlayer.getName() + " selection: ");
        int energyIndex = in.nextInt() - 1;

        // Check if the player wants to skip attaching an Energy card
        if (energyIndex == -1) 
        {
            System.out.println(currentPlayer.getName() + " does not attach any Energy card.");
            sleep(1000);
            return false;
        }

        // Validate the selection is within range of the hand size
        while (energyIndex < 0 || energyIndex >= currentPlayer.getHand().getSize())
        {
            System.out.print("Invalid selection. Please choose again (1 - " + (currentPlayer.getHand().getSize()) + "): ");
            energyIndex = in.nextInt() - 1;
        }

        // Validate the selected card is an Energy card
        Card selectedCard = currentPlayer.getHand().getCardAtIndex(energyIndex);
        if (!(selectedCard instanceof EnergyCard))
        {
            System.out.println("Invalid selection. Please select an Energy card.");
            attachEnergy(currentPlayer);
            return false;
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
        return true;
    }

    private void playTrainerCard(Player currentPlayer)
    {
        // Player chooses a Trainer card from their hand to play
        System.out.println("\n" + currentPlayer.getName() + ", select a Trainer card to play (0 to skip):");
        currentPlayer.getHand().display();
        System.out.print(currentPlayer.getName() + " selection: ");
        int trainerCardIndex = in.nextInt() - 1;

        // Check if the player wants to skip playing a Trainer card
        if (trainerCardIndex == -1)
        {
            System.out.println(currentPlayer.getName() + " does not play any Trainer card.");
            sleep(1000);
            return;
        }

        // Validate the selection is within range of the hand size
        while (trainerCardIndex < 0 || trainerCardIndex >= currentPlayer.getHand().getSize())
        {
            System.out.print("Invalid selection. Please choose again (1 - " + (currentPlayer.getHand().getSize()) + ") or '0' to skip: ");
            trainerCardIndex = in.nextInt() - 1;
        }

        // Validate the selected card is a Trainer card
        Card selectedCard = currentPlayer.getHand().getCardAtIndex(trainerCardIndex);
        if (!(selectedCard instanceof TrainerCard))
        {
            System.out.println("Invalid selection. Please select a Trainer card.");
            playTrainerCard(currentPlayer);
            return;
        }

        // At this point, the selected card is a valid Trainer card, so we can proceed to play it
        TrainerCard trainerCard = (TrainerCard) currentPlayer.getHand().getCardAtIndex(trainerCardIndex);
        currentPlayer.getHand().removeCard(trainerCard);
        System.out.println(currentPlayer.getName() + " played " + trainerCard.getName() + ".");
        sleep(1000);

        trainerCard.useEffect(currentPlayer.getDeck(), currentPlayer.getHand(), currentPlayer.getDiscardPile());
        sleep(1000);
    }

    private boolean retreatActivePokemon(Player currentPlayer)
    {
        // Confirm the player wants to retreat their Active Pokémon
        System.out.println("\n" + currentPlayer.getName() + ", do you want to retreat your Active Pokémon? (y/n)");
        String response = in.nextLine().trim().toLowerCase();
        if (!response.equals("y") && !response.equals("yes"))
        {
            System.out.println(currentPlayer.getName() + " does not retreat their Active Pokémon.");
            return false;
        }

        // Check if the player has enough Energy to retreat
        PokemonCard activePokemon = (PokemonCard) currentPlayer.getActive().getCardAtIndex(0);
        if (activePokemon.getRetreatCost() > activePokemon.getTotalEnergy())
        {
            System.out.println("Not enough Energy to retreat " + activePokemon.getName() + ".");
            return false;
        }

        // At this point the player has confirmed they want to retreat and has enough Energy to do so
        System.out.println("\n" + currentPlayer.getName() + ", select a replacement Pokémon from your bench (0 to cancel): ");
        currentPlayer.getBench().display();
        System.out.print(currentPlayer.getName() + " selection: ");
        int benchIndex = in.nextInt() - 1;

        // Check if the player wants to cancel the retreat
        if (benchIndex == -1)
        {
            System.out.println(currentPlayer.getName() + " cancels the retreat.");
            return false;
        }

        // Validate the selection is within range of the bench size
        while (benchIndex < 0 || benchIndex >= currentPlayer.getBench().getSize())
        {
            System.out.print("Invalid selection. Please choose again (1 - " + (currentPlayer.getBench().getSize()) + ") or '0' to cancel: ");
            benchIndex = in.nextInt() - 1;
        }

        in.nextLine(); // Consume the newline character

        // At this point the player is ready to retreat their Active Pokémon.
        // If the retreat cost is 0, swap cards without discarding any energy
        if (activePokemon.getRetreatCost() == 0)
        {
            PokemonCard benchPokemon = (PokemonCard) currentPlayer.getBench().getCardAtIndex(benchIndex);
            currentPlayer.getActive().addCard(benchPokemon);
            currentPlayer.getBench().removeCard(benchPokemon);

            currentPlayer.getActive().removeCard(activePokemon);
            currentPlayer.getBench().addCard(activePokemon);
            System.out.println(currentPlayer.getName() + " retreated " + activePokemon.getName() + " for " + benchPokemon.getName() + ".");
            sleep(1000);
            return true;
        }

        // If the energy cost > 0, discard retreat cost # Energy from the Active Pokémon.
        // If # of Energies > retreat cost, prompt the user to select which Energies to remove 
        // TODO: Check input validation
        if (activePokemon.getTotalEnergy() > activePokemon.getRetreatCost())
        {
            System.out.println("You have " + activePokemon.getTotalEnergy() + " Energy attached to " + activePokemon.getName() + ".");
            System.out.println("Please select " + activePokemon.getRetreatCost() + " Energy cards to discard (comma-separated, e.g. 1, 2, 3): ");
            activePokemon.displayEnergy();
            System.out.println("Selection: ");
            String input = in.nextLine().trim();

            String[] selections = input.split(",");
            for (String selection : selections)
            {
                int energyIndex = Integer.parseInt(selection.trim()) - 1;
                activePokemon.removeEnergy(energyIndex);
            }
        }
        else
        {
            // Remove all Energy from the Active Pokémon
            //activePokemon.removeAllEnergy();
        }

        return true;
    }
}