package card.pokemon.pokemoncards;

import java.util.Map;

import card.pokemon.PokemonCard;
import card.pokemon.helper.Move;

/**
 * This class represents a "Hitmonchan" Pokémon card in the Pokémon Trading Card Game.
 * It extends the PokemonCard class, calls the superclass constructor to initialize the card with its unique attributes.
 * It also adds two unique moves to the card: "Jab" and "Special Punch".
 * 
 * @author  Tyler Mong
 * @version 1.0
 */
public class Hitmonchan extends PokemonCard
{
    /**
     * Constructor to initialize a "Hitmonchan" Pokémon card with its unique attributes.
     * It also adds two unique moves to the card: "Jab" and "Special Punch".
     */
    public Hitmonchan()
    {
        super("Hitmonchan", 70, "Psychic", null, 2);
        addMove(new Move("Jab", 20, Map.of("Fighting", 1), "No additional effect."));
        addMove(new Move("Special Punch", 40, Map.of("Fighting", 2, "Colorless", 1), "No additional effect."));
    }
}