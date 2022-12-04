/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2022 All Rights Reserved.
 */
package study.erik.algorithm.leetcode.search.binary;

import study.erik.algorithm.util.LetCodeCommit;

/**
 * @author yueyi
 * @version : CapacityToShipPackagesWithinDDays.java, v 0.1 2022年12月04日 17:07 yueyi Exp $
 */
public class CapacityToShipPackagesWithinDDays {

    @LetCodeCommit(title = "1011. Capacity To Ship Packages Within D Days")
    public int shipWithinDays(int[] weights, int days) {
        int left = 0;
        int right = 0;
        for (int weight : weights) {
            left = Math.max(left, weight);
            right += weight;
        }
        while (left < right) {
            int mid = (left + right) / 2;
            int dayNeed = 1;
            int cur = 0;
            for (int weight : weights) {
                if (cur + weight > mid) {
                    dayNeed++;
                    cur = weight;
                } else {
                    cur += weight;
                }
            }
            if (dayNeed > days) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }
        return left;
    }

}