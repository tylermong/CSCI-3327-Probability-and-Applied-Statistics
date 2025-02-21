public class RunGameshow
{
    public static void main(String[] args)
    {
        int numberOfRounds = 10000;

        Gameshow someShow = new Gameshow();

        int stickWithChoiceWinNumber = someShow.playGameStickWithChoice(numberOfRounds);
        System.out.println("Stick with choice win #: " + stickWithChoiceWinNumber + "/" + numberOfRounds);
        System.out.printf("Stick with choice win %%: %.2f%%\n", (double) stickWithChoiceWinNumber / numberOfRounds * 100);

        int switchChoiceWinNumber = someShow.playGameSwitchChoice(numberOfRounds);
        System.out.println("\nSwitch choice win #: " + switchChoiceWinNumber + "/" + numberOfRounds);
        System.out.printf("Switch choice win %%: %.2f%%\n", (double) switchChoiceWinNumber / numberOfRounds * 100);

        /*
         * A: If the contestant has no idea which curtains hide the various prizes and selects a curtain at
         * random, assign reasonable probabilities to the simple events and calculate the probability
         * that the contestant selects the curtain hiding the nice prize.
         * > P(E1) = 1/3, P(E2) = 1/3, P(E3) = 1/3
         *
         * B: Before showing the contestant what was behind the curtain initially chosen, the game show
         * host would open one of the curtains and show the contestant one of the duds (he could
         * always do this because he knew the curtain hiding the good prize). He then offered the
         * contestant the option of changing from the curtain initially selected to the other remaining
         * unopened curtain. Which strategy maximizes the contestant’s probability of winning the
         * good prize: stay with the initial choice or switch to the other curtain? In answering the
         * following sequence of questions, you will discover that, perhaps surprisingly, this question
         * can be answered by considering only the sample space above and using the probabilities
         * that you assigned to answer part (a).
         * > Switching to the other curtain maximizes the contestant's probability of winning the good prize.
         */
    }
}