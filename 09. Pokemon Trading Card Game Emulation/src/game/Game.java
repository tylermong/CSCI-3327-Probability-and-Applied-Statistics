package game;

import card.Card;
import card.energy.EnergyCard;
import card.energy.energycards.*;
import card.pokemon.PokemonCard;
import card.trainer.TrainerCard;
import card.pokemon.helper.Move;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * This class is responsible for setting up and managing the game flow of the Pokémon Trading Card Game emulation.
 * It handles the game setup, player actions, turn management, and win conditions.
 * 
 * @author  Tyler Mong
 * @version 1.0
 */
public class Game
{
    /**
     * A reference to the first player in the game.
     */
    private Player player1;

    /**
     * A reference to the second player in the game.
     */
    private Player player2;

    /**
     * A reference to the current player whose turn it is.
     * This player will take actions during their turn.
     */
    private Player currentPlayer;

    /**
     * A reference to the opponent player, who is not currently taking their turn.
     * This player will be affected by the current player's actions.
     */
    private Player opponent;

    /**
     * A string used to store the result of the coin flip to determine who goes first.
     */
    private String coin;

    /**
     * A Scanner object used to read user input from the console.
     */
    private static final Scanner in = new Scanner(System.in);

    /**
     * A flag used to indicate if the player has completed the action of attaching an Energy card to a Pokémon this turn.
     * This is used to prevent multiple Energy attachments in a single turn.
     */
    private boolean playerHasCompletedAction2ThisTurn;

    /**
     * A flag used to indicate if the player has completed the action of retreating their Active Pokémon this turn.
     * This is used to prevent multiple retreats in a single turn.
     */
    private boolean playerHasCompletedAction4ThisTurn;
    
    /**
     * A flag used to indicate if the current player's turn is over.
     * This is set to true when the player has attacked and ended their turn.
     */
    private boolean isTurnOver;

    /**
     * A constructor to initialize the game with two players and their decks.
     */
    public Game()
    {
        player1 = new Player("Player 1");
        player2 = new Player("Player 2");
        
        DeckBuilder.initializePrebuiltDecks();

        selectDeck(player1);
        selectDeck(player2);
    }

    /**
     * Sets up the game by following the "Setting up to Play" sequence found in the official Pokémon TCG: Sun &amp;
     * Moon—Celestial Storm rulebook.
     * 
     * This setup includes the following steps: (1) shaking hands, (2) flipping a coin to determine who goes first,
     * (3) shuffling decks and drawing cards, (4) checking for Basic Pokémon in hand, (5) selecting an Active Pokémon,
     * (6) selecting Bench Pokémon, and (7) placing Prize cards.
     * 
     * @see <a href="https://assets.pokemon.com/assets/cms2/pdf/trading-card-game/rulebook/sm7_rulebook_en.pdf">Pokémon
     *      Trading Card Game Rules</a>
     */
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

        // Handle bonus draws for each player's mulligans.
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

    /**
     * This method handles deck selection for each player.
     * It displays the prebuilt decks, prompts for selection, validates the selection, and assigns the selected deck to
     * the player.
     * 
     * @param player    The player who is selecting a deck.
     */
    private void selectDeck(Player player)
    {
        // Store all of the decks in a String array for easy access.
        String[] decks = DeckBuilder.getAllPrebuiltDecks();

        // Displays the available prebuilt decks and prompts for selection.
        System.out.println("Select a deck for " + player.getName() + ": ");
        for (int i = 0; i < decks.length; i++)
        {
            System.out.println("\t" + (i + 1) + ". " + decks[i]);
        }
        System.out.print(player.getName() + " selection: ");
        int deckChoice = in.nextInt() - 1;  // Subtract 1 to match array index (0-based) with user's input (1-based).

        // Validate the user's selection is within the range of the available decks. While it's invalid, prompt again.
        while (deckChoice < 0 || deckChoice >= decks.length)
        {
            System.out.print("Invalid selection. Please choose again (1 - " + decks.length + "): ");
            deckChoice = in.nextInt() - 1;
        }
        
        // Set the selected deck for the player and print the chosen deck name.
        System.out.println(player.getName() + " selected " + decks[deckChoice] + " deck.\n");
        player.setDeck(DeckBuilder.getPrebuiltDeck(decks[deckChoice]));
        sleep(250);
    }

    /**
     * Simulates a coin flip.
     * 
     * @return  The result of the coin flip, either "Heads" or "Tails".
     */
    private String flipCoin()
    {
        // Generate a random number between 0 and 1.
        // If the number is less than 0.5, return "Heads".
        if (Math.random() < 0.5)
        {
            return "Heads";
        }

        // Otherwise, return "Tails".
        return "Tails";
    }

