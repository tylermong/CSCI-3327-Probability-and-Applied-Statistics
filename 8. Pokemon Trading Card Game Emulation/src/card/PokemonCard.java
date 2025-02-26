package card;

import card.pokemon.helper.Move;
import java.util.ArrayList;
import java.util.List;

public class PokemonCard extends Card
{
    private String weakness;
    private String resistance;
    private int retreatCost;
    private List<Move> moves;

    public PokemonCard(String name, String weakness, String resistance, int retreatCost)
    {
        super(name);
        this.weakness = weakness;
        this.resistance = resistance;
        this.retreatCost = retreatCost;
        this.moves = new ArrayList<>();
    }

    public String getWeakness()
    {
        return weakness;
    }

    public String getResistance()
    {
        return resistance;
    }

    public int getRetreatCost()
    {
        return retreatCost;
    }

    public void addMove(Move move)
    {
        moves.add(move);
    }
}