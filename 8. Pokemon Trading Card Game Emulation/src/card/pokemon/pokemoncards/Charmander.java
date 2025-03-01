package card.pokemon.pokemoncards;

import java.util.Map;

import card.pokemon.PokemonCard;
import card.pokemon.helper.Move;

public class Charmander extends PokemonCard
{
    public Charmander()
    {
        super("Charmander", 50, "Water", null, 1);
        addMove(new Move("Scratch", 10, Map.of("Colorless", 1), "No additional effect."));
        addMove(new Move("Ember", 30, Map.of("Fire", 1, "Colorless", 1), "Discard 1 Fire Energy card attached to Charmander in order to use this attack."));
    }
}