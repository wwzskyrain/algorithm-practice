/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2022 All Rights Reserved.
 */
package study.erik.algorithm.leetcode.array;

import study.erik.algorithm.util.LetCodeCommit;

/**
 * @author yueyi
 * @version : MaximizeDistanceToClosestPerson.java, v 0.1 2022年05月22日 11:58 yueyi Exp $
 */
public class MaximizeDistanceToClosestPerson {

    @LetCodeCommit(title = "849. Maximize Distance to Closest Person",
    diff = "m",
    selfRemark = "有一个水题")
    public int maxDistToClosest(int[] seats) {

        int last = -1;
        int max = 0;
        for (int i = 0; i < seats.length; i++) {
            if (seats[i] == 1) {
                max = Math.max(max, (i - last) / 2);
                last = i;
            }
        }
        max = Math.max(max, seats.length - 2 - last);
        return max;
    }

}