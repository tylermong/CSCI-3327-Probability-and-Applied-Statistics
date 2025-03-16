package card.pokemon.pokemoncards;

import java.util.Map;

import card.pokemon.PokemonCard;
import card.pokemon.helper.Move;

/**
 * This class represents a "Staryu" Pokémon card in the Pokémon Trading Card Game.
 * It extends the PokemonCard class, calls the superclass constructor to initialize the card with its unique attributes.
 * It also adds a unique move to the card: "Slap".
 * 
 * @author  Tyler Mong
 * @version 1.0
 */
public class Staryu extends PokemonCard
{
    /**
     * Constructor to initialize a "Staryu" Pokémon card with its unique attributes.
     * It also adds a unique move to the card: "Slap".
     */
    public Staryu()
    {
        super("Staryu",40, "Lightning", null, 1);
        addMove(new Move("Slap", 20, Map.of("Water", 1), "No additional effect."));
    }
}