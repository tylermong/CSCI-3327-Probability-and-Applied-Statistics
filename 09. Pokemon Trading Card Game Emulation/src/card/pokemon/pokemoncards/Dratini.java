package card.pokemon.pokemoncards;

import java.util.Map;

import card.pokemon.PokemonCard;
import card.pokemon.helper.Move;

/**
 * This class represents a "Dratini" Pokémon card in the Pokémon Trading Card Game.
 * It extends the PokemonCard class, calls the superclass constructor to initialize the card with its unique attributes.
 * It also adds a unique move to the card: "Pound".
 * 
 * @author  Tyler Mong
 * @version 1.0
 */
public class Dratini extends PokemonCard
{
    /**
     * Constructor to initialize a "Dratini" Pokémon card with its unique attributes.
     * It also adds a unique move to the card: "Pound".
     */
    public Dratini()
    {
        super("Dratini", 40, null, "Psychic", 1);
        addMove(new Move("Pound", 10, Map.of("Colorless", 1), "No additional effect."));
    }
}