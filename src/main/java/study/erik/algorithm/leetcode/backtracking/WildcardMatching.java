package study.erik.algorithm.leetcode.backtracking;

import org.junit.Assert;
import org.junit.Test;

/**
 * @author erik.wang
 * @date 2020-04-19 09:46
 */
public class WildcardMatching {

    public boolean isMatch(String s, String p) {
        return solution2(s, p);
    }

    /**
     * ac了，成绩惨淡：18 和 7
     * 我也用了dp呀，为啥就这么慢的。别人也是二维数组的dp，哼。
     * 不要在乎成绩，关键是要清楚这个dp的状态装换哈；
     * 首先把解法参考(照抄)记录下： https://github.com/Blankj/awesome-java-leetcode/blob/master/note/044/README.md
     *
     * dp心得：
     *  1.  当写dp状态转移公式的时候，不要计较dp[i-1][p-1]的具体值哈，一旦计较，就陷入递归中了。
     *  2.  我们的成绩为什么这么低呢？不知道；
     *
     * @param s
     * @param p
     * @return
     */
    public boolean solution2(String s, String p) {

        if (s.length() == 0) {
            int j = 0;
            while (j < p.length()) {
                if (p.charAt(j) != '*') {
                    return false;
                }
                j++;
            }
            return true;
        }
        if (p.length() == 0) {
            return false;
        }

        boolean[][] dp = new boolean[s.length()][p.length()];
        dp[0][0] = s.charAt(0) == p.charAt(0) || p.charAt(0) == '?' || p.charAt(0) == '*';
        boolean used = false;
        for (int j = 0; j < dp[0].length; j++) {
            if (used) {
                dp[0][j] = dp[0][j - 1] && p.charAt(j) == '*';
            } else {
                if (p.charAt(j) == '*') {
                    dp[0][j] = true;
                } else {
                    dp[0][j] = p.charAt(j) == '?' || p.charAt(j) == s.charAt(0);
                    used = true;
                }
            }
        }

        for (int i = 1; i < dp.length; i++) {
            dp[i][0] = p.charAt(0) == '*';
        }

        for (int i = 1; i < dp.length; i++) {
            for (int j = 1; j < dp[i].length; j++) {
                if (p.charAt(j) == '*') {
                    dp[i][j] = dp[i - 1][j] || dp[i][j - 1];
                } else {
                    dp[i][j] = dp[i - 1][j - 1] && (p.charAt(j) == '?' || p.charAt(j) == s.charAt(i));
                }
            }
        }
        return dp[s.length() - 1][p.length() - 1];

    }


    public boolean solution(String s, String p) {
        return dfs(s, 0, p, 0);
    }


    /**
     * 超时了，有比dfs更好的算法。
     * 除非这个题目是计算型，这种情况太小；
     * 那就是动态规划和贪心算法，我们不相信贪心算法，所以锁定动态规划；
     *
     * @param s
     * @param i
     * @param p
     * @param j
     * @return
     */
    public boolean dfs(String s, int i, String p, int j) {

        if (j == p.length()) {
            return i == s.length();
        }

        if (i == s.length()) {
            while (j < p.length()) {
                if (p.charAt(j) != '*') {
                    return false;
                }
                j++;
            }
            return true;
        }

        if (p.charAt(j) == '?') {
            return dfs(s, i + 1, p, j + 1);
        } else if (p.charAt(j) == '*') {
            return dfs(s, i + 1, p, j)
                    || dfs(s, i + 1, p, j + 1)
                    || dfs(s, i, p, j + 1);
        } else {

            return s.charAt(i) != p.charAt(j) ? false : dfs(s, i + 1, p, j + 1);
        }
    }

    @Test
    public void test_() {

        String s02 = "aaabababaaabaababbbaaaabbbbbbabbbbabbbabbaabbababab", p02 = "*ab***ba**b*b*aaab*b";
        Assert.assertTrue(solution2(s02, p02));

        String s01 = "", p01 = "***";
        Assert.assertTrue(solution2(s01, p01));

        String s5 = "acdcb", p5 = "a*c?b";
        Assert.assertFalse(solution2(s5, p5));

        String s4 = "adceb", p4 = "*a*b";
        Assert.assertTrue(solution2(s4, p4));

        String s3 = "cb", p3 = "?a";
        Assert.assertFalse(solution2(s3, p3));

        String s2 = "aa", p2 = "*";
        Assert.assertTrue(solution2(s2, p2));

        String s1 = "aa", p1 = "a";
        Assert.assertFalse(solution2(s1, p1));


    }

}
