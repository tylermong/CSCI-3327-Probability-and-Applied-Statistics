package card.pokemon.pokemoncards;

import java.util.Map;

import card.pokemon.PokemonCard;
import card.pokemon.helper.Move;

public class Hitmonchan extends PokemonCard
{
    public Hitmonchan()
    {
        super("Hitmonchan", 70, "Psychic", null, 2);
        addMove(new Move("Jab", 20, Map.of("Fighting", 1), "No additional effect."));
        addMove(new Move("Special Punch", 40, Map.of("Fighting", 2, "Colorless", 1), "No additional effect."));
    }
}