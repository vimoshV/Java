import java.util.*;

public class Main {
    public static void main(String[] args) {

        // Load collections with 100,000 random Integer objects
        Random random = new Random();
        List<Integer> elements = new ArrayList<>();
        for (int i = 0; i < 100000; i++) {
            elements.add(random.nextInt(100000));
        }

        // Define collection types
        String[] collections = {"HashSet", "TreeSet", "LinkedHashSet", "ArrayList", "LinkedList", "ArrayDeque", "PriorityQueue", "HashMap", "TreeMap", "LinkedHashMap"};

        // Perform tests for each collection type
        for (String collectionType : collections) {
            switch (collectionType) {
                case "HashSet":
                    testCollection(new HashSet<>(elements), collectionType, elements);
                    break;
                case "TreeSet":
                    testCollection(new TreeSet<>(elements), collectionType, elements);
                    break;
                case "LinkedHashSet":
                    testCollection(new LinkedHashSet<>(elements), collectionType, elements);
                    break;
                case "ArrayList":
                    testCollection(new ArrayList<>(elements), collectionType, elements);
                    break;
                case "LinkedList":
                    testCollection(new LinkedList<>(elements), collectionType, elements);
                    break;
                case "ArrayDeque":
                    testCollection(new ArrayDeque<>(elements), collectionType, elements);
                    break;
                case "PriorityQueue":
                    testCollection(new PriorityQueue<>(elements), collectionType, elements);
                    break;
                case "HashMap":
                    testMap(new HashMap<>(), elements, collectionType);
                    break;
                case "TreeMap":
                    testMap(new TreeMap<>(), elements, collectionType);
                    break;
                case "LinkedHashMap":
                    testMap(new LinkedHashMap<>(), elements, collectionType);
                    break;
            }
        }
    }

    private static void testCollection(Collection<Integer> collection, String collectionName, List<Integer> elements) {
        long addTime = testAddMethod(collection, elements);
        long containsTime = testContainsMethod(collection, elements);
        long removeTime = testRemoveMethod(collection, elements);
        long clearTime = testClearMethod(collection);

        // Display results
        System.out.printf("Collection: %s\n", collectionName);
        System.out.printf("Add: %d ns\n", addTime);
        System.out.printf("Contains: %d ns\n", containsTime);
        System.out.printf("Remove: %d ns\n", removeTime);
        System.out.printf("Clear: %d ns\n", clearTime);
        System.out.println("_____________________________________________________________________");
    }

    private static void testMap(Map<Integer, Integer> map, List<Integer> elements, String mapName) {
        for (int i = 0; i < 100000; i++) {
            map.put(i, elements.get(i));
        }

        long addTime = testAddMethod(new ArrayList<>(map.values()), elements);
        long containsTime = testContainsMethod(new ArrayList<>(map.values()), elements);
        long removeTime = testRemoveMethod(new ArrayList<>(map.values()), elements);
        long clearTime = testMapClearMethod(map);

        // Display results
        System.out.printf("Map: %s\n", mapName);
        System.out.printf("Add: %d ns\n", addTime);
        System.out.printf("Contains: %d ns\n", containsTime);
        System.out.printf("Remove: %d ns\n", removeTime);
        System.out.printf("Clear: %d ns\n", clearTime);
        System.out.println("_____________________________________________________________________");
    }

    private static long testAddMethod(Collection<Integer> collection, List<Integer> elements) {
        long totalTime = 0;
        for (int t = 0; t < 100; t++) {
            long startTime = System.nanoTime();
            collection.addAll(elements);
            long endTime = System.nanoTime();
            totalTime += (endTime - startTime);
            collection.clear();
        }
        return totalTime / 100;
    }

    private static long testContainsMethod(Collection<Integer> collection, List<Integer> elements) {
        long totalTime = 0;
        for (int t = 0; t < 100; t++) {
            collection.addAll(elements);
            long startTime = System.nanoTime();
            for (int element : elements) {
                collection.contains(element);
            }
            long endTime = System.nanoTime();
            totalTime += (endTime - startTime);
            collection.clear();
        }
        return totalTime / 100;
    }

    private static long testRemoveMethod(Collection<Integer> collection, List<Integer> elements) {
        long totalTime = 0;
        for (int t = 0; t < 100; t++) {
            collection.addAll(elements);
            long startTime = System.nanoTime();
            for (int element : elements) {
                collection.remove(element);
            }
            long endTime = System.nanoTime();
            totalTime += (endTime - startTime);
            collection.clear();
        }
        return totalTime / 100;
    }

    private static long testClearMethod(Collection<Integer> collection) {
        long totalTime = 0;
        for (int t = 0; t < 100; t++) {
            collection.clear();
            long startTime = System.nanoTime();
            collection.clear();
            long endTime = System.nanoTime();
            totalTime += (endTime - startTime);
        }
        return totalTime / 100;
    }

    private static long testMapClearMethod(Map<Integer, Integer> map) {
        long totalTime = 0;
        for (int t = 0; t < 100; t++) {
            map.clear();
            long startTime = System.nanoTime();
            map.clear();
            long endTime = System.nanoTime();
            totalTime += (endTime - startTime);
        }
        return totalTime / 100;
    }
}
