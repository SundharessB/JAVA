import java.util.*;

public class TopK {

    private static int[] numbers;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter the size of the array: ");
        int size = scanner.nextInt();
        numbers = new int[size];

        System.out.println("Enter the elements of the array:");
        for (int i = 0; i < size; i++) {
            numbers[i] = scanner.nextInt();
        }

        System.out.print("Enter the value of K: ");
        int k = scanner.nextInt();

        displayTopKFrequent(k);

        scanner.close();
    }

    private static void displayTopKFrequent(int k) {
        int[] result = findTopKFrequent(k);
        System.out.print("Output: ");
        for (int x : result) {
            System.out.print(x + " ");
        }
        System.out.println();
    }

    private static int[] findTopKFrequent(int k) {
        Map<Integer, Integer> frequencyMap = new HashMap<>();

        // Count the frequency of each number in the array
        for (int x : numbers) {
            frequencyMap.put(x, frequencyMap.containsKey(x) ? frequencyMap.get(x) + 1 : 1);
        }

        // Create an array to store frequencies and numbers
        int[][] freqArray = new int[frequencyMap.size()][2];
        int i = 0;
        for (Map.Entry<Integer, Integer> entry : frequencyMap.entrySet()) {
            freqArray[i][0] = entry.getKey();
            freqArray[i][1] = entry.getValue();
            i++;
        }

        // Sort the array based on frequency and number value
        for (int p = 0; p < freqArray.length - 1; p++) {
            for (int q = 0; q < freqArray.length - p - 1; q++) {
                if ((freqArray[q][1] < freqArray[q + 1][1]) ||
                        (freqArray[q][1] == freqArray[q + 1][1] && freqArray[q][0] < freqArray[q + 1][0])) {
                    int[] temp = freqArray[q];
                    freqArray[q] = freqArray[q + 1];
                    freqArray[q + 1] = temp;
                }
            }
        }

        // Retrieve the top K frequent numbers
        int[] result = new int[k];
        for (i = 0; i < k; i++) {
            result[i] = freqArray[i][0];
        }

        return result;
    }
}
