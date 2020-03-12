package study.erik.algorithm.leetcode.dp;

import org.junit.Assert;
import org.junit.Test;

/**
 * @author erik.wang
 * @date 2020-03-11 19:16
 * @description
 */
public class PalindromePartitionII {

    /**
     * url = https://leetcode.com/problems/palindrome-partitioning-ii/
     * difficulty: hard
     *
     * @param s
     * @return
     */
    public int minCut(String s) {
        return solution(s);
    }

    /*
      说实话，这中dp是有难度的。倒不是因为它是二位dp。
      这个解法呀，是'聚沙成堆'的解法。或者说，从最小局部最优解，到更大范围的局部最优解法，最后到全局最优解。
      成绩：5% 和 8% ，很不好。
     */
    public int solution(String s) {
        // cut二位数组的定义：cut[i][j] 为使字符串s[i...j]切个成全部是'回文数'的的最小cut；
        // 其实就是局部最优解
        // cut[i][k] as c[i][k]
        // c[i][k] = min{ 0 (s[i..k]是回文) , min{ c[i][j] + c[j+1][k] + 1} (i<=j and j<k)
        int[][] cut = new int[s.length()][];
        for (int i = 0; i < cut.length; i++) {
            cut[i] = new int[s.length()];
        }

        for (int i = 0; i < cut.length - 1; i++) {
            cut[i][i + 1] = s.charAt(i) == s.charAt(i + 1) ? 0 : 1;
        }

        for (int width = 2; width < s.length(); width++) {
//          width
            for (int i = 0; i < s.length() - width; i++) {
                if (isPalindrome(s, i, i + width)) {
                    cut[i][i + width] = 0;
                    // 注意，这里一定不要break;在这里找问题找了好久。要死的节奏。
                } else {
                    int minCut = Integer.MAX_VALUE;
                    for (int j = i; j < i + width; j++) {
                        int oneCut = cut[i][j] + cut[j + 1][i + width] + 1;
                        minCut = Math.min(minCut, oneCut);
                    }
                    cut[i][i + width] = minCut;
                }
            }
        }
        return cut[0][s.length() - 1];
    }

    /**
     * 判断s[...]是否是回文
     *
     * @param s
     * @param startIndex include
     * @param endIndex   inclule
     * @return
     */
    private boolean isPalindrome(String s, int startIndex, int endIndex) {
        if (s == null || s.length() == 0 || startIndex < 0 || endIndex >= s.length() || startIndex > endIndex) {
            throw new IllegalArgumentException("s is null");
        }

        if (startIndex == endIndex) {
            return true;
        }

        int low = startIndex;
        int high = endIndex;
        while (low < high) {
            if (s.charAt(low) != s.charAt(high)) {
                return false;
            }
            low++;
            high--;
        }
        return true;
    }

    @Test
    public void test_palindrome_partition() {

        String s10 = "caacabac";
        Assert.assertEquals(2, solution(s10));

        String s9 = "cabababcbc";
        Assert.assertEquals(3, solution(s9));

        String s8 = "cabababcbc";
        Assert.assertEquals(3, solution(s8));

        String s1 = "aab";
        Assert.assertEquals(1, solution(s1));

        String s2 = "cdd";
        Assert.assertEquals(1, solution(s2));

        String s3 = "ab";
        Assert.assertEquals(1, solution(s3));

        String s4 = "leet";
        Assert.assertEquals(2, solution(s4));

        String s5 = "abcdcba";
        Assert.assertEquals(0, solution(s5));

        String s6 = "abcddcba";
        Assert.assertEquals(0, solution(s6));

        String s7 = "abcdefghij";
        Assert.assertEquals(9, solution(s7));


    }

}
