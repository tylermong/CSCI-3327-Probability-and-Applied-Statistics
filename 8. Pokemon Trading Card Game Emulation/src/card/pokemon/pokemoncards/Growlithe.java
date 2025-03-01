package card.pokemon.pokemoncards;

import java.util.Map;

import card.pokemon.PokemonCard;
import card.pokemon.helper.Move;

public class Growlithe extends PokemonCard
{
    public Growlithe()
    {
        super("Growlithe", 60, "Water", null, 1);
        addMove(new Move("Flare", 20, Map.of("Fire", 1, "Colorless", 1), "No additional effect."));
    }
}