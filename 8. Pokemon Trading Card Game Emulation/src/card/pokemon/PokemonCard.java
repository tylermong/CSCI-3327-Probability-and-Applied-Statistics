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

    // TODO: Add energy to discard pile
    public void removeEnergy(String energyType)
    {
        int count = energyCount.get(energyType);
        energyCount.put(energyType, count - 1);
    }

    public Map<String, Integer> displayEnergyInMap()
    {
        return energyCount;
    }

    public List<String> displayEnergyInList()
    {
        System.out.println("Energy attached to " + getName() + ":");

        // Create a list of energy cards from the map
        List<String> energyList = new ArrayList<>();
        for (Map.Entry<String, Integer> entry : energyCount.entrySet())
        {
            String energyType = entry.getKey();
            int count = entry.getValue();

            // Add the energy type to the list based on its count
            for (int i = 0; i < count; i++)
            {
                energyList.add(energyType);
            }
        }

        for (int i = 0; i < energyCount.size(); i++)
        {
            System.out.println((i + 1) + ". " + energyList.get(i));
        }

        // Return the list of energy cards
        return energyList;
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