import java.util.*;

class Solution {
    public long minimumTotalDistance(List<Integer> robot, int[][] factory) {
        Collections.sort(robot);
        Arrays.sort(factory, Comparator.comparingInt((int[] a) -> a[0]));
        List<Long> fac = new ArrayList<>();
        for (int[] a : factory) {
            while (a[1] > 0) {
                fac.add((long) a[0]);
                a[1]--;
            }
        }


        int m = robot.size();
        int n = fac.size();
        long[][] dp = new long[m][n];
        dp[0][0] = Math.abs((long) robot.get(0) - fac.get(0));
        for (int i = 1; i < n; i++) {
            dp[0][i] = Math.min(dp[0][i - 1], Math.abs((long) robot.get(0) - fac.get(i)));
        }

        for (int i = 1; i < m; i++) {
            for (int j = i; j < n; j++) {
                if (j == i) {
                    dp[i][j] = dp[i - 1][j - 1] + Math.abs((long) robot.get(i) - fac.get(j));
                } else
                    dp[i][j] = Math.min(dp[i - 1][j - 1] + Math.abs((long) robot.get(i) - fac.get(j)), dp[i][j - 1]);
            }
        }


        return dp[m - 1][n - 1];

    }
}