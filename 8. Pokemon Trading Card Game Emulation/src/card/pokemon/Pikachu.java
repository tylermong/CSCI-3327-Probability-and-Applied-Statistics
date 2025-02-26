package card.pokemon;

import card.PokemonCard;
import card.pokemon.helper.Move;

public class Pikachu extends PokemonCard
{
    public Pikachu()
    {
        super("Pikachu", "Fighting", null, 1);
        addMove(new Move("Gnaw", 10, "No additional effect."));
        addMove(new Move("Thunder Jolt", 30, "Flip a coin. If tails, Pikachu does 10 damage to itself."));
    }
}