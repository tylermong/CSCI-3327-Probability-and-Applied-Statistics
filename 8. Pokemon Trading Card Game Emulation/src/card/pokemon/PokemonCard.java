package card.pokemon;

import card.Card;
import card.energy.EnergyCard;
import card.pokemon.helper.Move;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PokemonCard extends Card
{
    private int HP;
    private String weakness;
    private String resistance;
    private int retreatCost;
    private List<Move> moves;
    private Map<String, Integer> energyCount;

    public PokemonCard(String name, int HP, String weakness, String resistance, int retreatCost)
    {
        super(name, "Pokémon");
        this.HP = HP;
        this.weakness = weakness;
        this.resistance = resistance;
        this.retreatCost = retreatCost;
        this.moves = new ArrayList<>();
        this.energyCount = new HashMap<>();
    }

    public int getHP()
    {
        return HP;
    }

    public void setHP(int HP)
    {
        this.HP = HP;
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

    public Move[] getMoves()
    {
        return moves.toArray(new Move[0]);
    }

    public void addEnergy(EnergyCard energyCard)
    {
        String energyType = energyCard.getType();
        energyCount.put(energyType, energyCount.getOrDefault(energyType, 0) + 1);
    }

    public int getTypeEnergy(String energyType)
    {
        return energyCount.getOrDefault(energyType, 0);
    }

    public int getTotalEnergy()
    {
        int total = 0;
        for (int count : energyCount.values())
        {
            total += count;
        }
        return total;
    }
}