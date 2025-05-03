# Custom HashMap: Submission

## Overview
This project creates a custom HashMap in Java. It includes a `CustomHashMap` class that implements the basic functionality of a HashMap, including `put`, `get`, and `remove` methods. This class uses an array of linked lists to store the key-value pairs, and handles collisions using chaining. It also includes an `Entry` class that represents an individual key-value pair within the HashMap. Next, it includes a `CustomHashMapTester` class that contains tests to ensure the functionality of the `CustomHashMap` class along with performance tests compared to the built-in `HashMap` class. Finally, it includes a `Main` class that serves as the entry point to the program.

## Javadoc
The Javadoc for the project is available at the following link: [Custom HashMap Javadoc](https://custom-hashmap-docs.tylermong.dev/)

## Class Descriptions
`Main`: This class is the entry point of the program. It initializes the `CustomHashMapTester` class and calls its `runAllTests` method to execute all the tests.

`CustomHashMap`: This class implements the basic functionality of a HashMap. It uses an ArrayList of LinkedLists of `Entry` objects to store the key-value pairs. The `put` method adds a new key-value pair to the HashMap. The `get` method retrieves the value associated with a given key. The `remove` method removes a key-value pair from the HashMap. The `contains` method checks if a given key is present in the HashMap. The `size` method returns the number of key-value pairs in the HashMap. Internally, it uses a hash function, in the `hash` method, to map keys to indices in the array. It also dynamically resizes the array when the load factor exceeds a certain threshold, using the `resize` method.

`Entry`: This class represents an individual key-value pair within the HashMap. It contains a key and value, and uses generic types to allow for any type of key and value. The `getKey` and `getValue` methods return the key and value, respectively, and the `setValue` method allows for updating the value associated with a key.

`CustomHashMapTester`: This class contains tests to ensure the functionality of the `CustomHashMap` class. It includes tests for initializing the HashMap, putting, getting, and removing key-value pairs, checking if a key is present, and implicitly testing the hashing and resizing functionality. It also includes performance tests that compare the performance of the `CustomHashMap` class with the built-in `HashMap` class. It times the aforementioned operations for both classes for different sizes of data (1k, 10k, 100k, and 1M) over 10 iterations and prints the results to the console. The `performanceComparisonWithSize` method is used to run these tests and can be publicly called with `size` and `runs` parameters, if the user wants to run the performance tests with different parameters.

## How to Run
1. Navigate to the `/src` directory.
2. Run the `Main.java` file.
3. The program will run all the tests and print the results to the console.

### Images
#### Custom HashMap Tests:
![Custom HashMap Tests](./images/CustomHashMapTests.png)

#### HashMap Comparison 1k:
![HashMap Comparison 1k](./images/HashMapComparison1k.png)

#### HashMap Comparison 10k:
![HashMap Comparison 10k](./images/HashMapComparison10k.png)

#### HashMap Comparison 100k:
![HashMap Comparison 100k](./images/HashMapComparison100k.png)

#### HashMap Comparison 1M:
![HashMap Comparison 1M](./images/HashMapComparison1M.png)