package study.erik.algorithm.leetcode.backtracking;

import org.junit.Assert;
import org.junit.Test;

/**
 * @author erik.wang
 * @date 2020-04-14 22:21
 */
public class RegularExpressionMatchingTest {

    private final static char STAR = '*';
    private final static char DOT = '.';

    public boolean isMatch(String s, String p) {
        return solution(s, p);
    }

    public boolean solution(String s, String p) {
        return dfs(s, 0, p, 0);
    }

    /**
     * 成绩29和47
     * 分析：这道题目是独立做出来的，因为它是一个很标准的'回溯法'解题法，更重要的是，leetcode上hard的这类题是允许'dfs'的写法的，即不会超时的
     * 首先确定dfs的思路框架，省略。
     * 再次就是确定递归方程，这里以s为主，与p进行匹配的。当然也可以反过来写，可能成绩会好一些。
     * <p>
     * 收获：一份经历：做过'正则表达式'的算法实现；熟悉dfs
     *
     * @param s
     * @param i
     * @param p
     * @param j
     * @return
     */
    public boolean dfs(String s, int i, String p, int j) {

        if (i == s.length()) {
            if (j == p.length()) {
                return true;
            } else if (j < p.length() - 1 && p.charAt(j + 1) == STAR) {
                return dfs(s, i, p, j + 2);
            } else {
                return false;
            }
        }
        if (j == p.length()) {
            return i == s.length();
        }

        if (p.charAt(j) == '*') {
            return false;
        }

        boolean match = false;
        if (s.charAt(i) == p.charAt(j)) {
            if (j < p.length() - 1 && p.charAt(j + 1) == STAR) {
                match = match || dfs(s, i + 1, p, j) || dfs(s, i, p, j + 2);

            }
            match = match || dfs(s, i + 1, p, j + 1);
        } else if (p.charAt(j) == DOT) {
            match = match || dfs(s, i + 1, p, j + 1);
            if (j < p.length() - 1 && p.charAt(j + 1) == STAR) {
                match = match || dfs(s, i + 1, p, j) || dfs(s, i, p, j + 2);
            }
        } else if (j < p.length() - 1 && p.charAt(j + 1) == STAR) {
            match = match || dfs(s, i, p, j + 2);
        } else {
            return false;
        }

        return match;
    }


    @Test
    public void test_() {

        Assert.assertTrue(isMatch("aaa", "a*a"));

        Assert.assertTrue(isMatch("ab", ".*"));

        Assert.assertTrue(isMatch("aab", "c*a*b*"));

        Assert.assertFalse(isMatch("mississippi", "mis*is*p*."));
    }

}
