/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2023 All Rights Reserved.
 */
package study.erik.algorithm.leetcode.dp.hard;

import study.erik.algorithm.util.LetCodeCommit;

import java.util.Arrays;

/**
 * @author yueyi
 * @version : RestoreTheArray.java, v 0.1 2023年05月10日 08:30 yueyi Exp $
 */
public class RestoreTheArray {

    @LetCodeCommit(title = "1416. Restore The Array",
            diff = "h",
            selfRemark = "这一道题似乎很简单，因为它本质就是一维dp，只不过其dp公式中有sum。"
                    + "所以，这个题目应该是m题，而不是hard。"
                    + "3点："
                    + "1.涉及到大数取模的，一定要多用long"
                    + "2.这里是dfs和备忘录。其实很容易写出bottom-up的。"
                    + "3.备忘录中用int[]不要用Integer[]，有点low")
    public int numberOfArrays(String s, int k) {
        int length = s.length();
        long[] dp = new long[length];
        Arrays.fill(dp, -1);
        long dfs = dfs(s, 0, k, dp);
        return ((int) dfs);
    }

    public long dfs(String s, int idx, int k, long[] dp) {
        if (idx == s.length()) {
            return 1;
        }
        if (s.charAt(idx) == '0') {
            return 0;
        }
        if (dp[idx] != -1) {
            return dp[idx];
        }
        long num = 0;
        long total = 0;
        for (int i = idx; i < s.length(); i++) {
            int n = s.charAt(i) - '0';
            num *= 10;
            num += n;
            if (num > k) {
                break;
            }
            total += dfs(s, i + 1, k, dp);
            total %= (1e9 + 7);
        }
        dp[idx] = total;
        return dp[idx];
    }

}