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
            selfRemark = "这是个hard题目")
    public int minDistanceSolution2(String word1, String word2) {
        // dp[i][j]表示word1[..i]到word2[..j]的最小距离，即问题的解
        // dp[i][j]=
        /*
        case1：word1.charAt(i) == word2.charAt(j)
        dp[i][j] = max(dp[i-1][j-1]
        , dp[i][j-1] + 1   //在word1[0..i] -> word2[0..j-1]的基础上，加一个字符word2[j]
        , dp[i][j-1] + 1)  //在word1[0..i-1] -> word2[0..j]的基础上，加一个字符word1[i]

        case2：word1.charAt(i) != word2.charAt(j)
        dp[i][j] = max(dp[i-1][j-1] + 1 //修改word1[i] -> word2[j]
            , dp[i][j-1] + 1 //在word1[0..i] -> word2[0..j-1]的基础上，加一个字符word2[j]
            , dp[i-1][j] + 1 //在word1[0..i-1] -> word2[0..j]的基础上，加一个字符word1[i]
        )
        * */
        int m = word1.length();
        int n = word2.length();
        int[][] dp = new int[m + 1][n + 1];
        //dp[i][j]表示w1[0..i-1]与w2[0..j-1]的编辑距离
        for (int i = 0; i < m + 1; i++) {
            //从word1[0..i]到word2="",需要删除i个字符即可
            dp[i][0] = i;
        }
        for (int j = 0; j < n + 1; j++) {
            //从word1=""到word2[0..j],需要增加j个字符即可
            dp[0][j] = j;
        }
        for (int i = 1; i < dp.length; i++) {
            for (int j = 1; j < dp[i].length; j++) {
                //dp[i][j]来自于左、上、左上
                boolean sameChar = word1.charAt(i - 1) == word2.charAt(j - 1);
                dp[i][j] = Math.min(dp[i - 1][j] + 1, dp[i][j - 1] + 1);
                dp[i][j] = Math.min(dp[i][j], dp[i - 1][j - 1] + (sameChar ? 0 : 1));
            }
        }
        return dp[m][n];
    }

    @Parameter
    public String word1;
    @Parameter(1)
    public String word2;
    @Parameter(2)
    public int expect;

    @Parameters
    public static Object[][] data() {
        return new Object[][]{
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
        Assert.assertEquals(expect, minDistanceSolution2(word1, word2));
    }

}