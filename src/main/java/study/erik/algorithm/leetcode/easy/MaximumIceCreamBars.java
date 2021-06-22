/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2021 All Rights Reserved.
 */
package study.erik.algorithm.leetcode.easy;

import study.erik.algorithm.util.LetCodeCommit;

import java.util.Arrays;

/**
 * @author yueyi
 * @version : MaximumIceCreamBars.java, v 0.1 2021年06月22日 10:03 上午 yueyi Exp $
 */

public class MaximumIceCreamBars {

    @LetCodeCommit(title = "Maximum Ice Cream Bars", selfRemark = "太简单了，一个排序就搞定，leetcode上还是medium呢")
    public int maxIceCream(int[] costs, int coins) {
        Arrays.sort(costs);
        for (int i = 0; i < costs.length; i++) {
            if (coins >= costs[i]) {
                coins -= costs[i];
                continue;
            }
            return i;
        }
        return costs.length;
    }

}