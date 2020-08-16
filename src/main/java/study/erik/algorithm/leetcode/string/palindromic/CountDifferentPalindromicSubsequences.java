package study.erik.algorithm.leetcode.string.palindromic;

import org.junit.Assert;
import org.junit.Test;
import study.erik.algorithm.util.LetCodeCommit;

/**
 * @author erik.wang
 * @date 2020-08-16 19:54
 */
public class CountDifferentPalindromicSubsequences {


    @LetCodeCommit(no = 730, title = "Count Different Palindromic Subsequences",
            selfRemark = "这个题目把之前那个subString改成了subSequence，难度也上到了hard。思路：去重有点难哟。太细节了，答案也是抄的，算了，先这样",
            postLink = "https://leetcode.com/problems/count-different-palindromic-subsequences/discuss/109507/Java-96ms-DP-Solution-with-Detailed-Explanation")
    public int countPalindromicSubsequences(String S) {

        int mod = 1000000007;

//      dp[i][j] 表示在s[i,j]中有多少个回文子序列
        int[][] dp = new int[S.length()][S.length()];
        for (int i = 0; i < dp.length; i++) {
            dp[i][i] = 1;
        }

        for (int l = 1; l < dp.length; l++) {
            for (int i = 0; i < dp.length - l; i++) {
                int j = i + l;
                int c = 0;
                if (S.charAt(i) != S.charAt(j)) {
                    //这一加一减中， 一加的结果可能就是负值了
                    c = dp[i][j - 1] + dp[i + 1][j] - dp[i + 1][j - 1];  //s.charAt(i) != s.charAt(j)
                } else {
                    int low = i + 1;
                    int high = j - 1;
                    while (low <= high && S.charAt(low) != S.charAt(j)) {
                        low++;
                    }
                    while (low <= high && S.charAt(high) != S.charAt(j)) {
                        high--;
                    }
                    if (low < high) {
                        //这三个*2，都可能导致c为负值
                        c = dp[i + 1][j - 1] * 2 - dp[low + 1][high - 1];
                    } else if (low == high) {
                        c = dp[i + 1][j - 1] * 2 + 1;
                    } else {
                        c = dp[i + 1][j - 1] * 2 + 2;
                    }
                }

                if (c < 0) {
                    // 注意这里哈，c可能为负值。不考虑这一点的话，case3通不过
                    dp[i][j] = (c + mod);
                } else {
                    dp[i][j] = (c % mod);
                }
            }

        }

        return dp[0][dp[0].length - 1];
    }

    @Test
    public void test_solution_1() {
        Assert.assertEquals(6, countPalindromicSubsequences("bccb"));
    }


    @Test
    public void test_solution_2() {
        Assert.assertEquals(104860361, countPalindromicSubsequences("abcdabcdabcdabcdabcdabcdabcdabcddcbadcbadcbadcbadcbadcbadcbadcba"));
    }

    @Test
    public void test_solution_3() {
        Assert.assertEquals(117990582, countPalindromicSubsequences("bcbacbabdcbcbdcbddcaaccdcbbcdbcabbcdddadaadddbdbbbdacbabaabdddcaccccdccdbabcddbdcccabccbbcdbcdbdaada"));
    }

}
