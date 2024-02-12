import java.util.*;

public class Main {
    public static void main(String[] args) {

        // HashSet
        HashSet<Integer> hashSet = new HashSet<>();
        long hashSetAddTime = testAddMethod(hashSet);
        long hashSetContainsTime = testContainsMethod(hashSet);
        long hashSetRemoveTime = testRemoveMethod(hashSet);
        long hashSetClearTime = testClearMethod(hashSet);

        // TreeSet
        TreeSet<Integer> treeSet = new TreeSet<>();
        long treeSetAddTime = testAddMethod(treeSet);
        long treeSetContainsTime = testContainsMethod(treeSet);
        long treeSetRemoveTime = testRemoveMethod(treeSet);
        long treeSetClearTime = testClearMethod(treeSet);

        // LinkedHashSet
        LinkedHashSet<Integer> linkedHashSet = new LinkedHashSet<>();
        long linkedHashSetAddTime = testAddMethod(linkedHashSet);
        long linkedHashSetContainsTime = testContainsMethod(linkedHashSet);
        long linkedHashSetRemoveTime = testRemoveMethod(linkedHashSet);
        long linkedHashSetClearTime = testClearMethod(linkedHashSet);

        // ArrayList
        ArrayList<Integer> arrayList = new ArrayList<>();
        long arrayListAddTime = testAddMethod(arrayList);
        long arrayListContainsTime = testContainsMethod(arrayList);
        long arrayListRemoveTime = testRemoveMethod(arrayList);
        long arrayListClearTime = testClearMethod(arrayList);

        //LinkedList
        LinkedList<Integer> linkedList = new LinkedList<>();
        long linkedListAddTime = testAddMethod(linkedList);
        long linkedListContainsTime = testContainsMethod(linkedList);
        long linkedListRemoveTime = testRemoveMethod(linkedList);
        long linkedListClearTime = testClearMethod(linkedList);

        //ArrayDee
        ArrayDeque<Integer> arrayDeque = new ArrayDeque<>();
        long arrayDequeAddTime = testAddMethod(arrayDeque);
        long arrayDequeContainsTime = testContainsMethod(arrayDeque);
        long arrayDequeRemoveTime = testRemoveMethod(arrayDeque);
        long arrayDequeClearTime = testClearMethod(arrayDeque);


        // PriorityQueue
        PriorityQueue<Integer> priorityQueue = new PriorityQueue<>();
        long priorityQueueAddTime = testAddMethod(priorityQueue);
        long priorityQueueContainsTime = testContainsMethod(priorityQueue);
        // PriorityQueue does not support remove operation
        long priorityQueueClearTime = testClearMethod(priorityQueue);

        //HashMap
        HashMap<Integer, Integer> hashMap = new HashMap<>();
        long hashMapAddTime = testAddMethod(new ArrayList<>(hashMap.values()));
        long hashMapContainsTime = testContainsMethod(new ArrayList<>(hashMap.values()));
        long hashMapRemoveTime = testRemoveMethod(new ArrayList<>(hashMap.values()));
        long hashMapClearTime = testMapClearMethod(hashMap);

        //TreeMap
        TreeMap<Integer, Integer> treeMap = new TreeMap<>();
        long treeMapAddTime = testAddMethod(new ArrayList<>(treeMap.values()));
        long treeMapContainsTime = testContainsMethod(new ArrayList<>(treeMap.values()));
        long treeMapRemoveTime = testRemoveMethod(new ArrayList<>(treeMap.values()));
        long treeMapClearTime = testMapClearMethod(treeMap);

        //LinkedHashMap
        LinkedHashMap<Integer, Integer> linkedHashMap = new LinkedHashMap<>();
        long linkedHashMapAddTime = testAddMethod(new ArrayList<>(linkedHashMap.values()));
        long linkedHashMapContainsTime = testContainsMethod(new ArrayList<>(linkedHashMap.values()));
        long linkedHashMapRemoveTime = testRemoveMethod(new ArrayList<>(linkedHashMap.values()));
        long linkedHashMapClearTime = testMapClearMethod(linkedHashMap);

        // Load collections with 100,000 random Integer objects
        Random random = new Random();
        for (int i = 0; i < 100000; i++) {
            int randomValue = random.nextInt(100000);
            hashSet.add(randomValue);
            treeSet.add(randomValue);
            linkedHashSet.add(randomValue);
            arrayList.add(randomValue);
            linkedList.add(randomValue);
            arrayDeque.add(randomValue);
            priorityQueue.add(randomValue);
            // adding Key Value Pair
            hashMap.put(i, randomValue);
            treeMap.put(i, randomValue);
            linkedHashMap.put(i, randomValue);
        }

        // Display results
        System.out.println("                    Performance Comparison   ");
        System.out.println("_____________________________________________________________________");
        System.out.println("| Collection       |    Add     |  Contains  |   Remove   |  Clear   |");
        System.out.println("_____________________________________________________________________");
        displayResult("HashSet", hashSetAddTime, hashSetContainsTime, hashSetRemoveTime, hashSetClearTime);
        displayResult("TreeSet", treeSetAddTime, treeSetContainsTime, treeSetRemoveTime, treeSetClearTime);
        displayResult("LinkedHashSet", linkedHashSetAddTime, linkedHashSetContainsTime, linkedHashSetRemoveTime, linkedHashSetClearTime);
        displayResult("ArrayList", arrayListAddTime, arrayListContainsTime, arrayListRemoveTime, arrayListClearTime);
        displayResult("LinkedList", linkedListAddTime, linkedListContainsTime, linkedListRemoveTime, linkedListClearTime);
        displayResult("ArrayDeque", arrayDequeAddTime, arrayDequeContainsTime, arrayDequeRemoveTime, arrayDequeClearTime);
        displayResult("PriorityQueue", priorityQueueAddTime, priorityQueueContainsTime, 0, priorityQueueClearTime);
        displayResult("HashMap", hashMapAddTime, hashMapContainsTime, hashMapRemoveTime, hashMapClearTime);
        displayResult("TreeMap", treeMapAddTime, treeMapContainsTime, treeMapRemoveTime, treeMapClearTime);
        displayResult("LinkedHashMap", linkedHashMapAddTime, linkedHashMapContainsTime, linkedHashMapRemoveTime, linkedHashMapClearTime);
        System.out.println("_____________________________________________________________________");
    }

