package game;

import java.util.LinkedHashMap;
import java.util.Map;
import card.energy.energycards.*;
import card.pokemon.pokemoncards.*;
import card.trainer.trainercards.*;
import pile.piles.Deck;

/**
 * This class is responsible for building prebuilt decks.
 * It initializes the decks with specific Pokémon, Trainer, and Energy cards.
 * It also provides methods to retrieve all prebuilt decks and a specific prebuilt deck by name.
 * 
 * @author  Tyler Mong
 * @version 1.0
 */
public class DeckBuilder
{
    /**
     * Maps deck name to deck object. Uses a LinkedHashMap to maintain insertion order for consistent deck retrieval.
     */
    private static final Map<String, Deck> prebuiltDecks = new LinkedHashMap<>();

    /**
     * Initializes the prebuilt decks by calling individual methods for each deck type.
     * Each deck is populated with a specific set of Pokémon, Trainer, and Energy cards.
     * This method is called at the start of the game to build the decks, so the players can choose from them.
     * It also initializes some debug decks for testing purposes. These debug decks are not meant for actual gameplay.
     */
    public static void initializePrebuiltDecks()
    {
        initializeFireDeck();
        initializeFightingDeck();
        initializeWaterDeck();
        initializeColorlessDeck();
        
        // Debug decks for testing purposes.
        initializeMulliganDeck();   // The "Mulligan" deck has a high probability of Mulligan-ing.
        initializeEnergyDeck();     // The "Energy" deck has some Pokémon, lots of energy, and no trainers.
    }

    /**
     * Initializes the Fire deck with Fire-type Pokémon, Trainer cards, and Fire Energy cards.
     */
    private static void initializeFireDeck()
    {
        Deck fireDeck = new Deck();

        // Add 21 Fire-type Pokémon cards.
        for (int i = 0; i < 11; i++) fireDeck.addCard(new Growlithe());
        for (int i = 0; i < 10; i++) fireDeck.addCard(new Ponyta());

        // Add 11 Trainer cards.
        for (int i = 0; i < 7; i++) fireDeck.addCard(new Bill());
        for (int i = 0; i < 6; i++) fireDeck.addCard(new ProfessorOak());

        // Add 28 Fire Energy cards.
        for (int i = 0; i < 28; i++) fireDeck.addCard(new FireEnergy());

        // Add all 60 cards to the "Fire" deck.
        prebuiltDecks.put("Fire", fireDeck);
    }

    /**
     * Initializes the Fighting deck with Fighting-type Pokémon, Trainer cards, and Fighting Energy cards.
     */
    private static void initializeFightingDeck()
    {
        Deck fightingDeck = new Deck();

        // Add 21 Fighting-type Pokémon cards.
        for (int i = 0; i < 7; i++) fightingDeck.addCard(new Diglett());
        for (int i = 0; i < 7; i++) fightingDeck.addCard(new Hitmonchan());
        for (int i = 0; i < 7; i++) fightingDeck.addCard(new Machop());

        // Add 11 Trainer cards.
        for (int i = 0; i < 7; i++) fightingDeck.addCard(new Bill());
        for (int i = 0; i < 6; i++) fightingDeck.addCard(new ProfessorOak());

        // Add 28 Fighting Energy cards.
        for (int i = 0; i < 28; i++) fightingDeck.addCard(new FightingEnergy());

        // Add all 60 cards to the "Fighting" deck.
        prebuiltDecks.put("Fighting", fightingDeck);
    }

    /**
     * Initializes the Water deck with Water-type Pokémon, Trainer cards, and Water Energy cards.
     */
    private static void initializeWaterDeck()
    {
        Deck waterDeck = new Deck();

        // Add 21 Water-type Pokémon cards.
        for (int i = 0; i < 11; i++) waterDeck.addCard(new Seel());
        for (int i = 0; i < 10; i++) waterDeck.addCard(new Staryu());

        // Add 11 Trainer cards.
        for (int i = 0; i < 7; i++) waterDeck.addCard(new Bill());
        for (int i = 0; i < 6; i++) waterDeck.addCard(new ProfessorOak());

        // Add 28 Water Energy cards.
        for (int i = 0; i < 28; i++) waterDeck.addCard(new WaterEnergy());

        // Add all 60 cards to the "Water" deck.
        prebuiltDecks.put("Water", waterDeck);
    }

