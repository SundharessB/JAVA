import java.util.Arrays;
import java.util.Scanner;

public class AlphabetWarGame {
    private static final String LEFT_SIDE = "wpbs";
    private static final String RIGHT_SIDE = "mqdz";

    private int[] leftStrengths;
    private int[] rightStrengths;

  

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

      
        System.out.println("Enter strengths for left side (comma-separated): ");
        int[] leftStrengths = Arrays.stream(scanner.nextLine().split(","))
                .mapToInt(Integer::parseInt)
                .toArray();

        System.out.println("Enter strengths for right side (comma-separated): ");
        int[] rightStrengths = Arrays.stream(scanner.nextLine().split(","))
                .mapToInt(Integer::parseInt)
                .toArray();

        
        AlphabetWarGame defaultGame = new AlphabetWarGame();
        AlphabetWarGame customGame = new AlphabetWarGame(leftStrengths, rightStrengths);

       
        System.out.println("Enter a word for the default game: ");
        String defaultWord = scanner.nextLine();

        System.out.println("Enter a word for the custom game: ");
        String customWord = scanner.nextLine();

        
        System.out.println(defaultGame.alphabetWar(defaultWord));    
        System.out.println(customGame.alphabetWar(customWord, leftStrengths, rightStrengths));
    }
}
