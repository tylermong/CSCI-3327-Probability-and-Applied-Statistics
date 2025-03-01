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

        for (int i = 0; i < 7; i++)
        {
            lightningDeck.addCard(new Pikachu());
        }

        for (int i = 0; i < 7; i++)
        {
            lightningDeck.addCard(new Electabuzz());
        }

        for (int i = 0; i < 7; i++)
        {
            lightningDeck.addCard(new Zapdos());
        }

        for (int i = 0; i < 21; i++)
        {
            lightningDeck.addCard(new LightningEnergy());
        }

        for (int i = 0; i < 7; i++)
        {
            lightningDeck.addCard(new Bill());
        }

        for (int i = 0; i < 6; i++)
        {
            lightningDeck.addCard(new ProfessorOak());
        }

        prebuiltDecks.put("Lightning", lightningDeck);
    }
}