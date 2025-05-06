package dev.tylermong.plottersaltersmootherapi;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class APISalter
{
    private static final Random random = new Random();
    private static final Scanner scanner = new Scanner(System.in);
    private int variability;
    private ArrayList<Double> yValues;
    private ArrayList<Double> saltedYValues;

    // Constructor to initialize variability with user input or default value
    // If the input is invalid or none is provided, it will use the default value of 10
    public APISalter()
    {
        System.out.print("\nEnter variability (or press Enter for default value of 10): ");
        String input = scanner.nextLine().trim();

        if (input.isEmpty())
        {
            this.variability = 10;
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

        yValues = new ArrayList<>();
        saltedYValues = new ArrayList<>();
    }

    // Main method to run the Salter
    public void run(ArrayList<Double> yValues)
    {
        this.yValues = new ArrayList<>(yValues);
        salt();
        System.out.println("Salted data generated, with variability of " + variability + ".");
    }

    // Getter for the salted Y values
    public ArrayList<Double> getSaltedYValues()
    {
        return saltedYValues;
    }

    // Salts (adds noise to) the y-values
    private void salt()
    {
        for (Double y : yValues)
        {
            // Add noise to the y-value based on the variability
            double saltedY = y + random.nextInt(-variability, variability);
            saltedYValues.add(saltedY);
        }
    }
}
