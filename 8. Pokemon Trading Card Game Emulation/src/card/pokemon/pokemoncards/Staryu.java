package card.pokemon.pokemoncards;

import java.util.Map;

import card.pokemon.PokemonCard;
import card.pokemon.helper.Move;

public class Staryu extends PokemonCard
{
    public Staryu()
    {
        super("Staryu",40, "Lightning", null, 1);
        addMove(new Move("Slap", 20, Map.of("Water", 1), "No additional effect."));
    }
}