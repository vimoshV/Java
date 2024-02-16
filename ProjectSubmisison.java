import java.util.*;

public class Main {
    public static void main(String[] args) {

        // Load collections with 100,000 random Integer objects
        Random random = new Random();

        // elements for collections that allows duplicates
        List<Integer> elements = new ArrayList<>();
        for (int i = 0; i < 100000; i++) {
            elements.add(random.nextInt(100000));
        }

        // creating map
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < 100000; i++) {
            map.put(i, elements.get(i));
        }

        // creating list that not allows duplicates
        List<Integer> setElements = new ArrayList<>();
        for (int i = 0; i < 100000; i++) {
            setElements.add(i);
        }
        Collections.shuffle(setElements, random);

        // Define collection types
        String[] collections = {"HashSet", "TreeSet", "LinkedHashSet", "ArrayList", "LinkedList", "ArrayDeque", "PriorityQueue", "HashMap", "TreeMap", "LinkedHashMap"};
        System.out.println();
        System.out.println("                    Performance Comparison   ");
        System.out.println();
        System.out.println("---------------------------------------------------------------------");
        System.out.println("| Collection       |    Add     |  Contains  |   Remove   |  Clear   |");
        System.out.println("---------------------------------------------------------------------");
        // Perform tests for each collection type
        for (String collectionType : collections) {
            switch (collectionType) {
                case "HashSet":
                    testCollection(new HashSet<>(setElements), collectionType, elements);
                    break;
                case "TreeSet":
                    testCollection(new TreeSet<>(setElements), collectionType, elements);
                    break;
                case "LinkedHashSet":
                    testCollection(new LinkedHashSet<>(setElements), collectionType, elements);
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
                    testMap(new HashMap<>(map), elements, collectionType);
                    break;
                case "TreeMap":
                    testMap(new TreeMap<>(map), elements, collectionType);
                    break;
                case "LinkedHashMap":
                    testMap(new LinkedHashMap<>(map), elements, collectionType);
                    break;
            }
        }
    }



    // defining a function to display the output
    private static void testCollection(Collection<Integer> collection, String collectionName, List<Integer> elements) {
        long addTime = testAddMethod(collection, elements, collectionName);
        long containsTime = testContainsMethod(collection, elements);
        long removeTime = testRemoveMethod(collection, elements, collectionName);
        long clearTime = testClearMethod(collection);

        System.out.printf("| %-16s | %10d | %10d | %10d | %8d |\n", collectionName, addTime, containsTime, removeTime, clearTime);
        System.out.println("---------------------------------------------------------------------");
    }

    private static void testMap(Map<Integer, Integer> map, List<Integer> elements, String mapName) {
        long addTime = testMapAddMethod(map, elements, mapName);
        long containsTime = testMapContainsMethod(map, elements);
        long removeTime = testMapRemoveMethod(map, elements);
        long clearTime = testMapClearMethod(map);

        System.out.printf("| %-16s | %10d | %10d | %10d | %8d |\n", mapName, addTime, containsTime, removeTime, clearTime);
        System.out.println("---------------------------------------------------------------------");
    }

    private static long testAddMethod(Collection<Integer> collection, List<Integer> elements, String collection_name) {
        long totalTime = 0;
        boolean removeElement = !"HashSet".equals(collection_name) && !"TreeSet".equals(collection_name) && !"LinkedHashSet".equals(collection_name);

        for (int t = 0; t < 100; t++) {
            Random random = new Random();
            int element = random.nextInt(100000);
            // start time
            long startTime = System.nanoTime();
            collection.add(element);
            // end time
            long endTime = System.nanoTime();
            totalTime += (endTime - startTime);
          
            // if it is not a set then
            if (removeElement) {
                // to main the size to 100,000
                collection.remove(element);
            }
        }
        return totalTime / 100;
    }


    private static long testMapAddMethod(Map<Integer, Integer> map, List<Integer> elements, String mapName) {
        long totalTime = 0;

        for (int t = 0; t < 100; t++) {
            Random random = new Random();
            int key = random.nextInt(100000); // random key
            int value = random.nextInt(100000); // random value
            // start time
            long startTime = System.nanoTime();
            map.put(key, value); // adding key value
            // end time
            long endTime = System.nanoTime();
            totalTime += (endTime - startTime);
            // no need to reset here
        }
        return totalTime / 100;
    }

    private static long testContainsMethod(Collection<Integer> collection, List<Integer> elements) {
        long totalTime = 0;

        for (int t = 0; t < 100; t++) {
            // creating random value to search
            Random random = new Random();
            int element = random.nextInt(100000);
            // start time
            long startTime = System.nanoTime();
            collection.contains(element);
            // end time
            long endTime = System.nanoTime();
            totalTime += (endTime - startTime);
        }
        return totalTime / 100;
    }

    private static long testMapContainsMethod(Map<Integer, Integer> map, List<Integer> elements) {
        long totalTime = 0;

        for (int t = 0; t < 100; t++) {
            // creating key value to search
            Random random = new Random();
            int element = random.nextInt(100000);
            // start time
            long startTime = System.nanoTime();
            map.containsKey(element);
            long endTime = System.nanoTime();
            totalTime += (endTime - startTime);
            // end time
        }
        return totalTime / 100;
    }
    private static long testRemoveMethod(Collection<Integer> collection, List<Integer> elements, String collection_name) {
        long totalTime = 0 ;
        boolean removeElement = !"ArrayDeque".equals(collection_name) && !"ArrayList".equals(collection_name);

        for (int t = 0; t < 100; t++) {
            Random random = new Random();
            int element = random.nextInt(100000);
            int remove_element;

            if (removeElement) {
                remove_element = Integer.valueOf(element);
            } else {
                remove_element = element;
            }
            long startTime = System.nanoTime();
            boolean removed = collection.remove(remove_element);
            long endTime = System.nanoTime();

            totalTime += (endTime - startTime);
            if (removed) {
                collection.add(remove_element);
            }
        }
        return totalTime / 100;
    }
    private static long testMapRemoveMethod(Map<Integer, Integer> map, List<Integer> elements) {
        long totalTime = 0;

        for (int t = 0; t < 100; t++) {
            Random random = new Random();
            int keyElement = random.nextInt(100000);

            long startTime = System.nanoTime();
            Integer removed = map.remove(keyElement);
            long endTime = System.nanoTime();

            totalTime += (endTime - startTime);
            if (removed != null) {
                map.put(keyElement, removed);
            }
        }
        return totalTime / 100;
    }
    private static long testClearMethod(Collection<Integer> collection) {
        long totalTime = 0;
        List<Integer> backupList = new ArrayList<>(collection);
        for (int t = 0; t < 100; t++) {
            long startTime = System.nanoTime();
            collection.clear();
            long endTime = System.nanoTime();
            totalTime += (endTime - startTime);
            collection.addAll(backupList);
        }
        return totalTime / 100;
    }
    private static long testMapClearMethod(Map<Integer, Integer> map) {
        long totalTime = 0;
        Map<Integer, Integer> backup = new HashMap<>(map);
        for (int t = 0; t < 100; t++) {
            long startTime = System.nanoTime();
            map.clear();
            long endTime = System.nanoTime();
            totalTime += (endTime - startTime);
            map.putAll(backup);
        }
        return totalTime / 100;
    }
}