    /**
     * Checks if the player's hand contains any Basic Pokémon cards.
     * If not, the player must reveal their hand, return all cards to their deck, shuffle it, and draw 7 new cards.
     * This process is repeated until the player has at least one Basic Pokémon in their hand.
     * 
     * @param player    The player whose hand is being validated.
     * @return          The number of Mulligans (invalid hands) the player had before getting a valid hand.
     */
    private int validateHand(Player player)
    {
        // Store the number of mulligans to be used for bonus draws later.
        int mulliganCounter = 0;

        // While the player has no Basic Pokémon in their hand (i.e., the hand is invalid)
        while (!player.getHand().hasBasicPokemon())
        {
            // Add to the mulligan counter.
            mulliganCounter++;

            // Indicate the hand is invalid.
            System.out.println("\n" + player.getName() + " does not have any Basic Pokémon in their hand.");
            sleep(1000);

            // Reveal the player's hand.
            System.out.println("Revealing hand...");
            sleep(1000);
            player.getHand().display();
            sleep(1000);

            // Indicate the player is returning all cards to their deck, shuffling it, and drawing 7 new cards.
            System.out.println(player.getName() + " adds all cards back to their deck, shuffles it, and draws 7 new cards.");

            // Return all cards in hand to the deck.
            int handSize = player.getHand().getSize();
            for (int i = 0; i < handSize; i++)
            {
                Card card = player.getHand().drawCard();
                player.getDeck().addCard(card);
            }
            
            // Shuffle the deck.
            player.getDeck().shuffle();

            // Draw 7 new cards from the deck.
            for (int i = 0; i < 7; i++)
            {
                Card card = player.getDeck().drawCard();
                player.getHand().addCard(card);
            }
        }

        // Once the player has a valid hand, return the number of Mulligans they had.
        return mulliganCounter;
    }

    /**
     * Handles the bonus draws for the current player based on the opponent's Mulligan count.
     * 
     * @param currentPlayer         The player drawing bonus cards.
     * @param opponentMulliganCount The player's opponent's Mulligan count.
     */
    private void handleBonusDraws(Player currentPlayer, int opponentMulliganCount)
    {
        // Indicate the number of bonus cards the player gets to draw.
        System.out.println("\n" + currentPlayer.getName() + " gets to draw " + opponentMulliganCount + " extra card(s) for their opponent's mulligan.");

        // Draw the bonus cards.
        for (int i = 0; i < opponentMulliganCount; i++)
        {
            Card bonusCard = currentPlayer.getDeck().drawCard();
            currentPlayer.getHand().addCard(bonusCard);
        }
        sleep(1000);
    }

    /**
     * Sets the player's Active Pokémon.
     * Prompts the player to select a Basic Pokémon from their hand, validates the selection, and moves the selected
     * card to the Active position.
     * 
     * @param player    The player selecting an Active Pokémon.
     */
    private void setActivePokemon(Player player)
    {
        // Prompt the user to select a Basic Pokémon from their hand.
        System.out.println("\n" + player.getName() + ", please select a Basic Pokémon to put in the Active position.");
        player.getHand().display();
        System.out.print(player.getName() + " selection: ");
        int activeIndex = in.nextInt() - 1; // Subtract 1 to match array index (0-based) with user's input (1-based).

        // Validate the selection is within range of the hand size. While it's invalid, prompt again.
        while (activeIndex < 0 || activeIndex >= player.getHand().getSize())
        {
            System.out.print("Invalid selection. Please choose again (1 - " + (player.getHand().getSize()) + "): ");
            activeIndex = in.nextInt() - 1;
        }

        // Validate the selected card is a Basic Pokémon. If not, prompt again.
        Card activeCard = player.getHand().getCardAtIndex(activeIndex);
        if (!(activeCard instanceof PokemonCard))
        {
            System.out.println("Invalid selection. Please select a Basic Pokémon.");
            setActivePokemon(player);
            return;
        }

        in.nextLine();  // Consume the newline character.
        
        // At this point, the selected card is valid, so set it as the Active Pokémon.
        player.getActive().addCard(activeCard);
        player.getHand().removeCard(activeCard);
        System.out.println(player.getName() + " put " + activeCard.getName() + " in the Active position.");
        sleep(1000);
    }

    /**
     * Sets the player's Benched Pokémon.
     * Prompts the player to select Basic Pokémon from their hand, validates the selections, and moves the selected
     * cards to the Bench.
     * 
     * @param player    The player selecting Benched Pokémon.
     */
    private void addBenchPokemon(Player player)
    {
        // Prompt the user to select up to the number of available spots on the Bench Basic Pokémon from their hand.
        System.out.println("\n" + player.getName() + ", select up to "
                + (player.getBench().getMaxSize() - player.getBench().getSize())
                + " Basic Pokémon to put on the Bench.");
        player.getHand().display();
        System.out.print(player.getName() + " selection (comma-separated, e.g. 1, 2, 3 or 0 to skip): ");
        String input = in.nextLine().trim();

        // Put the input in an array for easier parsing.
        String[] rawSelections = input.split(",");
        String[] selections = new String[rawSelections.length];
        for (int i = 0; i < rawSelections.length; i++)
        {
            selections[i] = rawSelections[i].trim();
        }

        // Validate the input is not empty. If it is, prompt again.
        if (selections.length == 0)
        {
            System.out.print("Invalid selection. Enter at least one selection or '0' to skip: ");
            addBenchPokemon(player);
            return;
        }
        // Check if the input is "0" to skip. If it is, return now.
        else if (selections[0].equals("0"))
        {
            System.out.println(player.getName() + " does not put any Pokémon on the Bench.");
            sleep(1000);
            return;
        }
        // Validate the input is within range of the hand size. If not, prompt again.
        else if (selections.length > player.getHand().getSize())
        {
            System.out.print("Invalid selection, too many Pokémon selected. Please choose again (up to " + Math.min(player.getHand().getSize(), player.getBench().getMaxSize() - player.getBench().getSize()) + "): ");
            addBenchPokemon(player);
            return;
        }
        // Validate the input is within range of the available spots on the Bench. If not, prompt again.
        else if (selections.length > (player.getBench().getMaxSize() - player.getBench().getSize()))
        {
            System.out.print("Invalid selection, not enough spots on the bench. Please choose again (up to " + (player.getBench().getMaxSize() - player.getBench().getSize()) + "): ");
            addBenchPokemon(player);
            return;
        }

        // Validate the selections are all Pokémon cards. If not, prompt again.
        for (String selection : selections)
        {
            // Get the index of the selected card.
            int index = Integer.parseInt(selection.trim()) - 1;

            // Get the card at the selected index.
            Card card = player.getHand().getCardAtIndex(index);

            // Check if the card is a Pokémon card. If not, prompt again.
            if (!(card instanceof PokemonCard))
            {
                System.out.print("Invalid selection, not a Basic Pokémon. Please choose again: ");
                addBenchPokemon(player);
                return;
            }
        }

        // Validate the selections are not duplicates. If they are, prompt again.
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

        // Finally add the validated cards to the Bench.
        for (int i = 0; i < selections.length; i++)
        {
            // Get the index of the selected card.
            int index = Integer.parseInt(selections[i].trim()) - 1;

            // Get the card at the selected index and add it to the Bench. Subtract the index by i to account for the
            // index shifting as cards are being removed.
            Card benchCard = player.getHand().getCardAtIndex(index - i);
            player.getBench().addCard(benchCard);
            player.getHand().removeCard(benchCard);

            // Indicate the card has been added to the Bench.
            System.out.println(player.getName() + " put " + benchCard.getName() + " on the Bench.");
        }
        // Indicate the number of Pokémon on the Bench.
        System.out.println(player.getName() + " has " + player.getBench().getSize() + " Pokémon on the Bench.");
        sleep(1000);
    }

