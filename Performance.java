import java.util.Scanner;

public class Performance {
    private int[] marks;

    // Constructor
    public Performance() {
        marks = new int[10];
    }

    public void readMarks() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter the marks of 10 students:");

        for (int i = 0; i < 10; i++) {
            System.out.print("Student " + (i + 1) + ": ");
            marks[i] = scanner.nextInt();

            // Validate marks (0 <= marks <= 100)
            if (marks[i] < 0 || marks[i] > 100) {
                System.out.println("Invalid mark! Please enter a mark between 0 and 100.");
                i--; 
            }
        }
    }

    public int highestMark() {
        int maxMark = marks[0];

        for (int i = 1; i < marks.length; i++) {
            if (marks[i] > maxMark) {
                maxMark = marks[i];
            }
        }

        return maxMark;
    }

    public int leastMark() {
        int minMark = marks[0];

        for (int i = 1; i < marks.length; i++) {
            if (marks[i] < minMark) {
                minMark = marks[i];
            }
        }

        return minMark;
    }

    public int getMode() {
        int mode = marks[0];
        int maxFrequency = 0;

        for (int i = 0; i < marks.length; i++) {
            int frequency = 0;

            for (int j = 0; j < marks.length; j++) {
                if (marks[i] == marks[j]) {
                    frequency++;
                }
            }

            if (frequency > maxFrequency || (frequency == maxFrequency && marks[i] > mode)) {
                maxFrequency = frequency;
                mode = marks[i];
            }
        }

        return mode;
    }

    public int getFreqAtMode() {
        int mode = getMode();
        int frequency = 0;

        for (int i = 0; i < marks.length; i++) {
            if (marks[i] == mode) {
                frequency++;
            }
        }

        return frequency;
    }

    public void display() {
        System.out.println("Highest Mark: " + highestMark());
        System.out.println("Least Mark: " + leastMark());
        System.out.println("Mode: " + getMode());
        System.out.println("Mode Frequency: " + getFreqAtMode());
    }

    public static void main(String[] args) {
        Performance performance = new Performance();
        performance.readMarks();
        performance.display();
    }
}
