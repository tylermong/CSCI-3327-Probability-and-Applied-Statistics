package card.pokemon.pokemoncards;

import java.util.Map;

import card.pokemon.PokemonCard;
import card.pokemon.helper.Move;

/**
 * This class represents a "Diglett" Pokémon card in the Pokémon Trading Card Game.
 * It extends the PokemonCard class, calls the superclass constructor to initialize the card with its unique attributes.
 * It also adds two unique moves to the card: "Dig" and "Mud Slap".
 * 
 * @author  Tyler Mong
 * @version 1.0
 */
public class Diglett extends PokemonCard
{
    /**
     * Constructor to initialize a "Diglett" Pokémon card with its unique attributes.
     * It also adds two unique moves to the card: "Dig" and "Mud Slap".
     */
    public Diglett()
    {
        super("Diglett", 30, "Grass", "Lightning", 0);
        addMove(new Move("Dig", 10, Map.of("Fightning", 1), "No additional effect."));
        addMove(new Move("Mud Slap", 30, Map.of("Fightning", 1), "No additional effect."));
    }
}