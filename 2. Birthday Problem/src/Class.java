/**
 * This class builds a Class object, used to represent a room of people (class of students) in the birthday problem. It
 * contains methods to set random birthdays for all students, count the number of shared birthdays, and print the shared
 * birthdays.
 * 
 * @author  Tyler Mong
 * @version 1.0
 */
public class Class
{
    int[] people;

    /**
     * Constructs a Class object with the given size of students.
     * @param userSize  The size of the class.
     */
    public Class(int userSize)
    {
        people = new int[userSize];
    }

    /**
     * Sets random birthdays for all students in the class.
     */
    public void setRandomBirthdays()
    {
        for (int i = 0; i < people.length; i++)
        {
            people[i] = (int)(Math.random() * 365) + 1;
        }
    }

    /**
     * Counts the number of shared birthdays in the class.
     * @return  The number of shared birthdays.
     */
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

    /**
     * Prints the shared birthdays in the class.
     * @return  The number of shared birthdays.
     */
    public int printSharedBirthdays()
    {
        int counter = 0;
        
        // Loops through the entire class, comparing each student's birthday to the rest of the class.
        for (int i = 0; i < people.length; i++)
        {
            for (int j = i + 1; j < people.length; j++)
            {
                // If two students share a birthday, print the students and the shared day, and increment the counter.
                if (people[i] == people[j])
                {
                    System.out.println("Students " + i + " and " + j + " share day: " + people[i]);
                    counter++;
                }
            }
        }
        return counter;
    }
}