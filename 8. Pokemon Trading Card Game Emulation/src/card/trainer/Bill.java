package card.trainer;

import card.TrainerCard;
import card.trainer.helper.TrainerEffect;

// TODO: Add deck and hand to support drawing cards
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
        TrainerEffect.drawCards(2);
    }
}