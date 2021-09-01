/** * Alipay.com Inc. * Copyright (c) 2004-2021 All Rights Reserved. */package study.erik.algorithm.leetcode.array;import org.junit.Assert;import org.junit.Test;import org.junit.runner.RunWith;import org.junit.runners.Parameterized;import org.junit.runners.Parameterized.Parameter;import org.junit.runners.Parameterized.Parameters;import study.erik.algorithm.util.ArrayUtils;import study.erik.algorithm.util.LetCodeCommit;/** * @author yueyi * @version : ArrayNesting.java, v 0.1 2021年09月01日 11:24 下午 yueyi Exp $ */@RunWith(Parameterized.class)public class ArrayNesting {    @LetCodeCommit(title = "Array Nesting",            selfRemark = "深度优先遍历吧，不知道用并查集会不会好很多."                    + "我操，两层循环，其实这个题这么简单，这是好题，一题可以多解答")    public int arrayNesting(int[] nums) {        boolean[] beSeen = new boolean[nums.length];        int max = 0;        for (int i = 0; i < nums.length; i++) {            int length = 0;            int num = nums[i];            while (!beSeen[num]) {                length++;                beSeen[num] = true;                num = nums[num];            }            max = Math.max(max, length);        }        return max;    }    /**     * 这个dfs可以用while来表达     *     * @param nums     * @param dp     * @param index     * @return     */    public int dfs(int[] nums, int[] dp, int index) {        if (dp[index] > 0) {            return dp[index];        }        if (dp[index] == -1) {            return 0;        }        dp[index] = -1;        dp[index] = 1 + dfs(nums, dp, nums[index]);        return dp[index];    }    @Parameter    public int[] nums;    @Parameter(1)    public int   expect;    @Parameters    public static Object[][] data() {        return new Object[][] {                //{ArrayUtils.buildArray("[5,4,0,3,1,6,2]"), 4},                //{ArrayUtils.buildArray("[0,1,2]"), 1},                {ArrayUtils.buildArray("[0,2,1]"), 2},        };    }    @Test    public void test_() {        Assert.assertEquals(expect, arrayNesting(nums));    }}