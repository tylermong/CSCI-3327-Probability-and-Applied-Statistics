package card.pokemon.pokemoncards;

import java.util.Map;

import card.pokemon.PokemonCard;
import card.pokemon.helper.Move;

public class Machop extends PokemonCard
{
    public Machop()
    {
        super("Machop", 50, "Psychic", null, 1);
        addMove(new Move("Low Kick", 20, Map.of("Fightning", 1), "No additional effect."));
    }
}