package card.pokemon.pokemoncards;

import java.util.Map;

import card.pokemon.PokemonCard;
import card.pokemon.helper.Move;

public class Diglett extends PokemonCard
{
    public Diglett()
    {
        super("Diglett", 30, "Grass", "Lightning", 0);
        addMove(new Move("Dig", 10, Map.of("Fightning", 1), "No additional effect."));
        addMove(new Move("Mud Slap", 30, Map.of("Fightning", 1), "No additional effect."));
    }
}