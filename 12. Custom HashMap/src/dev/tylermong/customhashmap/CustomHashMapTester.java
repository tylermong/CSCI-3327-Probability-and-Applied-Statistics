package dev.tylermong.customhashmap;

import java.util.HashMap;
import java.util.Random;
import java.util.UUID;

/**
 * This class tests the functionality of the CustomHashMap class and compares its performance with the Java HashMap. It
 * includes tests for different key-value pairs and performance comparisons for various sizes of data
 * 
 * @author  Tyler Mong
 * @version 1.0
 */
public class CustomHashMapTester
{
	/**
	 * Runs all the test in this class along with the performance comparison tests.
	 */
	public void runAllTests()
	{
		testStringIntegerMap();
		testIntegerDoubleMap();
		testStringStringMap();

		performanceComparison();
	}

	/**
	 * Tests the functionality of the CustomHashMap with String keys and Integer values.
	 */
	public void testStringIntegerMap()
	{
		System.out.println("[Custom String:Integer Map Tests]");

		CustomHashMap<String, Integer> customHashMap = new CustomHashMap<>();
		customHashMap.put("One", 1);
		customHashMap.put("Two", 2);
		customHashMap.put("Three", 3);
		System.out.println("\tPut the following key-value pairs into the map:");
		System.out.println("\tOne: 1, Two: 2, Three: 3");

		System.out.println("\tget(\"One\"): " + customHashMap.get("One"));
		System.out.println("\tget(\"Two\"): " + customHashMap.get("Two"));
		System.out.println("\tget (\"Three\"): " + customHashMap.get("Three"));

		System.out.println("\tcontains(\"One\"): " + customHashMap.contains("One"));
		System.out.println("\tcontains(\"Two\"): " + customHashMap.contains("Two"));
		System.out.println("\tcontains(\"Three\"): " + customHashMap.contains("Three"));
		System.out.println("\tcontains(\"Four\"): " + customHashMap.contains("Four"));

		System.out.println("\tsize(): " + customHashMap.size());
		System.out.println();
	}

	/**
	 * Tests the functionality of the CustomHashMap with Integer keys and Double values.
	 */
	public void testIntegerDoubleMap()
	{
		System.out.println("[Custom Integer:Double Map Tests]");
		CustomHashMap<Integer, Double> customHashMap = new CustomHashMap<>();
		customHashMap.put(1, 10.0);
		customHashMap.put(2, 20.0);
		customHashMap.put(3, 30.0);
		System.out.println("\tPut the following key-value pairs into the map:");
		System.out.println("\t1: 10.0, 2: 20.0, 3: 30.0");

		System.out.println("\tget(1): " + customHashMap.get(1));
		System.out.println("\tget(2): " + customHashMap.get(2));
		System.out.println("\tget(3): " + customHashMap.get(3));

		System.out.println("\tcontains(1): " + customHashMap.contains(1));
		System.out.println("\tcontains(2): " + customHashMap.contains(2));
		System.out.println("\tcontains(3): " + customHashMap.contains(3));
		System.out.println("\tcontains(4): " + customHashMap.contains(4));

		System.out.println("\tsize(): " + customHashMap.size());
		System.out.println();
	}

	/**
	 * Tests the functionality of the CustomHashMap with String keys and String values.
	 */
	public void testStringStringMap()
	{
		System.out.println("[Custom String:String Map Tests]:");
		CustomHashMap<String, String> customHashMap = new CustomHashMap<>();
		customHashMap.put("One", "Ten");
		customHashMap.put("Two", "Twenty");
		customHashMap.put("Three", "Thirty");
		System.out.println("\tPut the following key-value pairs into the map:");
		System.out.println("\tOne: Ten, Two: Twenty, Three: Thirty");

		System.out.println("\tget(\"One\"): " + customHashMap.get("One"));
		System.out.println("\tget(\"Two\"): " + customHashMap.get("Two"));
		System.out.println("\tget(\"Three\"): " + customHashMap.get("Three"));

		System.out.println("\tcontains(\"One\"): " + customHashMap.contains("One"));
		System.out.println("\tcontains(\"Two\"): " + customHashMap.contains("Two"));
		System.out.println("\tcontains(\"Three\"): " + customHashMap.contains("Three"));
		System.out.println("\tcontains(\"Four\"): " + customHashMap.contains("Four"));

		System.out.println("\tsize(): " + customHashMap.size());
		System.out.println();
	}

