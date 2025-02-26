package card.pokemon;

import java.util.Map;

import card.PokemonCard;
import card.pokemon.helper.Move;

public class Electabuzz extends PokemonCard
{
    public Electabuzz()
    {
        super("Electabuzz", "Fighting", null, 2);
        addMove(new Move("Thundershock", 10, Map.of("Lightning", 1), "Flip a coin. If heads, the Defending Pokémon is now Paralyzed."));
        addMove(new Move("Thunderpunch", 30, Map.of("Lightning", 1, "Colorless", 1), "Flip a coin. If heads, this attack does 30 damage plus 10 more damage; if tails, this attack does 30 damage and Electabuzz does 10 damage to itself."));
    }
}