package study.erik.algorithm.leetcode.dp;

import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;

/**
 * @author erik.wang
 * @date 2020-03-02 16:30
 * @description
 */
public class KnightDialer {

    /**
     * 935. Knight Dialer
     * url = https://leetcode.com/problems/knight-dialer/
     * 问题就是一般的动态规划，或者简单得说，就是一个递归公式，
     * 在编程的时候，从底向上解就可以了。
     * 坑爹的是，原题非要N很大，知道要溢出，还有取模。扯淡。
     * @param N
     * @return
     */
    public int knightDialer(int N) {

        int MOD = 1_000_000_007;

        long[][] dp = new long[N][10];

        for (int i = 0; i < N; i++) {
            dp[i] = new long[10];
        }

        for (int i = 0; i < 10; i++) {
            dp[0][i] = 1;
        }

        int[][] map = new int[10][];
        map[0] = new int[]{4, 6};
        map[1] = new int[]{6, 8};
        map[2] = new int[]{7, 9};
        map[3] = new int[]{4, 8};
        map[4] = new int[]{0, 3, 9};
        map[5] = new int[]{};
        map[6] = new int[]{0, 1, 7};
        map[7] = new int[]{2, 6};
        map[8] = new int[]{1, 3};
        map[9] = new int[]{2, 4};


        for (int i = 1; i < dp.length; i++) {
            for (int number = 0; number < dp[i].length; number++) {
                long sum = 0;
                for (int nextNumber : map[number]) {
                    sum += dp[i - 1][nextNumber];
                    sum %= MOD;
                }
                dp[i][number] = sum;
            }
        }

        long count = 0;
        for (long countItem : dp[N - 1]) {
            count += countItem;
        }

        return (int) (count % MOD);
    }

    @Test
    public void test() {
        Assert.assertEquals(10, knightDialer(1));
        Assert.assertEquals(20, knightDialer(2));
        Assert.assertEquals(46, knightDialer(3));

        for (int i = 1; i < 100; i++) {
            System.out.printf("N = %2d , knightDialer count = %20d \n", i, knightDialer(i));
            System.out.printf("N = %2d , knightDialer count = %20d \n", i, knightDialer2(i));
        }

    }

    /**
     * 官方解法；和我的解法一样的。
     * @param N
     * @return
     */
    public int knightDialer2(int N) {
        int MOD = 1_000_000_007;
        int[][] moves = new int[][]{
                {4, 6}, {6, 8}, {7, 9}, {4, 8}, {3, 9, 0},
                {}, {1, 7, 0}, {2, 6}, {1, 3}, {2, 4}};

        int[][] dp = new int[2][10];
        Arrays.fill(dp[0], 1);
        for (int hops = 0; hops < N - 1; ++hops) {
            Arrays.fill(dp[~hops & 1], 0);
            for (int node = 0; node < 10; ++node)
                for (int nei : moves[node]) {
                    dp[~hops & 1][nei] += dp[hops & 1][node];
                    dp[~hops & 1][nei] %= MOD;
                }
        }

        long ans = 0;
        for (int x : dp[~N & 1])
            ans += x;
        return (int) (ans % MOD);
    }


}
