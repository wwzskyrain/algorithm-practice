package study.erik.algorithm.leetcode.dp;

import org.junit.Assert;
import org.junit.Test;

/**
 * @author erik.wang
 * @date 2020-05-04 22:08
 */
public class MinimumASCIIDeleteSumforTwoStrings {

    @Test
    public void test_solution() {

        Assert.assertEquals(403, minimumDeleteSum("delete", "leet"));
        Assert.assertEquals(231, minimumDeleteSum("sea", "eat"));
    }

    /**
     * title = Minimum ASCII Delete Sum for Two Strings
     * @param s1
     * @param s2
     * @return
     */
    public int minimumDeleteSum(String s1, String s2) {
        return solutionWithDp(s1, s2);
    }

    /**
     * 成绩：79 和 100 ，excellent
     * 出了修改了一下循环变量的边界值，我们的代码可谓是一气呵成，而且就直接过了，成绩还不错，这会让我们骄傲的，哈哈。
     * 状态转移公式在注释处解释
     * @param s1
     * @param s2
     * @return
     */
    public int solutionWithDp(String s1, String s2) {

        int[][] dp = new int[s1.length() + 1][s2.length() + 1];
        for (int i = 1; i < dp.length; i++) {
            dp[i][0] = dp[i - 1][0] + s1.charAt(i - 1);
        }
        for (int i = 1; i < dp[0].length; i++) {
            dp[0][i] = dp[0][i - 1] + s2.charAt(i - 1);
        }

        for (int i = 1; i < dp.length; i++) {
            for (int j = 1; j < dp[i].length; j++) {
                if (s1.charAt(i - 1) == s2.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1];
                } else {
                    //两种情况，一种是删除s1(i),另一种是删除s2(j)
                    dp[i][j] = Math.min(dp[i - 1][j] + s1.charAt(i - 1), dp[i][j - 1] + s2.charAt(j - 1));
                }
            }
        }

        return dp[s1.length()][s2.length()];
    }

}
