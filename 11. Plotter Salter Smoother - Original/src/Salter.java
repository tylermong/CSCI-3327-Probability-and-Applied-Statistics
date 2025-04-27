import java.io.*;
import java.util.Random;

public class Salter
{
    private static final Random random = new Random();
    private int variability;

    public Salter(int variability)
    {
        this.variability = variability;
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
