package dev.tylermong.customhashmap;

public class CustomHashMapTester
{
	public void runAllTests()
	{
		testStringIntegerMap();
		testIntegerDoubleMap();
		testStringStringMap();
	}

	public void testStringIntegerMap()
	{
		CustomHashMap<String, Integer> customHashMap = new CustomHashMap<>();
		customHashMap.put("One", 1);
		customHashMap.put("Two", 2);
		customHashMap.put("Three", 3);

		System.out.println("String-Integer Map Tests:");
		System.out.println("Get 'One': " + customHashMap.get("One"));
		System.out.println("Get 'Two': " + customHashMap.get("Two"));
		System.out.println("Get 'Three': " + customHashMap.get("Three"));

		System.out.println("Contains 'One': " + customHashMap.contains("One"));
		System.out.println("Contains 'Two': " + customHashMap.contains("Two"));
		System.out.println("Contains 'Three': " + customHashMap.contains("Three"));
		System.out.println("Contains 'Four': " + customHashMap.contains("Four"));

		System.out.println("Size: " + customHashMap.size());
		System.out.println();
	}

	public void testIntegerDoubleMap()
	{
		CustomHashMap<Integer, Double> customHashMap = new CustomHashMap<>();
		customHashMap.put(1, 10.0);
		customHashMap.put(2, 20.0);
		customHashMap.put(3, 30.0);

		System.out.println("Integer-Double Map Tests:");
		System.out.println("Get 1: " + customHashMap.get(1));
		System.out.println("Get 2: " + customHashMap.get(2));
		System.out.println("Get 3: " + customHashMap.get(3));

		System.out.println("Contains 1: " + customHashMap.contains(1));
		System.out.println("Contains 2: " + customHashMap.contains(2));
		System.out.println("Contains 3: " + customHashMap.contains(3));
		System.out.println("Contains 4: " + customHashMap.contains(4));

		System.out.println("Size: " + customHashMap.size());
		System.out.println();
	}

	public void testStringStringMap()
	{
		CustomHashMap<String, String> customHashMap = new CustomHashMap<>();
		customHashMap.put("One", "Ten");
		customHashMap.put("Two", "Twenty");
		customHashMap.put("Three", "Thirty");

		System.out.println("String-String Map Tests:");
		System.out.println("Get 'One': " + customHashMap.get("One"));
		System.out.println("Get 'Two': " + customHashMap.get("Two"));
		System.out.println("Get 'Three': " + customHashMap.get("Three"));

		System.out.println("Contains 'One': " + customHashMap.contains("One"));
		System.out.println("Contains 'Two': " + customHashMap.contains("Two"));
		System.out.println("Contains 'Three': " + customHashMap.contains("Three"));
		System.out.println("Contains 'Four': " + customHashMap.contains("Four"));

		System.out.println("Size: " + customHashMap.size());
		System.out.println();
	}
}