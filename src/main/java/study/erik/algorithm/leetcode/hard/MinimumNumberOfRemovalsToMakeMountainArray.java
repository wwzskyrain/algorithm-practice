/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2023 All Rights Reserved.
 */
package study.erik.algorithm.leetcode.hard;

import study.erik.algorithm.util.LetCodeCommit;

/**
 * @author yueyi
 * @version : MinimumNumberOfRemovalsToMakeMountainArray.java, v 0.1 2023年06月12日 07:16 yueyi Exp $
 */
public class MinimumNumberOfRemovalsToMakeMountainArray {

    @LetCodeCommit(title = "1671. Minimum Number of Removals to Make Mountain Array",
            diff = "h",
            selfRemark = "")
    public int minimumMountainRemovals(int[] nums) {
        int n = nums.length;
        int[] delLeft = new int[n];
        for (int i = 0; i < delLeft.length; i++) {
            delLeft[i] = i;
        }
        for (int i = 0; i < nums.length; i++) {
            for (int j = i - 1; j >= 0; j--) {
                if (nums[j] < nums[i]) {
                    //如果nums[j]>=nums[i]呢？放心，后面的j会收拾他们——直接删除。这就是循环的好处
                    delLeft[i] = Math.min(delLeft[i], delLeft[j] + (i - j - 1));
                }
            }
        }
        int[] delRight = new int[n];
        for (int i = 0; i < delRight.length; i++) {
            delRight[i] = n - i - 1;
        }
        for (int i = n - 1; i >= 0; i--) {
            for (int j = i + 1; j < n; j++) {
                if (nums[j] < nums[i]) {
                    delRight[i] = Math.min(delRight[i], delRight[j] + (j - i - 1));
                }
            }
        }
        int ans = n;
        for (int i = 1; i < n - 1; i++) {
            if (delLeft[i] == i || delRight[i] == (n - i - 1)) {
                //如果左边删除完了，或者右边删除完了，那就是单山了，不算。
                continue;
            }
            ans = Math.min(ans, delLeft[i] + delRight[i]);
        }
        return ans;
    }

}