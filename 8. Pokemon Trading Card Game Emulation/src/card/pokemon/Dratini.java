package card.pokemon;

import card.PokemonCard;
import card.pokemon.helper.Move;

public class Dratini extends PokemonCard
{
    public Dratini()
    {
        super("Dratini", null, "Psychic", 1);
        addMove(new Move("Pound", 10, "No additional effect."));
    }
}