	/**
	 * Calls the performance comparison method for different sizes of data.
	 */
	public void performanceComparison()
	{
		performanceComparisonWithSize(1000, 10);
		performanceComparisonWithSize(10000, 10);
		performanceComparisonWithSize(100000, 10);
		performanceComparisonWithSize(1000000, 10);
	}

	/**
	 * Compares the performance of the CustomHashMap to the Java HashMap for a given size of data. It measures the time
	 * taken for initialization, insertion, retrieval, and deletion operations for both maps and prints the results.
	 * Uses random unrelated key-value pairs to simulate real-world usage. Runs the tests multiple times and reports the
	 * average performance.
	 * 
	 * @param size the size of data to be used for the performance comparison
	 * @param runs the number of runs to be performed for each operation to calculate the average
	 */
	public void performanceComparisonWithSize(int size, int runs)
	{
		System.out.println("[Performance comparison for size: " + size + " with random key-value pairs, averaged over "
				+ runs + " runs]");

		// Store the time taken for each operation in each run
		double[] javaInitializationTimes = new double[runs];
		double[] javaInsertionTimes = new double[runs];
		double[] javaRetrievalTimes = new double[runs];
		double[] javaDeletionTimes = new double[runs];

		double[] customInitializationTimes = new double[runs];
		double[] customInsertionTimes = new double[runs];
		double[] customRetrievalTimes = new double[runs];
		double[] customDeletionTimes = new double[runs];

		// Run the tests for the given number of runs
		for (int run = 0; run < runs; run++)
		{
			// Generate random keys and values for testing. Uses a different (but deterministic) seed for each run.
			String[] randomKeys = new String[size];
			Integer[] randomValues = new Integer[size];
			Random random = new Random(run);

			// Random key using UUID for wide distribution.
			// Random value using Random, since value distribution spread is not important for this test.
			for (int i = 0; i < size; i++)
			{
				randomKeys[i] = UUID.randomUUID().toString();
				randomValues[i] = random.nextInt(1000000);
			}

			// [Java tests]
			long startTime, endTime;

			// Java initialization test
			startTime = System.nanoTime();
			HashMap<String, Integer> javaHashMap = new HashMap<>();
			endTime = System.nanoTime();
			javaInitializationTimes[run] = (endTime - startTime) / 1000000.0;

			// Java insertion test
			startTime = System.nanoTime();
			for (int i = 0; i < size; i++)
			{
				javaHashMap.put(randomKeys[i], randomValues[i]);
			}
			endTime = System.nanoTime();
			javaInsertionTimes[run] = (endTime - startTime) / 1000000.0;

			// Java retrieval test
			startTime = System.nanoTime();
			for (int i = 0; i < size; i++)
			{
				javaHashMap.get(randomKeys[i]);
			}
			endTime = System.nanoTime();
			javaRetrievalTimes[run] = (endTime - startTime) / 1000000.0;

			// Java deletion test
			startTime = System.nanoTime();
			for (int i = 0; i < size; i++)
			{
				javaHashMap.remove(randomKeys[i]);
			}
			endTime = System.nanoTime();
			javaDeletionTimes[run] = (endTime - startTime) / 1000000.0;

			// [Custom tests]

			// Custom initialization test
			startTime = System.nanoTime();
			CustomHashMap<String, Integer> customHashMap = new CustomHashMap<>();
			endTime = System.nanoTime();
			customInitializationTimes[run] = (endTime - startTime) / 1000000.0;

			// Custom insertion test
			startTime = System.nanoTime();
			for (int i = 0; i < size; i++)
			{
				customHashMap.put(randomKeys[i], randomValues[i]);
			}
			endTime = System.nanoTime();
			customInsertionTimes[run] = (endTime - startTime) / 1000000.0;

			// Custom retrieval test
			startTime = System.nanoTime();
			for (int i = 0; i < size; i++)
			{
				customHashMap.get(randomKeys[i]);
			}
			endTime = System.nanoTime();
			customRetrievalTimes[run] = (endTime - startTime) / 1000000.0;

			// Custom deletion test
			startTime = System.nanoTime();
			for (int i = 0; i < size; i++)
			{
				customHashMap.remove(randomKeys[i]);
			}
			endTime = System.nanoTime();
			customDeletionTimes[run] = (endTime - startTime) / 1000000.0;
		}

		// Calculate average times
		double avgJavaInitializationTime = calculateAverage(javaInitializationTimes);
		double avgJavaInsertionTime = calculateAverage(javaInsertionTimes);
		double avgJavaRetrievalTime = calculateAverage(javaRetrievalTimes);
		double avgJavaDeletionTime = calculateAverage(javaDeletionTimes);

		double avgCustomInitializationTime = calculateAverage(customInitializationTimes);
		double avgCustomInsertionTime = calculateAverage(customInsertionTimes);
		double avgCustomRetrievalTime = calculateAverage(customRetrievalTimes);
		double avgCustomDeletionTime = calculateAverage(customDeletionTimes);

		// Print the initialization results
		System.out.printf("\tCustom HashMap Initialization Time: %.2f ms%n", avgCustomInitializationTime);
		System.out.printf("\tJava HashMap Initialization Time: %.2f ms%n", avgJavaInitializationTime);
		double initializationTimeDifference = (avgCustomInitializationTime - avgJavaInitializationTime);
		double initializationTimeDifferenceMultiplier = (avgCustomInitializationTime / avgJavaInitializationTime);
		System.out.printf("\tInitialization Time Difference: %s%.2f ms (%.2fx) %n%n",
				initializationTimeDifference > 0 ? "+" : "", initializationTimeDifference,
				initializationTimeDifferenceMultiplier);

		// Print the insertion results
		System.out.printf("\tCustom HashMap Insertion Time: %.2f ms%n", avgCustomInsertionTime);
		System.out.printf("\tJava HashMap Insertion Time: %.2f ms%n", avgJavaInsertionTime);
		double insertionTimeDifference = (avgCustomInsertionTime - avgJavaInsertionTime);
		double insertionTimeDifferenceMultiplier = (avgCustomInsertionTime / avgJavaInsertionTime);
		System.out.printf("\tInsertion Time Difference: %s%.2f ms (%.2fx) %n%n", insertionTimeDifference > 0 ? "+" : "",
				insertionTimeDifference, insertionTimeDifferenceMultiplier);

		// Print the retrieval results
		System.out.printf("\tCustom HashMap Retrieval Time: %.2f ms%n", avgCustomRetrievalTime);
		System.out.printf("\tJava HashMap Retrieval Time: %.2f ms%n", avgJavaRetrievalTime);
		double retrievalTimeDifference = (avgCustomRetrievalTime - avgJavaRetrievalTime);
		double retrievalTimeDifferenceMultiplier = (avgCustomRetrievalTime / avgJavaRetrievalTime);
		System.out.printf("\tRetrieval Time Difference: %s%.2f ms (%.2fx) %n%n", retrievalTimeDifference > 0 ? "+" : "",
				retrievalTimeDifference, retrievalTimeDifferenceMultiplier);

		// Print the deletion results
		System.out.printf("\tCustom HashMap Deletion Time: %.2f ms%n", avgCustomDeletionTime);
		System.out.printf("\tJava HashMap Deletion Time: %.2f ms%n", avgJavaDeletionTime);
		double deletionTimeDifference = (avgCustomDeletionTime - avgJavaDeletionTime);
		double deletionTimeDifferenceMultiplier = (avgCustomDeletionTime / avgJavaDeletionTime);
		System.out.printf("\tDeletion Time Difference: %s%.2f ms (%.2fx) %n%n", deletionTimeDifference > 0 ? "+" : "",
				deletionTimeDifference, deletionTimeDifferenceMultiplier);

		// Print the total time results
		double customTotalTime = (avgCustomInitializationTime + avgCustomInsertionTime + avgCustomRetrievalTime
				+ avgCustomDeletionTime);
		double javaTotalTime = (avgJavaInitializationTime + avgJavaInsertionTime + avgJavaRetrievalTime
				+ avgJavaDeletionTime);
		System.out.printf("\tCustom HashMap Total Time: %.2f ms%n", customTotalTime);
		System.out.printf("\tJava HashMap Total Time: %.2f ms%n", javaTotalTime);
		double totalTimeDifference = (customTotalTime - javaTotalTime);
		double totalTimeDifferenceMultiplier = (customTotalTime / javaTotalTime);
		System.out.printf("\tTotal Time Difference: %s%.2f ms (%.2fx) %n%n", totalTimeDifference > 0 ? "+" : "",
				totalTimeDifference, totalTimeDifferenceMultiplier);
	}

	/**
	 * Calculates the average of an array of doubles.
	 * 
	 * @param  values the array of values to calculate the average for
	 * @return        the average of the values
	 */
	private double calculateAverage(double[] values)
	{
		double sum = 0;
		for (double value : values)
		{
			sum += value;
		}
		return sum / values.length;
	}
}