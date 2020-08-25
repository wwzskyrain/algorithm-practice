package study.erik.algorithm.leetcode.series.subsequence;

import org.junit.Assert;
import org.junit.jupiter.api.Test;
import study.erik.algorithm.util.LetCodeCommit;

/**
 * @author erik.wang
 * @date 2020-08-22 12:38
 */
public class ShortestCommonSupersequence {

    @LetCodeCommit(title = "1092. Shortest Common Supersequence", diff = "hard",
            selfRemark = "成绩不理想的；" +
                    "好像title中应该是subsequence，而不是supersequence")
    public String shortestCommonSupersequence(String str1, String str2) {

        String lcs = lcs(str1, str2);
        int i = 0, i1 = 0, i2 = 0;
        StringBuilder sb = new StringBuilder();
        while (i < lcs.length()) {
            while (i1 < str1.length() && str1.charAt(i1) != lcs.charAt(i)) {
                sb.append(str1.charAt(i1));
                i1++;
            }
            while (i2 < str2.length() && str2.charAt(i2) != lcs.charAt(i)) {
                sb.append(str2.charAt(i2));
                i2++;
            }
            sb.append(lcs.charAt(i));
            i++;
            i1++;
            i2++;
        }
        while (i1 < str1.length()) {
            sb.append(str1.charAt(i1));
            i1++;
        }
        while (i2 < str2.length()) {
            sb.append(str2.charAt(i2));
            i2++;
        }
        return sb.toString();

    }

    public String lcs(String str1, String str2) {

//       这里长度+1，在计算dp是非常有利
        String[][] dp = new String[str1.length() + 1][str2.length() + 1];
//      case1: str1[i]=str2[j], dp[i][j] = dp[i-1][j-1] + 1
//      case2: str1[i]=str2[j], dp[i][j] = max(dp[i-1][j], dp[i][j-1])
        for (int i = 0; i < dp.length; i++) {
            for (int j = 0; j < dp[i].length; j++) {
                dp[i][j] = "";
            }
        }

        for (int i = 1; i < dp.length; i++) {
            for (int j = 1; j < dp[i].length; j++) {

                if (str1.charAt(i - 1) == str2.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1] + str1.charAt(i - 1);
                } else {
                    String s1 = dp[i - 1][j];
                    String s2 = dp[i][j - 1];
                    dp[i][j] += s1.length() > s2.length() ? s1 : s2;
                }
            }
        }
        return dp[str1.length()][str2.length()];
    }

    @Test
    public void test_solution_1() {
        String str1 = "abac", str2 = "cab";
        Assert.assertEquals("cabac", shortestCommonSupersequence(str1, str2));
    }

    @Test
    public void test_solution_2() {
        String str1 = "babbbbaa", str2 = "baabbbbba";
        Assert.assertEquals("baabbbbaba", shortestCommonSupersequence(str1, str2));
    }

}
