package game;

import pile.piles.Deck;
import java.util.HashMap;
import java.util.Map;

import card.energy.energycards.LightningEnergy;
import card.pokemon.pokemoncards.Electabuzz;
import card.pokemon.pokemoncards.Pikachu;
import card.pokemon.pokemoncards.Zapdos;
import card.trainer.trainercards.Bill;
import card.trainer.trainercards.ProfessorOak;
import card.energy.energycards.DoubleColorlessEnergy;

public class DeckBuilder
{
    private static final Map<String, Deck> prebuiltDecks = new HashMap<>();

    public static void initializePrebuiltDecks()
    {
        initializeLightningDeck();
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
}