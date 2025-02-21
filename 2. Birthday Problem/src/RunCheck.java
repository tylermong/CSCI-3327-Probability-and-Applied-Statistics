import java.util.Scanner;

/**
 * Birthday problem simulation: Given a class of N students, what is the probability that at least two students share
 * the same birthday?
 * 
 * This class calls the Class class to simulate the birthday problem, and prints the number of shared birthdays and the
 * days in common.
 * 
 * @author  Tyler Mong
 * @version 1.0
 */
public class RunCheck
{
    /**
     * Runs the simulation and prints the results to the console.
     * @param args  Command line arguments.
     */
    public static void main(String[] args)
    {
        // Prompts the user to enter the class size.
        Scanner in = new Scanner(System.in);
        System.out.print("Enter class size: ");
        int classSize = in.nextInt();

        // Creates a class object and set random birthdays for all students.
        Class class1 = new Class(classSize);
        class1.setRandomBirthdays();

        // Prints the number of shared birthdays and the days in common.
        System.out.println("Number of shared birthdays: " + class1.countSameBirthday());
        System.out.println("Days in common: " + class1.printSharedBirthdays());
    }
}