    private static long testAddMethod(Collection<Integer> collection) {
        // this function is used to calculate the average adding time taken for 100 iterations
        long startTime = System.nanoTime();
        for (int i = 0; i < 100; i++) {
            for (int j = 0; j < 100000; j++) {
                collection.add(j);
            }
        }
        long endTime = System.nanoTime();
        return (endTime - startTime) / 100; // Average time for 100 runs
    }

    private static long testContainsMethod(Collection<Integer> collection) {
        // this function is used to calculate the average containing(searching) time taken for 100 iterations
        long startTime = System.nanoTime();
        for (int i = 0; i < 100; i++) {
            for (int j = 0; j < 100000; j++) {
                collection.contains(j);
            }
        }
        long endTime = System.nanoTime();
        return (endTime - startTime) / 100; // Average time for 100 runs
    }

    private static long testRemoveMethod(Collection<Integer> collection) {
        // this function is used to calculate the average removing time taken for 100 iterations
        long startTime = System.nanoTime();
        for (int i = 0; i < 100; i++) {
            for (int j = 0; j < 100000; j++) {
                collection.remove(j);
            }
        }
        long endTime = System.nanoTime();
        return (endTime - startTime) / 100; // Average time for 100 runs
    }

    private static long testClearMethod(Collection<Integer> collection) {
        // this function is used to calculate the average clearing time taken for 100 iterations
        long startTime = System.nanoTime();
        for (int i = 0; i < 100; i++) {
            collection.clear();
        }
        long endTime = System.nanoTime();
        return (endTime - startTime) / 100; // Average time for 100 runs
    }

    private static long testMapClearMethod(Map<Integer, Integer> map) {
        long startTime = System.nanoTime();
        for (int i = 0; i < 100; i++) {
            map.clear();
        }
        long endTime = System.nanoTime();
        return (endTime - startTime) / 100; // Average time for 100 runs
    }

    private static void displayResult(String collectionName, long addTime, long containsTime, long removeTime, long clearTime) {
        System.out.printf("| %-16s | %7d | %8d | %6d | %6d |\n", collectionName, addTime, containsTime, removeTime, clearTime);
    }
}
