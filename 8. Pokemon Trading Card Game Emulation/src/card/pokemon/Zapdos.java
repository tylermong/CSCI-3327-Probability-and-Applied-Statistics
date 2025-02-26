package card.pokemon;

import card.PokemonCard;
import card.pokemon.helper.Move;

public class Zapdos extends PokemonCard
{
    public Zapdos()
    {
        super("Zapdos", null, "Fighting", 3);
        addMove(new Move("Thunder", 60, "Flip a coin. If tails, Zapdos does 30 damage to itself."));
        addMove(new Move("Thunderbolt", 100, "Discard all Energy cards attached to Zapdos in order to use this attack."));
    }
}