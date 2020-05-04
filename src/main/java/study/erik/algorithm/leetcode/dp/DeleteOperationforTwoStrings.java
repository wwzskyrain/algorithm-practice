package study.erik.algorithm.leetcode.dp;

import org.junit.Assert;
import org.junit.Test;

/**
 * @author erik.wang
 * @date 2020-05-04 22:39
 */
public class DeleteOperationforTwoStrings {

    @Test
    public void test_solution() {
        Assert.assertEquals(2, minDistance("sea", "eat"));
    }


    /**
     * title = Delete Operation for Two Strings
     *
     * @param word1
     * @param word2
     * @return
     */
    public int minDistance(String word1, String word2) {
        return solutionWithDp(word1, word2);
    }


    /**
     * 成绩：86和11
     * 这道题和另外一道删除字符题，其思路是一样一样的，我们也是做了那道题之后，在一并把这道题写了，几乎不用思考，全部都是写代码的时间，而且一边过。
     *
     * @param word1
     * @param word2
     * @return
     */
    public int solutionWithDp(String word1, String word2) {

        int[][] dp = new int[word1.length() + 1][word2.length() + 1];
        for (int i = 1; i < dp.length; i++) {
            dp[i][0] = dp[i - 1][0] + 1;
        }
        for (int i = 1; i < dp[0].length; i++) {
            dp[0][i] = dp[0][i - 1] + 1;
        }

        for (int i = 1; i < dp.length; i++) {
            for (int j = 1; j < dp[i].length; j++) {
                if (word1.charAt(i - 1) == word2.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1];
                } else {
                    dp[i][j] = Math.min(dp[i - 1][j] + 1, dp[i][j - 1] + 1);
                }
            }
        }
        return dp[word1.length()][word2.length()];
    }


}
