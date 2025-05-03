import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class DataBuilder
{
    private final static Scanner in = new Scanner(System.in);

    public void run()
    {
        int selectedFunction = selectFunction();
        ArrayList<Double> xValues = getXValues();
        ArrayList<Double> yValues = getYValues(selectedFunction, xValues);

        try
        {
            File outputDirectory = new File("11. Plotter Salter Smoother - Original/output/");
            if (!outputDirectory.exists())
            {
                outputDirectory.mkdirs();
                System.out.println("Created output directory: " + outputDirectory.getPath());
            }

            FileWriter writer = new FileWriter("11. Plotter Salter Smoother - Original/output/data.csv");
            
            writer.append("x,y\n");
            for (int i = 0; i < xValues.size(); i++)
            {
                writer.append(xValues.get(i) + "," + yValues.get(i) + "\n");
            }
            writer.close();
            System.out.println("Initial data written to output/data.csv");
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    private int selectFunction()
    {
        System.out.println("Select a function to plot:");
        System.out.println("1. f(x) = x + 1");
        System.out.println("2. f(x) = x^2");
        System.out.println("3. f(x) = log(x)");
        System.out.print("Selection: ");
        int selection = in.nextInt() - 1;

        if (selection < 0 || selection > 2)
        {
            System.out.println("Invalid selection. Please try again.");
            return selectFunction();
        }

        return selection;
    }

    private ArrayList<Double> getXValues()
    {
        ArrayList<Double> xValues = new ArrayList<>();

        System.out.print("\nEnter the values of x to plot (comma-separated, e.g. 1, 2, 3): ");
        
        // Consume the newline character.
        in.nextLine();

        // Trim trailing whitespace.
        String input = in.nextLine().trim();
    
        // Put input in array for easy parsing.
        String[] rawSelections = input.split(",");

        // Parse the array, filtering out extra spaces.
        for (int i = 0; i < rawSelections.length; i++)
        {
            xValues.add(Double.parseDouble(rawSelections[i].trim()));
        }

        // Return filtered input.
        return xValues;
    }

    private ArrayList<Double> getYValues(int selectedFunction, ArrayList<Double> xValues)
    {
        switch (selectedFunction)
        {
            case 0:
                return function1(xValues);
            case 1:
                return function2(xValues);
            case 2:
                return function3(xValues);
            default:
                throw new IllegalArgumentException("Invalid function selection");
        }
    }

    private ArrayList<Double> function1(ArrayList<Double> xValues)
    {
        ArrayList<Double> yValues = new ArrayList<>();
        for (double x : xValues)
        {
            yValues.add(x + 1);
        }
        
        return yValues;
    }

    private ArrayList<Double> function2(ArrayList<Double> xValues)
    {
        ArrayList<Double> yValues = new ArrayList<>();
        for (double x : xValues)
        {
            yValues.add((double) Math.pow(x, 2));
        }
        
        return yValues;
    }

    private ArrayList<Double> function3(ArrayList<Double> xValues)
    {
        ArrayList<Double> yValues = new ArrayList<>();
        for (double x : xValues)
        {
            yValues.add((double) Math.log(x));
        }
        
        return yValues;
    }
}