    /**
     * Adds 6 cards to the player's Prize pile.
     * This method is called during the game setup in order to setup the Prize pile for each player.
     * 
     * @param player    The player who is adding cards to their Prize pile.
     */
    private void addPrizePokemon(Player player)
    {
        // Indicate the player is adding 6 cards to their Prize pile.
        System.out.println("\n" + player.getName() + " adds 6 cards to their Prize pile.");
        
        // Draw 6 cards from the deck and add them to the Prize pile.
        for (int i = 0; i < 6; i++)
        {
            Card prizeCard = player.getDeck().drawCard();
            player.getPrizeCards().addCard(prizeCard);
        }
        sleep(1000);
    }

    /**
     * Starts the game by following the "Parts of a Turn" sequence found in the official Pokémon TCG: Sun &amp;
     * Moon—Celestial Storm rulebook.
     * 
     * This includes (1) drawing a card, (2) performing actions (putting Basic Pokémon on Bench, attaching Energy cards,
     * playing Trainer cards, and retreating Active Pokémon), and (3) attacking and ending the turn.
     * 
     * @see <a href="https://assets.pokemon.com/assets/cms2/pdf/trading-card-game/rulebook/sm7_rulebook_en.pdf">Pokémon
     *      Trading Card Game Rules</a>
     */
    public void startGame()
    {
        // Indicate the game is starting
        System.out.println("\nThe battle begins!");
        sleep(1000);

        // Loop until the game is over (i.e., one of the win conditions is met). Win conditions are checked at the start
        // of each turn.
        while (!isGameOver())
        {            
            // Indicate the current player's turn has begun.
            System.out.println("\n" + currentPlayer.getName() + "'s turn begins.");
            sleep(1000);

             // Reset the turn management flags for the new turn.
             playerHasCompletedAction2ThisTurn = false;
             playerHasCompletedAction4ThisTurn = false;
             isTurnOver = false;

            // Step 1: Draw a card.
            System.out.println(currentPlayer.getName() + " draws a card...");
            sleep(1000);

            // Draw the card.
            Card drawnCard = currentPlayer.getDeck().drawCard();
            currentPlayer.getHand().addCard(drawnCard);

            // Indicate the card drawn.
            System.out.println(currentPlayer.getName() + " drew a " + drawnCard.getName() + ".");
            sleep(1000);

            /*
             * Step 2: Do any of the following actions in any order:
             * - Put Basic Pokémon cards from your hand onto your Bench (as many times as you want).
             * - Attach an Energy card from your hand to one of your Pokémon (once per turn).
             * - Play Trainer cards (as many as you want).
             * - Retreat your Active Pokémon (only once per turn).
             * - Plus other actions added for game emulation (e.g., show hand, show bench Pokémon, show card stats).
             * 
             * Step 3: Attack. Then, end your turn.
             */

            // Loop turn actions until the player has ended their turn by attacking.
            while (!isTurnOver)
            {
                // Prompt the player to select an action.
                System.out.println("\n" + currentPlayer.getName() + ", select an action:");
                System.out.println("\t1. Put Basic Pokémon on Bench");
                System.out.println("\t2. Attach Energy card");
                System.out.println("\t3. Play Trainer card");
                System.out.println("\t4. Retreat Active Pokémon");
                System.out.println("\t5. Show hand");
                System.out.println("\t6. Show active Pokémon");
                System.out.println("\t7. Show bench Pokémon");
                System.out.println("\t8. Show card stats");
                System.out.println("\t0. Attack, then end turn");
                System.out.print(currentPlayer.getName() + " selection: ");
                int action = in.nextInt();

                // Validate the selection is within range of the available actions. While it's invalid, prompt again.
                while (action < 0 || action > 8)
                {
                    System.out.print("Invalid selection. Please choose again (0 - 8): ");
                    action = in.nextInt();
                }
                in.nextLine();  // Consume the newline character.
                
                // Based on the player's selection, perform the corresponding action.
                switch (action)
                {
                    // 1. Put Basic Pokémon on Bench.
                    case 1:
                        addBenchPokemon(currentPlayer);
                        break;

                    // 2. Attach Energy card.
                    case 2:
                        // Check if the player has already completed this action this turn. If so, prompt again.
                        if (playerHasCompletedAction2ThisTurn)
                        {
                            System.out.println("You have already attached an Energy card this turn.");
                            break;
                        }
                        // Call the attachEnergy method and set the flag to the return value (true if successful).
                        playerHasCompletedAction2ThisTurn = attachEnergy(currentPlayer);
                        break;

                    // 3. Play Trainer card.
                    case 3:
                        playTrainerCard(currentPlayer);
                        break;

                    // 4. Retreat Active Pokémon.
                    case 4:
                        // Check if the player has already completed this action this turn. If so, prompt again.
                        if (playerHasCompletedAction4ThisTurn)
                        {
                            System.out.println("You have already retreated your Active Pokémon this turn.");
                            break;
                        }
                        // Call the retreatActivePokemon method and set the flag to the return value (true if successful).
                        playerHasCompletedAction4ThisTurn = retreatActivePokemon(currentPlayer);
                        break;

                    // 5. Show hand.
                    case 5:
                        System.out.println("\n" + currentPlayer.getName() + "'s hand:");
                        currentPlayer.getHand().display();
                        break;

                    // 6. Show active Pokémon.
                    case 6:
                        System.out.println("\n" + currentPlayer.getName() + "'s Active Pokémon:");
                        currentPlayer.getActive().display();
                        break;
                    
                    // 7. Show benched Pokémon.
                    case 7:
                        System.out.println("\n" + currentPlayer.getName() + "'s Bench Pokémon:");
                        currentPlayer.getBench().display();
                        break;
                    
                    // 8. Show card stats.
                    case 8:
                        showCardStats(currentPlayer);
                        break;
                    
                    // 0. Attack, then end turn.
                    case 0:
                        // Indicate the player is attacking.
                        System.out.println("\n" + currentPlayer.getName() + " attacks " + opponent.getName() + "'s Active Pokémon.");
                        sleep(1000);

                        // Perform the attack. This method will return true if a knockout occurred.
                        boolean knockoutOccurred = currentPlayer.getActive().attack(opponent.getActive());

                        // If a knockout occurred, handle it by calling the helper method.
                        if (knockoutOccurred)
                        {
                            handleKnockout(currentPlayer, opponent);
                        }

                        // Indicate the player has ended their turn and set the flag to true to exit the loop.
                        System.out.println(currentPlayer.getName() + " ends their turn.");
                        isTurnOver = true;
                        sleep(1000);

                        // Switch the current player and opponent for the next turn.
                        currentPlayer = (currentPlayer == player1) ? player2 : player1;
                        opponent = (opponent == player1) ? player2 : player1;
                        break;
                }
            }

            // If the current player (previously the opponent) has no Active Pokémon, handle it. 
            if (currentPlayer.getActive().getSize() == 0)
            {
                handleNoActivePokemon(currentPlayer);
            }
        }
    }

