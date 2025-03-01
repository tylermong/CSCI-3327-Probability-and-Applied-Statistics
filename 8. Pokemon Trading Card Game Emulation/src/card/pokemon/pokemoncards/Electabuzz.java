package card.pokemon.pokemoncards;

import java.util.Map;

import card.pokemon.PokemonCard;
import card.pokemon.helper.Move;

public class Electabuzz extends PokemonCard
{
    public Electabuzz()
    {
        super("Electabuzz", 70, "Fighting", null, 2);
        addMove(new Move("Thundershock", 10, Map.of("Lightning", 1), "Flip a coin. If heads, the Defending Pokémon is now Paralyzed."));
        addMove(new Move("Thunderpunch", 30, Map.of("Lightning", 1, "Colorless", 1), "Flip a coin. If heads, this attack does 30 damage plus 10 more damage; if tails, this attack does 30 damage and Electabuzz does 10 damage to itself."));
    }
}