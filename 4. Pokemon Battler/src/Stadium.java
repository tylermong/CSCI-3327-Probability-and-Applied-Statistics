public class Stadium
{
    public void battle(Pokemon p1, Pokemon p2)
    {
        if (p1.getSpeed() > p2.getSpeed())
        {
            while (p1.getHp() > 0 && p2.getHp() > 0)
            {
                p2.setHp(p2.getHp() - (p1.getAttack() - p2.getDefense()));
                p1.setHp(p1.getHp() - (p2.getAttack() - p1.getDefense()));
            }
        }
        else
        {
            while (p1.getHp() > 0 && p2.getHp() > 0)
            {
                p1.setHp(p1.getHp() - (p2.getAttack() - p1.getDefense()));
                p2.setHp(p2.getHp() - (p1.getAttack() - p2.getDefense()));
            }
        }

        if (p1.getHp() <= 0)
        {
            System.out.println("Player 1 wins!");
        }
        else
        {
            System.out.println("Player 2 wins!");
        }
    }
}