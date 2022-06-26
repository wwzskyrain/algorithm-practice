/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2022 All Rights Reserved.
 */
package study.erik.algorithm.leetcode.array.hard;

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
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * @author yueyi
 * @version : SplitArrayWithSameAverage.java, v 0.1 2022年06月26日 17:32 yueyi Exp $
 * 这个题目经过简单的转化之后，就变成了是否存在"子集和为target"，简称目标子集和问题。
 * 这个问题有两个解法
 * 方法一：直接找所有的子集和，看是否contains
 *      这里有个固定格式：
 * 方法二：用递归的形式，寻找：
 *    find(target, k, i)
 *    = find(target-A[i],k-1,i+1)  //包含当前i这个元素
 *      OR
 *      find(target,k,i+1)         //不包含当前i这个元素
 *
 *
 */
@RunWith(Parameterized.class)
public class SplitArrayWithSameAverage {

    @LetCodeCommit(title = "805. Split Array With Same Average",
            diff = "h",
            selfRemark = "这是迭代的写法")
    public boolean splitArraySameAverage(int[] nums) {

        int n = nums.length, sum = Arrays.stream(nums).sum(), m = n / 2;
        boolean possible = false;
        for (int i = 1; i <= m && !possible; i++) {
            if (sum * i % n == 0) {
                possible = true;
            }
        }
        if (!possible) {
            return false;
        }
        Set<Integer>[] dp = new Set[m + 1];
        for (int i = 0; i < dp.length; i++) {
            dp[i] = new HashSet<>();
        }
        dp[0].add(0);
        for (int num : nums) {
            for (int i = m; i > 0; i--) {
                for (Integer s : dp[i - 1]) {
                    dp[i].add(s + num);
                }
            }
        }
        for (int i = 1; i <= m; i++) {
            if (sum * i % n == 0 && dp[i].contains(sum * i / n)) {
                return true;
            }
        }
        return false;
    }

    /**
     * 递归的解法.
     *
     * @param nums
     * @return
     */
    public boolean solution(int[] nums) {
        int n = nums.length, sum = Arrays.stream(nums).sum();

        for (int i = 1; i < n / 2 + 1; i++) {
            if (sum * i % n != 0) {
                continue;
            }
            int sum1 = sum * i / n;
            if (target(sum1, i, 0, nums, new HashMap<>())) {
                return true;
            }
        }
        return false;
    }

    public boolean target(int sum, int k, int i, int[] nums, Map<String, Boolean> map) {
        String key = key(sum, k, i);
        if (map.containsKey(key)) {
            return map.get(key);
        }
        boolean ret = false;
        if (k == 0) {
            ret = sum == 0;
        } else if (k + i > nums.length) {
            ret = false;
        } else {
            ret = target(sum - nums[i], k - 1, i + 1, nums, map)
                    || target(sum, k, i + 1, nums, map);
        }
        map.put(key, ret);
        return ret;
    }

    public String key(int sum, int k, int i) {
        return String.format("%s-%s-%s", sum, k, i);
    }

    @Parameter
    public int[]   nums;
    @Parameter(1)
    public boolean expect;

    @Parameters
    public static Object[][] data() {
        return new Object[][] {
                {ArrayUtils.buildArray("[3,1,2]"), true},
                {ArrayUtils.buildArray("[1,2,3,4,5,6,7,8]"), true},
                {ArrayUtils.buildArray("[5,3,11,19,2]"), true},
                {ArrayUtils.buildArray("[3,1]"), false},
        };
    }

    @Test
    public void test_() {
        Assert.assertEquals(expect, splitArraySameAverage(nums));
    }
}