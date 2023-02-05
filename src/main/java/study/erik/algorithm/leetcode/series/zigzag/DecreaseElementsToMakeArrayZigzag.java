/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2023 All Rights Reserved.
 */
package study.erik.algorithm.leetcode.series.zigzag;

import study.erik.algorithm.util.LetCodeCommit;

/**
 * @author yueyi
 * @version : DecreaseElementsToMakeArrayZigzag.java, v 0.1 2023年02月05日 20:38 yueyi Exp $
 */
public class DecreaseElementsToMakeArrayZigzag {

    @LetCodeCommit(title = "1144. Decrease Elements To Make Array Zigzag",
            diff = "m",
            selfRemark = "这个题，我思路没打开，我有点拙。"
                    + "这个题要的是最值，其实可选值就那么两种，也就是解空间就两个，这一点我没有看到，拙在这儿。"
                    + "其实解空间很大，但是局部最优解就两个。"
                    + "局部最优解为啥就两个，首先这里面只允许--操作，其次自己实操一下就能发现在相邻的三个中，只需要对中间值进行操作就行，其他在操作就会更差。")
    public int movesToMakeZigzag(int[] nums) {
        int[] ret = new int[2];
        for (int i = 0; i < nums.length; i++) {
            int left = i > 0 ? nums[i - 1] : 100001;
            int right = i + 1 < nums.length ? nums[i + 1] : 100001;
            ret[i % 2] += Math.max(0, nums[i] - Math.min(left, right) + 1);
        }
        return Math.min(ret[0], ret[1]);
    }

}