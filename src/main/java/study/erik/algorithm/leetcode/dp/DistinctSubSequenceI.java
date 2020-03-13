package study.erik.algorithm.leetcode.dp;

import org.junit.Assert;
import org.junit.Test;

/**
 * @author erik.wang
 * @date 2020-03-13 16:13
 * @description
 */
public class DistinctSubSequenceI {

    public int numDistinct(String s, String t) {
        return solution1(s, t);
    }

    /*
    dp[i][j]表示s[0..i]包含t[0..j]的个数——就是问题定义
    dp[i,j] = dp[i-1,j] + {dp[i-1,j-1] (if s[i]!=t[j])}
    注意：dp[i-1,j] 是 dp[i-1,j-1]所不能包含的。
     */
    public int solution1(String s, String t) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        if (t == null || t.length() == 0) {
            return 1;
        }

        int[][] dp = new int[t.length()][];
        for (int i = 0; i < dp.length; i++) {
            dp[i] = new int[s.length()];
        }

        dp[0][0] = s.charAt(0) == t.charAt(0) ? 1 : 0;
// 只需要初始化第一行
        for (int i = 1; i < dp[0].length; i++) {
            dp[0][i] = dp[0][i - 1] + (s.charAt(i) == t.charAt(0) ? 1 : 0);
        }
// 只需要计算上三角
        for (int i = 1; i < dp.length; i++) {
            for (int j = i; j < dp[i].length; j++) {
                dp[i][j] = (s.charAt(j) == t.charAt(i) ? dp[i - 1][j - 1] : 0)
                        + dp[i][j - 1];
            }
        }
        return dp[t.length() - 1][s.length() - 1];
    }

    @Test
    public void test_solution() {
        Assert.assertEquals(5, solution1("babgbag", "bag"));
    }

}
