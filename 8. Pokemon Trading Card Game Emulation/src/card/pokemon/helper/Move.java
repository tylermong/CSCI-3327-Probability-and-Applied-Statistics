package card.pokemon.helper;

import java.util.Map;
public class Move
{
    private String name;
    private int damage;
    private Map<String, Integer> energyCost;
    private String effect;

    public Move(String name, int damage, Map<String, Integer> energyCost, String effect)
    {
        this.name = name;
        this.damage = damage;
        this.energyCost = energyCost;
        this.effect = effect;
    }

    public String getName()
    {
        return name;
    }

    public int getDamage()
    {
        return damage;
    }

    public Map<String, Integer> getEnergyCost()
    {
        return energyCost;
    }

    public String getEffect()
    {
        return effect;
    }
}