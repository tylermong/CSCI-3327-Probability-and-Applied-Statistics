package card.trainer;

import card.Card;
import pile.piles.Deck;
import pile.piles.Hand;

public abstract class TrainerCard extends Card
{
    private String description;

    public TrainerCard(String name, String description)
    {
        super(name);
        this.description = description;
    }

    public String getDescription()
    {
        return description;
    }

    public abstract void useEffect(Deck deck, Hand hand);
}