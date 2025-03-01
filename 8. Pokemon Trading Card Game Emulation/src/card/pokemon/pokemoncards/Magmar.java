package card.pokemon.pokemoncards;

import java.util.Map;

import card.pokemon.PokemonCard;
import card.pokemon.helper.Move;

public class Magmar extends PokemonCard
{
    public Magmar()
    {
        super("Magmar", "Water", null, 2);
        addMove(new Move("Fire Punch", 30, Map.of("Fire", 2), "No additional effect."));
        addMove(new Move("Flamethrower", 50, Map.of("Fire", 2, "Colorless", 1), "Discard 1 Fire Energy card attached to Magmar in order to use this attack."));
    }
}