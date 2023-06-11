/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2023 All Rights Reserved.
 */
package study.erik.algorithm.leetcode.hard;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameter;
import org.junit.runners.Parameterized.Parameters;
import study.erik.algorithm.util.ArrayUtils;
import study.erik.algorithm.util.LetCodeCommit;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * @author yueyi
 * @version : DistributeRepeatingIntegers.java, v 0.1 2023年06月11日 14:53 yueyi Exp $
 */
@RunWith(Parameterized.class)
@LetCodeCommit(title = "1655. Distribute Repeating Integers")
public class DistributeRepeatingIntegers {

    public boolean canDistributeWithBitmask(int[] nums, int[] quantity) {
        Map<Integer, Integer> counter = new HashMap<>();
        for (int num : nums) {
            Integer c = counter.getOrDefault(num, 0);
            c++;
            counter.put(num, c);
        }
        int[] cArr = new int[counter.size()];
        int i = 0;
        for (Integer key : counter.keySet()) {
            cArr[i++] = counter.get(key);
        }
        int m = quantity.length;
        int[] sum = new int[1 << m];
        for (int state = 0; state < (1 << m); state++) {
            for (int j = 0; j < m; j++) {
                sum[state] += ((state & (1 << j)) != 0) ? quantity[j] : 0;
            }
        }
        // mask表示 quantity的组合。
        Boolean[][] dp = new Boolean[1 << m][cArr.length];
        return doDp((1 << m) - 1, cArr.length - 1, sum, cArr, dp);
    }

    public boolean doDp(int mask, int i, int[] sum, int[] nums, Boolean[][] dp) {
        if (mask == 0) {
            return true;
        }
        if (i < 0) {
            return false;
        }
        if (dp[mask][i] != null) {
            return dp[mask][i];
        }
        int cur = mask;
        while (cur > 0) {
            if (sum[cur] <= nums[i] && doDp(mask ^ cur, i - 1, sum, nums, dp)) {
                //mask的一个子集，可以被当前num满足，并且mask剔除该子集之后，能被nums[0...i-1]满足。
                //那位说nums[i]满足了cur之后，还剩下呢，剩下的还不少呢，不用啦？对，不用啦。
                // 浪费吗？对于这个cur是浪费了，但是，后面循环肯定能找到一个'最大'、'最合适'的子集，能最大限度
                // 的利用nums[i]，所以，不着急，都安排上。
                dp[mask][i] = true;
                return dp[mask][i];
            }
            cur = (cur - 1) & mask;
        }
        dp[mask][i] = doDp(mask, i - 1, sum, nums, dp);
        return dp[mask][i];
    }

    /**
     * 这就过了？简单的backtrace
     *
     * @param nums
     * @param quantity
     * @return
     */
    public boolean canDistributeWithBacktrace(int[] nums, int[] quantity) {
        Map<Integer, Integer> counter = new HashMap<>();
        for (int num : nums) {
            Integer c = counter.getOrDefault(num, 0);
            c++;
            counter.put(num, c);
        }
        int[] cArr = new int[counter.size()];
        int i = 0;
        for (Integer key : counter.keySet()) {
            cArr[i++] = counter.get(key);
        }
        Arrays.sort(cArr);
        Arrays.sort(quantity);
        return backtrace(cArr, quantity, quantity.length - 1);
    }

    public boolean backtrace(int[] nums, int[] quantity, int j) {
        if (j < 0) {
            return true;
        }
        int n = nums.length;
        int curQuantity = quantity[j];
        for (int i = n - 1; i >= 0; i--) {
            if (nums[i] >= curQuantity) {
                //在第i位就满足第j个顾客。
                nums[i] -= curQuantity;
                if (backtrace(nums, quantity, j - 1)) {
                    return true;
                }
                nums[i] += curQuantity;
            }
        }
        return false;
    }

    @Parameter
    public int[]   nums;
    @Parameter(1)
    public int[]   quantity;
    @Parameter(2)
    public boolean expect;

    @Parameters
    public static Object[][] data() {
        return new Object[][] {
                {ArrayUtils.buildArray(
                        "[1,1,2,2,3,3,4,4,5,5,6,6,7,7,8,8,9,9,10,10,11,11,12,12,13,13,14,14,15,15,16,16,17,17,18,18,19,19,20,20,21,21,22,"
                                + "22,23,23,24,24,25,25,26,26,27,27,28,28,29,29,30,30,31,31,32,32,33,33,34,34,35,35,36,36,37,37,38,38,39,"
                                + "39,40,40,41,41,42,42,43,43,44,44,45,45,46,46,47,47,48,48,49,49,50,50]"),
                        ArrayUtils.buildArray("[2,2,2,2,2,2,2,2,2,3]"), false},
                {ArrayUtils.buildArray("[1,2,3,4]"), ArrayUtils.buildArray("[2]"), false},
                {ArrayUtils.buildArray("[1,2,3,3]"), ArrayUtils.buildArray("[2]"), true},
                {ArrayUtils.buildArray("[1,1,2,2]"), ArrayUtils.buildArray("[2,2]"), true},
        };
    }

    @Test
    public void test_() {
        Assert.assertEquals(expect, canDistributeWithBitmask(nums, quantity));
    }
}