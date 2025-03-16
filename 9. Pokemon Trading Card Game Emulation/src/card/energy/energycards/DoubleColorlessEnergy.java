package card.energy.energycards;

import card.energy.EnergyCard;

/**
 * This class represents a "Double Colorless Energy" card in the Pokémon Trading Card Game.
 * It extends the EnergyCard class, calls the superclass constructor to initialize the card with its unique attributes.
 * 
 * @author  Tyler Mong
 * @version 1.0
 */
public class DoubleColorlessEnergy extends EnergyCard
{
    /**
     * Constructor to initialize a "Double Colorless Energy" card with its type and value.
     */
    public DoubleColorlessEnergy()
    {
        super("Double Colorless Energy", "Colorless", 2);
    }
}