    /**
     * Checks if the game is over by evaluating the win conditions at the start of each turn.
     * 
     * The following win conditions are checked: (1) If the current player has taken all of their Prize cards, (2) if
     * the opponent has no Pokémon in play (both active and bench), and (3) if the opponent has no cards left in their
     * deck at the beginning of their turn.
     * 
     * @return  True if any of the win conditions are met, false otherwise.
     */
    private boolean isGameOver()
    {
        if (checkWinCondition1() || checkWinCondition2() || checkWinCondition3())
        {
            // If any of the win conditions are met, return true to end the game.
            return true;
        }

        // Otherwise, return false to continue the game.
        return false;
    }

    /**
     * Checks the following win condition: If the opponent has taken all of their prize cards.
     * 
     * The opponent is checked as they are the player who just had their turn (since this is checked at the start of a
     * turn, after the roles have switched).
     * 
     * @return  True if the opponent has no Prize cards left, false otherwise.
     */
    private boolean checkWinCondition1()
    {
        if (opponent.getPrizeCards().getSize() == 0)
        {
            // Indicate the game is over and the opponent has won by taking all of their Prize cards.
            System.out.println("\nGame over, " + opponent.getName() + " wins by taking all of their Prize cards!");
            
            // Return true to end the game.
            return true;
        }

        // Otherwise, return false to continue the game.
        return false;
    }

    /**
     * Checks the following win condition: If the current player has no Pokémon in play (both active and bench).
     * 
     * The current player is checked as they are the player who just had their turn (since this is checked at the start
     * of a turn, after the roles have switched).
     * 
     * @return  True if the current player has no Pokémon in play, false otherwise.
     */
    private boolean checkWinCondition2()
    {
        if (currentPlayer.getActive().getSize() == 0 && currentPlayer.getBench().getSize() == 0)
        {
            // Indicate the game is over and the opponent has won by knocking out all of the current player's Pokémon.
            System.out.println("\nGame over, " + opponent.getName() + " wins by knocking out all of " + currentPlayer.getName() + "'s Pokémon!");

            // Return true to end the game.
            return true;
        }

        // Otherwise, return false to continue the game.
        return false;
    }

