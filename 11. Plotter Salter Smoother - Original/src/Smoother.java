import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Smoother
{
    private static final Scanner scanner = new Scanner(System.in);
    private int windowValue;
    private ArrayList<Double> xValues;
    private ArrayList<Double> yValues;
    private ArrayList<Double> smoothedYValues;

    public Smoother()
    {
        System.out.print("\nEnter window value (or press Enter for default value of 5): ");
        String input = scanner.nextLine().trim();
        
        if (input.isEmpty())
        {
            this.windowValue = 5;
            System.out.println("Using default window value: 5");
        }
        else
        {
            try
            {
                this.windowValue = Integer.parseInt(input);
            }
            catch (NumberFormatException e)
            {
                System.out.println("Invalid input. Using default window value: 5");
                this.windowValue = 5;
            }
        }
        xValues = new ArrayList<>();
        yValues = new ArrayList<>();
        smoothedYValues = new ArrayList<>();
    }

    public void run()
    {
        loadData();
        smoothData();
        saveSmoothedData();
    }
    
    private void loadData()
    {
        try
        {
            BufferedReader bufferedReader = new BufferedReader(new FileReader("11. Plotter Salter Smoother - Original/output/salted_data.csv"));
            String line;
            
            // Skip header line
            bufferedReader.readLine();
            
            while ((line = bufferedReader.readLine()) != null)
            {
                String[] values = line.split(",");
                double x = Double.parseDouble(values[0]);
                double y = Double.parseDouble(values[1]);
                
                xValues.add(x);
                yValues.add(y);
            }
            
            bufferedReader.close();
        }
        catch (Exception exception)
        {
            System.out.println("Error loading data: " + exception);
        }
    }
    
    private void smoothData()
    {
        System.out.println("Smoothing data with window value of " + windowValue + "...");
        for (int i = 0; i < yValues.size(); i++)
        {
            // Calculate the window boundaries
            int leftBound = Math.max(0, i - windowValue);
            int rightBound = Math.min(yValues.size() - 1, i + windowValue);
            
            double sum = 0;
            int count = 0;
            
            // Sum all values within the window
            for (int j = leftBound; j <= rightBound; j++)
            {
                sum += yValues.get(j);
                count++;
            }
            
            // Calculate the average and add to smoothed values
            double average = sum / count;
            smoothedYValues.add(average);
        }
    }
    
    private void saveSmoothedData()
    {
        try
        {
            FileWriter fileWriter = new FileWriter("11. Plotter Salter Smoother - Original/output/smoothed_data.csv");
            fileWriter.append("x,y\n");
            
            for (int i = 0; i < xValues.size(); i++)
            {
                fileWriter.append(xValues.get(i) + "," + smoothedYValues.get(i) + "\n");
            }
            
            fileWriter.close();
            System.out.println("Smoothing complete, data data saved to output/smoothed_data.csv");
        }
        catch (Exception exception)
        {
            System.out.println("Error saving smoothed data: " + exception);
        }
    }
}
