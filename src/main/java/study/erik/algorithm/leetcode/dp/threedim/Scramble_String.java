package study.erik.algorithm.leetcode.dp.threedim;


import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import study.erik.algorithm.util.ArrayUtils;
import study.erik.algorithm.util.LetCodeCommit;

import java.util.Arrays;
import java.util.Collection;

/**
 * 日期：2023/9/15 ，时间：16:13
 * 作者：yueyi
 * 描述：
 */
@RunWith(Parameterized.class)
public class Scramble_String {

    @LetCodeCommit(title = "87. Scramble String",
            selfRemark = "这是一个三维dp了。定义十分清楚。" +
                    "以后我们应该多多接触这种题目。" +
                    "第三位是长度。长度的变化从1到len。" +
                    "长度作为外层循环。")
    public boolean isScramble(String s1, String s2) {
        if (s1.length() != s2.length()) return false;
        int len = s1.length();
        /**
         * Let F(i, j, k) = whether the substring S1[i..i + k - 1] is a scramble of S2[j..j + k - 1] or not
         * Since each of these substrings is a potential node in the tree, we need to check for all possible cuts.
         * Let q be the length of a cut (hence, q < k), then we are in the following situation:
         *
         * S1 [   x1    |         x2         ]
         *    i         i + q                i + k - 1
         *
         * here we have two possibilities:
         *
         * S2 [   y1    |         y2         ]
         *    j         j + q                j + k - 1
         *
         * or
         *
         * S2 [       y1        |     y2     ]
         *    j                 j + k - q    j + k - 1
         *
         * which in terms of F means:
         *
         * F(i, j, k) = for some 1 <= q < k we have:
         *  (F(i, j, q) AND F(i + q, j + q, k - q)) OR (F(i, j + k - q, q) AND F(i + q, j, k - q))
         *
         * Base case is k = 1, where we simply need to check for S1[i] and S2[j] to be equal
         * */
        boolean[][][] F = new boolean[len][len][len + 1];
        for (int k = 1; k <= len; ++k)
            for (int i = 0; i + k <= len; ++i)
                for (int j = 0; j + k <= len; ++j)
                    if (k == 1)
                        F[i][j][k] = s1.charAt(i) == s2.charAt(j);
                    else for (int q = 1; q < k && !F[i][j][k]; ++q) {
                        // 为什么 !F[i][j][k]呢，明白了，快速结束，其实F[i][j][k]是多种可能（q）的或。
                        F[i][j][k] = (F[i][j][q] && F[i + q][j + q][k - q]) || (F[i][j + k - q][q] && F[i + q][j][k - q]);
                    }
        return F[0][0][len];
    }

    @Parameterized.Parameters
    public static Collection primeNumbers() {
        return Arrays.asList(new Object[][]{
                {true, "great", "rgeat"},
                {false, "abcde", "caebd"},
                {true, "a", "a"},
        });
    }

    @Parameterized.Parameter
    public boolean expect;
    @Parameterized.Parameter(1)
    public String s1;
    @Parameterized.Parameter(2)
    public String s2;

    @Test
    public void test() {
        Assert.assertEquals(expect, isScramble(s1, s2));
    }

}
