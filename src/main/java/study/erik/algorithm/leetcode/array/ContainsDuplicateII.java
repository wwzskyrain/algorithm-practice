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

import java.util.HashSet;
import java.util.Set;

/**
 * @author yueyi
 * @version : ContainsDuplicateII.java, v 0.1 2021年12月22日 10:36 下午 yueyi Exp $
 */
@RunWith(Parameterized.class)
public class ContainsDuplicateII {

    @LetCodeCommit(title = "219. Contains Duplicate II")
    public boolean containsNearbyDuplicate(int[] nums, int k) {

        Set<Integer> set = new HashSet<Integer>();
        int i = 0;
        while (i < nums.length && i <= k) {
            int n = nums[i];
            if (set.contains(n)) {
                return true;
            }
            set.add(n);
            i++;
        }
        if (i <= k) {
            return false;
        }
        int j = 0;
        while (i < nums.length) {
            int n = nums[j++];
            set.remove(n);
            n = nums[i++];
            if (set.contains(n)) {
                return true;
            }
            set.add(n);
        }
        return false;

    }

    @Parameter
    public int[]   nums;
    @Parameter(1)
    public int     k;
    @Parameter(2)
    public boolean expect;

    @Parameters
    public static Object[][] data() {
        return new Object[][] {
                {ArrayUtils.buildArray("[1,2,3,1]"), 3, true},
                {ArrayUtils.buildArray("[1,0,1,1]"), 1, true},
                {ArrayUtils.buildArray("[1,2,3,1,2,3]"), 2, false},

        };
    }

    @Test
    public void test_() {
        Assert.assertEquals(expect, containsNearbyDuplicate(nums, k));
    }

}