    /**
     * Checks the following win condition: If the opponent has no cards left in their deck at the beginning of their turn.
     * 
     * The opponent is checked as they are the player who just had their turn (since this is checked at the start of a
     * turn, after the roles have switched).
     * 
     * @return  True if the opponent has no cards left in their deck, false otherwise.
     */
    private boolean checkWinCondition3()
    {
        if (opponent.getDeck().getSize() == 0)
        {
            // Indicate the game is over and the current player has won by making the opponent run out of cards.
            System.out.println("\nGame over, " + currentPlayer.getName() + " wins by making " + opponent.getName() + " run out of cards!");
            
            // Return true to end the game.
            return true;
        }

        // Otherwise, return false to continue the game.
        return false;
    }

    /**
     * Handles the knockout of a Pokémon after an attack.
     * This method is called when a Pokémon is knocked out by an attack. It moves the knocked out Pokémon to the
     * discard pile and has current player draw a prize card.
     * 
     * @param currentPlayer The attacking player who knocked out the opponent's Pokémon.
     * @param opponent      The defending player whose Pokémon was knocked out.
     */
    private void handleKnockout(Player currentPlayer, Player opponent)
    {
        // Get the knocked out Pokémon, move it to the discard pile, and indicate the action.
        Card knockedOutCard = opponent.getActive().drawCard();
        opponent.getDiscardPile().addCard(knockedOutCard);
        System.out.println("\n" + knockedOutCard.getName() + " was moved to " + opponent.getName() + "'s discard pile.");
        sleep(1000);

        // Draw a prize card and indicate the action.
        Card prizeCard = currentPlayer.getPrizeCards().drawCard();
        currentPlayer.getHand().addCard(prizeCard);
        System.out.println(currentPlayer.getName() + " drew a prize card: " + prizeCard.getName() + ".");
        sleep(1000);
    }

    /**
     * Handles the case of the current player having no Active Pokémon, at the end of their opponent's turn.
     * 
     * @param currentPlayer The player who has no Active Pokémon.
     */
    private void handleNoActivePokemon(Player currentPlayer)
    {
        // Check if the player has any Pokémon on the Bench.
        if (currentPlayer.getBench().getSize() == 0)
        {
            // If not, indicate this and return.
            System.out.println(currentPlayer.getName() + " has no Pokémon on the Bench.");
            return;
        }

        // If they do, prompt them to select one to put in the Active position.
        System.out.println("\n" + currentPlayer.getName() + " has no Active Pokémon. Please select a new Active Pokémon from your Bench.");
        currentPlayer.getBench().display();
        System.out.print(currentPlayer.getName() + " selection: ");
        int activeIndex = in.nextInt() - 1;

        // Validate the selection is within range of the Bench size. While it's invalid, prompt again.
        while (activeIndex < 0 || activeIndex >= currentPlayer.getBench().getSize())
        {
            System.out.print("Invalid selection. Please choose again (1 - " + (currentPlayer.getBench().getSize()) + "): ");
            activeIndex = in.nextInt() - 1;
        }

        // At this point, the selected card is valid, so set it as the Active Pokémon.
        Card activeCard = currentPlayer.getBench().getCardAtIndex(activeIndex);

        // Remove the selected card from the Bench and add it to the Active position.
        currentPlayer.getActive().addCard(activeCard);
        currentPlayer.getBench().removeCard(activeCard);

        // Indicate the above action.
        System.out.println(currentPlayer.getName() + " put " + activeCard.getName() + " in the Active position.");
        sleep(1000);
    }

    /**
     * Handles attaching energy to a Pokémon.
     * This method prompts for Pokémon selection, validates the selection, prompts for Energy selection, validates the
     * selection, and attaches the selected Energy card to the selected Pokémon.
     * 
     * @param currentPlayer The player who is attaching an Energy card to a Pokémon.
     * @return              True if an Energy card was attached, false otherwise.
     */
    private boolean attachEnergy(Player currentPlayer)
    {
        // Prompt the player to select a Pokémon to attach an Energy card to.
        System.out.println("\n" + currentPlayer.getName() + ", select a Pokémon to attach an Energy card to (0 to skip):");
        currentPlayer.getActive().display();
        currentPlayer.getBench().secondaryDisplay();
        System.out.print(currentPlayer.getName() + " selection: ");
        int pokemonIndex = in.nextInt() - 1;

        // If the player chooses 0, return false to indicate no Energy card was attached.
        if (pokemonIndex == -1) 
        {
            System.out.println(currentPlayer.getName() + " does not attach any Energy card.");
            sleep(1000);
            return false;
        }

        // Validate the selection is within range of the active and bench Pokémon.
        while (pokemonIndex < 0 || pokemonIndex >= currentPlayer.getActive().getSize() + currentPlayer.getBench().getSize())
        {
            System.out.print("Invalid selection. Please choose again (1 - " + (currentPlayer.getActive().getSize() + currentPlayer.getBench().getSize()) + "): ");
            pokemonIndex = in.nextInt() - 1;
        }

        // Prompt the player to select an Energy card to attach to the selected Pokémon.
        System.out.println("\n" + currentPlayer.getName() + ", select an Energy card to attach (0 to skip):");
        currentPlayer.getHand().display();
        System.out.print(currentPlayer.getName() + " selection: ");
        int energyIndex = in.nextInt() - 1;

        // If the player chooses 0, return false to indicate no Energy card was attached.
        if (energyIndex == -1) 
        {
            System.out.println(currentPlayer.getName() + " does not attach any Energy card.");
            sleep(1000);
            return false;
        }

        // Validate the selection is within range of the hand size.
        while (energyIndex < 0 || energyIndex >= currentPlayer.getHand().getSize())
        {
            System.out.print("Invalid selection. Please choose again (1 - " + (currentPlayer.getHand().getSize()) + "): ");
            energyIndex = in.nextInt() - 1;
        }

        // Validate the selected card is an Energy card. If not, prompt again.
        Card selectedCard = currentPlayer.getHand().getCardAtIndex(energyIndex);
        if (!(selectedCard instanceof EnergyCard))
        {
            System.out.println("Invalid selection. Please select an Energy card.");
            attachEnergy(currentPlayer);
            return false;
        }

        // Attach the Energy card to the selected Pokémon.
        EnergyCard energyCard = (EnergyCard) currentPlayer.getHand().getCardAtIndex(energyIndex);
        currentPlayer.getHand().removeCard(energyCard);

        // Check if the selected Pokémon is an Active Pokémon or a Bench Pokémon, and attach the Energy card accordingly.
        if (pokemonIndex < currentPlayer.getActive().getSize())
        {
            PokemonCard activeCard = (PokemonCard) currentPlayer.getActive().getCardAtIndex(pokemonIndex);
            activeCard.addEnergy(energyCard);
            System.out.println(currentPlayer.getName() + " attached " + energyCard.getName() + " to " + activeCard.getName() + ".");
        }
        else
        {
            PokemonCard benchCard = (PokemonCard) currentPlayer.getBench().getCardAtIndex(pokemonIndex - currentPlayer.getActive().getSize());
            benchCard.addEnergy(energyCard);
            System.out.println(currentPlayer.getName() + " attached " + energyCard.getName() + " to " + benchCard.getName() + ".");
        }
        sleep(1000);
        
        // Return true to indicate an Energy card was successfully attached.
        return true;
    }

