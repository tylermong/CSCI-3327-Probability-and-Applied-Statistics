package card.pokemon;

import card.PokemonCard;
import card.pokemon.helper.Move;

public class Hitmonchan extends PokemonCard
{
    public Hitmonchan()
    {
        super("Hitmonchan", "Psychic", null, 2);
        addMove(new Move("Jab", 20, "No additional effect."));
        addMove(new Move("Special Punch", 40, "No additional effect."));
    }
}