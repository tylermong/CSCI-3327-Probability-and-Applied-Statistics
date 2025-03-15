package card.pokemon.pokemoncards;

import java.util.Map;

import card.pokemon.PokemonCard;
import card.pokemon.helper.Move;

/**
 * This class represents a "Ponyta" Pokémon card in the Pokémon Trading Card Game.
 * It extends the PokemonCard class, calls the superclass constructor to initialize the card with its unique attributes.
 * It also adds two unique moves to the card: "Smash Kick" and "Flame Tail".
 * 
 * @author  Tyler Mong
 * @version 1.0
 */
public class Ponyta extends PokemonCard
{
    /**
     * Constructor to initialize a "Ponyta" Pokémon card with its unique attributes.
     * It also adds two unique moves to the card: "Smash Kick" and "Flame Tail".
     */
    public Ponyta()
    {
        super("Ponyta", 40, "Water", null, 1);
        addMove(new Move("Smash Kick", 20, Map.of("Colorless", 2), "No additional effect."));
        addMove(new Move("Flame Tail", 30, Map.of("Fire", 2), "No additional effect."));
    }
}