    /**
     * Initializes the Colorless deck with Colorless-type Pokémon, Trainer cards, and Double Colorless Energy cards.
     */
    private static void initializeColorlessDeck()
    {
        Deck colorlessDeck = new Deck();

        // Add 21 Colorless-type Pokémon cards.
        for (int i = 0; i < 11; i++) colorlessDeck.addCard(new Dratini());
        for (int i = 0; i < 10; i++) colorlessDeck.addCard(new Rattata());

        // Add 11 Trainer cards.
        for (int i = 0; i < 7; i++) colorlessDeck.addCard(new Bill());
        for (int i = 0; i < 6; i++) colorlessDeck.addCard(new ProfessorOak());

        // Add 28 Double Colorless Energy cards.
        for (int i = 0; i < 28; i++) colorlessDeck.addCard(new DoubleColorlessEnergy());

        // Add all 60 cards to the "Colorless" deck.
        prebuiltDecks.put("Colorless", colorlessDeck);
    }

    /**
     * Initializes the Mulligan deck, used for testing purposes. It contains a high probability of Mulligan-ing.
     * With 1 Pokémon card, the odds of Mulligan-ing are ~88.33%.
     */
    private static void initializeMulliganDeck()
    {
        Deck mulliganDeck = new Deck();

        // Add 1 Pokémon card.
        for (int i = 0; i < 1; i++) mulliganDeck.addCard(new Diglett());

        // Add 22 Trainer cards.
        for (int i = 0; i < 11; i++) mulliganDeck.addCard(new Bill());
        for (int i = 0; i < 11; i++) mulliganDeck.addCard(new ProfessorOak());

        // Add 37 Energy cards.
        for (int i = 0; i < 37; i++) mulliganDeck.addCard(new FightingEnergy());

        // Add all 60 cards to the "Mulligan" deck.
        prebuiltDecks.put("[Debug] Mulligan", mulliganDeck);
    }

    /**
     * Initializes the Energy deck, used for testing purposes. It contains some Pokémon cards, lots of energy, and no trainers.
     */
    private static void initializeEnergyDeck()
    {
        Deck energyDeck = new Deck();

        // Add 20 Pokémon cards.
        for (int i = 0; i < 10; i++) energyDeck.addCard(new Diglett());
        for (int i = 0; i < 10; i++) energyDeck.addCard(new Dratini());

        // Add 40 Energy cards.
        for (int i = 0; i < 10; i++) energyDeck.addCard(new FightingEnergy());
        for (int i = 0; i < 10; i++) energyDeck.addCard(new FireEnergy());
        for (int i = 0; i < 10; i++) energyDeck.addCard(new LightningEnergy());
        for (int i = 0; i < 10; i++) energyDeck.addCard(new DoubleColorlessEnergy());

        // Add all 60 cards to the "Energy" deck.
        prebuiltDecks.put("[Debug] Energy", energyDeck);
    }

    /**
     * Returns an array of the prebuiltDeck map keys (deck names).
     * This method is used to retrieve the names of all prebuilt decks available during the player's deck selection phase.
     * 
     * @return  An array of deck names.
     */
    public static String[] getAllPrebuiltDecks()
    {
        return prebuiltDecks.keySet().toArray(new String[0]);
    }

    /**
     * Returns a specific prebuilt deck by its name.
     * This method is used to retrieve a specific deck when the player selects a deck to play with.
     * 
     * @param deckName  The name of the deck to retrieve.
     * @return          The prebuilt deck associated with the given name.
     */
    public static Deck getPrebuiltDeck(String deckName)
    {
        return prebuiltDecks.get(deckName);
    }
}