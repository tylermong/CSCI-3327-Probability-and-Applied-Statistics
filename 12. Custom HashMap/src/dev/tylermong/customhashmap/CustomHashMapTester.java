package dev.tylermong.customhashmap;

import java.util.HashMap;

public class CustomHashMapTester
{
	public void runAllTests()
	{
		testStringIntegerMap();
		testIntegerDoubleMap();
		testStringStringMap();

		performanceComparison();
	}

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

	public void performanceComparison()
	{
		performanceComparisonWithSize(1000);
		performanceComparisonWithSize(10000);
		performanceComparisonWithSize(100000);
	}

	public void performanceComparisonWithSize(int size)
	{
		long startTime;
		long endTime;
		System.out.println("[Performance comparison for size: " + size + "]");

		// Java initialization test
		startTime = System.nanoTime();
		HashMap<String, Integer> javaHashMap = new HashMap<>();
		endTime = System.nanoTime();
		double javaInitializationTime = (endTime - startTime) / 1000000.0;

		// Java insertion test
		startTime = System.nanoTime();
		for (int i = 0; i < size; i++)
		{
			javaHashMap.put(String.valueOf(i), i);
		}
		endTime = System.nanoTime();
		double javaInsertionTime = (endTime - startTime) / 1000000.0;

		// Java retrieval test
		startTime = System.nanoTime();
		for (int i = 0; i < size; i++)
		{
			javaHashMap.get(String.valueOf(i));
		}
		endTime = System.nanoTime();
		double javaRetrievalTime = (endTime - startTime) / 1000000.0;

		// Java deletion test
		startTime = System.nanoTime();
		for (int i = 0; i < size; i++)
		{
			javaHashMap.remove(String.valueOf(i));
		}
		endTime = System.nanoTime();
		double javaDeletionTime = (endTime - startTime) / 1000000.0;

		// Custom initialization test
		startTime = System.nanoTime();
		CustomHashMap<String, Integer> customHashMap = new CustomHashMap<>();
		endTime = System.nanoTime();
		double customInitializationTime = (endTime - startTime) / 1000000.0;

		// Custom insertion test
		startTime = System.nanoTime();
		for (int i = 0; i < size; i++)
		{
			customHashMap.put(String.valueOf(i), i);
		}
		endTime = System.nanoTime();
		double customInsertionTime = (endTime - startTime) / 1000000.0;

		// Custom retrieval test
		startTime = System.nanoTime();
		for (int i = 0; i < size; i++)
		{
			customHashMap.get(String.valueOf(i));
		}
		endTime = System.nanoTime();
		double customRetrievalTime = (endTime - startTime) / 1000000.0;

		// Custom deletion test
		startTime = System.nanoTime();
		for (int i = 0; i < size; i++)
		{
			customHashMap.remove(String.valueOf(i));
		}
		endTime = System.nanoTime();
		double customDeletionTime = (endTime - startTime) / 1000000.0;

		System.out.printf("\tCustom HashMap Initialization Time: %.2f ms%n", customInitializationTime);
		System.out.printf("\tJava HashMap Initialization Time: %.2f ms%n", javaInitializationTime);
		double initializationTimeDifference = (customInitializationTime - javaInitializationTime);
		System.out.printf("\tInitialization Time Difference: %s%.2f ms%n%n",
				initializationTimeDifference > 0 ? "+" : "", initializationTimeDifference);

		System.out.printf("\tCustom HashMap Insertion Time: %.2f ms%n", customInsertionTime);
		System.out.printf("\tJava HashMap Insertion Time: %.2f ms%n", javaInsertionTime);
		double insertionTimeDifference = (customInsertionTime - javaInsertionTime);
		System.out.printf("\tInsertion Time Difference: %s%.2f ms%n%n", insertionTimeDifference > 0 ? "+" : "",
				insertionTimeDifference);

		System.out.printf("\tCustom HashMap Retrieval Time: %.2f ms%n", customRetrievalTime);
		System.out.printf("\tJava HashMap Retrieval Time: %.2f ms%n", javaRetrievalTime);
		double retrievalTimeDifference = (customRetrievalTime - javaRetrievalTime);
		System.out.printf("\tRetrieval Time Difference: %s%.2f ms%n%n", retrievalTimeDifference > 0 ? "+" : "",
				retrievalTimeDifference);

		System.out.printf("\tCustom HashMap Deletion Time: %.2f ms%n", customDeletionTime);
		System.out.printf("\tJava HashMap Deletion Time: %.2f ms%n", javaDeletionTime);
		double deletionTimeDifference = (customDeletionTime - javaDeletionTime);
		System.out.printf("\tDeletion Time Difference: %s%.2f ms%n%n", deletionTimeDifference > 0 ? "+" : "",
				deletionTimeDifference);

		double customTotalTime = (customInitializationTime + customInsertionTime + customRetrievalTime
				+ customDeletionTime);
		double javaTotalTime = (javaInitializationTime + javaInsertionTime + javaRetrievalTime + javaDeletionTime);
		System.out.printf("\tCustom HashMap Total Time: %.2f ms%n", customTotalTime);
		System.out.printf("\tJava HashMap Total Time: %.2f ms%n", javaTotalTime);
		double totalTimeDifference = (customTotalTime - javaTotalTime);
		System.out.printf("\tTotal Time Difference: %s%.2f ms%n", totalTimeDifference > 0 ? "+" : "",
				totalTimeDifference);

		System.out.println();
	}
}