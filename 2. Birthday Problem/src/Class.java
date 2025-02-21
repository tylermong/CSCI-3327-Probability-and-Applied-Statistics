public class Class
{
    int[] people;

    public Class(int userSize)
    {
        people = new int[userSize];
    }

    public void setRandomBirthdays()
    {
        for (int i = 0; i < people.length; i++)
        {
            people[i] = (int)(Math.random() * 365) + 1;
        }
    }

    public int countSameBirthday()
    {
        int counter = 0;
        for (int i = 0; i < people.length; i++)
        {
            for (int j = i + 1; j < people.length; j++)
            {
                if (people[i] == people[j])
                    counter++;
            }
        }
        return counter;
    }

    public int printSharedBirthdays()
    {
        int counter = 0;
        for (int i = 0; i < people.length; i++)
        {
            for (int j = i + 1; j < people.length; j++)
            {
                if (people[i] == people[j])
                    System.out.println(people[i]);
            }
        }
        return counter;
    }
}