import java.io.*;
import java.util.Random;
import java.util.Scanner;

public class Salter
{
    private static final Random random = new Random();
    private static final Scanner scanner = new Scanner(System.in);
    private int variability;

    public Salter()
    {
        System.out.print("Enter variability (or press Enter for default value of 10): ");
        String input = scanner.nextLine().trim();
        
        if (input.isEmpty())
        {
            this.variability = 10;
            System.out.println("Using default variability: 10");
        }
        else
        {
            try
            {
                this.variability = Integer.parseInt(input);
            }
            catch (NumberFormatException e)
            {
                System.out.println("Invalid input. Using default variability: 10");
                this.variability = 10;
            }
        }
    }

    public void run()
    {
        salt();
    }   
    
    private void salt()
    {
        try
        {
            BufferedReader bufferedReader = new BufferedReader(new FileReader("11. Plotter Salter Smoother - Original/output/data.csv"));
            FileWriter fileWriter = new FileWriter("11. Plotter Salter Smoother - Original/output/salted_data.csv");
            String line = bufferedReader.readLine();
            System.out.println("\nSalting data with variability of " + variability + "...");

            fileWriter.append("x,y\n");
            while ((line = bufferedReader.readLine()) != null)
            {
                String[] values = line.split(",");
                double x = Double.parseDouble(values[0]);
                double y = Double.parseDouble(values[1]);

                y += random.nextInt(-variability, variability);

                fileWriter.append(x + "," + y + "\n");
            }
            System.out.println("Salting complete, data saved to output/salted_data.csv");
            bufferedReader.close();
            fileWriter.close();
        }
        catch (Exception exception)
        {
            System.out.println(exception);
        }
    }
}
