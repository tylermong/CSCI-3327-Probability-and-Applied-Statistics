package game;

import pile.piles.Deck;
import java.util.HashMap;
import java.util.Map;

import card.energy.energycards.LightningEnergy;
import card.pokemon.pokemoncards.Charmander;
import card.pokemon.pokemoncards.Diglett;
import card.pokemon.pokemoncards.Electabuzz;
import card.pokemon.pokemoncards.Growlithe;
import card.pokemon.pokemoncards.Hitmonchan;
import card.pokemon.pokemoncards.Machop;
import card.pokemon.pokemoncards.Magmar;
import card.pokemon.pokemoncards.Pikachu;
import card.pokemon.pokemoncards.Ponyta;
import card.pokemon.pokemoncards.Zapdos;
import card.trainer.trainercards.Bill;
import card.trainer.trainercards.ProfessorOak;
import card.energy.energycards.FightingEnergy;
import card.energy.energycards.FireEnergy;

public class DeckBuilder
{
    private static final Map<String, Deck> prebuiltDecks = new HashMap<>();

    public static void initializePrebuiltDecks()
    {
        initializeLightningDeck();
        initializeFightingDeck();
        initializeFireDeck();
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
}