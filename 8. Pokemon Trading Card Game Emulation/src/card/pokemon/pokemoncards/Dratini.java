package card.pokemon.pokemoncards;

import java.util.Map;

import card.pokemon.PokemonCard;
import card.pokemon.helper.Move;

public class Dratini extends PokemonCard
{
    public Dratini()
    {
        super("Dratini", 40, null, "Psychic", 1);
        addMove(new Move("Pound", 10, Map.of("Colorless", 1), "No additional effect."));
    }
}