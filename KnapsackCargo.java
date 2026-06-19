public class KnapsackCargo {

    public static void main(String[] args) {

        String[] items = {"A","B","C","D","E","F","G","H"};
        int[] wt = {5,8,3,10,4,6,7,2};
        int[] val = {40,50,20,70,30,35,45,15};

        int n = wt.length;
        int W = 24;

        int[][] dp = new int[n + 1][W + 1];

        // Build DP table
        for (int i = 1; i <= n; i++) {
            for (int w = 1; w <= W; w++) {

                if (wt[i - 1] <= w) {
                    dp[i][w] = Math.max(
                            dp[i - 1][w],
                            dp[i - 1][w - wt[i - 1]] + val[i - 1]
                    );
                } else {
                    dp[i][w] = dp[i - 1][w];
                }
            }
        }

        System.out.println("Maximum Value = ₹" + dp[n][W] + "k");

        // Find selected items
        int w = W;
        System.out.print("Selected Items: ");

        for (int i = n; i > 0 && w > 0; i--) {
            if (dp[i][w] != dp[i - 1][w]) {
                System.out.print(items[i - 1] + " ");
                w -= wt[i - 1];
            }
        }
    }
}