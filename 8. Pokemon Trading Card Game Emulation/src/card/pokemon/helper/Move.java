package card.pokemon.helper;

import java.util.Map;
/**
 * This class provides helper methods for Pokémon cards in the Pokémon Trading Card Game.
 * It contains methods to get the name, damage, energy cost, and effect of a move.
 * 
 * @author  Tyler Mong
 * @version 1.0
 */
public class Move
{
    // Stores the name, damage, energy cost, and effect of a move.
    private String name;
    private int damage;
    private Map<String, Integer> energyCost;
    private String effect;

    /**
     * Constructor to initialize a Move with a name, damage, energy cost, and effect.
     * 
     * @param name
     * @param damage
     * @param energyCost
     * @param effect
     */
    public Move(String name, int damage, Map<String, Integer> energyCost, String effect)
    {
        this.name = name;
        this.damage = damage;
        this.energyCost = energyCost;
        this.effect = effect;
    }

    /**
     * Returns the name of the move.
     * 
     * @return  The name of the move.
     */
    public String getName()
    {
        return name;
    }

    /**
     * Returns the damage of the move.
     * 
     * @return  The damage of the move.
     */
    public int getDamage()
    {
        return damage;
    }

    /**
     * Returns the energy cost of the move.
     * 
     * @return  A map of energy types and their required amounts for the move.
     */
    public Map<String, Integer> getEnergyCost()
    {
        return energyCost;
    }

    /**
     * Returns the effect of the move.
     * 
     * @return  The effect of the move.
     */
    public String getEffect()
    {
        return effect;
    }
}