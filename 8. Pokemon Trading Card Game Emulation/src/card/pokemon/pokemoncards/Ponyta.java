package card.pokemon.pokemoncards;

import java.util.Map;

import card.pokemon.PokemonCard;
import card.pokemon.helper.Move;

public class Ponyta extends PokemonCard
{
    public Ponyta()
    {
        super("Ponyta", 40, "Water", null, 1);
        addMove(new Move("Smash Kick", 20, Map.of("Colorless", 2), "No additional effect."));
        addMove(new Move("Flame Tail", 30, Map.of("Fire", 2), "No additional effect."));
    }
}