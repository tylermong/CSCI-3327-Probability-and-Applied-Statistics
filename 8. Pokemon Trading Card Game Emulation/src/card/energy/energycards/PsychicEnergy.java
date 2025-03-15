package card.energy.energycards;

import card.energy.EnergyCard;

/**
 * This class represents a "Psychic Energy" card in the Pokémon Trading Card Game.
 * It extends the EnergyCard class, calls the superclass constructor to initialize the card with its unique attributes.
 * 
 * @author  Tyler Mong
 * @version 1.0
 */
public class PsychicEnergy extends EnergyCard
{
    /**
     * Constructor to initialize a "Psychic Energy" card with its type and value.
     */
    public PsychicEnergy()
    {
        super("Psychic Energy", "Psychic", 1);
    }
}