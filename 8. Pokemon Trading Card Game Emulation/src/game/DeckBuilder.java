package game;

import java.util.LinkedHashMap;
import java.util.Map;
import card.energy.energycards.*;
import card.pokemon.pokemoncards.*;
import card.trainer.trainercards.*;
import pile.piles.Deck;

public class DeckBuilder
{
    private static final Map<String, Deck> prebuiltDecks = new LinkedHashMap<>();

    public static void initializePrebuiltDecks()
    {
        initializeFireDeck();
        initializeFightingDeck();
        initializeWaterDeck();
        initializeColorlessDeck();
        
        initializeMulliganDeck();   // High probability of Mulligan-ing (for testing)
        initializeEnergyDeck();     // Some Pokémon, lots of energy, no trainers (for testing)
    }

    private static void initializeFireDeck()
    {
        Deck fireDeck = new Deck();

        // Add 21 Pokemon cards
        for (int i = 0; i < 11; i++) fireDeck.addCard(new Growlithe());
        for (int i = 0; i < 10; i++) fireDeck.addCard(new Ponyta());

        // Add 11 Trainer cards
        for (int i = 0; i < 7; i++) fireDeck.addCard(new Bill());
        for (int i = 0; i < 6; i++) fireDeck.addCard(new ProfessorOak());

        // Add 28 Energy cards
        for (int i = 0; i < 28; i++) fireDeck.addCard(new FireEnergy());

        // Add all 60 cards to the deck
        prebuiltDecks.put("Fire", fireDeck);
    }

    private static void initializeFightingDeck()
    {
        Deck fightingDeck = new Deck();

        // Add 21 Pokemon cards
        for (int i = 0; i < 7; i++) fightingDeck.addCard(new Diglett());
        for (int i = 0; i < 7; i++) fightingDeck.addCard(new Hitmonchan());
        for (int i = 0; i < 7; i++) fightingDeck.addCard(new Machop());

        // Add 11 Trainer cards
        for (int i = 0; i < 7; i++) fightingDeck.addCard(new Bill());
        for (int i = 0; i < 6; i++) fightingDeck.addCard(new ProfessorOak());

        // Add 28 Energy cards
        for (int i = 0; i < 28; i++) fightingDeck.addCard(new FightingEnergy());

        // Add all 60 cards to the deck
        prebuiltDecks.put("Fighting", fightingDeck);
    }

    private static void initializeWaterDeck()
    {
        Deck waterDeck = new Deck();

        // Add 21 Pokemon cards
        for (int i = 0; i < 11; i++) waterDeck.addCard(new Seel());
        for (int i = 0; i < 10; i++) waterDeck.addCard(new Staryu());

        // Add 11 Trainer cards
        for (int i = 0; i < 7; i++) waterDeck.addCard(new Bill());
        for (int i = 0; i < 6; i++) waterDeck.addCard(new ProfessorOak());

        // Add 28 Energy cards
        for (int i = 0; i < 28; i++) waterDeck.addCard(new WaterEnergy());

        // Add all 60 cards to the deck
        prebuiltDecks.put("Water", waterDeck);
    }

    private static void initializeColorlessDeck()
    {
        Deck colorlessDeck = new Deck();

        // Add 21 Pokemon cards
        for (int i = 0; i < 11; i++) colorlessDeck.addCard(new Dratini());
        for (int i = 0; i < 10; i++) colorlessDeck.addCard(new Rattata());

        // Add 11 Trainer cards
        for (int i = 0; i < 7; i++) colorlessDeck.addCard(new Bill());
        for (int i = 0; i < 6; i++) colorlessDeck.addCard(new ProfessorOak());

        // Add 28 Energy cards
        for (int i = 0; i < 28; i++) colorlessDeck.addCard(new WaterEnergy());

        // Add all 60 cards to the deck
        prebuiltDecks.put("Colorless", colorlessDeck);
    }

    private static void initializeMulliganDeck()
    {
        Deck mulliganDeck = new Deck();

        // Add 1 Pokemon cards
        for (int i = 0; i < 1; i++) mulliganDeck.addCard(new Diglett());

        // Add 22 Trainer cards
        for (int i = 0; i < 11; i++) mulliganDeck.addCard(new Bill());
        for (int i = 0; i < 11; i++) mulliganDeck.addCard(new ProfessorOak());

        // Add 37 Energy cards
        for (int i = 0; i < 37; i++) mulliganDeck.addCard(new FightingEnergy());

        // Add all 60 cards to the deck
        prebuiltDecks.put("[Testing] Mulligan", mulliganDeck);
    }

    private static void initializeEnergyDeck()
    {
        Deck energyDeck = new Deck();

        // Add 20 Pokemon cards
        for (int i = 0; i < 10; i++) energyDeck.addCard(new Diglett());
        for (int i = 0; i < 10; i++) energyDeck.addCard(new Dratini());

        // Add 40 Energy cards
        for (int i = 0; i < 10; i++) energyDeck.addCard(new FightingEnergy());
        for (int i = 0; i < 10; i++) energyDeck.addCard(new FireEnergy());
        for (int i = 0; i < 10; i++) energyDeck.addCard(new LightningEnergy());
        for (int i = 0; i < 10; i++) energyDeck.addCard(new DoubleColorlessEnergy());

        // Add all 60 cards to the deck
        prebuiltDecks.put("[Testing] Energy", energyDeck);
    }

    public static String[] getAllPrebuiltDecks()
    {
        return prebuiltDecks.keySet().toArray(new String[0]);
    }

    public static Deck getPrebuiltDeck(String deckName)
    {
        return prebuiltDecks.get(deckName);
    }
}