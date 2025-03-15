package card.pokemon;

import card.Card;
import card.energy.EnergyCard;
import card.pokemon.helper.Move;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * This class represents a Pokémon card in the Pokémon Trading Card Game.
 * It extends the Card class and provides methods to get various attributes of the card, including HP, weakness, resistance, retreat cost, energy count, moves, and more.
 * 
 * @author  Tyler Mong
 * @version 1.0
 */
public class PokemonCard extends Card
{
    // Stores various attributes of the Pokémon card.
    private int HP;
    private String weakness;
    private String resistance;
    private int retreatCost;
    private List<Move> moves;
    private Map<String, Integer> energyCount;   // Stores the attached energy cards and their counts.

    /**
     * Constructor to initialize a Pokémon card with a name, HP, weakness, resistance, retreat cost, moves, and energy count.
     * This constructor is called by the subclasses to create a new Pokémon card.
     */
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

    /**
     * Returns the HP of the Pokémon card.
     * 
     * @return  The HP of the Pokémon card.
     */
    public int getHP()
    {
        return HP;
    }

    /**
     * Sets the HP of the Pokémon card.
     * This method is used to update the HP of the Pokémon card when it takes damage.
     * 
     * @param HP    The HP to set for the Pokémon card.
     */
    public void setHP(int HP)
    {
        this.HP = HP;
    }

    /**
     * Returns the weakness of the Pokémon card.
     * 
     * @return  The weakness of the Pokémon card.
     */
    public String getWeakness()
    {
        return weakness;
    }

    /**
     * Returns the resistance of the Pokémon card.
     * 
     * @return  The resistance of the Pokémon card.
     */
    public String getResistance()
    {
        return resistance;
    }

    /**
     * Returns the Pokémon card's retreat cost.
     * 
     * @return  The Pokémon card's retreat cost.
     */
    public int getRetreatCost()
    {
        return retreatCost;
    }

    /**
     * Adds a move to the Pokémon card's list of moves.
     * 
     * @param move  The move to add.
     */
    public void addMove(Move move)
    {
        moves.add(move);
    }

    /**
     * Returns a Pokémon card's moves.
     * 
     * @return  The list of moves associated with the Pokémon card.
     */
    public Move[] getMoves()
    {
        return moves.toArray(new Move[0]);
    }

    /**
     * Attaches an energy card to the Pokémon card.
     * 
     * @param energyCard    The energy card to attach to the Pokémon card.
     */
    public void addEnergy(EnergyCard energyCard)
    {
        // Get the type of energy.
        String energyType = energyCard.getType();

        // Add the energy type to the map and increment its count by 1. If the energy type is not already in the map, it will be added with a count of 1.
        energyCount.put(energyType, energyCount.getOrDefault(energyType, 0) + 1);
    }

    /**
     * Removes an energy card from the Pokémon card.
     * 
     * @param energyType    The type of energy to remove.
     */
    public void removeEnergy(String energyType)
    {
        // Get the count of the energy type.
        int count = energyCount.get(energyType);

        // Decrement the count of this type by 1.
        energyCount.put(energyType, count - 1);
    }

    /**
     * Returns the type of energy attached to the Pokémon card and its count.
     * 
     * @return  A map containing the type of energy attached to the Pokémon card and its count.
     */
    public Map<String, Integer> displayEnergyInMap()
    {
        return energyCount;
    }

    /**
     * Displays the energy attached to the Pokémon card in a list format.
     * 
     * @return  A list of energy attached to the Pokémon card.
     */
    public List<String> displayEnergyInList()
    {
        System.out.println("Energy attached to " + getName() + ":");

        // Create a list to store the attached energy in.
        List<String> energyList = new ArrayList<>();
        
        // For each entry in energyCount map, get the energy type and its count.
        for (Map.Entry<String, Integer> entry : energyCount.entrySet())
        {
            String energyType = entry.getKey();
            int count = entry.getValue();

            // Add the energy type to the list, for its count in the map.
            for (int i = 0; i < count; i++)
            {
                energyList.add(energyType);
            }
        }

        // Print the energy list in a numbered format.
        for (int i = 0; i < energyCount.size(); i++)
        {
            System.out.println((i + 1) + ". " + energyList.get(i));
        }

        // Return the list of energy cards.
        return energyList;
    }

    /**
     * Returns the count of a specific type of energy attached to the Pokémon card.
     * This method is used to verify Energy requirements for attack moves.
     * 
     * @param energyType    The type of energy to get the count for.
     * @return              The count of the specified type of energy attached to the Pokémon card.
     */
    public int getTypeEnergy(String energyType)
    {
        // Get the count of the specified energy type from the map. If the energy type is not found, return 0.
        return energyCount.getOrDefault(energyType, 0);
    }

    /**
     * Returns the total count of all energy attached to the Pokémon card.
     * This method is used to verify the total energy count for retreating.
     * 
     * @return  The total count of all energy attached to the Pokémon card.
     */
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