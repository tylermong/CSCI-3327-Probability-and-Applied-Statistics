package game;

import java.util.HashMap;
import java.util.Map;
import card.energy.energycards.*;
import card.pokemon.pokemoncards.*;
import card.trainer.trainercards.*;
import pile.piles.Deck;

public class DeckBuilder
{
    private static final Map<String, Deck> prebuiltDecks = new HashMap<>();

    public static void initializePrebuiltDecks()
    {
        initializeFireDeck();
        initializeLightningDeck();
        initializeFightingDeck();
    }

    private static void initializeFireDeck()
    {
        Deck fireDeck = new Deck();

        // Add 21 Pokemon cards
        for (int i = 0; i < 5; i++) fireDeck.addCard(new Charmander());
        for (int i = 0; i < 5; i++) fireDeck.addCard(new Growlithe());
        for (int i = 0; i < 5; i++) fireDeck.addCard(new Magmar());
        for (int i = 0; i < 6; i++) fireDeck.addCard(new Ponyta());

        // Add 11 Trainer cards
        for (int i = 0; i < 7; i++) fireDeck.addCard(new Bill());
        for (int i = 0; i < 6; i++) fireDeck.addCard(new ProfessorOak());

        // Add 28 Energy cards
        for (int i = 0; i < 28; i++) fireDeck.addCard(new FireEnergy());

        // Add all 60 cards to the deck
        prebuiltDecks.put("Fire", fireDeck);
    }

    private static void initializeLightningDeck()
    {
        Deck lightningDeck = new Deck();

        // Add 21 Pokemon cards
        for (int i = 0; i < 7; i++) lightningDeck.addCard(new Pikachu());
        for (int i = 0; i < 7; i++) lightningDeck.addCard(new Electabuzz());
        for (int i = 0; i < 7; i++) lightningDeck.addCard(new Zapdos());

        // Add 11 Trainer cards
        for (int i = 0; i < 7; i++) lightningDeck.addCard(new Bill());
        for (int i = 0; i < 6; i++) lightningDeck.addCard(new ProfessorOak());

        // Add 28 Energy cards
        for (int i = 0; i < 28; i++) lightningDeck.addCard(new LightningEnergy());

        // Add all 60 cards to the deck
        prebuiltDecks.put("Lightning", lightningDeck);
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

    public static String[] getAllPrebuiltDecks()
    {
        return prebuiltDecks.keySet().toArray(new String[0]);
    }
}