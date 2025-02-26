package card.pokemon.species;

import java.util.Map;

import card.pokemon.PokemonCard;
import card.pokemon.helper.Move;

public class Pikachu extends PokemonCard
{
    public Pikachu()
    {
        super("Pikachu", "Fighting", null, 1);
        addMove(new Move("Gnaw", 10, Map.of("Colorless", 1), "No additional effect."));
        addMove(new Move("Thunder Jolt", 30, Map.of("Lightning", 1, "Colorless", 1), "Flip a coin. If tails, Pikachu does 10 damage to itself."));
    }
}