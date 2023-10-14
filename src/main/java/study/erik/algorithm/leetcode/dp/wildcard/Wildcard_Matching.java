package study.erik.algorithm.leetcode.dp.wildcard;


import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import study.erik.algorithm.util.ArrayUtils;
import study.erik.algorithm.util.LetCodeCommit;

import java.util.Arrays;
import java.util.Collection;

/**
 * 日期：2023/10/12 ，时间：07:30
 * 作者：yueyi
 * 描述：
 */
@RunWith(Parameterized.class)
public class Wildcard_Matching {

    @LetCodeCommit(title = "44. Wildcard Matching",
            selfRemark = "简单一些，*直接匹配————通配符匹配")
    public boolean isMatch(String s, String p) {
        int m = s.length();
        int n = p.length();

        boolean[][] dp = new boolean[m + 1][n + 1];
        dp[0][0] = true;
        for (int i = 1; i < n + 1; i++) {
            if (p.charAt(i - 1) == '*') {
                //只有p0、p1、,,,、pi-1 都是'*'时，他们才能匹配空串
                dp[0][i] = true;
            } else {
                break;
            }
        }
        //dp[i][j]表示s[0...i-1]与p[0,...,j-1]匹配
        for (int i = 1; i < m + 1; i++) {
            for (int j = 1; j < n + 1; j++) {
                if (p.charAt(j - 1) == '*') {
                    dp[i][j] = dp[i][j - 1] || dp[i - 1][j]; //用*和不用*两种case的或
                } else if (p.charAt(j - 1) == '?' || s.charAt(i - 1) == p.charAt(j - 1)) {
                    //
                    dp[i][j] = dp[i - 1][j - 1];
                } else {
                    //dp[i][j] = false.
                }
            }
        }
        return dp[m][n];
    }

    @Parameterized.Parameters
    public static Collection testData() {
        return Arrays.asList(new Object[][]{
                {false, "aa", "a"},
                {true, "aa", "*"},
                {false, "cb", "?a"},
                });
    }

    @Parameterized.Parameter
    public boolean expect;
    @Parameterized.Parameter(1)
    public String s;
    @Parameterized.Parameter(2)
    public String p;

    @Test
    public void test() {
        Assert.assertEquals(expect, isMatch(s, p));
    }

}
