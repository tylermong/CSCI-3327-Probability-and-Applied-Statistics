package card;

public class EnergyCard extends Card
{
    private String energyType;
    private int energyValue;

    public EnergyCard(String name, String energyType, int energyValue)
    {
        super(name);
        this.energyType = energyType;
        this.energyValue = energyValue;
    }

    public String getType()
    {
        return energyType;
    }

    public int energyValue()
    {
        return energyValue;
    }
}