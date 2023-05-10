/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2023 All Rights Reserved.
 */
package study.erik.algorithm.leetcode.array.hard;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameter;
import org.junit.runners.Parameterized.Parameters;
import study.erik.algorithm.util.LetCodeCommit;

/**
 * @author yueyi
 * @version : BuildArrayWhereYouCanFindTheMaximumExactlyKComparisons.java, v 0.1 2023年05月07日 22:23 yueyi Exp $
 */
@RunWith(Parameterized.class)
@LetCodeCommit(title = "1420. Build Array Where You Can Find The Maximum Exactly K Comparisons",
        diff = "h",
        selfRemark = "这个题目乍一看很唬人。但是确实一个设计精巧的题目———不难"
                + "这里的top-down的解法，其思路是从左往右的幂——有条件的幂。"
                + "如果没有条件，则每一个元素都有m中可能，那就是m的n次幂。"
                + "但是这里有个递增关系，所以，条件也就出来了。"
                + ""
                + "这里的dp是动态规划吗？似乎不像。"
                + "这里的dp准确说是动态规划，只是它不是down-top的，因为这里的dp递推公式确实很难写。"
                + "所以这里用了top-down，并用dp来做记事本。"
                + ""
                + "那么记事本生效了吗？生效了。why。"
                + ""
                + "这里帖子中还有进一步优化的，是特例优化，也很明显，可以一看。https://leetcode.com/problems/build-array-where-you-can-find-the-maximum-exactly-k-comparisons/solutions/586494/java-dfs-memoization-clean-code/")
public class BuildArrayWhereYouCanFindTheMaximumExactlyKComparisons {

    public int numOfArrays(int n, int m, int k) {
        Integer[][][] dp = new Integer[n + 1][m + 1][k + 1];
        int dfs = dfs(n, m, k, 0, 0, 0, dp);
        return dfs;
    }

    // dfs(... i, currMax, currCost) the number of ways to build the valid array `arr[i:]`
    //     keeping in mind that current maximum element is `currMax` and current search cost is `currCost`
    int dfs(int n, int m, int k, int i, int currMax, int currCost, Integer[][][] dp) {
        if (i == n) {
            if (k == currCost) {
                return 1; // valid if the value search cost is equal to k
            }
            return 0;
        }
        if (dp[i][currMax][currCost] != null) {
            return dp[i][currMax][currCost];
        }
        int ans = 0;
        for (int num = 1; num <= m; num++) {
            int newCost = currCost;
            int newMax = currMax;
            if (num > currMax) {
                newCost++;
                newMax = num;
            }
            if (newCost > k) {break;}
            ans += dfs(n, m, k, i + 1, newMax, newCost, dp);
            ans %= 1_000_000_007;
        }
        return dp[i][currMax][currCost] = ans;
    }

    @Parameter
    public int n;
    @Parameter(1)
    public int m;
    @Parameter(2)
    public int k;
    @Parameter(3)
    public int expect;

    @Parameters
    public static Object[][] data() {
        return new Object[][] {
                {2, 3, 1, 6},
                {5, 2, 3, 0},
                {9, 1, 1, 1},
                {2, 3, 1, 6},
        };
    }

    @Test
    public void test_() {
        Assert.assertEquals(expect, numOfArrays(n, m, k));
    }

}