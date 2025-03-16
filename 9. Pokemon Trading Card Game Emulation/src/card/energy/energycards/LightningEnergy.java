package card.energy.energycards;

import card.energy.EnergyCard;

/**
 * This class represents a "Lightning Energy" card in the Pokémon Trading Card Game.
 * It extends the EnergyCard class, calls the superclass constructor to initialize the card with its unique attributes.
 * 
 * @author  Tyler Mong
 * @version 1.0
 */
public class LightningEnergy extends EnergyCard
{
    /**
     * Constructor to initialize a "Lightning Energy" card with its type and value.
     */
    public LightningEnergy()
    {
        super("Lightning Energy", "Lightning", 1);
    }
}