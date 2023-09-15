package study.erik.algorithm.leetcode.dp;


import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import study.erik.algorithm.util.ArrayUtils;
import study.erik.algorithm.util.LetCodeCommit;

import java.util.Arrays;
import java.util.Collection;

/**
 * 日期：2023/9/14 ，时间：11:11
 * 作者：yueyi
 * 描述：
 */
@RunWith(Parameterized.class)
public class Regular_Expression_Matching {

    @LetCodeCommit(title = "10. Regular Expression Matching",
            selfRemark = "可以按照回溯的思想来解答。" +
                    "也可以用这种dp来减少重复计算。")
    public boolean isMatch(String s, String p) {
        int m = s.length();
        int n = p.length();
        //f[i][j]表示s[0..i-1]与p[0..j-1]的匹配情况，所以f[m][n]就是问题的解，其中m和n分别是s、p的长度
        boolean[][] f = new boolean[m + 1][n + 1];
        f[0][0] = true;
        for (int i = 0; i <= m; ++i) {
            /*
            为啥i从0，而j从1呢?
            首先j取0，表示p是空字符串，它只能匹配空字符串，这一点在f[0][0]=true已经完成。
            其次，i不能不从0开始，因为s为空字符串时，正则表达式却可以不为空，比如'a*'。
             */
            for (int j = 1; j <= n; ++j) {
                if (p.charAt(j - 1) == '*') {
                    //case1：匹配0次
                    f[i][j] = f[i][j - 2];
                    if (matches(s, p, i, j - 1)) {
                        //case2：匹配一次，匹配掉s中的当前i
                        f[i][j] = f[i][j] || f[i - 1][j];
                    }
                } else {
                    //硬生生的必须匹配
                    if (matches(s, p, i, j)) {
                        f[i][j] = f[i - 1][j - 1];
                    }
                }
            }
        }
        return f[m][n];
    }

    public boolean matches(String s, String p, int i, int j) {
        if (i == 0) {
            return false;
        }
        //case1:.可以匹配任何一个字符 ； case2:两个字符相等
        return p.charAt(j - 1) == '.' || s.charAt(i - 1) == p.charAt(j - 1);
    }


    @Parameterized.Parameters
    public static Collection primeNumbers() {
        return Arrays.asList(new Object[][]{
                {"abc", "a***abc", true},
                {"ab", ".*", true},
                {"aa", "a", false}
        });
    }

    @Parameterized.Parameter
    public String s;
    @Parameterized.Parameter(1)
    public String p;
    @Parameterized.Parameter(2)
    public boolean expect;

    @Test
    public void test() {
        Assert.assertEquals(expect, isMatch(s, p));
    }
}
