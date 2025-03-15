package card.pokemon.pokemoncards;

import java.util.Map;

import card.pokemon.PokemonCard;
import card.pokemon.helper.Move;

/**
 * This class represents a "Rattata" Pokémon card in the Pokémon Trading Card Game.
 * It extends the PokemonCard class, calls the superclass constructor to initialize the card with its unique attributes.
 * It also adds a unique move to the card: "Pound".
 * 
 * @author  Tyler Mong
 * @version 1.0
 */
public class Rattata extends PokemonCard
{
    /**
     * Constructor to initialize a "Rattata" Pokémon card with its unique attributes.
     * It also adds a unique move to the card: "Bite".
     */
    public Rattata()
    {
        super("Rattata",30, "Fighting", "Psychic", 0);
        addMove(new Move("Bite", 20, Map.of("Colorless", 1), "No additional effect."));
    }
}