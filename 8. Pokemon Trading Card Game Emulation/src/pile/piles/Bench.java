package pile.piles;

import pile.Pile;

public class Bench extends Pile
{
    private static final int MAX_SIZE = 5;

    public Bench()
    {
        super();
    }

    public int getMaxSize()
    {
        return MAX_SIZE;
    }
}