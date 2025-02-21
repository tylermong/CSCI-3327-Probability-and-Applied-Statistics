import java.util.Scanner;

public class RunCheck
{
    public static void main(String[] args)
    {
        Scanner in = new Scanner(System.in);
        System.out.print("Enter class size: ");
        int classSize = in.nextInt();

        Class class1 = new Class(classSize);
        class1.setRandomBirthdays();

        System.out.println("Number of shared birthdays: " + class1.countSameBirthday());
        System.out.println("Days in common: " + class1.printSharedBirthdays());
    }
}