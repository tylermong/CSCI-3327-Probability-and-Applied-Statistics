package card.trainer.trainercards;

import card.trainer.TrainerCard;
import card.trainer.helper.TrainerEffect;

// TODO: Add deck and hand to support drawing cards
public class ProfessorOak extends TrainerCard
{
    public ProfessorOak()
    {
        super("Professor Oak", "Discard your hand, then draw 7 cards.");
    }

    @Override
    public void useEffect()
    {
        System.out.println("Using effect: Discard your hand, then draw 7 cards.");
        //TODO: TrainerEffect.discardHand();
        TrainerEffect.drawCards(7);
    }
}