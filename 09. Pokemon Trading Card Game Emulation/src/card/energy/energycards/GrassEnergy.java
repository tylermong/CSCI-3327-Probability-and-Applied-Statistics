package card.energy.energycards;

import card.energy.EnergyCard;

/**
 * This class represents a "Grass Energy" card in the Pokémon Trading Card Game.
 * It extends the EnergyCard class, calls the superclass constructor to initialize the card with its unique attributes.
 * 
 * @author  Tyler Mong
 * @version 1.0
 */
public class GrassEnergy extends EnergyCard
{
    /**
     * Constructor to initialize a "Grass Energy" card with its type and value.
     */
    public GrassEnergy()
    {
        super("Grass Energy", "Grass", 1);
    }
}