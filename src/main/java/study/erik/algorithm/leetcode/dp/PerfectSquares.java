package study.erik.algorithm.leetcode.dp;

import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;

/**
 * @author erik.wang
 * @date 2020-04-23 15:23
 */
public class PerfectSquares {

    /**
     * title = Perfect Squares
     * difficulty = medium
     * url = https://leetcode.com/problems/perfect-squares/
     *
     * @param n
     * @return
     */
    public int numSquares(int n) {
        return solution(n);
    }

    /**
     * 成绩：84和13
     * 这道题目不难，直接就是dp
     * 首先：dp[i] 表示i的问题的解，即完美数字和的最小个数。
     * 那么，dp[i] = min{ dp[k] + dp[i-k] } （k = {1,2,...,i-1}）
     * 这样是可以解出来的，但是效率不高；
     * <p>
     * 第二版：优化版
     * 首先dp[i] 还是表示i的问题的解，即完美数字和的最小个数。
     * dp[i] = min{ dp[i-k*k]+1 } （k = {1,2,...,Math.sqr(i)-向下取整}）
     * 优化版来源: https://leetcode.com/problems/perfect-squares/discuss/71488/Summary-of-4-different-solutions-(BFS-DP-static-DP-and-mathematics)
     *
     * @param n
     * @return
     */
    public int solution(int n) {
        int[] dp = new int[n + 1];
        dp[1] = 1;
        int i = 2;
        while (i <= n) {
            int sqrt = (int) Math.sqrt(i);
            if (i - sqrt * sqrt < 0.00001) {
                dp[i] = 1;
            } else {
                int minNum = Integer.MAX_VALUE;
                int j = 1;
                while (j * j <= i) {
                    minNum = Math.min(minNum, 1 + dp[i - j * j]);
                    j++;
                }
                dp[i] = minNum;
            }
            i++;
        }
//        System.out.printf("%d = %s\n", n, Arrays.toString(dp));
        return dp[n];
    }

    @Test
    public void test_() {
        Assert.assertEquals(2, numSquares(13));
        Assert.assertEquals(3, numSquares(12));
    }

}
