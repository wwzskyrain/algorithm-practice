package study.erik.algorithm.nowcoder.huawei.medium;

import java.util.*;

public class BalloonColorK {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        String s = sc.next();
        int[] t = new int[n];

        for (int i = 0; i < n; i++) {
            t[i] = sc.nextInt();
        }

        // 这里假设颜色是 0,1,2（也可以扩展）
        int K = 3;

        System.out.println(solve(n, s, t, K));
    }

    public static long solve(int n, String s, int[] t, int K) {

        long[] dp = new long[K];
        Arrays.fill(dp, 0);

        for (int i = 0; i < n; i++) {

            long[] newDp = new long[K];

            // 找最小和次小
            long min1 = Long.MAX_VALUE, min2 = Long.MAX_VALUE;
            int idx1 = -1;

            for (int c = 0; c < K; c++) {
                if (dp[c] < min1) {
                    min2 = min1;
                    min1 = dp[c];
                    idx1 = c;
                } else if (dp[c] < min2) {
                    min2 = dp[c];
                }
            }

            int orig = s.charAt(i) - '0';

            for (int c = 0; c < K; c++) {

                long cost = (orig == c) ? 0 : t[i];

                // 继续 or 切换
                long stay = dp[c];
                long change = (c == idx1 ? min2 : min1);

                newDp[c] = Math.min(stay, change) + cost;
            }

            dp = newDp;
        }

        long ans = Long.MAX_VALUE;
        for (long v : dp) {
            ans = Math.min(ans, v);
        }

        return ans;
    }
}