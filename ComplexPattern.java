import java.util.*;

public class TopKFrequentNumbers {

    private static int[] inputArray;

    public static void main(String[] args) {
        // Test case 1
        int[] array1 = {3, 1, 4, 4, 5, 2, 6, 1};
        int k1 = 2;
        System.out.println("Test Case 1:");
        displayTopKFrequentNumbers(array1, k1);

        // Test case 2
        int[] array2 = {7, 10, 11, 5, 2, 5, 5, 7, 11, 8, 9};
        int k2 = 4;
        System.out.println("\nTest Case 2:");
        displayTopKFrequentNumbers(array2, k2);
    }

    private static void displayTopKFrequentNumbers(int[] array, int k) {
        inputArray = array;
        List<Integer> result = findTopKFrequentNumbers(k);
        System.out.println("Output: " + result);
    }

    private static List<Integer> findTopKFrequentNumbers(int k) {
        Map<Integer, Integer> frequencyMap = new HashMap<>();

        // Count the frequency of each number in the array
        for (int num : inputArray) {
            frequencyMap.put(num, frequencyMap.getOrDefault(num, 0) + 1);
        }

        // Create a priority queue based on frequency and number value
        PriorityQueue<Map.Entry<Integer, Integer>> pq = new PriorityQueue<>(
                (a, b) -> a.getValue() != b.getValue() ? b.getValue() - a.getValue() : b.getKey() - a.getKey()
        );

        // Add entries to the priority queue
        pq.addAll(frequencyMap.entrySet());

        // Retrieve the top K frequent numbers
        List<Integer> result = new ArrayList<>();
        for (int i = 0; i < k; i++) {
            result.add(pq.poll().getKey());
        }

        return result;
    }
}
