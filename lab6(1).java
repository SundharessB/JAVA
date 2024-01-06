import java.util.Scanner;

 class CoinChange {
    public static int countWays(int[] coins, int sum) {
        int n = coins.length;
        int[][] dp = new int[n + 1][sum + 1];

        // Base case: If the sum is 0, there is 1 way (empty set).
        for (int i = 0; i <= n; i++) {
            dp[i][0] = 1;
        }

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= sum; j++) {
                // Exclude the current coin
                dp[i][j] = dp[i - 1][j];

                // Include the current coin if it doesn't exceed the sum
                if (coins[i - 1] <= j) {
                    dp[i][j] += dp[i][j - coins[i - 1]];
                }
            }
        }

        return dp[n][sum];
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter the number of coins: ");
        int n = scanner.nextInt();

        if (n <= 0) {
            System.out.println("Number of coins must be a positive integer.");
            return;
        }

        int[] coins = new int[n];
        System.out.println("Enter the values of coins separated by spaces:");
        for (int i = 0; i < n; i++) {
            coins[i] = scanner.nextInt();
            if (coins[i] <= 0) {
                System.out.println("Coin values must be positive integers.");
                return;
            }
        }

        System.out.print("Enter the target sum: ");
        int sum = scanner.nextInt();

        if (sum < 0) {
            System.out.println("Target sum must be a non-negative integer.");
            return;
        }

        System.out.println("Number of ways to make the given sum: " + countWays(coins, sum));
    }
}
