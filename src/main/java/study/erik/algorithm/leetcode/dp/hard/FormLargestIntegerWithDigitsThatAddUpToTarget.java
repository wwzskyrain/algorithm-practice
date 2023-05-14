/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2023 All Rights Reserved.
 */
package study.erik.algorithm.leetcode.dp.hard;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameter;
import org.junit.runners.Parameterized.Parameters;
import study.erik.algorithm.util.ArrayUtils;
import study.erik.algorithm.util.LetCodeCommit;

/**
 * @author yueyi
 * @version : FormLargestIntegerWithDigitsThatAddUpToTarget.java, v 0.1 2023年05月14日 10:16 yueyi Exp $
 */
@RunWith(Parameterized.class)
public class FormLargestIntegerWithDigitsThatAddUpToTarget {

    @LetCodeCommit(title = "1449. Form Largest Integer With Digits That Add up to Target",
            diff = "h",
            selfRemark = "思路很简单的，就是背包问题。"
                    + "但是超时了。"
                    + "网上有两种答案：第一种是用一维dp和mem的，和我这个本质上是一样的。"
                    + "还有一个是lee的，是先用dp求最大长度，在逆向取解。我很羡慕这种接法。"
                    + "要求：关于背包问题，这三种接法我都要掌握。")
    public String largestNumber(int[] cost, int target) {
        int n = cost.length;
        String[][] dp = new String[n + 1][target + 1];
        // dp[i][j]=前i个数据，代价为j，的最值
        for (int i = 0; i < n; i++) {
            dp[i][0] = "";
        }
        for (int j = 1; j < target + 1; j++) {
            int c = cost[0];
            if (j % c == 0) {
                dp[1][j] = dp[1][j - c] + '1';
            }
        }
        for (int i = 2; i < n + 1; i++) {
            int c = cost[i - 1];
            char ch = (char) ('0' + i);
            for (int j = 1; j < target + 1; j++) {
                String max = null;
                StringBuilder sb = new StringBuilder();
                for (int k = 0; (j - k * c) >= 0; k++) {
                    if (k != 0) {
                        sb.append(ch);
                    }
                    String last = dp[i - 1][j - k * c];
                    if (last == null) {
                        continue;
                    }
                    String num = sb + last;
                    max = compare(max, num) ? max : num;
                }
                dp[i][j] = max;
            }
        }
        return dp[n][target] != null ? dp[n][target] : "0";
    }

    public boolean compare(String s1, String s2) {
        if (s1 == null) {
            return false;
        }
        if (s1.length() == s2.length()) {
            return s1.compareTo(s2) > 0;
        }
        return s1.length() - s2.length() > 0;
    }

    @Parameter
    public int[]  cost;
    @Parameter(1)
    public int    target;
    @Parameter(2)
    public String expect;

    @Parameters
    public static Object[][] data() {
        return new Object[][] {
                {ArrayUtils.buildArray("[1,1,1,1,1,1,1,1,1]"), 5000, "11111111111111111111"},
                {ArrayUtils.buildArray("[6,15,10,47,31,47,34,33,41]"), 120, "11111111111111111111"},
                //{ArrayUtils.buildArray("[6,10,15,40,40,40,40,40,40]"), 47, "32211"},
                //{ArrayUtils.buildArray("[4,3,2,5,6,7,2,5,5]"), 9, "7772"},
                //{ArrayUtils.buildArray("[7,6,5,5,5,6,8,7,8]"), 12, "85"},
                //{ArrayUtils.buildArray("[2,4,6,2,4,6,4,4,4]"), 5, "0"},

        };
    }

    @Test
    public void test_() {
        Assert.assertEquals(expect, largestNumber(cost, target));
    }

}