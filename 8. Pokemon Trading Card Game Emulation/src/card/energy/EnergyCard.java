package card.energy;

import card.Card;

/**
 * This class represents an Energy card in the Pokémon Trading Card Game.
 * It extends the Card class and provides methods to get the type and value of the energy card.
 * 
 * @author  Tyler Mong
 * @version 1.0
 */
public class EnergyCard extends Card
{
    /**
     * The type of the Energy card (e.g., "Fire", "Water").
     */
    private String energyType;

    /**
     * The value of the Energy card (e.g., 1 for basic energy, 2 for double energy).
     */
    private int energyValue;

    /**
     * Constructor to initialize an Energy card with a name, energy type, and energy value.
     * This constructor is called by the subclasses to create a new Energy card.
     * 
     * @param name      The name of the Energy card.
     * @param energyType    The type of energy (e.g., "Fire", "Water").
     * @param energyValue   The value of the energy (e.g., 1 for basic energy, 2 for double energy).
     */
    public EnergyCard(String name, String energyType, int energyValue)
    {
        super(name, "Energy");
        this.energyType = energyType;
        this.energyValue = energyValue;
    }

    /**
     * Returns the type of the Energy card.
     * 
     * @return  The type of the Energy card (e.g., "Fire", "Water").
     */
    public String getType()
    {
        return energyType;
    }

    /**
     * Returns the value of the Energy card.
     * 
     * @return  The value of the Energy card (e.g., 1 for basic energy, 2 for double energy).
     */
    public int energyValue()
    {
        return energyValue;
    }
}