package card.trainer;

import card.TrainerCard;

public class Bill extends TrainerCard
{
    public Bill()
    {
        super("Bill", "Draw 2 cards.");
    }

    @Override
    public void useEffect()
    {
        System.out.println("Using effect: Draw 2 cards.");
        // TODO: Add draw card helper
    }
}