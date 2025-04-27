import java.io.*;
import java.util.ArrayList;

public class Smoother
{
    private int windowValue;
    private ArrayList<Double> xValues;
    private ArrayList<Double> yValues;
    private ArrayList<Double> smoothedYValues;

    public Smoother(int windowValue)
    {
        this.windowValue = windowValue;
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
        System.out.println("\nSmoothing data with window value of " + windowValue + "...");
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
