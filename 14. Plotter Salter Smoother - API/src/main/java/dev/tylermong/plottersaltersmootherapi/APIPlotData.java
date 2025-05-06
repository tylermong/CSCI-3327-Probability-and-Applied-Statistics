package dev.tylermong.plottersaltersmootherapi;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class APIPlotData
{
	private ArrayList<Double> xValues;
	private ArrayList<Double> yValues;
	private ArrayList<Double> saltedYValues;
	private ArrayList<Double> smoothedYValues;

	public APIPlotData(ArrayList<Double> xValues, ArrayList<Double> yValues, ArrayList<Double> saltedYValues,
			ArrayList<Double> smoothedYValues)
	{
		this.xValues = xValues;
		this.yValues = yValues;
		this.saltedYValues = saltedYValues;
		this.smoothedYValues = smoothedYValues;
	}

	public void plotData()
	{
		// Create dataset
		XYSeriesCollection dataset = createDataset();

		// Create chart
		JFreeChart chart = ChartFactory.createXYLineChart(
				"Plotter Salter Smoother", // Chart title
				"X Values", // X-Axis Label
				"Y Values", // Y-Axis Label
				dataset, // Dataset
				PlotOrientation.VERTICAL, // Plot Orientation
				true, // Show Legend
				true, // Use tooltips
				false // Configure chart to generate URLs
		);

		// Display the chart in a frame
		displayChart(chart);
	}

	private XYSeriesCollection createDataset()
	{
		// Create a dataset with multiple series
		XYSeriesCollection dataset = new XYSeriesCollection();

		// Create series for original data
		XYSeries originalSeries = new XYSeries("Original Data");

		// Create series for salted data
		XYSeries saltedSeries = new XYSeries("Salted Data");

		// Create series for smoothed data
		XYSeries smoothedSeries = new XYSeries("Smoothed Data");

		// Add data points to each series
		for (int i = 0; i < xValues.size(); i++)
		{
			double x = xValues.get(i);

			originalSeries.add(x, yValues.get(i));
			saltedSeries.add(x, saltedYValues.get(i));
			smoothedSeries.add(x, smoothedYValues.get(i));
		}

		// Add the series to the dataset
		dataset.addSeries(originalSeries);
		dataset.addSeries(saltedSeries);
		dataset.addSeries(smoothedSeries);

		return dataset;
	}

	private void displayChart(JFreeChart chart)
	{
		// Create a panel for the chart
		ChartPanel chartPanel = new ChartPanel(chart);
		chartPanel.setPreferredSize(new Dimension(800, 600));

		// Create and set up the frame
		JFrame frame = new JFrame("Plotter Salter Smoother Chart");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().add(chartPanel);
		frame.pack();
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);

		System.out.println("Chart displayed successfully.");
	}
}