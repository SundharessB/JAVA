interface WaterConservationSystem
{
    double calculateTrappedWater(int[] blockHeights);
}

abstract class RainyConservation implements WaterConservationSystem
{
    // interface derived to RainyConservation class
}

class CityBlockConservation extends RainyConservation
{
    @Override
    public double calculateTrappedWater(int[] blockHeights)
    {
        int n = blockHeights.length;
        if (n < 2)
        {
            return 0; // not enough blocks to trap water
        }

        int[] left_highest = new int[n];
        int [] right_highest = new int[n];

        //To calculate the left highest block we assign the
        //left highest array to first block and traverse the array
        // to find the left boundary block and find the height to left of the block
        left_highest[0] = blockHeights[0];
        for( int i = 1; i < n; i++)
        {
            left_highest[i] = Math.max(left_highest[i - 1], blockHeights[i]);
        }

        //To calculate the right highest block we assign the right highest array
        //to right most block and traverse the array in reverse to find the max 
        //height to the left of right highest block
        right_highest[n - 1] = blockHeights[n-1];
        for( int i = n-2; i >=0 ; i--)
        {
            right_highest[i] = Math.max(right_highest[i+1],blockHeights[i]);
        }

        // To calculate the trapped water between the blocks, if no blocks are not present
        //between any two consecutive blocks we select the minimum value to find the trapped water
        //if any blocks are present in between we subtract the block height value with the water level height
        //if the blocks are of same height then we put 0 as the value
        double water = 0;
        for(int i = 0; i < n; i++)
        {
            water += Math.max(0, Math.min(left_highest[i],right_highest[i]) - blockHeights[i]);
        }

        return water;
    }
}

// Main Method
public class Water_Conservation
{
    public static void main(String args[])
    {
        CityBlockConservation con = new CityBlockConservation();
        int[] test1 = {3,0,0,2,0,4}; // Test Cases
        int[] test2 = {3,0,2,0,4};
        double water_result1 = con.calculateTrappedWater(test1); // Inherited class method calls
        double water_result2 = con.calculateTrappedWater(test2);
        System.out.println("Water Conservation Amount");
        System.out.println("---------------------------------------------------------------------");
        System.out.println("For testcase 1: {3,0,0,2,0,4} result is " + water_result1);
        System.out.println("For testcase 2: {3,0,2,0,4} result is " + water_result2);
    }
}
