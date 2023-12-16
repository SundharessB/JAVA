import java.util.Arrays;

abstract class Robber {

    // Define abstract methods
    public abstract int RowHouses(int[] rowHouses);
    public abstract int RoundHouses(int[] roundHouses);
    public abstract int SquareHouse(int[] squareHouse);
    public abstract int MultiHouseBuilding(int[]... multiTypeHouses);

    // Default method
    public void MachineLearning() {
        System.out.println("I love MachineLearning.");
    }

    // Directly callable method
    public void RobbingClass() {
        System.out.println("MScAI&ML");
    }
}

class JAVAProfessionalRobber extends Robber {

    // Dynamic programming implementation for RowHouses
    @Override
    public int RowHouses(int[] houses) {
        if (houses == null || houses.length == 0) return 0;

        int n = houses.length;
        if (n == 1) return houses[0];

        int[] dp = new int[n];
        dp[0] = houses[0];
        dp[1] = Math.max(houses[0], houses[1]);

        for (int i = 2; i < n; i++) {
            dp[i] = Math.max(dp[i - 1], dp[i - 2] + houses[i]);
        }

        return dp[n - 1];
    }

    // Dynamic programming with a circular array twist for RoundHouses
    @Override
    public int RoundHouses(int[] houses) {
        if (houses == null || houses.length == 0) return 0;
        if (houses.length == 1) return houses[0];

        return Math.max(robLinear(Arrays.copyOfRange(houses, 0, houses.length - 1)),
                robLinear(Arrays.copyOfRange(houses, 1, houses.length)));
    }

    // Utility function used by RoundHouses for the linear house robbing problem.
    private int robLinear(int[] houses) {
        int prevMax = 0, currMax = 0;
        for (int x : houses) {
            int temp = currMax;
            currMax = Math.max(prevMax + x, currMax);
            prevMax = temp;
        }
        return currMax;
    }

    // Dynamic programming implementation for SquareHouse
    @Override
    public int SquareHouse(int[] houses) {
        return robLinear(houses); // Same as RowHouses (linear)
    }

    // Multi-type house robbing: multi-dimensional dp approach for MultiHouseBuilding
    @Override
    public int MultiHouseBuilding(int[]... multiTypeHouses) {
        // Convert the multi-dimensional array to a single array representing the summed value of each type of house.
        int n = multiTypeHouses.length > 0 ? multiTypeHouses[0].length : 0;
        int[] allHouses = new int[n];

        for (int i = 0; i < n; ++i) {
            for (int[] houseType : multiTypeHouses) {
                allHouses[i] += houseType[Math.min(i, houseType.length - 1)];
            }
        }

        return robLinear(allHouses);
    }

    // Test function to check the correctness of the implementations
    public static void main(String[] args) {
        JAVAProfessionalRobber robber = new JAVAProfessionalRobber();
        System.out.println("RowHouses: " + robber.RowHouses(new int[]{1, 2, 3, 0}));
        System.out.println("RoundHouses: " + robber.RoundHouses(new int[]{1, 2, 3, 4}));
        System.out.println("SquareHouse: " + robber.SquareHouse(new int[]{5, 10, 2, 7}));
        System.out.println("MultiHouseBuilding: " + robber.MultiHouseBuilding(
                new int[]{5, 3, 8, 2}, new int[]{10, 12, 7, 6},
                new int[]{4, 9, 11, 5}, new int[]{8, 6, 3, 7})
        );
        robber.RobbingClass();
        robber.MachineLearning();
    }
}
