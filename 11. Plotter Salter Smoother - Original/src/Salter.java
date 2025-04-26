import java.io.*;
import java.util.Random;

public class Salter
{
    private static final Random random = new Random();

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
            String line;

            line = bufferedReader.readLine();
            fileWriter.append("x,y\n");
            while ((line = bufferedReader.readLine()) != null)
            {
                String[] values = line.split(",");
                double x = Double.parseDouble(values[0]);
                double y = Double.parseDouble(values[1]);

                y += random.nextInt(-10, 10);

                fileWriter.append(x + "," + y + "\n");
            }

            bufferedReader.close();
            fileWriter.close();
        }
        catch (Exception exception)
        {
            System.out.println(exception);
        }
    }
}
