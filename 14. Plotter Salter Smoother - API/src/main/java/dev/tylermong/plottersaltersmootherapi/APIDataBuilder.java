package dev.tylermong.plottersaltersmootherapi;

import java.util.ArrayList;
import java.util.Scanner;

public class APIDataBuilder
{
    private final static Scanner in = new Scanner(System.in);
    private ArrayList<Double> xValues;
    private ArrayList<Double> yValues;

    // Constructor to initialize the ArrayLists
    public APIDataBuilder()
    {
        xValues = new ArrayList<>();
        yValues = new ArrayList<>();
    }

    // Main method to run the DataBuilder. Prompts the user to select a function and input x values.
    public void run()
    {
        int selectedFunction = selectFunction();
        xValues = getXValues();
        yValues = getYValues(selectedFunction, xValues); // Based on user selected function and x values
        System.out.println("Initial data generated.");
    }

    // Getters for the x and y values
    public ArrayList<Double> getXValues()
    {
        if (xValues.isEmpty())
        {
            // Prompts the user to input x values, which will be used to generate the y values.
            ArrayList<Double> newXValues = new ArrayList<>();

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
                newXValues.add(Double.parseDouble(rawSelections[i].trim()));
            }

            return newXValues;
        }
        return xValues;
    }

    public ArrayList<Double> getYValues()
    {
        return yValues;
    }

    // Prompts the user to select a function, that will be used to generate the y values.
    private int selectFunction()
    {
        System.out.println("Select a function to plot:");
        System.out.println("1. f(x) = x + 1");
        System.out.println("2. f(x) = x^2");
        System.out.println("3. f(x) = log(x)");
        System.out.print("Selection: ");
        int selection = in.nextInt() - 1;

        // Validate the selection
        if (selection < 0 || selection > 2)
        {
            System.out.println("Invalid selection. Please try again.");
            return selectFunction();
        }

        return selection;
    }

    // Based on the user selected function and x values, this method generates the y values.
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

    // f(x) = x + 1
    private ArrayList<Double> function1(ArrayList<Double> xValues)
    {
        ArrayList<Double> yValues = new ArrayList<>();
        for (double x : xValues)
        {
            yValues.add(x + 1);
        }

        return yValues;
    }

    // f(x) = x^2
    private ArrayList<Double> function2(ArrayList<Double> xValues)
    {
        ArrayList<Double> yValues = new ArrayList<>();
        for (double x : xValues)
        {
            yValues.add((double) Math.pow(x, 2));
        }

        return yValues;
    }

    // f(x) = log(x)
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