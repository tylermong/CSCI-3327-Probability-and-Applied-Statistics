package card.trainer.trainercards;

import card.trainer.TrainerCard;
import card.trainer.helper.TrainerEffect;
import pile.piles.Deck;
import pile.piles.DiscardPile;
import pile.piles.Hand;

public class Bill extends TrainerCard
{
    public Bill()
    {
        super("Bill", "Draw 2 cards.");
    }

    @Override
    public void useEffect(Deck deck, Hand hand, DiscardPile discardPile)
    {
        System.out.println("Using effect: Draw 2 cards.");
        TrainerEffect.drawCards(deck, hand, 2);
        System.out.println("Bill effect used.");
    }
}