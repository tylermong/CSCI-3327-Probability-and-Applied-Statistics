package card.pokemon;

import card.Card;
import card.pokemon.helper.Move;
import java.util.ArrayList;
import java.util.List;

public class PokemonCard extends Card
{
    private int HP;
    private String weakness;
    private String resistance;
    private int retreatCost;
    private List<Move> moves;

    public PokemonCard(String name, int HP, String weakness, String resistance, int retreatCost)
    {
        super(name);
        this.HP = HP;
        this.weakness = weakness;
        this.resistance = resistance;
        this.retreatCost = retreatCost;
        this.moves = new ArrayList<>();
    }

    public int getHP()
    {
        return HP;
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