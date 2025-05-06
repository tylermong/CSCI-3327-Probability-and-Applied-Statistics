package dev.tylermong.plottersaltersmootherapi;

import java.util.ArrayList;
import java.util.Scanner;

public class APISmoother
{
    private static final Scanner scanner = new Scanner(System.in);
    private int windowValue;
    private ArrayList<Double> yValues;
    private ArrayList<Double> smoothedYValues;

    // Constructor to initialize window value with user input or default value
    // If the input is invalid or none is provided, it will use the default value of 5
    public APISmoother()
    {
        System.out.print("\nEnter window value (or press Enter for default value of 5): ");
        String input = scanner.nextLine().trim();

        if (input.isEmpty())
        {
            this.windowValue = 5;
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

        yValues = new ArrayList<>();
        smoothedYValues = new ArrayList<>();
    }

    // Main method to run the Smoother
    public void run(ArrayList<Double> yValues)
    {
        this.yValues = new ArrayList<>(yValues);
        smoothData();
        System.out.println("Smoothed data generated, with window value of " + windowValue + ".");
    }

    // Getter for the smoothed Y values
    public ArrayList<Double> getSmoothedYValues()
    {
        return smoothedYValues;
    }

    // Smooths the y-values using a moving average with the specified window value
    private void smoothData()
    {
        for (int i = 0; i < yValues.size(); i++)
        {
            // Calculate the window boundaries
            int leftBound = Math.max(0, i - windowValue); // Ensure left bound is not negative
            int rightBound = Math.min(yValues.size() - 1, i + windowValue); // Ensure right bound is not out of bounds

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
}
