import java.util.*;

public class CricketBucketSort {

    public static void main(String[] args) {

        int[][] deliveries = {
                {2,4},
                {1,1},
                {3,6},
                {1,5},
                {2,2},
                {3,1},
                {1,3},
                {2,6},
                {3,4},
                {1,2}
        };

        // Create buckets for overs 0 to 3
        ArrayList<int[]>[] buckets = new ArrayList[4];

        for (int i = 0; i < 4; i++) {
            buckets[i] = new ArrayList<>();
        }

        // Place deliveries into buckets
        for (int[] delivery : deliveries) {
            int over = delivery[0];
            buckets[over].add(delivery);
        }

        // Sort each bucket by ball number
        for (ArrayList<int[]> bucket : buckets) {
            bucket.sort((a, b) -> a[1] - b[1]);
        }

        // Display sorted deliveries
        System.out.println("Sorted Deliveries:");

        for (int over = 0; over < 4; over++) {
            for (int[] delivery : buckets[over]) {
                System.out.println("(" + delivery[0] + "," + delivery[1] + ")");
            }
        }
    }
}