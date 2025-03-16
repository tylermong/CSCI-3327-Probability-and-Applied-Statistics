package card;

/**
 * This class represents a generic card in the Pokémon Trading Card Game.
 * It provides methods to get the card name and card type.
 * 
 * @author  Tyler Mong
 * @version 1.0
 */
public class Card
{
    // Variables to store the name and type of the card.
    private String name;
    private String type;

    /**
     * Constructor to initialize a card with a name and type.
     * This constructor is called by the subclasses to create a new card.
     * 
     * @param name  The name of the card.
     * @param type  The type of the card (e.g., Pokémon, Energy, Trainer).
     */
    public Card(String name, String type)
    {
        this.name = name;
        this.type = type;
    }

    /**
     * Returns the name of the card.
     * 
     * @return  The name of the card.
     */
    public String getName()
    {
        return name;
    }

    /**
     * Returns the type of the card.
     * 
     * @return  The type of the card (e.g., Pokémon, Energy, Trainer).
     */
    public String getType()
    {
        return type;
    }
}