    /**
     * Handles playing a Trainer card.
     * This method prompts the player to select a Trainer card, validates the selection, and uses the effect of the card.
     * 
     * @param currentPlayer The player playing a Trainer card.
     */
    private void playTrainerCard(Player currentPlayer)
    {
        // Prompt the player to select a Trainer card from their hand.
        System.out.println("\n" + currentPlayer.getName() + ", select a Trainer card to play (0 to skip):");
        currentPlayer.getHand().display();
        System.out.print(currentPlayer.getName() + " selection: ");
        int trainerCardIndex = in.nextInt() - 1;

        // Check if the player wants to skip playing a Trainer card.
        if (trainerCardIndex == -1)
        {
            System.out.println(currentPlayer.getName() + " does not play any Trainer card.");
            sleep(1000);
            return;
        }

        // Validate the selection is within range of the hand size.
        while (trainerCardIndex < 0 || trainerCardIndex >= currentPlayer.getHand().getSize())
        {
            System.out.print("Invalid selection. Please choose again (1 - " + (currentPlayer.getHand().getSize()) + ") or '0' to skip: ");
            trainerCardIndex = in.nextInt() - 1;
        }

        // Validate the selected card is a Trainer card.
        Card selectedCard = currentPlayer.getHand().getCardAtIndex(trainerCardIndex);
        if (!(selectedCard instanceof TrainerCard))
        {
            System.out.println("Invalid selection. Please select a Trainer card.");
            playTrainerCard(currentPlayer);
            return;
        }

        // At this point, the selected card is a valid Trainer card, so we can proceed to play it.
        TrainerCard trainerCard = (TrainerCard) currentPlayer.getHand().getCardAtIndex(trainerCardIndex);

        // Remove the card from the player's hand and add it to the discard pile.
        currentPlayer.getHand().removeCard(trainerCard);
        currentPlayer.getDiscardPile().addCard(trainerCard);

        // Indicate the card has been played.
        System.out.println(currentPlayer.getName() + " played " + trainerCard.getName() + ".");
        sleep(1000);

        // Use the effect of the Trainer card.
        trainerCard.useEffect(currentPlayer.getDeck(), currentPlayer.getHand(), currentPlayer.getDiscardPile());
        sleep(1000);
    }

