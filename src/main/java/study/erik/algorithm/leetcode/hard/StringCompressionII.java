/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2023 All Rights Reserved.
 */
package study.erik.algorithm.leetcode.hard;

import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import study.erik.algorithm.util.LetCodeCommit;

import java.util.Arrays;

/**
 * @author yueyi
 * @version : StringCompressionII.java, v 0.1 2023年05月27日 14:18 yueyi Exp $
 */
@RunWith(Parameterized.class)
public class StringCompressionII {

    @LetCodeCommit(title = "1531. String Compression II",
            diff = "h",
            selfRemark = "这个题目由于其自操作不容易确定，所以想到dp数组的解法的也很难。"
                    + "我们本想用回溯+分而治之呢，因为有个规律那就是有钢用到刀刃上——"
                    + "k个都要用的完成的子字符串上，不要分散使用。"
                    + "这一个操作，在下面的dp解法中也是用了的。"
                    + "不过接下来就说dp数组解法吧。"
                    + "他在保留s[i]的case中，对子问题的分解还是挺到位的，当然是基于有钢用到刀刃上的。")
    public int getLengthOfOptimalCompression(String s, int k) {
        int n = s.length();
        int[][] dp = new int[n + 1][k + 1];
        for (int i = 1; i < dp.length; i++) {
            Arrays.fill(dp[i], Integer.MAX_VALUE);
        }
        for (int i = 1; i <= n; i++) {
            //开始计算dp[i][0]/dp[i][1]/dp[i][2]...dp[i][k]
            for (int j = 0; j <= k; j++) {
                //删除s[i]这个字符
                if (j > 0) {
                    dp[i][j] = dp[i - 1][j - 1];
                }
                int cnt = 0; //只用来计算s[i-1]向左的个数
                int removed = 0;
                //开始往前删除，能删几个是几个，最多删除j吧；
                for (int p = i; p > 0; p--) {
                    if (s.charAt(p - 1) == s.charAt(i - 1)) {
                        cnt++;
                        //注意：如果往左数，各了几个子串后有遇到与s[i-1]相同的字符，也要cnt的了.
                    } else {
                        // 当前s[p-1]已经开是!=s[i-1]了。
                        removed++;
                    }
                    if (removed > j) {
                        break;
                    }
                    dp[i][j] = Math.min(dp[i][j], dp[p][j - removed] + calLength(cnt));
                }
            }
        }
        return dp[n][k];
    }

    public int calLength(int count) {
        if (count == 1) {
            return 0;
        } else if (count > 1 && count < 10) {
            return 1;
        } else if (count > 10 && count < 100) {
            return 2;
        } else if (count > 100 && count < 1000) {
            return 3;
        }
        return 4;
    }

}