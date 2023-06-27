/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2023 All Rights Reserved.
 */
package study.erik.algorithm.leetcode.hard.dp;

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
 * @version : MaximumScoreOfAGoodSubarray.java, v 0.1 2023年06月27日 06:24 yueyi Exp $
 */
@RunWith(Parameterized.class)
public class MaximumScoreOfAGoodSubarray {

    @LetCodeCommit(title = "1793. Maximum Score of a Good Subarray",
            diff = "h",
            selfRemark = "这个题目需要好好分析才能想到dp的思路。")
    public int minChanges(int[] nums, int k) {
        int MAX_CHANGES = 0x3f3f3f3f;
        int[][] dp = new int[k][1024];
        for (int[] ints : dp) {
            Arrays.fill(ints, MAX_CHANGES);
        }
        int n = nums.length;
        int preMin = MAX_CHANGES; //记录k=i-1时，整个[0....1024]的最小值
        for (int i = 0, cnt = 0; i < k; i++, cnt = 0) {
            // 使用 map 和 cnt 分别统计当前列的「每个数的出现次数」和「有多少个数」
            Map<Integer, Integer> map = new HashMap<>();
            for (int j = i; j < n; j += k) {
                map.put(nums[j], map.getOrDefault(nums[j], 0) + 1);
                cnt++;
            }
            if (i == 0) {
                for (int j = 0; j < dp[0].length; j++) {
                    dp[0][j] = Math.min(dp[0][j], cnt - map.getOrDefault(j, 0));
                    preMin = Math.min(preMin, dp[0][j]);
                }
                continue;
            }
            int tempPreMin = MAX_CHANGES;
            for (int j = 0; j < dp[i].length; j++) {
                //前i-1的最小值 + cnt(=把第i列都'变成'一个值)， 这是一个保底的。
                //不需要对i-1的所有[0...1024]个状态都进行状态变化。
                dp[i][j] = preMin + cnt;
                for (Integer cur : map.keySet()) {
                    //状态变化：在 [i - 1][j ^ cur] 的基础上，把前i个数的异或变成j。
                    //此时，就需要把前i-1个的异或结果[j^cur]再异或上cur，即[j^cur^cur]=j
                    //这个变化需要的"change"是多少呢？就是dp[i - 1][j ^ cur] + (cnt - map.get(cur))
                    //解释：
                    // 第一部分就是把前i-1列异或成j^cur的change数，即 dp[i - 1][j ^ cur]
                    // 第二部分就是把第i列都变成cur(注意这里不是变成j，j是我们变成cur之后前i列异或的目标)，
                    // 即(当前列的数的个数) - (本身已经是cur的数的个数)
                    dp[i][j] = Math.min(dp[i][j], dp[i - 1][j ^ cur] + cnt - map.get(cur));
                }
                tempPreMin = Math.min(tempPreMin, dp[i][j]);
            }
            preMin = tempPreMin;
        }
        return dp[k - 1][0];
    }

    @Parameter
    public int[] nums;
    @Parameter(1)
    public int   k;
    @Parameter(2)
    public int   expect;

    @Parameters
    public static Object[][] data() {
        return new Object[][] {
                {ArrayUtils.buildArray("[26,19,19,28,13,14,6,25,28,19,0,15,25,11]"), 3, 11},
                {ArrayUtils.buildArray("[1,2,0,3,0]"), 1, 3},
                {ArrayUtils.buildArray("[1,2,0,3,0]"), 1, 3},
                {ArrayUtils.buildArray("[1,2,0,3,0]"), 1, 3},
                {ArrayUtils.buildArray("[3,4,5,2,1,7,3,4,7]"), 3, 3},
                {ArrayUtils.buildArray("[1,2,4,1,2,5,1,2,6]"), 3, 3}

        };
    }

    @Test
    public void test_() {
        Assert.assertEquals(expect, minChanges(nums, k));
    }
}