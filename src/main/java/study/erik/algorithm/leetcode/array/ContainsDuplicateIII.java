/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2021 All Rights Reserved.
 */
package study.erik.algorithm.leetcode.array;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameter;
import org.junit.runners.Parameterized.Parameters;
import study.erik.algorithm.util.ArrayUtils;
import study.erik.algorithm.util.LetCodeCommit;

import java.util.HashMap;
import java.util.Map;

/**
 * @author yueyi
 * @version : ContainsDuplicateIII.java, v 0.1 2021年12月22日 11:10 下午 yueyi Exp $
 */
@RunWith(Parameterized.class)
public class ContainsDuplicateIII {

    @LetCodeCommit(title = "220. Contains Duplicate III",
            tag = "好题-do-yourself。" +
                    "也没有很好，就是简单的锻炼一下数字游戏。")
    public boolean containsNearbyAlmostDuplicate(int[] nums, int k, int t) {
        if (k == 0) {
            return false;
        }
        Map<Long, Long> map = new HashMap<>();
        long bucketSize = ((long) t) + 1;
        for (int i = 0; i < nums.length; i++) {
            Long num = (long) nums[i];
            long bucketNo = calculateBucketNo(nums[i], bucketSize);
            Long saveNum = map.get(bucketNo);
            if (saveNum != null && Math.abs(saveNum - num) <= t) {
                return true;
            }
            saveNum = map.get(bucketNo + 1);
            if (saveNum != null && Math.abs(saveNum - num) <= t) {
                return true;
            }
            saveNum = map.get(bucketNo - 1);
            if (saveNum != null && Math.abs(saveNum - num) <= t) {
                return true;
            }
            if (map.size() == k) {
                long bucketNoForFirst = calculateBucketNo(nums[i - k], bucketSize);
                map.remove(bucketNoForFirst);
            }
            map.put(bucketNo, num);
        }
        return false;
    }

    private long calculateBucketNo(Integer num, long bucketSize) {
        long no = ((long) num) - Integer.MIN_VALUE;
        return no / bucketSize;
    }

    @Parameter
    public int[] nums;
    @Parameter(1)
    public int k;
    @Parameter(2)
    public int t;
    @Parameter(3)
    public boolean expect;

    @Parameters
    public static Object[][] data() {
        return new Object[][]{
                {ArrayUtils.buildArray("[1,2,3,1]"), 3, 0, true},
                {ArrayUtils.buildArray("[1,0,1,1]"), 1, 2, true},
                {ArrayUtils.buildArray("[1,5,9,1,5,9]"), 2, 3, false},
                {ArrayUtils.buildArray("[2147483647,-1,2147483647]"), 1, 2147483647, false},
                {ArrayUtils.buildArray("[1,2]"), 0, 1, false},
                {ArrayUtils.buildArray("[0]"), 0, 0, false},
        };
    }

    @Test
    public void test_() {
        Assert.assertEquals(expect, containsNearbyAlmostDuplicate(nums, k, t));
    }

}