    /**
     * Handles retreating the Active Pokémon to the Bench.
     * This method prompts the player for confirmation, validates if they can retreat, and allows them to select a card
     * to switch with.
     * 
     * @param currentPlayer The player who is retreating their Active Pokémon.
     * @return              True if the retreat was successful, false otherwise.
     */
    private boolean retreatActivePokemon(Player currentPlayer)
    {
        // Prompt the player for confirmation to retreat their Active Pokémon.
        System.out.println("\n" + currentPlayer.getName() + ", do you want to retreat your Active Pokémon? (y/n)");
        System.out.print(currentPlayer.getName() + " selection: ");
        String response = in.nextLine().trim().toLowerCase();

        // If they do not want to retreat, return false to indicate the action was not taken.
        if (!response.equals("y") && !response.equals("yes"))
        {
            System.out.println(currentPlayer.getName() + " does not retreat their Active Pokémon.");
            return false;
        }

        // Check if the player has any Pokémon on the Bench. If not, they can not retreat, so return false to indicate the action was not taken.
        if (currentPlayer.getBench().getSize() == 0)
        {
            System.out.println(currentPlayer.getName() + " has no Pokémon on the Bench to retreat to.");
            return false;
        }

        // Check if the player has enough Energy to retreat. If not, they can not retreat, so return false to indicate the action was not taken.
        PokemonCard activePokemon = (PokemonCard) currentPlayer.getActive().getCardAtIndex(0);
        if (activePokemon.getRetreatCost() > activePokemon.getTotalEnergy())
        {
            System.out.println("Not enough Energy to retreat " + activePokemon.getName() + ".");
            return false;
        }

        // At this point the player has confirmed they want to retreat and has enough Energy to do so.
        // Prompt the player to select a replacement Pokémon from their Bench.
        System.out.println("\n" + currentPlayer.getName() + ", select a replacement Pokémon from your bench (0 to cancel): ");
        currentPlayer.getBench().display();
        System.out.print(currentPlayer.getName() + " selection: ");
        int benchIndex = in.nextInt() - 1;

        // Check again if the player wants to cancel the retreat.
        if (benchIndex == -1)
        {
            System.out.println(currentPlayer.getName() + " cancels the retreat.");
            return false;
        }

        // Validate the selection is within range of the bench size.
        while (benchIndex < 0 || benchIndex >= currentPlayer.getBench().getSize())
        {
            System.out.print("Invalid selection. Please choose again (1 - " + (currentPlayer.getBench().getSize()) + ") or '0' to cancel: ");
            benchIndex = in.nextInt() - 1;
        }

        in.nextLine(); // Consume the newline character.

        // At this point the player is ready to retreat their Active Pokémon.
        // If the retreat cost is 0, swap cards without discarding any energy.
        if (activePokemon.getRetreatCost() == 0)
        {
            // Move the Bench Pokémon to the Active position.
            PokemonCard benchPokemon = (PokemonCard) currentPlayer.getBench().getCardAtIndex(benchIndex);
            currentPlayer.getActive().addCard(benchPokemon);
            currentPlayer.getBench().removeCard(benchPokemon);

            // Move the Active Pokémon to the Bench.
            currentPlayer.getActive().removeCard(activePokemon);
            currentPlayer.getBench().addCard(activePokemon);

            // Indicate the action has been taken.
            System.out.println(currentPlayer.getName() + " retreated " + activePokemon.getName() + " for " + benchPokemon.getName() + ".");
            sleep(1000);
            return true;
        }

        // Else if the Energy attached is greater than the retreat cost, prompt the player to select which Energy they wish to discard.
        else if (activePokemon.getTotalEnergy() > activePokemon.getRetreatCost())
        {
            // Prompt the player to select the Energy cards they wish to discard.
            System.out.println("You have " + activePokemon.getTotalEnergy() + " Energy attached to " + activePokemon.getName() + ".");
            System.out.println("Please select " + activePokemon.getRetreatCost() + " Energy cards to discard (comma-separated, e.g. 1, 2, 3): ");
            List<String> energyList = activePokemon.displayEnergyInList();
            System.out.print("Selection: ");
            String input = in.nextLine().trim();
            String[] selections = input.split(",");

            // Validate the input is not empty. 
            if (selections.length == 0)
            {
                System.out.print("Invalid selection. Enter at least one selection: ");
                return false;
            }

            // Validate the correct number of Energy cards were selected.
            if (selections.length != activePokemon.getRetreatCost())
            {
                System.out.print("Invalid selection. Please select " + activePokemon.getRetreatCost() + " Energy cards to discard: ");
                return false;
            }

            // Validate the selections are within range of the energy list size.
            for (String selection : selections)
            {
                int index = Integer.parseInt(selection.trim()) - 1;
                if (index < 0 || index >= energyList.size())
                {
                    System.out.print("Invalid selection. Please choose again (1 - " + energyList.size() + "): ");
                    return false;
                }
            }

            // Validate the selections are not duplicates.
            for (int i = 0; i < selections.length; i++)
            {
                for (int j = i + 1; j < selections.length; j++)
                {
                    if (selections[i].equals(selections[j]))
                    {
                        System.out.print("Invalid selection, duplicate Energy selected. Please choose again: ");
                        return false;
                    }
                }
            }

            // Extract the selected indices from the input.
            List<Integer> selectedMoveIndices = new ArrayList<>();
            for (String selection : selections)
            {
                selectedMoveIndices.add(Integer.parseInt(selection.trim()) - 1);
            }
            
            // Remove the selected Energy cards.
            for (int index: selectedMoveIndices)
            {
                // Remove the energy.
                String energyType = energyList.get(index);
                activePokemon.removeEnergy(energyType);

                // Then add it to the discard pile.
                EnergyCard discardedEnergy = createEnergyCard(energyType);
                currentPlayer.getDiscardPile().addCard(discardedEnergy);

                // Print the discarded energy.
                System.out.println("Discarded " + energyType + " from " + activePokemon.getName() + ".");
            }
            sleep(1000);

            // Indicate the number of Energy cards removed.
            System.out.println("Removed " + selectedMoveIndices.size() + " Energy cards from " + activePokemon.getName() + ".");
            sleep(1000);
        }

        // Else (if the total energy is equal to the retreat cost), remove all Energy cards attached to the Active Pokémon.
        else
        {
            // Store all of the energy in a list.
            List<String> energyList = activePokemon.displayEnergyInList();
            
            // For each Energy in the list, remove it from the Active Pokémon and add it to the discard pile.
            for (String energyType : new ArrayList<>(energyList))
            {
                // Remove the energy.
                activePokemon.removeEnergy(energyType);

                // Then add it to the discard pile.
                EnergyCard discardedEnergy = createEnergyCard(energyType);
                currentPlayer.getDiscardPile().addCard(discardedEnergy);

                // Print the discarded energy.
                System.out.println("Discarded " + energyType + " from " + activePokemon.getName() + ".");
            }

            // Indicate the number of Energy cards removed.
            System.out.println("Removed all Energy cards from " + activePokemon.getName() + ".");
            sleep(1000);
        }

        PokemonCard benchPokemon = (PokemonCard) currentPlayer.getBench().getCardAtIndex(benchIndex);
        
        // Move the Bench Pokémon to the Active position.
        currentPlayer.getActive().addCard(benchPokemon);
        currentPlayer.getBench().removeCard(benchPokemon);

        // Move the Active Pokémon to the Bench.
        currentPlayer.getActive().removeCard(activePokemon);
        currentPlayer.getBench().addCard(activePokemon);

        // Indicate the action has been taken.
        System.out.println(currentPlayer.getName() + " retreated " + activePokemon.getName() + " for " + benchPokemon.getName() + ".");
        sleep(1000);

        // Return true to indicate the retreat was successful.
        return true;
    }

