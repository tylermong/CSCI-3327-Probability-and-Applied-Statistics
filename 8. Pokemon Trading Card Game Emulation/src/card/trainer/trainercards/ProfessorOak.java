package card.trainer.trainercards;

import card.trainer.TrainerCard;
import card.trainer.helper.TrainerEffect;
import pile.piles.Deck;
import pile.piles.DiscardPile;
import pile.piles.Hand;

// TODO: Add deck and hand to support drawing cards
public class ProfessorOak extends TrainerCard
{
    public ProfessorOak()
    {
        super("Professor Oak", "Discard your hand, then draw 7 cards.");
    }

    @Override
    public void useEffect(Deck deck, Hand hand, DiscardPile discardPile)
    {
        System.out.println("Using effect: Discard your hand, then draw 7 cards.");
        TrainerEffect.discardHand(hand, discardPile);
        TrainerEffect.drawCards(deck, hand, 7);
    }
}