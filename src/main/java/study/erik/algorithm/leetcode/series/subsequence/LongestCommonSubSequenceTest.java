package study.erik.algorithm.leetcode.series.subsequence;

import org.junit.Assert;
import org.junit.Test;
import study.erik.algorithm.util.LetCodeCommit;

/**
 * @author erik.wang
 * @date 2020-04-01 14:33
 */
public class LongestCommonSubSequenceTest {

    /**
     * difficulty = medium
     * title = Longest Common Subsequence
     * url = https://leetcode.com/problems/longest-common-subsequence/
     * next challenge =
     * 1.https://leetcode.com/problems/longest-palindromic-subsequence/
     * 2.https://leetcode.com/problems/delete-operation-for-two-strings/
     * 3.Shortest Common Supersequence
     *
     * @param text1
     * @param text2
     * @return
     */
    @LetCodeCommit(title = "1143. Longest Common Subsequence",
            related = {"1092. Shortest Common Supersequence"})
    public int longestCommonSubsequence(String text1, String text2) {
        return solution1(text1, text2);
    }

    public int solution1(String text1, String text2) {
        int[][] dp = new int[text1.length()][text2.length()];
        for (int i = 0; i < dp.length; i++) {
            for (int j = 0; j < dp[i].length; j++) {
                int up = i > 0 ? dp[i - 1][j] : 0;
                int left = j > 0 ? dp[i][j - 1] : 0;
                int topLeft = (i > 0 && j > 0 ? dp[i - 1][j - 1] : 0) + (text1.charAt(i) == text2.charAt(j) ? 1 : 0);
                dp[i][j] = Math.max(up, Math.max(left, topLeft));
            }
        }
        return dp[text1.length() - 1][text2.length() - 1];
    }

    /**
     * 成绩：68% 和 100% ，肯定自己一下，虽然第二个100没有第一个100可喜。
     * 这种dp我们很熟悉了，或者说着个题目我们早烂熟于心了，才一遍通过
     *
     * @param text1
     * @param text2
     * @return
     */
    public int solution(String text1, String text2) {
        if (text1.length() == 0 || text2.length() == 0) {
            return 0;
        }

        int[][] dp = new int[text1.length() + 1][text2.length() + 1];

        for (int i = 1; i < dp.length; i++) {
            for (int j = 1; j < dp[i].length; j++) {
                if (text1.charAt(i - 1) == text2.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                }
            }
        }
        return dp[text1.length()][text2.length()];
    }

    @Test
    public void test_solution() {
        String text5 = "abc", text6 = "def";
        Assert.assertEquals(0, longestCommonSubsequence(text5, text6));

        String text3 = "abc", text4 = "abc";
        Assert.assertEquals(3, longestCommonSubsequence(text3, text4));

        String text1 = "abcde", text2 = "ace";
        Assert.assertEquals(3, longestCommonSubsequence(text1, text2));
    }
}