    /**
     * Helper method to create an Energy card based on the given type.
     * This method is used to create an Energy card when discarding energy during a retreat.
     * 
     * @param energyType    The type of Energy card to create (e.g., "Grass", "Fire", etc.).
     * @return              The created Energy card.
     */
    private EnergyCard createEnergyCard(String energyType)
    {
        // Based on the type, create the corresponding Energy card and return it.
        switch (energyType)
        {
            case "Grass":
                return new GrassEnergy();

            case "Fire":
                return new FireEnergy();

            case "Water":
                return new WaterEnergy();

            case "Lightning":
                return new LightningEnergy();

            case "Fighting":
                return new FightingEnergy();

            case "Psychic":
                return new PsychicEnergy();

            case "Colorless":
                return new DoubleColorlessEnergy();

            default:
                return null;
        }
    }

    /**
     * Displays the stats of a selected Pokémon card.
     * This method prompts the player to select a pile (Active or Bench), validates the selection, and displays the stats
     * of the selected Pokémon card, including HP, Moves, Weakness, Resistance, and Energy attached.
     * 
     * @param currentPlayer The player selecting a Pokémon card to view stats for.
     */
    private void showCardStats(Player currentPlayer)
    {
        // Prompt the player to select a pile (Active or Bench).
        System.out.println("\n" + currentPlayer.getName() + ", select a pile:");
        System.out.println("\t1. Active Pokémon");
        System.out.println("\t2. Bench Pokémon");
        System.out.print(currentPlayer.getName() + " selection: ");
        int choice = in.nextInt();

        // If the player selected 1, display the Active Pokémon's stats.
        if (choice == 1)
        {
            System.out.println("\n" + currentPlayer.getName() + "'s Active Pokémon:");
            currentPlayer.getActive().display();

            // Display HP, Moves, Weakness, Resistance, and Energy attached to the Active Pokémon
            PokemonCard selectedPokemon = (PokemonCard) currentPlayer.getActive().getCardAtIndex(0);
            showCardStatsHelper(selectedPokemon);
        }

        // Else if the player selected 2, prompt for selecting a Pokémon from the bench.
        else if (choice == 2)
        {
            // Prompt the player to select a Pokémon from the Bench.
            System.out.println("\n" + currentPlayer.getName() + "'s Bench Pokémon:");
            currentPlayer.getBench().display();

            // If The player has no Pokémon on the Bench, indicate this and return.
            if (currentPlayer.getBench().getSize() == 0)
            {
                System.out.println("No Pokémon on the Bench.");
                return;
            }
            System.out.print("Selection: ");
            int benchIndex = in.nextInt() - 1;

            // Validate the selection is within range of the bench size. While it's invalid, prompt again.
            while (benchIndex < 0 || benchIndex >= currentPlayer.getBench().getSize())
            {
                System.out.print("Invalid selection. Please choose again (1 - " + (currentPlayer.getBench().getSize()) + "): ");
                benchIndex = in.nextInt() - 1;
            }

            // At this point, the selected card is valid, so we can proceed to display its stats.
            PokemonCard selectedPokemon = (PokemonCard) currentPlayer.getBench().getCardAtIndex(benchIndex);
            showCardStatsHelper(selectedPokemon);
        }
        // If the player selected an invalid option, nothing happens.
    }

    /**
     * Helper method to display the stats of a selected Pokémon card.
     * This method prints the HP, Moves, Weakness, Resistance, Energy attached, and Retreat Cost of the selected Pokémon card.
     * 
     * @param selectedPokemon   The Pokémon to display stats for.
     */
    private void showCardStatsHelper(PokemonCard selectedPokemon)
    {
        System.out.println("HP: " + selectedPokemon.getHP());
        System.out.println("Moves: ");
        for (Move move : selectedPokemon.getMoves())
        {
            System.out.println("\t" + move.getName() + " - Damage: " + move.getDamage() + " - " + move.getEffect() + " - Energy Cost: " + move.getEnergyCost());
        }
        System.out.println("Weakness: " + selectedPokemon.getWeakness());
        System.out.println("Resistance: " + selectedPokemon.getResistance());
        System.out.println("Energy attached: " + selectedPokemon.displayEnergyInMap());
        System.out.println("Retreat Cost: " + selectedPokemon.getRetreatCost());
        sleep(1000);
    }

    /**
     * Helper method to call Thread.sleep() and handle the InterruptedException.
     * Used to pause the game, allowing the player's to more easily read the text output in the console.
     * 
     * @param ms                    The number of milliseconds to sleep for.
     * @throws InterruptedException If the sleep is interrupted.
     */
    private void sleep(int ms)
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
}