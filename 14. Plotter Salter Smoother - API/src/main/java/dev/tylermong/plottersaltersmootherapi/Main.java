package dev.tylermong.plottersaltersmootherapi;

import java.util.ArrayList;

public class Main
{
    public static void main(String[] args)
    {
        // Create the data builder and generate initial data
        APIDataBuilder dataBuilder = new APIDataBuilder();
        dataBuilder.run();
        ArrayList<Double> xValues = dataBuilder.getXValues();
        ArrayList<Double> yValues = dataBuilder.getYValues();
        
        // Create the salter and salt the data
        APISalter salter = new APISalter();
        salter.run(yValues);
        ArrayList<Double> saltedYValues = salter.getSaltedYValues();
        
        // Create the smoother and smooth the salted data
        APISmoother smoother = new APISmoother();
        smoother.run(saltedYValues);
        ArrayList<Double> smoothedYValues = smoother.getSmoothedYValues();

        // Print the data
        System.out.println("\nX Values: " + xValues);
        System.out.println("Y Values: " + yValues);
        System.out.println("Salted Y Values: " + saltedYValues);
        System.out.println("Smoothed Y Values: " + smoothedYValues);

        // Create the plot data object and plot the data
        APIPlotData plotData = new APIPlotData(xValues, yValues, saltedYValues, smoothedYValues);
        plotData.plotData();
    }
}