package card.pokemon.pokemoncards;

import java.util.Map;

import card.pokemon.PokemonCard;
import card.pokemon.helper.Move;

/**
 * This class represents a "Growlithe" Pokémon card in the Pokémon Trading Card Game.
 * It extends the PokemonCard class, calls the superclass constructor to initialize the card with its unique attributes.
 * It also adds a unique move to the card: "Flare".
 * 
 * @author  Tyler Mong
 * @version 1.0
 */
public class Growlithe extends PokemonCard
{
    /**
     * Constructor to initialize a "Growlithe" Pokémon card with its unique attributes.
     * It also adds a unique move to the card: "Flare".
     */
    public Growlithe()
    {
        super("Growlithe", 60, "Water", null, 1);
        addMove(new Move("Flare", 20, Map.of("Fire", 1, "Colorless", 1), "No additional effect."));
    }
}