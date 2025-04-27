public class Main
{
    // TODO: Abstract logic into a separate class
    public static void main(String[] args)
    {
        CustomHashMap<String, Integer> customHashMap1 = new CustomHashMap<>();
        customHashMap1.put("One", 1);
        customHashMap1.put("Two", 2);
        customHashMap1.put("Three", 3);

        System.out.println(customHashMap1.get("One"));
        System.out.println(customHashMap1.get("Two"));
        System.out.println(customHashMap1.get("Three"));

        System.out.println(customHashMap1.contains("One"));
        System.out.println(customHashMap1.contains("Two"));
        System.out.println(customHashMap1.contains("Three"));
        System.out.println(customHashMap1.contains("Four"));

        System.out.println(customHashMap1.size());


        
        CustomHashMap<Integer, Double> customHashMap2 = new CustomHashMap<>();
        customHashMap2.put(1, 10.0);
        customHashMap2.put(2, 20.0);
        customHashMap2.put(3, 30.0);

        System.out.println(customHashMap2.get(1));
        System.out.println(customHashMap2.get(2));
        System.out.println(customHashMap2.get(3));

        System.out.println(customHashMap2.contains(1));
        System.out.println(customHashMap2.contains(2));
        System.out.println(customHashMap2.contains(3));
        System.out.println(customHashMap2.contains(4));

        System.out.println(customHashMap2.size());
    }   
}
