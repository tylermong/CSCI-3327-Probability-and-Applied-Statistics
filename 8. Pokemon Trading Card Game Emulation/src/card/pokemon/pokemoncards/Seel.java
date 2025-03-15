package card.pokemon.pokemoncards;

import java.util.Map;

import card.pokemon.PokemonCard;
import card.pokemon.helper.Move;

/**
 * This class represents a "Seel" Pokémon card in the Pokémon Trading Card Game.
 * It extends the PokemonCard class, calls the superclass constructor to initialize the card with its unique attributes.
 * It also adds a unique move to the card: "Headbutt".
 * 
 * @author  Tyler Mong
 * @version 1.0
 */
public class Seel extends PokemonCard
{
    /**
     * Constructor to initialize a "Seel" Pokémon card with its unique attributes.
     * It also adds a unique move to the card: "Headbutt".
     */
    public Seel()
    {
        super("Seel", 50, "Lightning", null, 1);
        addMove(new Move("Headbutt", 10, Map.of("Water", 1), "No additional effect."));
    }
}