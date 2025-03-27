import java.io.*;
import java.util.ArrayList;
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
            BufferedReader br = new BufferedReader(new FileReader("12. Salting\\\\data.csv"));
            FileWriter writer = new FileWriter("12. Salting\\salted_data.csv");
            String line;

            line = br.readLine();
            writer.append("x,y\n");
            while ((line = br.readLine()) != null)
            {
                String[] values = line.split(",");
                double x = Double.parseDouble(values[0]);
                double y = Double.parseDouble(values[1]);

                y += random.nextInt(-10, 10);

                writer.append(x + "," + y + "\n");
            }

            br.close();
            writer.close();
        }
        catch (Exception e)
        {
            System.out.println(e);
        }
    }
}
