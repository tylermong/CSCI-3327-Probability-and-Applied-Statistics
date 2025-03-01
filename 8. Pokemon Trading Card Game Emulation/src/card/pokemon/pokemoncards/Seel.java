package card.pokemon.pokemoncards;

import java.util.Map;

import card.pokemon.PokemonCard;
import card.pokemon.helper.Move;

public class Seel extends PokemonCard
{
    public Seel()
    {
        super("Seel", 50, "Lightning", null, 1);
        addMove(new Move("Headbutt", 10, Map.of("Water", 1), "No additional effect."));
    }
}