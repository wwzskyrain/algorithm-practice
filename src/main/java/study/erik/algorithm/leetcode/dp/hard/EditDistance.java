/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2021 All Rights Reserved.
 */
package study.erik.algorithm.leetcode.dp.hard;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameter;
import org.junit.runners.Parameterized.Parameters;
import study.erik.algorithm.util.LetCodeCommit;

/**
 * @author yueyi
 * @version : EditDistance.java, v 0.1 2021年12月05日 8:27 上午 yueyi Exp $
 */
@RunWith(Parameterized.class)
public class EditDistance {

    @LetCodeCommit(title = "72. Edit Distance",
            selfRemark = "这是个hard题目"
                    + "1.首先，这个题目和'Longest Common Subsequence'的解法很像——dp的定义很像。"
                    + "2.解答时，需要注意i=0和j=0的情况，最好分别作答，比较清晰明了"
                    + "3.今天终于把这道题目完完整整的写下来了。这是一个从source到target的题目，"
                    + "如果按照最短距离算法来写的话，由于每一个状态的子状态太多，所以不用几步就是一个太庞大的"
                    + "搜索树了，即使优化为两头相向而行，也是太庞大的搜索树，所以此路不通的。")
    public int minDistance(String word1, String word2) {
        // dp[i][j]表示word1[..i]到word2[..j]的最小距离，即问题的解
        // dp[i][j]=
        //  case1：word1.charAt(0) == word2.charAt(j)
        //  case2：word1.charAt(0) != word2.charAt(j)

        if (word1.length() == 0 || word2.length() == 0) {
            return word1.length() == 0 ? word2.length() : word1.length();
        }
        int[][] dp = new int[word1.length()][word2.length()];
        dp[0][0] = word1.charAt(0) == word2.charAt(0) ? 0 : 1;
        for (int j = 1; j < dp[0].length; j++) {
            // 这里不能大意，word1.charAt(0) == word2.charAt(j)时，
            // 已经消耗掉了word1.charAt(0), 所以就补充前面j个字符即可。
            // 否则在前驱+1
            dp[0][j] = (word1.charAt(0) == word2.charAt(j) ? j : dp[0][j - 1] + 1);
        }
        for (int i = 1; i < dp.length; i++) {
            dp[i][0] = (word1.charAt(i) == word2.charAt(0) ? i : dp[i - 1][0] + 1);
        }
        for (int i = 1; i < dp.length; i++) {
            for (int j = 1; j < dp[i].length; j++) {
                boolean sameChar = word1.charAt(i) == word2.charAt(j);
                int topLeft = dp[i - 1][j - 1];
                int left = dp[i][j - 1];
                int top = dp[i - 1][j];
                if (sameChar) {
                    //这里准确的表达是如下，不能
                    dp[i][j] = Math.min(topLeft, Math.min(left, top) + 1);
                } else {
                    dp[i][j] = Math.min(topLeft, Math.min(top, left)) + 1;
                }
            }
        }
        return dp[word1.length() - 1][word2.length() - 1];
    }

    @Parameter
    public String word1;
    @Parameter(1)
    public String word2;
    @Parameter(2)
    public int    expect;

    @Parameters
    public static Object[][] data() {
        return new Object[][] {
                {"horse", "ros", 3},
                {"intention", "execution", 5},
                {"zoologicoarchaeologist", "zoogeologist", 10},
                {"", "", 0},
                {"a", "b", 1},
                {"pneumonoultramicroscopicsilicovolcanoconiosis", "ultramicroscopically", 27}
        };
    }

    @Test
    public void test_() {
        Assert.assertEquals(expect, minDistance(word1, word2));
    }

}