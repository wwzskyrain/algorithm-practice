package study.erik.algorithm.leetcode.dp;

import org.junit.Assert;
import org.junit.Test;

/**
 * @author erik.wang
 * @Date 2019-10-12
 */
public class EditDistance {

    public int minDistance(String word1, String word2) {
        return solution(word1, word2);
    }

    /*
     又是一个非常经典的dp问题。这篇文章写得很清楚 https://www.jianshu.com/p/a617d20162cf
     我想思考：为什么这种dp思想-递归思想可以解决问题？
     知道了递归公式，写代码就很简单了。
     */
    public int solution(String word1, String word2) {
        if (word1.length() == 0 || word2.length() == 0) {
            return Math.max(word1.length(), word2.length());
        }
        // dp[0][j]表示从无到word2[j]
        // dp[i][0]表示从word1[0]到无
        int[][] dp = new int[word1.length() + 1][];
        //dp[i][j] 表示 w1[0..i] 转到 w2[0..j]的最小次数,原问题=dp[w1.length-1][w2.length-1];
        for (int i = 0; i < dp.length; i++) {
            dp[i] = new int[word2.length() + 1];
        }
        //初始化
        for (int j = 0; j < dp[0].length; j++) {
            dp[0][j] = j;
        }
        //初始化
        for (int i = 0; i < dp.length; i++) {
            dp[i][0] = i;
        }

        //
        for (int i = 1; i < dp.length; i++) {
            for (int j = 1; j < dp[i].length; j++) {
                int a = dp[i - 1][j] + 1; //word1增加一个
                int b = dp[i][j - 1] + 1; //word2增加一个
                //替换或者刚好 word1[i-1] == word2[j-1]
                int c = dp[i - 1][j - 1] + (word1.charAt(i - 1) == word2.charAt(j - 1) ? 0 : 1);
                dp[i][j] = Math.min(Math.min(a, b), c);
            }
        }
        return dp[word1.length()][word2.length()];
    }

    @Test
    public void test_solution() {
        String word1 = "horse", word2 = "ros";
        Assert.assertEquals(3, solution(word1, word2));
    }


}
