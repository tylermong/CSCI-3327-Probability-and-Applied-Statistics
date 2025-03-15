package card.pokemon.pokemoncards;

import java.util.Map;

import card.pokemon.PokemonCard;
import card.pokemon.helper.Move;

/**
 * This class represents a "Machop" Pokémon card in the Pokémon Trading Card Game.
 * It extends the PokemonCard class, calls the superclass constructor to initialize the card with its unique attributes.
 * It also adds a unique move to the card: "Low Kick".
 * 
 * @author  Tyler Mong
 * @version 1.0
 */
public class Machop extends PokemonCard
{
    /**
     * Constructor to initialize a "Machop" Pokémon card with its unique attributes.
     * It also adds a unique move to the card: "Low Kick".
     */
    public Machop()
    {
        super("Machop", 50, "Psychic", null, 1);
        addMove(new Move("Low Kick", 20, Map.of("Fightning", 1), "No additional effect."));
    }
}