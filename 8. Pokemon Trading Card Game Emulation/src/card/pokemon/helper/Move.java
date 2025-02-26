package card.pokemon.helper;

public class Move
{
    private String name;
    private int damage;
    private String effect;

    public Move(String name, int damage, String effect)
    {
        this.name = name;
        this.damage = damage;
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

    public String getEffect()
    {
        return effect;
    }
}