package dev.tylermong.customhashmap;

/**
 * This class serves as the entry point for the application. It creates an instance of the CustomHashMapTester class and
 * runs all tests.
 */
public class Main
{
    /**
     * The main method that runs the application. It creates an instance of CustomHashMapTester and runs all tests.
     * 
     * @param args command line arguments
     */
    public static void main(String[] args)
    {
        CustomHashMapTester customHashMapTester = new CustomHashMapTester();
        customHashMapTester.runAllTests();
    }
}
