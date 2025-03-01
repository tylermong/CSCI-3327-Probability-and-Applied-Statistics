package card.pokemon.pokemoncards;

import java.util.Map;

import card.pokemon.PokemonCard;
import card.pokemon.helper.Move;

public class Rattata extends PokemonCard
{
    public Rattata()
    {
        super("Rattata",30, "Fighting", "Psychic", 0);
        addMove(new Move("Bite", 20, Map.of("Colorless", 1), "No additional effect."));
    }
}