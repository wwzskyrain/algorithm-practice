/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2023 All Rights Reserved.
 */
package study.erik.algorithm.leetcode.dp.hard;

import study.erik.algorithm.util.LetCodeCommit;

/**
 * @author yueyi
 * @version : NumberOfWaysToPaintN_3Grid.java, v 0.1 2023年05月05日 08:50 yueyi Exp $
 */
public class NumberOfWaysToPaintN_3Grid {

    @LetCodeCommit(title = "1411. Number of Ways to Paint N × 3 Grid",
            diff = "h",
            selfRemark = "这是一个经典的dp题目。很像斐波那契额。"
                    + "首先理解题意，是n*3的grid，也就是三个一组，摞在一起共n组。"
                    + "解题：以121表示中间颜色和两边颜色，123表示三个颜色都不同。"
                    + "这样很容易推算出递推公式——略。")
    public int numOfWays(int n) {
        long a121 = 6, a123 = 6, b121, b123, mod = (long) 1e9 + 7;
        for (int i = 1; i < n; ++i) {
            b121 = a121 * 3 + a123 * 2;
            b123 = a121 * 2 + a123 * 2;
            a121 = b121 % mod;
            a123 = b123 % mod;
        }
        return (int) ((a121 + a123) % mod);
    }

}